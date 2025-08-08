package com.common.redis.cache.manager;

import com.common.redis.cache.exception.CacheConnectionException;
import com.common.redis.cache.exception.CacheException;
import com.common.redis.cache.model.CacheStats;
import com.common.redis.cache.model.ListCacheData;
import com.common.redis.cache.service.CacheSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis列表缓存管理器实现
 * 
 * <p>基于Redisson实现的缓存管理器，提供高性能的缓存操作和完善的监控功能。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@Component
public class RedisListCacheManager implements ListCacheManager {
    
    @Autowired
    private RedissonClient redissonClient;
    
    @Autowired
    private CacheSerializer cacheSerializer;
    
    /**
     * 缓存统计信息
     */
    private final CacheStats cacheStats = CacheStats.create();
    
    /**
     * 默认过期时间（秒）
     */
    private static final long DEFAULT_EXPIRE_TIME = 1800; // 30分钟
    
    /**
     * 连接超时时间（毫秒）
     */
    private static final long CONNECTION_TIMEOUT = 200;
    
    /**
     * 初始化缓存管理器
     */
    @PostConstruct
    public void init() {
        log.info("Redis list cache manager initialized");
        
        // 测试连接
        if (!testConnection()) {
            log.warn("Redis connection test failed during initialization");
        }
    }
    
    @Override
    public <T> ListCacheData<T> get(String key, TypeReference<ListCacheData<T>> valueType) {
        if (key == null || key.trim().isEmpty()) {
            return null;
        }
        
        long startTime = System.currentTimeMillis();
        
        try {
            RBucket<byte[]> bucket = redissonClient.getBucket(key);
            byte[] data = bucket.get();
            
            if (data != null) {
                try {
                    ListCacheData<T> result = cacheSerializer.deserializeFromBytes(data, valueType);
                    
                    // 检查是否过期
                    if (result != null && !result.isExpired()) {
                        cacheStats.recordHit();
                        cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
                        
                        log.debug("Cache hit for key: {}, size: {} items", key, result.size());
                        return ListCacheData.fromCache(result);
                    } else {
                        // 数据已过期，删除缓存
                        bucket.delete();
                        log.debug("Cache expired for key: {}, deleted", key);
                    }
                } catch (RuntimeException deserializeException) {
                    // 反序列化失败，可能是数据格式不兼容，删除缓存
                    log.warn("Cache deserialization failed for key: {}, deleting cache entry", key, deserializeException);
                    bucket.delete();
                    cacheStats.recordMiss();
                    cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
                    return null;
                }
            }
            
            cacheStats.recordMiss();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.debug("Cache miss for key: {}", key);
            return null;
            
        } catch (Exception e) {
            cacheStats.recordException();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.error("Failed to get cache for key: {}", key, e);
            
            if (isConnectionException(e)) {
                throw new CacheConnectionException("Redis connection failed", e);
            }
            
            throw new CacheException("Cache get operation failed", e);
        }
    }
    
    @Override
    public <T> void set(String key, ListCacheData<T> value, long expireTime) {
        if (key == null || key.trim().isEmpty() || value == null) {
            return;
        }
        
        long startTime = System.currentTimeMillis();
        
        try {
            byte[] data = cacheSerializer.serializeToBytes(value);
            if (data == null) {
                log.warn("Serialization returned null for key: {}", key);
                return;
            }
            
            RBucket<byte[]> bucket = redissonClient.getBucket(key);
            
            if (expireTime > 0) {
                bucket.set(data, expireTime, TimeUnit.SECONDS);
            } else {
                bucket.set(data);
            }
            
            cacheStats.recordLoad();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            cacheStats.incrementCacheSize(data.length);
            
            log.debug("Cache set for key: {}, size: {} bytes, expire: {}s", 
                    key, data.length, expireTime);
            
        } catch (Exception e) {
            cacheStats.recordException();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.error("Failed to set cache for key: {}", key, e);
            
            if (isConnectionException(e)) {
                throw new CacheConnectionException("Redis connection failed", e);
            }
            
            throw new CacheException("Cache set operation failed", e);
        }
    }
    
    @Override
    public <T> void set(String key, ListCacheData<T> value) {
        set(key, value, DEFAULT_EXPIRE_TIME);
    }
    
