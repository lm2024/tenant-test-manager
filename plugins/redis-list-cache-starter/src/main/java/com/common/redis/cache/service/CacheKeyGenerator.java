package com.common.redis.cache.service;

import com.common.redis.cache.annotation.ListCache;
import com.common.redis.cache.constants.RedisKeyConstants;
import com.common.redis.cache.model.PageInfo;
import com.tenant.routing.core.TenantContextHolder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 缓存键生成器
 * 
 * <p>负责生成唯一的缓存键，支持租户隔离、方法签名识别和参数哈希。</p>
 * 
 * <p>缓存键格式：{tenant_id}:list_cache:{class_name}:{method_name}:{param_hash}:page_{page_num}</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@Component
public class CacheKeyGenerator {
    
    /**
     * 生成列表缓存键
     * 
     * @param method 目标方法
     * @param args 方法参数
     * @param listCache 缓存注解
     * @param pageInfo 分页信息
     * @return 缓存键
     */
    public String generateListCacheKey(Method method, Object[] args, ListCache listCache, PageInfo pageInfo) {
        try {
            // 生成参数哈希
            String paramHash = generateParameterHash(args);
            
            // 获取缓存名称
            String cacheName = getCacheName(method, listCache);
            
            // 使用统一的key常量生成缓存键
            String tenantId = getCurrentTenantId();
            return RedisKeyConstants.buildListCacheKey(tenantId, cacheName, paramHash, 
                    pageInfo.getPageNumber(), pageInfo.getPageSize());
            
        } catch (Exception e) {
            log.error("Failed to generate cache key for method: {}", method.getName(), e);
            throw new RuntimeException("Cache key generation failed", e);
        }
    }
    
    /**
     * 生成缓存失效键模式
     * 
     * @param method 目标方法
     * @param cacheName 缓存名称
     * @param keyPattern 键模式
     * @param tenantAware 是否租户隔离
     * @return 失效键模式
     */
    public String generateEvictKeyPattern(Method method, String cacheName, String keyPattern, boolean tenantAware) {
        try {
            // 如果指定了自定义键模式，直接使用
            if (StrUtil.isNotBlank(keyPattern)) {
                return keyPattern;
            }
            
            // 生成通用的失效模式
            String tenantId = tenantAware ? getCurrentTenantId() : "default";
            String actualCacheName = StrUtil.isNotBlank(cacheName) ? cacheName : getMethodCacheName(method);
            
            // 生成失效模式: tenant:cache_test:cacheName:*
            return RedisKeyConstants.buildKey(tenantId, "cache_test", actualCacheName + ":*");
            
        } catch (Exception e) {
            log.error("Failed to generate evict key pattern for method: {}", method.getName(), e);
            throw new RuntimeException("Evict key pattern generation failed", e);
        }
    }
    
    /**
     * 生成简单的缓存键（不包含分页信息）
     * 
     * @param cacheName 缓存名称
     * @param tenantAware 是否租户隔离
     * @return 简单缓存键
     */
    public String generateSimpleCacheKey(String cacheName, boolean tenantAware) {
        String tenantId = tenantAware ? getCurrentTenantId() : "default";
        return RedisKeyConstants.buildKey(tenantId, "cache_test", cacheName);
    }
    
    /**
     * 从缓存键中提取租户ID
     * 
     * @param cacheKey 缓存键
     * @return 租户ID，如果不存在则返回null
     */
    public String extractTenantId(String cacheKey) {
        if (StrUtil.isBlank(cacheKey)) {
            return null;
        }
        
        String[] parts = cacheKey.split(":");
        if (parts.length >= 2 && !"cache_test".equals(parts[0])) {
            return parts[0];
        }
        
        return null;
    }
    
    /**
     * 检查缓存键是否匹配模式
     * 
     * @param cacheKey 缓存键
     * @param pattern 匹配模式
     * @return 是否匹配
     */
    public boolean matchesPattern(String cacheKey, String pattern) {
        if (StrUtil.isBlank(cacheKey) || StrUtil.isBlank(pattern)) {
            return false;
        }
        
        // 简单的通配符匹配实现
        String regex = pattern.replace("*", ".*").replace("?", ".");
        return cacheKey.matches(regex);
    }
    
