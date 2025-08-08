package com.common.redis.cache.aspect;

import com.common.redis.cache.annotation.CacheConfig;
import com.common.redis.cache.annotation.ListCache;
import com.common.redis.cache.exception.CacheException;
import com.common.redis.cache.manager.ListCacheManager;
import com.common.redis.cache.model.ListCacheData;
import com.common.redis.cache.model.PageInfo;
import com.common.redis.cache.service.CacheKeyGenerator;
import com.common.redis.cache.service.CacheSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 列表缓存切面处理器
 * 
 * <p>拦截@ListCache注解的方法调用，实现透明的缓存功能。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@Aspect
@Component
public class ListCacheAspect {
    
    @Autowired
    private ListCacheManager cacheManager;
    
    @Autowired
    private CacheKeyGenerator keyGenerator;
    
    @Autowired
    private CacheSerializer cacheSerializer;
    
    /**
     * SpEL表达式解析器
     */
    private final ExpressionParser expressionParser = new SpelExpressionParser();
    
    /**
     * 拦截@ListCache注解的方法
     * 
     * @param joinPoint 连接点
     * @param listCache 缓存注解
     * @return 方法执行结果
     * @throws Throwable 执行异常
     */
    @Around("@annotation(listCache)")
    public Object handleListCache(ProceedingJoinPoint joinPoint, ListCache listCache) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 1. 解析缓存配置
            CacheConfig cacheConfig = getCacheConfig(method.getDeclaringClass());
            ListCache effectiveCache = mergeConfiguration(listCache, cacheConfig);
            
            // 2. 提取分页参数
            PageInfo pageInfo = extractPageInfo(args);
            if (pageInfo == null) {
                log.debug("No pagination info found, executing method directly: {}", method.getName());
                return joinPoint.proceed();
            }
            
            // 3. 检查是否应该缓存此页面
            if (!pageInfo.shouldCache(effectiveCache.maxCachePages())) {
                log.debug("Page {} exceeds max cache pages {}, executing method directly", 
                        pageInfo.getPageNumber(), effectiveCache.maxCachePages());
                return joinPoint.proceed();
            }
            
            // 4. 检查缓存条件
            if (!evaluateCacheCondition(effectiveCache.condition(), method, args, null)) {
                log.debug("Cache condition not met, executing method directly: {}", method.getName());
                return joinPoint.proceed();
            }
            
            // 5. 生成缓存键
            String cacheKey = keyGenerator.generateListCacheKey(method, args, effectiveCache, pageInfo);
            
            // 6. 尝试从缓存获取数据
            TypeReference<ListCacheData<Object>> typeRef = CacheSerializer.createListCacheDataTypeReference();
            ListCacheData<Object> cachedData = cacheManager.get(cacheKey, typeRef);
            
            if (cachedData != null && !cachedData.isEmpty()) {
                log.debug("Cache hit for key: {}, returning cached data with {} items", 
                        cacheKey, cachedData.size());
                
                // 转换为原始返回类型
                return convertToOriginalType(cachedData, method.getReturnType(), method);
            }
            
            // 7. 缓存未命中，执行原方法
            log.debug("Cache miss for key: {}, executing original method", cacheKey);
            Object result = joinPoint.proceed();
            
            // 8. 检查unless条件
            String unlessCondition = effectiveCache.unless();
            log.debug("Checking unless condition: '{}' for method: {}", unlessCondition, method.getName());
            
            if (evaluateUnlessCondition(unlessCondition, method, args, result)) {
                log.debug("Unless condition '{}' evaluated to true, not caching result for key: {}", unlessCondition, cacheKey);
                return result;
            }
            
            log.debug("Unless condition '{}' evaluated to false, proceeding with caching for key: {}", unlessCondition, cacheKey);
            
            // 9. 将结果存入缓存
            if (result != null) {
                cacheResult(cacheKey, result, pageInfo, effectiveCache, method);
            }
            
            return result;
            
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("Cache aspect failed for method: {}, duration: {}ms", method.getName(), duration, e);
            
