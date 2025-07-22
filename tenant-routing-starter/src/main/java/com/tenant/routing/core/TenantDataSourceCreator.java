package com.tenant.routing.core;

import com.alibaba.druid.pool.DruidDataSource;
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
            
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            
            // 使用默认值或配置值
            if (properties != null) {
                dataSource.setDriverClassName(properties.getDriverClassName());
                dataSource.setMaxActive(properties.getMaxPoolSize());
                dataSource.setMinIdle(properties.getMinIdle());
                dataSource.setMaxWait(properties.getConnectionTimeout());
                dataSource.setTimeBetweenEvictionRunsMillis(properties.getIdleTimeout());
                dataSource.setMinEvictableIdleTimeMillis(properties.getMaxLifetime());
            } else {
                // 默认配置
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setMaxActive(10);
                dataSource.setMinIdle(2);
                dataSource.setMaxWait(30000);
                dataSource.setTimeBetweenEvictionRunsMillis(60000);
                dataSource.setMinEvictableIdleTimeMillis(300000);
            }
            
            // Druid特有配置
            dataSource.setInitialSize(5);
            dataSource.setPoolPreparedStatements(true);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTestWhileIdle(true);
            dataSource.setTestOnBorrow(false);
            dataSource.setTestOnReturn(false);
            
            try {
                dataSource.setFilters("stat,wall,slf4j");
                dataSource.init();
            } catch (Exception e) {
                logger.error("Error initializing Druid data source for tenant: " + tenantId, e);
            }
            
            return dataSource;
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
        if (dataSource instanceof DruidDataSource) {
            logger.info("Closing data source for tenant: {}", tenantId);
            ((DruidDataSource) dataSource).close();
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