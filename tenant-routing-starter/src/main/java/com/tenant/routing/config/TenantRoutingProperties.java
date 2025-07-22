package com.tenant.routing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * 租户路由配置属性
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
     */
    private int maxPoolSize = 10;
    
    /**
     * 连接池最小空闲连接数
     */
    private int minIdle = 2;
    
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
    
    public TenantCenterConfig getTenantCenter() { return tenantCenter; }
    public void setTenantCenter(TenantCenterConfig tenantCenter) { this.tenantCenter = tenantCenter; }
    
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