            // 缓存异常时降级执行原方法
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new CacheException("Cache aspect and method execution both failed", throwable);
            }
        }
    }
    
    /**
     * 提取分页信息
     * 
     * @param args 方法参数
     * @return 分页信息
     */
    private PageInfo extractPageInfo(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        
        for (Object arg : args) {
            if (arg instanceof Pageable) {
                return PageInfo.fromPageable((Pageable) arg);
            }
            
            // 检查是否为自定义分页参数
            if (arg != null && isPageParameter(arg)) {
                return extractPageInfoFromCustomParam(arg);
            }
        }
        
        return null;
    }
    
    /**
     * 检查是否为分页参数
     * 
     * @param arg 参数对象
     * @return 是否为分页参数
     */
    private boolean isPageParameter(Object arg) {
        String className = arg.getClass().getName();
        return className.contains("Page") || 
               className.contains("Pagination") ||
               hasPageFields(arg);
    }
    
    /**
     * 检查对象是否包含分页字段
     * 
     * @param obj 对象
     * @return 是否包含分页字段
     */
    private boolean hasPageFields(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            return clazz.getDeclaredField("pageNumber") != null || 
                   clazz.getDeclaredField("pageSize") != null ||
                   clazz.getDeclaredField("page") != null ||
                   clazz.getDeclaredField("size") != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 从自定义参数中提取分页信息
     * 
     * @param param 自定义参数
     * @return 分页信息
     */
    private PageInfo extractPageInfoFromCustomParam(Object param) {
        try {
            Class<?> clazz = param.getClass();
            int pageNumber = 0;
            int pageSize = 10;
            
            // 尝试获取页码
            try {
                Object pageValue = clazz.getDeclaredField("pageNumber").get(param);
                if (pageValue instanceof Integer) {
                    pageNumber = (Integer) pageValue;
                }
            } catch (Exception e) {
                try {
                    Object pageValue = clazz.getDeclaredField("page").get(param);
                    if (pageValue instanceof Integer) {
                        pageNumber = (Integer) pageValue;
                    }
                } catch (Exception ignored) {
                }
            }
            
            // 尝试获取页大小
            try {
                Object sizeValue = clazz.getDeclaredField("pageSize").get(param);
                if (sizeValue instanceof Integer) {
                    pageSize = (Integer) sizeValue;
                }
            } catch (Exception e) {
                try {
                    Object sizeValue = clazz.getDeclaredField("size").get(param);
                    if (sizeValue instanceof Integer) {
                        pageSize = (Integer) sizeValue;
                    }
                } catch (Exception ignored) {
                }
            }
            
            return PageInfo.of(pageNumber, pageSize);
            
        } catch (Exception e) {
            log.debug("Failed to extract page info from custom param", e);
            return PageInfo.of(0, 10); // 默认值
        }
    }
    
    /**
     * 缓存执行结果
     * 
     * @param cacheKey 缓存键
     * @param result 执行结果
     * @param pageInfo 分页信息
     * @param listCache 缓存配置
     * @param method 目标方法
     */
    private void cacheResult(String cacheKey, Object result, PageInfo pageInfo, 
                           ListCache listCache, Method method) {
        try {
            log.debug("Starting to cache result for key: {}, result type: {}", 
                    cacheKey, result != null ? result.getClass().getSimpleName() : "null");
            
            List<?> content = extractContentFromResult(result);
            if (content == null) {
                log.warn("Cannot extract content from result for method: {}, result type: {}, not caching", 
                        method.getName(), result != null ? result.getClass().getName() : "null");
                return;
            }
            
            log.debug("Successfully extracted content with size: {} for key: {}", content.size(), cacheKey);
            
            // 更新分页信息
            PageInfo updatedPageInfo = updatePageInfoFromResult(pageInfo, result);
            
            // 创建缓存数据
            String tenantId = keyGenerator.extractTenantId(cacheKey);
            ListCacheData<Object> cacheData = ListCacheData.create(
                    (List<Object>) content, 
                    updatedPageInfo, 
                    tenantId, 
                    cacheKey, 
                    listCache.expireTime()
            );
            
            log.debug("Created cache data for key: {}, data size: {}", cacheKey, cacheData.size());
            
            // 存入缓存
            if (listCache.sync()) {
                // 同步缓存
                cacheManager.set(cacheKey, cacheData, listCache.expireTime());
            } else {
                // 异步缓存（简化实现，实际可以使用线程池）
                cacheManager.set(cacheKey, cacheData, listCache.expireTime());
            }
            
            log.info("Successfully cached result for key: {}, content size: {}", cacheKey, content.size());
            
        } catch (Exception e) {
            log.error("Failed to cache result for key: {}, method: {}", cacheKey, method.getName(), e);
        }
    }
    
    /**
     * 从结果中提取内容列表
     * 
     * @param result 方法执行结果
     * @return 内容列表
     */
    @SuppressWarnings("unchecked")
    private List<?> extractContentFromResult(Object result) {
        if (result == null) {
            log.debug("Result is null, cannot extract content");
            return null;
        }
        
        log.debug("Extracting content from result type: {}", result.getClass().getName());
        
        // 处理ResponseEntity包装的数据
        if (result instanceof org.springframework.http.ResponseEntity) {
            org.springframework.http.ResponseEntity<?> responseEntity = (org.springframework.http.ResponseEntity<?>) result;
            Object body = responseEntity.getBody();
            if (body != null) {
                log.debug("Extracting content from ResponseEntity body type: {}", body.getClass().getName());
                return extractContentFromResult(body); // 递归处理body
            } else {
                log.debug("ResponseEntity body is null");
                return null;
            }
        }
        
        // 处理Spring Data的Page类型
        if (result instanceof Page) {
            Page<?> page = (Page<?>) result;
            List<?> content = page.getContent();
            log.debug("Extracted content from Page: size={}, totalElements={}", 
                    content != null ? content.size() : 0, page.getTotalElements());
            return content;
        }
        
        // 处理直接的List类型
        if (result instanceof List) {
            List<?> list = (List<?>) result;
            log.debug("Result is direct List with size: {}", list.size());
            return list;
        }
        
        // 尝试通过反射获取content字段
        log.debug("Attempting to extract content via reflection from type: {}", result.getClass().getName());
        List<?> reflectionResult = extractContentViaReflection(result);
        if (reflectionResult != null) {
            log.debug("Successfully extracted content via reflection: size={}", reflectionResult.size());
        } else {
            log.debug("Failed to extract content via reflection");
        }
        return reflectionResult;
    }
    
    /**
     * 通过反射从结果对象中提取内容列表
     * 
     * @param result 结果对象
     * @return 内容列表
     */
    @SuppressWarnings("unchecked")
    private List<?> extractContentViaReflection(Object result) {
        try {
            Class<?> clazz = result.getClass();
            
            // 首先尝试直接获取content字段
            List<?> content = tryExtractField(result, clazz, "content");
            if (content != null) {
                log.debug("Successfully extracted content from direct content field");
                return content;
            }
            
            // 尝试获取body字段，然后从body中获取content
            content = tryExtractNestedField(result, clazz, "body", "content");
            if (content != null) {
                log.debug("Successfully extracted content from body.content structure");
                return content;
            }
            
            // 尝试其他可能的字段名
            String[] possibleFields = {"data", "list", "items", "records", "results"};
            for (String fieldName : possibleFields) {
                content = tryExtractField(result, clazz, fieldName);
                if (content != null) {
                    log.debug("Successfully extracted content from field: {}", fieldName);
                    return content;
                }
                
                // 尝试嵌套结构，如 body.data, body.list 等
                content = tryExtractNestedField(result, clazz, "body", fieldName);
                if (content != null) {
                    log.debug("Successfully extracted content from body.{} structure", fieldName);
                    return content;
                }
            }
            
        } catch (Exception e) {
            log.debug("Failed to extract content via reflection", e);
        }
        
        return null;
    }
    
    /**
     * 尝试从对象中提取指定字段的值
     * 
     * @param obj 目标对象
     * @param clazz 对象类型
     * @param fieldName 字段名
     * @return 如果字段存在且为List类型，返回该List；否则返回null
     */
    @SuppressWarnings("unchecked")
    private List<?> tryExtractField(Object obj, Class<?> clazz, String fieldName) {
        try {
            log.debug("Trying to extract field '{}' from class {}", fieldName, clazz.getSimpleName());
            
            // 首先尝试从当前类获取字段
            java.lang.reflect.Field field = null;
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // 如果当前类没有，尝试从父类获取
                Class<?> superClass = clazz.getSuperclass();
                while (superClass != null && superClass != Object.class) {
                    try {
                        field = superClass.getDeclaredField(fieldName);
                        break;
                    } catch (NoSuchFieldException ignored) {
                        superClass = superClass.getSuperclass();
                    }
                }
            }
            
            if (field == null) {
                log.debug("Field '{}' not found in class hierarchy of {}", fieldName, clazz.getSimpleName());
                return null;
            }
            
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            
            log.debug("Field '{}' value type: {}, value: {}", 
                    fieldName, 
                    fieldValue != null ? fieldValue.getClass().getSimpleName() : "null",
                    fieldValue instanceof List ? "List with " + ((List<?>) fieldValue).size() + " items" : fieldValue);
            
            if (fieldValue instanceof List) {
                List<?> list = (List<?>) fieldValue;
                log.debug("Successfully extracted List from field '{}' with size: {}", fieldName, list.size());
                return list;
            }
            
            // 如果字段值不是List，但可能包含List，继续递归查找
            if (fieldValue != null) {
                log.debug("Field '{}' is not a List, attempting recursive extraction", fieldName);
                return extractContentViaReflection(fieldValue);
            }
            
        } catch (IllegalAccessException e) {
            log.debug("Cannot access field '{}' in class {}: {}", fieldName, clazz.getSimpleName(), e.getMessage());
        } catch (Exception e) {
            log.debug("Unexpected error extracting field '{}' from class {}: {}", 
                    fieldName, clazz.getSimpleName(), e.getMessage());
        }
        
        return null;
    }
    
    /**
     * 尝试从嵌套结构中提取字段值
     * 
     * @param obj 目标对象
     * @param clazz 对象类型
     * @param parentFieldName 父字段名
     * @param childFieldName 子字段名
     * @return 如果嵌套字段存在且为List类型，返回该List；否则返回null
     */
    @SuppressWarnings("unchecked")
    private List<?> tryExtractNestedField(Object obj, Class<?> clazz, String parentFieldName, String childFieldName) {
        try {
            log.debug("Trying to extract nested field '{}.{}' from class {}", 
                    parentFieldName, childFieldName, clazz.getSimpleName());
            
            // 获取父字段
            java.lang.reflect.Field parentField = null;
            try {
                parentField = clazz.getDeclaredField(parentFieldName);
            } catch (NoSuchFieldException e) {
                // 尝试从父类获取
                Class<?> superClass = clazz.getSuperclass();
                while (superClass != null && superClass != Object.class) {
                    try {
                        parentField = superClass.getDeclaredField(parentFieldName);
                        break;
                    } catch (NoSuchFieldException ignored) {
                        superClass = superClass.getSuperclass();
                    }
                }
            }
            
            if (parentField == null) {
                log.debug("Parent field '{}' not found in class hierarchy of {}", parentFieldName, clazz.getSimpleName());
                return null;
            }
            
            parentField.setAccessible(true);
            Object parentValue = parentField.get(obj);
            
            if (parentValue != null) {
                log.debug("Parent field '{}' found, type: {}", parentFieldName, parentValue.getClass().getSimpleName());
                
                Class<?> parentClass = parentValue.getClass();
                
                // 获取子字段
                java.lang.reflect.Field childField = null;
                try {
                    childField = parentClass.getDeclaredField(childFieldName);
                } catch (NoSuchFieldException e) {
                    // 尝试从父类获取
                    Class<?> superClass = parentClass.getSuperclass();
                    while (superClass != null && superClass != Object.class) {
                        try {
                            childField = superClass.getDeclaredField(childFieldName);
                            break;
                        } catch (NoSuchFieldException ignored) {
                            superClass = superClass.getSuperclass();
                        }
                    }
                }
                
                if (childField == null) {
                    log.debug("Child field '{}' not found in class hierarchy of {}", childFieldName, parentClass.getSimpleName());
                    return null;
                }
                
                childField.setAccessible(true);
                Object childValue = childField.get(parentValue);
                
                log.debug("Child field '{}' value type: {}", childFieldName, 
                        childValue != null ? childValue.getClass().getSimpleName() : "null");
                
                if (childValue instanceof List) {
                    List<?> list = (List<?>) childValue;
                    log.debug("Successfully extracted List from nested field '{}.{}' with size: {}", 
                            parentFieldName, childFieldName, list.size());
                    return list;
                }
                
                // 继续递归查找
                if (childValue != null) {
                    log.debug("Nested field '{}.{}' is not a List, attempting recursive extraction", 
                            parentFieldName, childFieldName);
                    return extractContentViaReflection(childValue);
                }
            } else {
                log.debug("Parent field '{}' is null", parentFieldName);
            }
            
        } catch (IllegalAccessException e) {
            log.debug("Cannot access nested field '{}.{}' in class {}: {}", 
                    parentFieldName, childFieldName, clazz.getSimpleName(), e.getMessage());
        } catch (Exception e) {
            log.debug("Unexpected error extracting nested field '{}.{}' from class {}: {}", 
                    parentFieldName, childFieldName, clazz.getSimpleName(), e.getMessage());
        }
        
        return null;
    }
    
    /**
     * 从结果中更新分页信息
     * 
     * @param originalPageInfo 原始分页信息
     * @param result 方法执行结果
     * @return 更新后的分页信息
     */
    private PageInfo updatePageInfoFromResult(PageInfo originalPageInfo, Object result) {
        // 处理ResponseEntity包装的数据
        if (result instanceof org.springframework.http.ResponseEntity) {
            org.springframework.http.ResponseEntity<?> responseEntity = (org.springframework.http.ResponseEntity<?>) result;
            Object body = responseEntity.getBody();
            if (body != null) {
                return updatePageInfoFromResult(originalPageInfo, body); // 递归处理body
            }
        }
        
        if (result instanceof Page) {
            return PageInfo.fromPage((Page<?>) result);
        }
        
        return originalPageInfo;
    }
    
    /**
     * 转换为原始返回类型
     * 
     * @param cachedData 缓存数据
     * @param returnType 返回类型
     * @return 转换后的结果
     */
    private Object convertToOriginalType(ListCacheData<Object> cachedData, Class<?> returnType, Method method) {
        List<Object> content = cachedData.getContent();
        PageInfo pageInfo = cachedData.getPageInfo();
        
        // 如果返回类型是ResponseEntity，需要特殊处理
        if (returnType.getName().contains("ResponseEntity")) {
            try {
                // 检查泛型参数是否是Page类型
                java.lang.reflect.Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof java.lang.reflect.ParameterizedType) {
                    java.lang.reflect.ParameterizedType paramType = (java.lang.reflect.ParameterizedType) genericReturnType;
                    java.lang.reflect.Type[] actualTypes = paramType.getActualTypeArguments();
                    
                    if (actualTypes.length > 0) {
                        String actualTypeName = actualTypes[0].getTypeName();
                        if (actualTypeName.contains("Page")) {
                            // 构造Page对象
                            org.springframework.data.domain.Pageable pageable = 
                                org.springframework.data.domain.PageRequest.of(
                                    pageInfo.getPageNumber(), 
                                    pageInfo.getPageSize()
                                );
                            
                            org.springframework.data.domain.Page<?> page = new org.springframework.data.domain.PageImpl<>(
                                content, 
                                pageable, 
                                pageInfo.getTotalElements()
                            );
                            
                            // 返回ResponseEntity.ok(page)
                            return org.springframework.http.ResponseEntity.ok(page);
                        }
                    }
                }
                
                // 如果不是Page类型，直接返回ResponseEntity.ok(content)
                return org.springframework.http.ResponseEntity.ok(content);
                
            } catch (Exception e) {
                log.warn("Failed to construct ResponseEntity object, returning content list", e);
                return content;
            }
        }
        
        if (returnType.isAssignableFrom(List.class)) {
            return content;
        }
        
        // 如果返回类型是Page，需要构造Page对象
        if (returnType.getName().contains("Page") || returnType.getSimpleName().equals("Page")) {
            try {
                // 使用PageImpl构造Page对象
                org.springframework.data.domain.Pageable pageable = 
                    org.springframework.data.domain.PageRequest.of(
                        pageInfo.getPageNumber(), 
                        pageInfo.getPageSize()
                    );
                
                return new org.springframework.data.domain.PageImpl<>(
                    content, 
                    pageable, 
                    pageInfo.getTotalElements()
                );
            } catch (Exception e) {
                log.warn("Failed to construct Page object, returning content list", e);
                return content;
            }
        }
        
        return content;
    }
    
    /**
     * 评估缓存条件表达式（condition）
     * 
     * @param condition 条件表达式
     * @param method 目标方法
     * @param args 方法参数
     * @param result 方法结果（可为null）
     * @return 条件是否满足
     */
    private boolean evaluateCacheCondition(String condition, Method method, Object[] args, Object result) {
        if (StrUtil.isBlank(condition)) {
            log.debug("Cache condition is blank, returning true (allow caching by default)");
            return true;  // 空的condition表示无条件缓存
        }
        
        return evaluateSpelExpression(condition, method, args, result, "condition");
    }
    
    /**
     * 评估排除条件表达式（unless）
     * 
     * @param unless 排除条件表达式
     * @param method 目标方法
     * @param args 方法参数
     * @param result 方法结果（可为null）
     * @return 是否应该排除缓存
     */
    private boolean evaluateUnlessCondition(String unless, Method method, Object[] args, Object result) {
        if (StrUtil.isBlank(unless)) {
            log.debug("Unless condition is blank, returning false (do not exclude caching)");
            return false;  // 空的unless表示不排除缓存
        }
        
        return evaluateSpelExpression(unless, method, args, result, "unless");
    }
    
    /**
     * 评估SpEL表达式
     * 
     * @param expression 表达式字符串
     * @param method 目标方法
     * @param args 方法参数
     * @param result 方法结果（可为null）
     * @param expressionType 表达式类型（用于日志）
     * @return 表达式结果
     */
    private boolean evaluateSpelExpression(String expression, Method method, Object[] args, Object result, String expressionType) {
        try {
            log.debug("Evaluating {} expression: '{}' for method: {}", expressionType, expression, method.getName());
            
            Expression spelExpression = expressionParser.parseExpression(expression);
            EvaluationContext context = new StandardEvaluationContext();
            
            // 设置上下文变量
            context.setVariable("args", args);
            context.setVariable("result", result);
            context.setVariable("method", method);
            context.setVariable("target", method.getDeclaringClass());
            
            // 添加分页参数到上下文
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Pageable) {
                        context.setVariable("pageable", args[i]);
                        log.debug("Added pageable to context: page={}, size={}", 
                                ((Pageable) args[i]).getPageNumber(), 
                                ((Pageable) args[i]).getPageSize());
                        break;
                    }
                }
            }
            
            Boolean expressionResult = spelExpression.getValue(context, Boolean.class);
            log.debug("{} expression '{}' evaluated to: {}", expressionType, expression, expressionResult);
            
            return expressionResult != null && expressionResult;
            
        } catch (Exception e) {
            log.warn("Failed to evaluate {} expression: {}, assuming false for safety", expressionType, expression, e);
            return false;  // 表达式执行失败时，为了安全起见返回false
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
     * @param methodCache 方法级别配置
     * @param classConfig 类级别配置
     * @return 合并后的配置
     */
    private ListCache mergeConfiguration(ListCache methodCache, CacheConfig classConfig) {
        if (classConfig == null) {
            return methodCache;
        }
        
        // 创建一个代理对象来合并配置
        return new ListCache() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return ListCache.class;
            }
            
            @Override
            public String value() {
                return StrUtil.isNotBlank(methodCache.value()) ? 
                        methodCache.value() : 
                        (classConfig.cacheNames().length > 0 ? classConfig.cacheNames()[0] : "");
            }
            
            @Override
            public String keyPrefix() {
                return StrUtil.isNotBlank(methodCache.keyPrefix()) ? 
                        methodCache.keyPrefix() : classConfig.keyPrefix();
            }
            
            @Override
            public long expireTime() {
                return methodCache.expireTime() != 1800 ? 
                        methodCache.expireTime() : classConfig.expireTime();
            }
            
            @Override
            public int maxCachePages() {
                return methodCache.maxCachePages() != 5 ? 
                        methodCache.maxCachePages() : classConfig.maxCachePages();
            }
            
            @Override
            public boolean tenantAware() {
                return methodCache.tenantAware();
            }
            
            @Override
            public String condition() {
                return StrUtil.isNotBlank(methodCache.condition()) ? 
                        methodCache.condition() : classConfig.condition();
            }
            
            @Override
            public String unless() {
                return StrUtil.isNotBlank(methodCache.unless()) ? 
                        methodCache.unless() : classConfig.unless();
            }
            
            @Override
            public boolean sync() {
                return methodCache.sync();
            }
        };
    }
}