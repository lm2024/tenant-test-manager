package com.tenant.routing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * 租户路由配置属性
 * 针对4000+租户场景优化配置
 */
@ConfigurationProperties(prefix = "tenant.routing")
public class TenantRoutingProperties {
    
    /**
     * 是否启用租户路由
     */
    private boolean enabled = true;
    
    /**
     * 租户ID请求头名称
     */
    private String headerName = "X-Tenant-ID";
    
    /**
     * 是否必需租户ID
     */
    private boolean required = false;
    
    /**
     * 排除的路径
     */
    private List<String> excludePaths = Arrays.asList(
        "/swagger-ui.html", "/doc.html", "/druid/**", "/actuator/**"
    );
    
    /**
     * 数据库驱动类名
     */
    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    
    /**
     * 连接池最大连接数
     * 针对大量租户场景，每个租户连接池不宜过大
     */
    private int maxPoolSize = 8;
    
    /**
     * 连接池最小空闲连接数
     */
    private int minIdle = 1;
    
    /**
     * 连接超时时间(毫秒)
     */
    private long connectionTimeout = 30000;
    
    /**
     * 空闲超时时间(毫秒)
     */
    private long idleTimeout = 600000;
    
    /**
     * 连接最大生命周期(毫秒)
     */
    private long maxLifetime = 1800000;
    
    /**
     * 是否启用租户预加载
     */
    private boolean preloadEnabled = true;
    
    /**
     * 租户缓存过期时间(秒)
     */
    private long cacheExpireTime = 7 * 24 * 60 * 60; // 7天
    
    /**
     * 批量操作大小
     */
    private int batchSize = 100;
    
    /**
     * Redis配置
     */
    private RedisConfig redis = new RedisConfig();
    
    /**
     * 租户中心数据库配置
     */
    private TenantCenterConfig tenantCenter = new TenantCenterConfig();
    
    // Getters and Setters
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    
    public String getHeaderName() { return headerName; }
    public void setHeaderName(String headerName) { this.headerName = headerName; }
    
    public boolean isRequired() { return required; }
    public void setRequired(boolean required) { this.required = required; }
    
    public List<String> getExcludePaths() { return excludePaths; }
    public void setExcludePaths(List<String> excludePaths) { this.excludePaths = excludePaths; }
    
    public String getDriverClassName() { return driverClassName; }
    public void setDriverClassName(String driverClassName) { this.driverClassName = driverClassName; }
    
    public int getMaxPoolSize() { return maxPoolSize; }
    public void setMaxPoolSize(int maxPoolSize) { this.maxPoolSize = maxPoolSize; }
    
    public int getMinIdle() { return minIdle; }
    public void setMinIdle(int minIdle) { this.minIdle = minIdle; }
    
    public long getConnectionTimeout() { return connectionTimeout; }
    public void setConnectionTimeout(long connectionTimeout) { this.connectionTimeout = connectionTimeout; }
    
    public long getIdleTimeout() { return idleTimeout; }
    public void setIdleTimeout(long idleTimeout) { this.idleTimeout = idleTimeout; }
    
    public long getMaxLifetime() { return maxLifetime; }
    public void setMaxLifetime(long maxLifetime) { this.maxLifetime = maxLifetime; }
    
    public boolean isPreloadEnabled() { return preloadEnabled; }
    public void setPreloadEnabled(boolean preloadEnabled) { this.preloadEnabled = preloadEnabled; }
    
    public long getCacheExpireTime() { return cacheExpireTime; }
    public void setCacheExpireTime(long cacheExpireTime) { this.cacheExpireTime = cacheExpireTime; }
    
    public int getBatchSize() { return batchSize; }
    public void setBatchSize(int batchSize) { this.batchSize = batchSize; }
    
    public RedisConfig getRedis() { return redis; }
    public void setRedis(RedisConfig redis) { this.redis = redis; }
    
    public TenantCenterConfig getTenantCenter() { return tenantCenter; }
    public void setTenantCenter(TenantCenterConfig tenantCenter) { this.tenantCenter = tenantCenter; }
    
    /**
     * Redis配置
     */
    public static class RedisConfig {
        private String host = "localhost";
        private int port = 6379;
        private String password = "";
        private int database = 0;
        private RedissonPoolConfig pool = new RedissonPoolConfig();
        
        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }
        
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
        
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        
        public int getDatabase() { return database; }
        public void setDatabase(int database) { this.database = database; }
        
        public RedissonPoolConfig getPool() { return pool; }
        public void setPool(RedissonPoolConfig pool) { this.pool = pool; }
        
        /**
         * Redisson连接池配置
         */
        public static class RedissonPoolConfig {
            private int maxActive = 64;
            private int maxIdle = 24;
            private int minIdle = 8;
            private long maxWait = 5000;
            private int connectionTimeout = 10000;
            private int timeout = 3000;
            
            public int getMaxActive() { return maxActive; }
            public void setMaxActive(int maxActive) { this.maxActive = maxActive; }
            
            public int getMaxIdle() { return maxIdle; }
            public void setMaxIdle(int maxIdle) { this.maxIdle = maxIdle; }
            
            public int getMinIdle() { return minIdle; }
            public void setMinIdle(int minIdle) { this.minIdle = minIdle; }
            
            public long getMaxWait() { return maxWait; }
            public void setMaxWait(long maxWait) { this.maxWait = maxWait; }
            
            public int getConnectionTimeout() { return connectionTimeout; }
            public void setConnectionTimeout(int connectionTimeout) { this.connectionTimeout = connectionTimeout; }
            
            public int getTimeout() { return timeout; }
            public void setTimeout(int timeout) { this.timeout = timeout; }
        }
    }
    
    /**
     * 租户中心数据库配置
     */
    public static class TenantCenterConfig {
        private String url = "jdbc:mysql://localhost:3306/tenant_center";
        private String username = "root";
        private String password = "root";
        
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}