    /**
     * 获取缓存名称
     * 
     * @param method 目标方法
     * @param listCache 缓存注解
     * @return 缓存名称
     */
    private String getCacheName(Method method, ListCache listCache) {
        if (StrUtil.isNotBlank(listCache.value())) {
            return listCache.value();
        }
        
        return getMethodCacheName(method);
    }
    
    /**
     * 获取方法级别的缓存名称
     * 
     * @param method 目标方法
     * @return 方法缓存名称
     */
    private String getMethodCacheName(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        return className + "_" + methodName;
    }
    
    /**
     * 生成参数哈希
     * 
     * @param args 方法参数
     * @return 参数哈希值
     */
    private String generateParameterHash(Object[] args) {
        if (args == null || args.length == 0) {
            return "no_params";
        }
        
        try {
            // 过滤掉分页参数，避免重复
            Object[] filteredArgs = Arrays.stream(args)
                    .filter(arg -> arg != null && !isPageableParameter(arg))
                    .toArray();
            
            if (filteredArgs.length == 0) {
                return "no_params";
            }
            
            // 生成参数字符串
            StringBuilder paramBuilder = new StringBuilder();
            for (Object arg : filteredArgs) {
                paramBuilder.append(arg.toString()).append("|");
            }
            
            // 使用MD5生成哈希，避免键过长
            String paramString = paramBuilder.toString();
            return MD5.create().digestHex(paramString).substring(0, 8); // 取前8位
            
        } catch (Exception e) {
            log.warn("Failed to generate parameter hash, using fallback", e);
            return "param_hash_" + System.currentTimeMillis() % 10000;
        }
    }
    
    /**
     * 检查参数是否为分页参数
     * 
     * @param arg 参数对象
     * @return 是否为分页参数
     */
    private boolean isPageableParameter(Object arg) {
        if (arg == null) {
            return false;
        }
        
        String className = arg.getClass().getName();
        return className.contains("Pageable") || 
               className.contains("PageRequest") ||
               className.contains("PageInfo");
    }
    
    /**
     * 获取当前租户ID
     * 
     * @return 租户ID
     */
    private String getCurrentTenantId() {
        try {
            // 尝试从租户上下文获取租户ID
            if (TenantContextHolder.hasTenant()) {
                String tenantId = TenantContextHolder.getTenantId();
                if (StrUtil.isNotBlank(tenantId)) {
                    return tenantId;
                }
            }
        } catch (Exception e) {
            log.debug("Failed to get tenant ID from context, using default", e);
        }
        
        return "default";
    }
    
    /**
     * 验证缓存键格式
     * 
     * @param cacheKey 缓存键
     * @return 是否有效
     */
    public boolean isValidCacheKey(String cacheKey) {
        if (StrUtil.isBlank(cacheKey)) {
            return false;
        }
        
        String[] parts = cacheKey.split(":");
        return parts.length >= 3; // 至少包含前缀、名称和一个标识符
    }
    
    /**
     * 获取缓存键的调试信息
     * 
     * @param cacheKey 缓存键
     * @return 调试信息
     */
    public String getCacheKeyDebugInfo(String cacheKey) {
        if (StrUtil.isBlank(cacheKey)) {
            return "Invalid cache key";
        }
        
        String[] parts = cacheKey.split(":");
        StringBuilder info = new StringBuilder();
        info.append("CacheKey Analysis: ");
        
        int index = 0;
        if (parts.length > index && !"cache_test".equals(parts[index])) {
            info.append("tenant=").append(parts[index]).append(", ");
            index++;
        }
        
        if (parts.length > index) {
            info.append("prefix=").append(parts[index]).append(", ");
            index++;
        }
        
        if (parts.length > index) {
            info.append("name=").append(parts[index]).append(", ");
            index++;
        }
        
        if (parts.length > index) {
            info.append("paramHash=").append(parts[index]).append(", ");
            index++;
        }
        
        if (parts.length > index) {
            info.append("page=").append(parts[index]);
        }
        
        return info.toString();
    }
}