    @Override
    public boolean delete(String key) {
        if (key == null || key.trim().isEmpty()) {
            return false;
        }
        
        long startTime = System.currentTimeMillis();
        
        try {
            RBucket<byte[]> bucket = redissonClient.getBucket(key);
            boolean deleted = bucket.delete();
            
            if (deleted) {
                cacheStats.recordEviction();
            }
            
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.debug("Cache delete for key: {}, result: {}", key, deleted);
            return deleted;
            
        } catch (Exception e) {
            cacheStats.recordException();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.error("Failed to delete cache for key: {}", key, e);
            
            if (isConnectionException(e)) {
                throw new CacheConnectionException("Redis connection failed", e);
            }
            
            return false;
        }
    }
    
    @Override
    public long delete(List<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return 0;
        }
        
        long startTime = System.currentTimeMillis();
        long deletedCount = 0;
        
        try {
            RBatch batch = redissonClient.createBatch();
            
            for (String key : keys) {
                if (key != null && !key.trim().isEmpty()) {
                    batch.getBucket(key).deleteAsync();
                }
            }
            
            BatchResult<?> result = batch.execute();
            List<?> responses = result.getResponses();
            
            for (Object response : responses) {
                if (Boolean.TRUE.equals(response)) {
                    deletedCount++;
                }
            }
            
            cacheStats.recordEviction();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.debug("Batch delete {} keys, deleted: {}", keys.size(), deletedCount);
            return deletedCount;
            
        } catch (Exception e) {
            cacheStats.recordException();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.error("Failed to batch delete cache keys: {}", keys.size(), e);
            
            if (isConnectionException(e)) {
                throw new CacheConnectionException("Redis connection failed", e);
            }
            
            return deletedCount;
        }
    }
    
    @Override
    public long deleteByPattern(String pattern) {
        if (pattern == null || pattern.trim().isEmpty()) {
            return 0;
        }
        
        long startTime = System.currentTimeMillis();
        
        try {
            RKeys keys = redissonClient.getKeys();
            Iterable<String> matchingKeys = keys.getKeysByPattern(pattern);
            
            List<String> keyList = new ArrayList<>();
            for (String key : matchingKeys) {
                keyList.add(key);
                if (keyList.size() >= 1000) { // 限制批量删除数量
                    break;
                }
            }
            
            long deletedCount = 0;
            if (!keyList.isEmpty()) {
                deletedCount = keys.delete(keyList.toArray(new String[0]));
            }
            
            cacheStats.recordEviction();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.debug("Delete by pattern: {}, deleted: {}", pattern, deletedCount);
            return deletedCount;
            
        } catch (Exception e) {
            cacheStats.recordException();
            cacheStats.recordResponseTime(System.currentTimeMillis() - startTime);
            
            log.error("Failed to delete cache by pattern: {}", pattern, e);
            
            if (isConnectionException(e)) {
                throw new CacheConnectionException("Redis connection failed", e);
            }
            
            return 0;
        }
    }
    
    @Override
    public boolean exists(String key) {
        if (key == null || key.trim().isEmpty()) {
            return false;
        }
        
        try {
            RBucket<byte[]> bucket = redissonClient.getBucket(key);
            return bucket.isExists();
            
        } catch (Exception e) {
            cacheStats.recordException();
            log.error("Failed to check cache existence for key: {}", key, e);
            return false;
        }
    }
    
    @Override
    public long exists(List<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return 0;
        }
        
        try {
            RBatch batch = redissonClient.createBatch();
            
            for (String key : keys) {
                if (key != null && !key.trim().isEmpty()) {
                    batch.getBucket(key).isExistsAsync();
                }
            }
            
            BatchResult<?> result = batch.execute();
            List<?> responses = result.getResponses();
            
            return responses.stream()
                    .mapToLong(response -> Boolean.TRUE.equals(response) ? 1 : 0)
                    .sum();
            
        } catch (Exception e) {
            cacheStats.recordException();
            log.error("Failed to batch check cache existence for {} keys", keys.size(), e);
            return 0;
        }
    }
    
    @Override
    public boolean expire(String key, long expireTime) {
        if (key == null || key.trim().isEmpty()) {
            return false;
        }
        
        try {
            RBucket<byte[]> bucket = redissonClient.getBucket(key);
            return bucket.expire(expireTime, TimeUnit.SECONDS);
            
        } catch (Exception e) {
            cacheStats.recordException();
            log.error("Failed to set expire time for key: {}", key, e);
            return false;
        }
    }
    
    @Override
    public long getExpire(String key) {
        if (key == null || key.trim().isEmpty()) {
            return -2;
        }
        
        try {
            RBucket<byte[]> bucket = redissonClient.getBucket(key);
            return bucket.remainTimeToLive() / 1000; // 转换为秒
            
        } catch (Exception e) {
            cacheStats.recordException();
            log.error("Failed to get expire time for key: {}", key, e);
            return -2;
        }
    }
    
    @Override
    public Set<String> keys(String pattern) {
        return keys(pattern, 1000); // 默认限制1000个
    }
    
    @Override
    public Set<String> keys(String pattern, int limit) {
        if (pattern == null || pattern.trim().isEmpty()) {
            return Collections.emptySet();
        }
        
        try {
            RKeys keys = redissonClient.getKeys();
            Iterable<String> matchingKeys = keys.getKeysByPattern(pattern);
            
            Set<String> result = new HashSet<>();
            int count = 0;
            for (String key : matchingKeys) {
                result.add(key);
                if (++count >= limit) {
                    break;
                }
            }
            
            return result;
            
        } catch (Exception e) {
            cacheStats.recordException();
            log.error("Failed to get keys by pattern: {}", pattern, e);
            return Collections.emptySet();
        }
    }
    
    @Override
    public long size() {
        try {
            RKeys keys = redissonClient.getKeys();
            return keys.count();
            
        } catch (Exception e) {
            cacheStats.recordException();
            log.error("Failed to get cache size", e);
            return 0;
        }
    }
    
    @Override
    public long size(String pattern) {
        if (pattern == null || pattern.trim().isEmpty()) {
            return 0;
        }
        
        try {
            RKeys keys = redissonClient.getKeys();
            return keys.countExists(pattern);
            
        } catch (Exception e) {
            cacheStats.recordException();
            log.error("Failed to get cache size by pattern: {}", pattern, e);
            return 0;
        }
    }
    
    @Override
    public boolean clear() {
        try {
            redissonClient.getKeys().flushdb();
            cacheStats.reset();
            
            log.info("Cache cleared successfully");
            return true;
            
        } catch (Exception e) {
            cacheStats.recordException();
            log.error("Failed to clear cache", e);
            return false;
        }
    }
    
    @Override
    public long clear(String pattern) {
        return deleteByPattern(pattern);
    }
    
    @Override
    public CacheStats getStats() {
        return cacheStats;
    }
    
    @Override
    public void resetStats() {
        cacheStats.reset();
        log.info("Cache stats reset");
    }
    
    @Override
    public boolean isHealthy() {
        return testConnection();
    }
    
    @Override
    public String getConnectionInfo() {
        try {
            return String.format("Redis connection: %s, healthy: %s", 
                    redissonClient.getConfig().toString(), testConnection());
        } catch (Exception e) {
            return "Redis connection info unavailable: " + e.getMessage();
        }
    }
    
    @Override
    public void warmUp(List<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return;
        }
        
        log.info("Starting cache warm up for {} keys", keys.size());
        
        try {
            RBatch batch = redissonClient.createBatch();
            
            for (String key : keys) {
                if (key != null && !key.trim().isEmpty()) {
                    batch.getBucket(key).getAsync();
                }
            }
            
            batch.execute();
            log.info("Cache warm up completed for {} keys", keys.size());
            
        } catch (Exception e) {
            log.error("Cache warm up failed", e);
        }
    }
    
    @Override
    public String getMemoryInfo() {
        try {
            // 这里可以添加更详细的内存信息获取逻辑
            return String.format("Cache size: %d keys, Stats: %s", size(), cacheStats.toString());
        } catch (Exception e) {
            return "Memory info unavailable: " + e.getMessage();
        }
    }
    
    @Override
    public String maintenance() {
        try {
            // 执行维护操作，如清理过期键等
            long expiredKeys = 0; // 这里可以添加清理过期键的逻辑
            
            return String.format("Maintenance completed: cleaned %d expired keys", expiredKeys);
        } catch (Exception e) {
            return "Maintenance failed: " + e.getMessage();
        }
    }
    
    @Override
    public String getConfigInfo() {
        try {
            return String.format("Default expire time: %ds, Connection timeout: %dms", 
                    DEFAULT_EXPIRE_TIME, CONNECTION_TIMEOUT);
        } catch (Exception e) {
            return "Config info unavailable: " + e.getMessage();
        }
    }
    
    @Override
    public boolean testConnection() {
        try {
            RBucket<String> testBucket = redissonClient.getBucket("__cache_test__");
            testBucket.set("test", 1, TimeUnit.SECONDS);
            String result = testBucket.get();
            testBucket.delete();
            
            return "test".equals(result);
            
        } catch (Exception e) {
            log.debug("Redis connection test failed", e);
            return false;
        }
    }
    
    @Override
    public String getVersion() {
        return "Redis List Cache Manager v1.0.0";
    }
    
    @Override
    public List<String> exportData(String pattern) {
        // 简化实现，实际项目中可能需要更复杂的导出逻辑
        Set<String> keys = keys(pattern, 100);
        return new ArrayList<>(keys);
    }
    
    @Override
    public long importData(List<String> data) {
        // 简化实现，实际项目中需要解析数据并导入
        return 0;
    }
    
    @Override
    public String getKeyInfo(String key) {
        if (key == null || key.trim().isEmpty()) {
            return "Invalid key";
        }
        
        try {
            RBucket<byte[]> bucket = redissonClient.getBucket(key);
            boolean exists = bucket.isExists();
            long ttl = exists ? bucket.remainTimeToLive() / 1000 : -2;
            long size = exists ? getDataSize(key) : 0;
            
            return String.format("Key: %s, Exists: %s, TTL: %ds, Size: %d bytes", 
                    key, exists, ttl, size);
            
        } catch (Exception e) {
            return "Key info unavailable: " + e.getMessage();
        }
    }
    
    @Override
    public boolean rename(String oldKey, String newKey) {
        if (oldKey == null || newKey == null || oldKey.trim().isEmpty() || newKey.trim().isEmpty()) {
            return false;
        }
        
        try {
            RKeys keys = redissonClient.getKeys();
            keys.rename(oldKey, newKey);
            return true;
            
        } catch (Exception e) {
            log.error("Failed to rename key from {} to {}", oldKey, newKey, e);
            return false;
        }
    }
    
    @Override
    public boolean copy(String sourceKey, String targetKey) {
        if (sourceKey == null || targetKey == null || 
            sourceKey.trim().isEmpty() || targetKey.trim().isEmpty()) {
            return false;
        }
        
        try {
            RBucket<byte[]> sourceBucket = redissonClient.getBucket(sourceKey);
            RBucket<byte[]> targetBucket = redissonClient.getBucket(targetKey);
            
            byte[] data = sourceBucket.get();
            if (data != null) {
                long ttl = sourceBucket.remainTimeToLive();
                if (ttl > 0) {
                    targetBucket.set(data, ttl, TimeUnit.MILLISECONDS);
                } else {
                    targetBucket.set(data);
                }
                return true;
            }
            
            return false;
            
        } catch (Exception e) {
            log.error("Failed to copy key from {} to {}", sourceKey, targetKey, e);
            return false;
        }
    }
    
    @Override
    public long getDataSize(String key) {
        if (key == null || key.trim().isEmpty()) {
            return -1;
        }
        
        try {
            RBucket<byte[]> bucket = redissonClient.getBucket(key);
            byte[] data = bucket.get();
            return data != null ? data.length : -1;
            
        } catch (Exception e) {
            log.error("Failed to get data size for key: {}", key, e);
            return -1;
        }
    }
    
    @Override
    public boolean compress(String key) {
        // 简化实现，实际项目中可以添加压缩逻辑
        log.debug("Compress operation not implemented for key: {}", key);
        return false;
    }
    
    @Override
    public boolean decompress(String key) {
        // 简化实现，实际项目中可以添加解压逻辑
        log.debug("Decompress operation not implemented for key: {}", key);
        return false;
    }
    
    /**
     * 检查是否为连接异常
     * 
     * @param e 异常对象
     * @return 是否为连接异常
     */
    private boolean isConnectionException(Exception e) {
        String message = e.getMessage();
        return message != null && (
                message.contains("connection") ||
                message.contains("timeout") ||
                message.contains("refused") ||
                message.contains("unreachable")
        );
    }
}