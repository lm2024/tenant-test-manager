package com.example.functiondemand.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存配置类
 * 配置Redis缓存和本地缓存
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${cache.tree.ttl:1800}")
    private long treeCacheTtl;

    @Value("${cache.query.ttl:300}")
    private long queryCacheTtl;

    @Value("${cache.tree.max-size:10000}")
    private long treeCacheMaxSize;

    @Value("${cache.query.max-size:5000}")
    private long queryCacheMaxSize;

    /**
     * Redis模板配置
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * 缓存管理器配置
     */
    @Bean
    @ConditionalOnProperty(name = "cache.tree.enabled", havingValue = "true", matchIfMissing = true)
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // 配置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(treeCacheTtl))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        // 配置不同缓存的过期时间
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        
        // 树形结构缓存配置
        cacheConfigurations.put("tree-cache", config.entryTtl(Duration.ofSeconds(treeCacheTtl)));
        cacheConfigurations.put("requirement-tree", config.entryTtl(Duration.ofSeconds(treeCacheTtl)));
        cacheConfigurations.put("function-point-tree", config.entryTtl(Duration.ofSeconds(treeCacheTtl)));
        cacheConfigurations.put("category-tree", config.entryTtl(Duration.ofSeconds(treeCacheTtl)));
        
        // 查询结果缓存配置
        cacheConfigurations.put("query-cache", config.entryTtl(Duration.ofSeconds(queryCacheTtl)));
        cacheConfigurations.put("requirement-query", config.entryTtl(Duration.ofSeconds(queryCacheTtl)));
        cacheConfigurations.put("function-point-query", config.entryTtl(Duration.ofSeconds(queryCacheTtl)));
        cacheConfigurations.put("category-query", config.entryTtl(Duration.ofSeconds(queryCacheTtl)));
        
        // 统计数据缓存配置（较长的过期时间）
        cacheConfigurations.put("statistics-cache", config.entryTtl(Duration.ofMinutes(30)));
        
        // 元数据缓存配置（更长的过期时间）
        cacheConfigurations.put("metadata-cache", config.entryTtl(Duration.ofHours(2)));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

    /**
     * 缓存键生成器
     */
    @Bean
    public org.springframework.cache.interceptor.KeyGenerator customKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getSimpleName()).append(":");
            sb.append(method.getName()).append(":");
            
            if (params.length > 0) {
                for (Object param : params) {
                    if (param != null) {
                        sb.append(param.toString()).append(":");
                    } else {
                        sb.append("null:");
                    }
                }
                // 移除最后一个冒号
                sb.setLength(sb.length() - 1);
            }
            
            return sb.toString();
        };
    }

    /**
     * 租户相关的缓存键生成器
     */
    @Bean
    public org.springframework.cache.interceptor.KeyGenerator tenantKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            
            // 添加租户信息（从ThreadLocal或其他方式获取）
            String tenantId = getCurrentTenantId();
            sb.append("tenant:").append(tenantId).append(":");
            
            sb.append(target.getClass().getSimpleName()).append(":");
            sb.append(method.getName()).append(":");
            
            if (params.length > 0) {
                for (Object param : params) {
                    if (param != null) {
                        sb.append(param.toString()).append(":");
                    } else {
                        sb.append("null:");
                    }
                }
                // 移除最后一个冒号
                sb.setLength(sb.length() - 1);
            }
            
            return sb.toString();
        };
    }

    /**
     * 获取当前租户ID
     */
    private String getCurrentTenantId() {
        // 这里应该从租户上下文中获取租户ID
        // 暂时返回默认值，实际实现时需要集成tenant-routing-starter
        try {
            // 假设有一个TenantContext类来获取当前租户
            // return TenantContext.getCurrentTenantId();
            return "default";
        } catch (Exception e) {
            return "default";
        }
    }

    /**
     * 缓存异常处理器
     */
    @Bean
    public org.springframework.cache.interceptor.CacheErrorHandler cacheErrorHandler() {
        return new org.springframework.cache.interceptor.SimpleCacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, org.springframework.cache.Cache cache, Object key) {
                // 记录缓存获取异常，但不影响业务流程
                System.err.println("Cache get error for key: " + key + ", cache: " + cache.getName() + ", error: " + exception.getMessage());
            }

            @Override
            public void handleCachePutError(RuntimeException exception, org.springframework.cache.Cache cache, Object key, Object value) {
                // 记录缓存写入异常，但不影响业务流程
                System.err.println("Cache put error for key: " + key + ", cache: " + cache.getName() + ", error: " + exception.getMessage());
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, org.springframework.cache.Cache cache, Object key) {
                // 记录缓存清除异常，但不影响业务流程
                System.err.println("Cache evict error for key: " + key + ", cache: " + cache.getName() + ", error: " + exception.getMessage());
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, org.springframework.cache.Cache cache) {
                // 记录缓存清空异常，但不影响业务流程
                System.err.println("Cache clear error for cache: " + cache.getName() + ", error: " + exception.getMessage());
            }
        };
    }
}