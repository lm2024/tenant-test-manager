package com.common.redis.cache.config;

import com.common.redis.cache.aspect.CacheEvictAspect;
import com.common.redis.cache.aspect.ListCacheAspect;
import com.common.redis.cache.manager.ListCacheManager;
import com.common.redis.cache.manager.RedisListCacheManager;
import com.common.redis.cache.service.CacheKeyGenerator;
import com.common.redis.cache.service.CacheSerializer;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

/**
 * Redis列表缓存自动配置类
 * 
 * <p>自动装配缓存相关的Bean，包括管理器、切面处理器等核心组件。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnClass({RedissonClient.class})
@ConditionalOnProperty(prefix = "redis.list.cache", name = "enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedisListCacheProperties.class)
public class RedisListCacheAutoConfiguration {
    
    private final RedisListCacheProperties properties;
    
    public RedisListCacheAutoConfiguration(RedisListCacheProperties properties) {
        this.properties = properties;
    }
    
    /**
     * 初始化配置
     */
    @PostConstruct
    public void init() {
        // 验证配置参数
        properties.validate();
        
        log.info("Redis List Cache Auto Configuration initialized: {}", properties.getConfigSummary());
        
        if (properties.isDevelopmentMode()) {
            log.info("Redis List Cache running in DEVELOPMENT mode");
        } else {
            log.info("Redis List Cache running in PRODUCTION mode");
        }
    }
    
    /**
     * 缓存序列化器
     * 
     * @return CacheSerializer实例
     */
    @Bean
    @ConditionalOnMissingBean
    public CacheSerializer cacheSerializer() {
        log.debug("Creating CacheSerializer with type: {}", properties.getSerializationType());
        
        // 配置ObjectMapper支持Java 8时间类型
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        objectMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        return new CacheSerializer(objectMapper);
    }
    
    /**
     * 缓存键生成器
     * 
     * @return CacheKeyGenerator实例
     */
    @Bean
    @ConditionalOnMissingBean
    public CacheKeyGenerator cacheKeyGenerator() {
        log.debug("Creating CacheKeyGenerator with prefix: {}", properties.getKeyPrefix());
        return new CacheKeyGenerator();
    }
    
    /**
     * 缓存指标服务
     * 
     * @return CacheMetrics实例
     */
    @Bean
    @ConditionalOnMissingBean
    public com.common.redis.cache.service.CacheMetrics cacheMetrics() {
        log.debug("Creating CacheMetrics");
        return new com.common.redis.cache.service.CacheMetrics();
    }
    
    /**
     * 列表缓存管理器
     * 
     * @param redissonClient Redisson客户端
     * @return ListCacheManager实例
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(RedissonClient.class)
    public ListCacheManager listCacheManager(RedissonClient redissonClient) {
        log.debug("Creating RedisListCacheManager with Redisson client");
        return new RedisListCacheManager();
    }
    
    /**
     * 列表缓存切面
     * 
     * @return ListCacheAspect实例
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ListCacheManager.class)
    public ListCacheAspect listCacheAspect() {
        log.debug("Creating ListCacheAspect");
        return new ListCacheAspect();
    }
    
    /**
     * 缓存失效切面
     * 
     * @return CacheEvictAspect实例
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ListCacheManager.class)
    public CacheEvictAspect cacheEvictAspect() {
        log.debug("Creating CacheEvictAspect");
        return new CacheEvictAspect();
    }
    
    /**
     * 缓存监控配置
     */
    @Configuration
    @ConditionalOnProperty(prefix = "redis.list.cache.monitor", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class CacheMonitorConfiguration {
        
        /**
         * 缓存指标收集器
         * 
         * @param cacheManager 缓存管理器
         * @param properties 配置属性
         * @return CacheMetricsCollector实例
         */
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnBean(ListCacheManager.class)
        public CacheMetricsCollector cacheMetricsCollector(ListCacheManager cacheManager, 
                                                          RedisListCacheProperties properties) {
            log.debug("Creating CacheMetricsCollector with interval: {}s", 
                    properties.getMonitor().getMetricsInterval());
            return new CacheMetricsCollector(cacheManager, properties);
        }
    }
    
    /**
     * 缓存健康检查配置
     */
    @Configuration
    @ConditionalOnClass(name = "org.springframework.boot.actuator.health.HealthIndicator")
    public static class CacheHealthConfiguration {
        
        /**
         * 缓存健康指示器
         * 
         * @param cacheManager 缓存管理器
         * @return CacheHealthIndicator实例
         */
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnBean(ListCacheManager.class)
        public CacheHealthIndicator cacheHealthIndicator(ListCacheManager cacheManager) {
            log.debug("Creating CacheHealthIndicator");
            return new CacheHealthIndicator(cacheManager);
        }
    }
    
    /**
     * 缓存管理端点配置
     */
    @Configuration
    @ConditionalOnClass(name = "org.springframework.boot.actuator.endpoint.annotation.Endpoint")
    public static class CacheManagementConfiguration {
        
        /**
         * 缓存管理端点
         * 
         * @param cacheManager 缓存管理器
         * @return CacheManagementEndpoint实例
         */
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnBean(ListCacheManager.class)
        public CacheManagementEndpoint cacheManagementEndpoint(ListCacheManager cacheManager) {
            log.debug("Creating CacheManagementEndpoint");
            return new CacheManagementEndpoint(cacheManager);
        }
    }
    
    /**
     * 缓存指标收集器
     */
    public static class CacheMetricsCollector {
        
        private final ListCacheManager cacheManager;
        private final RedisListCacheProperties properties;
        
        public CacheMetricsCollector(ListCacheManager cacheManager, RedisListCacheProperties properties) {
            this.cacheManager = cacheManager;
            this.properties = properties;
            
            // 启动指标收集
            startMetricsCollection();
        }
        
        private void startMetricsCollection() {
            if (properties.getMonitor().isEnabled()) {
                log.info("Starting cache metrics collection with interval: {}s", 
                        properties.getMonitor().getMetricsInterval());
                
                // 这里可以添加定时任务来收集指标
                // 简化实现，实际项目中可以使用ScheduledExecutorService
            }
        }
    }
    
    /**
     * 缓存健康指示器
     */
    public static class CacheHealthIndicator {
        
        private final ListCacheManager cacheManager;
        
        public CacheHealthIndicator(ListCacheManager cacheManager) {
            this.cacheManager = cacheManager;
        }
        
        /**
         * 检查缓存健康状态
         * 
         * @return 健康状态
         */
        public String health() {
            try {
                boolean healthy = cacheManager.isHealthy();
                return healthy ? "UP" : "DOWN";
            } catch (Exception e) {
                log.error("Cache health check failed", e);
                return "DOWN";
            }
        }
    }
    
    /**
     * 缓存管理端点
     */
    public static class CacheManagementEndpoint {
        
        private final ListCacheManager cacheManager;
        
        public CacheManagementEndpoint(ListCacheManager cacheManager) {
            this.cacheManager = cacheManager;
        }
        
        /**
         * 获取缓存统计信息
         * 
         * @return 统计信息
         */
        public Object getStats() {
            return cacheManager.getStats();
        }
        
        /**
         * 清除缓存
         * 
         * @param pattern 键模式
         * @return 清除结果
         */
        public Object clearCache(String pattern) {
            if (pattern != null && !pattern.trim().isEmpty()) {
                long cleared = cacheManager.clear(pattern);
                return String.format("Cleared %d cache entries matching pattern: %s", cleared, pattern);
            } else {
                boolean cleared = cacheManager.clear();
                return cleared ? "All cache cleared successfully" : "Failed to clear cache";
            }
        }
        
        /**
         * 获取缓存信息
         * 
         * @return 缓存信息
         */
        public Object getCacheInfo() {
            return cacheManager.getConnectionInfo();
        }
    }
}