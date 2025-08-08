package com.common.redis.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Redis列表缓存配置属性
 * 
 * <p>定义缓存系统的各种配置参数，支持通过application.yml进行配置。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "redis.list.cache")
public class RedisListCacheProperties {
    
    /**
     * 是否启用列表缓存，默认启用
     */
    private boolean enabled = true;
    
    /**
     * 默认过期时间（秒），默认30分钟
     */
    private long defaultExpireTime = 1800;
    
    /**
     * 最大缓存页数，默认5页
     */
    private int maxCachePages = 5;
    
    /**
     * 缓存键前缀，默认为list_cache
     */
    private String keyPrefix = "list_cache";
    
    /**
     * 是否启用租户隔离，默认启用
     */
    private boolean tenantAware = true;
    
    /**
     * 序列化方式，默认JSON
     */
    private SerializationType serializationType = SerializationType.JSON;
    
    /**
     * 监控配置
     */
    private MonitorConfig monitor = new MonitorConfig();
    
    /**
     * 降级配置
     */
    private FallbackConfig fallback = new FallbackConfig();
    
    /**
     * 性能配置
     */
    private PerformanceConfig performance = new PerformanceConfig();
    
    /**
     * 序列化类型枚举
     */
    public enum SerializationType {
        /**
         * JSON序列化
         */
        JSON,
        
        /**
         * Java原生序列化
         */
        JAVA,
        
        /**
         * Kryo序列化
         */
        KRYO
    }
    
    /**
     * 监控配置
     */
    @Data
    public static class MonitorConfig {
        
        /**
         * 是否启用监控，默认启用
         */
        private boolean enabled = true;
        
        /**
         * 指标收集间隔（秒），默认60秒
         */
        private int metricsInterval = 60;
        
        /**
         * 是否启用详细日志，默认关闭
         */
        private boolean verboseLogging = false;
        
        /**
         * 慢查询阈值（毫秒），默认1000毫秒
         */
        private long slowQueryThreshold = 1000;
        
        /**
         * 是否启用JMX监控，默认关闭
         */
        private boolean jmxEnabled = false;
        
        /**
         * 统计信息保留时间（秒），默认24小时
         */
        private long statsRetentionTime = 86400;
    }
    
    /**
     * 降级配置
     */
    @Data
    public static class FallbackConfig {
        
        /**
         * 是否启用降级，默认启用
         */
        private boolean enabled = true;
        
        /**
         * 连接超时时间（毫秒），默认200毫秒
         */
        private long timeout = 200;
        
        /**
         * 最大重试次数，默认3次
         */
        private int maxRetries = 3;
        
        /**
         * 重试间隔（毫秒），默认100毫秒
         */
        private long retryInterval = 100;
        
        /**
         * 熔断器配置
         */
        private CircuitBreakerConfig circuitBreaker = new CircuitBreakerConfig();
        
        /**
         * 熔断器配置
         */
        @Data
        public static class CircuitBreakerConfig {
            
            /**
             * 是否启用熔断器，默认启用
             */
            private boolean enabled = true;
            
            /**
             * 失败阈值，默认5次
             */
            private int failureThreshold = 5;
            
            /**
             * 恢复时间（秒），默认60秒
             */
            private long recoveryTime = 60;
            
            /**
             * 半开状态测试请求数，默认3个
             */
            private int halfOpenMaxCalls = 3;
        }
    }
    
    /**
     * 性能配置
     */
    @Data
    public static class PerformanceConfig {
        
        /**
         * 是否启用异步缓存，默认关闭
         */
        private boolean asyncEnabled = false;
        
        /**
         * 异步线程池大小，默认5
         */
        private int asyncThreadPoolSize = 5;
        
        /**
         * 批量操作大小，默认100
         */
        private int batchSize = 100;
        
        /**
         * 是否启用缓存压缩，默认关闭
         */
        private boolean compressionEnabled = false;
        
        /**
         * 压缩阈值（字节），默认1KB
         */
        private int compressionThreshold = 1024;
        
        /**
         * 缓存预热配置
         */
        private WarmUpConfig warmUp = new WarmUpConfig();
        
        /**
         * 缓存预热配置
         */
        @Data
        public static class WarmUpConfig {
            
            /**
             * 是否启用预热，默认关闭
             */
            private boolean enabled = false;
            
            /**
             * 预热延迟时间（秒），默认30秒
             */
            private long delay = 30;
            
            /**
             * 预热键模式
             */
            private String[] keyPatterns = {};
            
            /**
             * 预热并发数，默认3
             */
            private int concurrency = 3;
        }
    }
    
    /**
     * 验证配置参数
     */
    public void validate() {
        if (defaultExpireTime <= 0) {
            throw new IllegalArgumentException("Default expire time must be positive");
        }
        
        if (maxCachePages <= 0) {
            throw new IllegalArgumentException("Max cache pages must be positive");
        }
        
        if (keyPrefix == null || keyPrefix.trim().isEmpty()) {
            throw new IllegalArgumentException("Key prefix cannot be empty");
        }
        
        if (monitor.metricsInterval <= 0) {
            throw new IllegalArgumentException("Metrics interval must be positive");
        }
        
        if (fallback.timeout <= 0) {
            throw new IllegalArgumentException("Fallback timeout must be positive");
        }
        
        if (performance.batchSize <= 0) {
            throw new IllegalArgumentException("Batch size must be positive");
        }
    }
    
    /**
     * 获取配置摘要信息
     * 
     * @return 配置摘要
     */
    public String getConfigSummary() {
        return String.format(
                "RedisListCache[enabled=%s, expireTime=%ds, maxPages=%d, tenantAware=%s, serialization=%s]",
                enabled, defaultExpireTime, maxCachePages, tenantAware, serializationType
        );
    }
    
    /**
     * 检查是否为开发模式
     * 
     * @return 是否为开发模式
     */
    public boolean isDevelopmentMode() {
        return monitor.verboseLogging || !fallback.circuitBreaker.enabled;
    }
    
    /**
     * 检查是否为生产模式
     * 
     * @return 是否为生产模式
     */
    public boolean isProductionMode() {
        return !isDevelopmentMode();
    }
    
    /**
     * 获取推荐的配置
     * 
     * @param environment 环境类型
     * @return 推荐配置
     */
    public static RedisListCacheProperties getRecommendedConfig(String environment) {
        RedisListCacheProperties properties = new RedisListCacheProperties();
        
        switch (environment.toLowerCase()) {
            case "dev":
            case "development":
                // 开发环境配置
                properties.setDefaultExpireTime(300); // 5分钟
                properties.getMonitor().setVerboseLogging(true);
                properties.getFallback().setEnabled(false);
                break;
                
            case "test":
            case "testing":
                // 测试环境配置
                properties.setDefaultExpireTime(600); // 10分钟
                properties.getMonitor().setEnabled(true);
                properties.getFallback().setEnabled(true);
                break;
                
            case "prod":
            case "production":
                // 生产环境配置
                properties.setDefaultExpireTime(1800); // 30分钟
                properties.getMonitor().setEnabled(true);
                properties.getMonitor().setJmxEnabled(true);
                properties.getFallback().setEnabled(true);
                properties.getPerformance().setAsyncEnabled(true);
                break;
                
            default:
                // 默认配置
                break;
        }
        
        return properties;
    }
}