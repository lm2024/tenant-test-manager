package com.example.functiondemand.common.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TreeQueryOptimizer {
    
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String TREE_CACHE_PREFIX = "tree:";
    private static final int CACHE_EXPIRE_MINUTES = 30;
    
    public TreeQueryOptimizer(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    /**
     * 生成树形查询缓存键
     */
    public String generateTreeCacheKey(String entityType, String parentId, String tenantId) {
        String key = TREE_CACHE_PREFIX + entityType + ":" + tenantId;
        if (StrUtil.isNotBlank(parentId)) {
            key += ":" + parentId;
        } else {
            key += ":root";
        }
        return key;
    }
    
    /**
     * 获取缓存的树形数据
     */
    @SuppressWarnings("unchecked")
    public <T> T getCachedTree(String cacheKey) {
        try {
            return (T) redisTemplate.opsForValue().get(cacheKey);
        } catch (Exception e) {
            log.warn("获取树形缓存失败: {}", cacheKey, e);
            return null;
        }
    }
    
    /**
     * 缓存树形数据
     */
    public void cacheTree(String cacheKey, Object treeData) {
        try {
            redisTemplate.opsForValue().set(cacheKey, treeData, CACHE_EXPIRE_MINUTES, TimeUnit.MINUTES);
            log.debug("树形数据已缓存: {}", cacheKey);
        } catch (Exception e) {
            log.warn("缓存树形数据失败: {}", cacheKey, e);
        }
    }
    
    /**
     * 清除相关的树形缓存
     */
    public void clearTreeCache(String entityType, String tenantId) {
        try {
            String pattern = TREE_CACHE_PREFIX + entityType + ":" + tenantId + "*";
            redisTemplate.delete(redisTemplate.keys(pattern));
            log.debug("已清除树形缓存: {}", pattern);
        } catch (Exception e) {
            log.warn("清除树形缓存失败: {}:{}", entityType, tenantId, e);
        }
    }
    
    /**
     * 优化路径查询
     */
    public String optimizePathQuery(String path) {
        if (StrUtil.isBlank(path)) {
            return "%";
        }
        // 确保路径以/结尾，避免匹配到部分路径
        if (!path.endsWith("/")) {
            path += "/";
        }
        return path + "%";
    }
    
    /**
     * 计算查询复杂度
     */
    public int calculateQueryComplexity(int level, int childrenCount) {
        // 简单的复杂度计算：层级 * 子节点数
        return level * childrenCount;
    }
    
    /**
     * 是否应该使用缓存
     */
    public boolean shouldUseCache(int queryComplexity) {
        // 复杂度超过10时使用缓存
        return queryComplexity > 10;
    }
}