package com.common.redis.cache.aspect;

import com.common.redis.cache.annotation.CacheConfig;
import com.common.redis.cache.annotation.CacheEvict;
import com.common.redis.cache.manager.ListCacheManager;
import com.common.redis.cache.service.CacheKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 缓存失效切面处理器
 * 
 * <p>拦截@CacheEvict注解的方法调用，实现自动缓存清除功能。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@Aspect
@Component
public class CacheEvictAspect {
    
    @Autowired
    private ListCacheManager cacheManager;
    
    @Autowired
    private CacheKeyGenerator keyGenerator;
    
    /**
     * SpEL表达式解析器
     */
    private final ExpressionParser expressionParser = new SpelExpressionParser();
    
    /**
     * 处理方法执行前的缓存清除
     * 
     * @param joinPoint 连接点
     * @param cacheEvict 缓存失效注解
     */
    @Before("@annotation(cacheEvict)")
    public void handleCacheEvictBefore(JoinPoint joinPoint, CacheEvict cacheEvict) {
        if (shouldEvictBefore(cacheEvict)) {
            evictCache(joinPoint, cacheEvict, null);
        }
    }
    
    /**
     * 处理方法执行后的缓存清除
     * 
     * @param joinPoint 连接点
     * @param cacheEvict 缓存失效注解
     */
    @After("@annotation(cacheEvict)")
    public void handleCacheEvictAfter(JoinPoint joinPoint, CacheEvict cacheEvict) {
        if (shouldEvictAfter(cacheEvict)) {
            evictCache(joinPoint, cacheEvict, null);
        }
    }
    
    /**
     * 处理需要获取方法返回值的缓存清除
     * 
     * @param joinPoint 连接点
     * @param cacheEvict 缓存失效注解
     * @return 方法执行结果
     * @throws Throwable 执行异常
     */
    @Around("@annotation(cacheEvict)")
    public Object handleCacheEvictAround(ProceedingJoinPoint joinPoint, CacheEvict cacheEvict) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        
        // 方法执行前清除（如果需要）
        if (shouldEvictBefore(cacheEvict)) {
            evictCache(joinPoint, cacheEvict, null);
        }
        
