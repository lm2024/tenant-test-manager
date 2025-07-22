package com.tenant.routing.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tenant.routing.config.TenantRoutingProperties;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 租户数据源创建器
 */
public class TenantDataSourceCreator {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantDataSourceCreator.class);
    
    @Autowired(required = false)
    private TenantRoutingProperties properties;
    
    private final Map<String, DataSource> dataSourceCache = new ConcurrentHashMap<>();
    
    /**
     * 创建租户数据源
     */
    public DataSource createDataSource(String tenantId, String url, String username, String password) {
        return dataSourceCache.computeIfAbsent(tenantId, key -> {
            logger.info("Creating data source for tenant: {}, URL: {}", tenantId, url);
            
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(username);
            config.setPassword(password);
            
            // 使用默认值或配置值
            if (properties != null) {
                config.setDriverClassName(properties.getDriverClassName());
                config.setMaximumPoolSize(properties.getMaxPoolSize());
                config.setMinimumIdle(properties.getMinIdle());
                config.setConnectionTimeout(properties.getConnectionTimeout());
                config.setIdleTimeout(properties.getIdleTimeout());
                config.setMaxLifetime(properties.getMaxLifetime());
            } else {
                // 默认配置
                config.setDriverClassName("com.mysql.cj.jdbc.Driver");
                config.setMaximumPoolSize(10);
                config.setMinimumIdle(2);
                config.setConnectionTimeout(30000);
                config.setIdleTimeout(600000);
                config.setMaxLifetime(1800000);
            }
            config.setPoolName("TenantPool-" + tenantId);
            
            return new HikariDataSource(config);
        });
    }
    
    /**
     * 获取租户数据源
     */
    public DataSource getDataSource(String tenantId) {
        return dataSourceCache.get(tenantId);
    }
    
    /**
     * 移除租户数据源
     */
    public void removeDataSource(String tenantId) {
        DataSource dataSource = dataSourceCache.remove(tenantId);
        if (dataSource instanceof HikariDataSource) {
            logger.info("Closing data source for tenant: {}", tenantId);
            ((HikariDataSource) dataSource).close();
        }
    }
    
    /**
     * 获取所有租户数据源
     */
    public Map<String, DataSource> getAllDataSources() {
        return new ConcurrentHashMap<>(dataSourceCache);
    }
    
    /**
     * 检查租户数据源是否存在
     */
    public boolean hasDataSource(String tenantId) {
        return dataSourceCache.containsKey(tenantId);
    }
}