        // 执行原方法
        Object result = null;
        Throwable methodException = null;
        
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            methodException = e;
            throw e;
        } finally {
            // 方法执行后清除（如果需要且条件满足）
            if (shouldEvictAfter(cacheEvict)) {
                // 只有在方法成功执行或配置为忽略异常时才清除缓存
                if (methodException == null || cacheEvict.ignoreExceptions()) {
                    evictCache(joinPoint, cacheEvict, result);
                }
            }
        }
        
        return result;
    }
    
    /**
     * 执行缓存清除操作
     * 
     * @param joinPoint 连接点
     * @param cacheEvict 缓存失效注解
     * @param result 方法执行结果（可为null）
     */
    private void evictCache(JoinPoint joinPoint, CacheEvict cacheEvict, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 1. 解析缓存配置
            CacheConfig cacheConfig = getCacheConfig(method.getDeclaringClass());
            CacheEvict effectiveEvict = mergeConfiguration(cacheEvict, cacheConfig);
            
            // 2. 检查清除条件
            if (!evaluateCondition(effectiveEvict.condition(), method, args, result)) {
                log.debug("Evict condition not met, skipping cache eviction for method: {}", method.getName());
                return;
            }
            
            // 3. 执行缓存清除
            long evictedCount = performEviction(method, effectiveEvict);
            
            long duration = System.currentTimeMillis() - startTime;
            
            // 4. 检查超时
            if (duration > effectiveEvict.timeout()) {
                log.warn("Cache eviction timeout: {}ms > {}ms for method: {}", 
                        duration, effectiveEvict.timeout(), method.getName());
            }
            
            log.debug("Cache eviction completed for method: {}, evicted: {}, duration: {}ms", 
                    method.getName(), evictedCount, duration);
            
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            
            if (cacheEvict.ignoreExceptions()) {
                log.warn("Cache eviction failed for method: {}, duration: {}ms, ignoring exception", 
                        method.getName(), duration, e);
            } else {
                log.error("Cache eviction failed for method: {}, duration: {}ms", 
                        method.getName(), duration, e);
                throw new RuntimeException("Cache eviction failed", e);
            }
        }
    }
    
    /**
     * 执行缓存清除操作
     * 
     * @param method 目标方法
     * @param cacheEvict 缓存失效配置
     * @return 清除的缓存数量
     */
    private long performEviction(Method method, CacheEvict cacheEvict) {
        long totalEvicted = 0;
        
        try {
            if (cacheEvict.allEntries()) {
                // 清除所有相关缓存
                totalEvicted += evictAllEntries(method, cacheEvict);
            } else if (StrUtil.isNotBlank(cacheEvict.keyPattern())) {
                // 根据键模式清除缓存
                totalEvicted += evictByPattern(method, cacheEvict);
            } else {
                // 根据缓存名称清除缓存
                totalEvicted += evictByNames(method, cacheEvict);
            }
            
        } catch (Exception e) {
            log.error("Failed to perform cache eviction", e);
            throw e;
        }
        
        return totalEvicted;
    }
    
    /**
     * 清除所有相关缓存
     * 
     * @param method 目标方法
     * @param cacheEvict 缓存失效配置
     * @return 清除的缓存数量
     */
    private long evictAllEntries(Method method, CacheEvict cacheEvict) {
        long totalEvicted = 0;
        
        String[] cacheNames = cacheEvict.value();
        if (cacheNames.length == 0) {
            // 使用默认缓存名称
            String defaultCacheName = getDefaultCacheName(method);
            String pattern = keyGenerator.generateEvictKeyPattern(method, defaultCacheName, "*", true);
            totalEvicted += cacheManager.deleteByPattern(pattern);
        } else {
            // 清除指定的缓存名称
            for (String cacheName : cacheNames) {
                String pattern = keyGenerator.generateEvictKeyPattern(method, cacheName, "*", true);
                totalEvicted += cacheManager.deleteByPattern(pattern);
            }
        }
        
        log.debug("Evicted all entries, total: {}", totalEvicted);
        return totalEvicted;
    }
    
    /**
     * 根据键模式清除缓存
     * 
     * @param method 目标方法
     * @param cacheEvict 缓存失效配置
     * @return 清除的缓存数量
     */
    private long evictByPattern(Method method, CacheEvict cacheEvict) {
        String keyPattern = cacheEvict.keyPattern();
        
        // 生成完整的键模式（包含租户前缀等）
        String fullPattern = keyGenerator.generateEvictKeyPattern(method, "", keyPattern, true);
        
        long evicted = cacheManager.deleteByPattern(fullPattern);
        
        log.debug("Evicted by pattern: {}, count: {}", fullPattern, evicted);
        return evicted;
    }
    
    /**
     * 根据缓存名称清除缓存
     * 
     * @param method 目标方法
     * @param cacheEvict 缓存失效配置
     * @return 清除的缓存数量
     */
    private long evictByNames(Method method, CacheEvict cacheEvict) {
        long totalEvicted = 0;
        
        String[] cacheNames = cacheEvict.value();
        if (cacheNames.length == 0) {
            // 使用默认缓存名称
            String defaultCacheName = getDefaultCacheName(method);
            String pattern = keyGenerator.generateEvictKeyPattern(method, defaultCacheName, "", true);
            totalEvicted += cacheManager.deleteByPattern(pattern + "*");
        } else {
            // 清除指定的缓存名称
            for (String cacheName : cacheNames) {
                String pattern = keyGenerator.generateEvictKeyPattern(method, cacheName, "", true);
                totalEvicted += cacheManager.deleteByPattern(pattern + "*");
            }
        }
        
        log.debug("Evicted by names: {}, total: {}", Arrays.toString(cacheNames), totalEvicted);
        return totalEvicted;
    }
    
    /**
     * 获取默认缓存名称
     * 
     * @param method 目标方法
     * @return 默认缓存名称
     */
    private String getDefaultCacheName(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        return className + "_" + methodName;
    }
    
    /**
     * 检查是否应该在方法执行前清除缓存
     * 
     * @param cacheEvict 缓存失效配置
     * @return 是否应该在执行前清除
     */
    private boolean shouldEvictBefore(CacheEvict cacheEvict) {
        return cacheEvict.timing() == CacheEvict.EvictTiming.BEFORE ||
               cacheEvict.timing() == CacheEvict.EvictTiming.BOTH;
    }
    
    /**
     * 检查是否应该在方法执行后清除缓存
     * 
     * @param cacheEvict 缓存失效配置
     * @return 是否应该在执行后清除
     */
    private boolean shouldEvictAfter(CacheEvict cacheEvict) {
        return cacheEvict.timing() == CacheEvict.EvictTiming.AFTER ||
               cacheEvict.timing() == CacheEvict.EvictTiming.BOTH;
    }
    
    /**
     * 评估SpEL条件表达式
     * 
     * @param condition 条件表达式
     * @param method 目标方法
     * @param args 方法参数
     * @param result 方法结果（可为null）
     * @return 条件是否满足
     */
    private boolean evaluateCondition(String condition, Method method, Object[] args, Object result) {
        if (StrUtil.isBlank(condition)) {
            return true;
        }
        
        try {
            Expression expression = expressionParser.parseExpression(condition);
            EvaluationContext context = new StandardEvaluationContext();
            
            // 设置上下文变量
            context.setVariable("args", args);
            context.setVariable("result", result);
            context.setVariable("method", method);
            context.setVariable("target", method.getDeclaringClass());
            
            Boolean conditionResult = expression.getValue(context, Boolean.class);
            return conditionResult != null && conditionResult;
            
        } catch (Exception e) {
            log.warn("Failed to evaluate evict condition: {}, assuming true", condition, e);
            return true;
        }
    }
    
    /**
     * 获取类级别的缓存配置
     * 
     * @param targetClass 目标类
     * @return 缓存配置
     */
    private CacheConfig getCacheConfig(Class<?> targetClass) {
        return AnnotationUtils.findAnnotation(targetClass, CacheConfig.class);
    }
    
    /**
     * 合并方法级别和类级别的缓存配置
     * 
     * @param methodEvict 方法级别配置
     * @param classConfig 类级别配置
     * @return 合并后的配置
     */
    private CacheEvict mergeConfiguration(CacheEvict methodEvict, CacheConfig classConfig) {
        if (classConfig == null) {
            return methodEvict;
        }
        
        // 创建一个代理对象来合并配置
        return new CacheEvict() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return CacheEvict.class;
            }
            
            @Override
            public String[] value() {
                return methodEvict.value().length > 0 ? 
                        methodEvict.value() : classConfig.cacheNames();
            }
            
            @Override
            public String keyPattern() {
                return methodEvict.keyPattern();
            }
            
            @Override
            public boolean allEntries() {
                return methodEvict.allEntries();
            }
            
            @Override
            public String condition() {
                return StrUtil.isNotBlank(methodEvict.condition()) ? 
                        methodEvict.condition() : classConfig.condition();
            }
            
            @Override
            public EvictTiming timing() {
                return methodEvict.timing() != EvictTiming.AFTER ? 
                        methodEvict.timing() : classConfig.evictTiming();
            }
            
            @Override
            public boolean ignoreExceptions() {
                return methodEvict.ignoreExceptions();
            }
            
            @Override
            public long timeout() {
                return methodEvict.timeout();
            }
        };
    }
    
    /**
     * 异步执行缓存清除（可选功能）
     * 
     * @param evictTask 清除任务
     * @param timeout 超时时间
     */
    private void executeAsyncEviction(Runnable evictTask, long timeout) {
        CompletableFuture.runAsync(evictTask)
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        log.warn("Async cache eviction failed", throwable);
                    } else {
                        log.debug("Async cache eviction completed");
                    }
                });
    }
}