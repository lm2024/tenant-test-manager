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
     * 针对4000+租户场景优化Druid连接池配置
     */
    public DataSource createDataSource(String tenantId, String url, String username, String password) {
        return dataSourceCache.computeIfAbsent(tenantId, key -> {
            logger.info("Creating Druid data source for tenant: {}, URL: {}", tenantId, url);
            
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
                // 针对大量租户场景，每个租户连接池不宜过大
                dataSource.setMaxActive(8);
                dataSource.setMinIdle(1);
                dataSource.setMaxWait(30000);
                dataSource.setTimeBetweenEvictionRunsMillis(60000);
                dataSource.setMinEvictableIdleTimeMillis(300000);
            }
            
            // Druid特有配置，针对多租户场景优化
            dataSource.setInitialSize(2);  // 初始连接数减少，避免启动时创建过多连接
            dataSource.setPoolPreparedStatements(true);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTestWhileIdle(true);
            dataSource.setTestOnBorrow(false);
            dataSource.setTestOnReturn(false);
            
            // 空闲连接回收参数，避免长时间不使用的租户占用过多资源
            dataSource.setRemoveAbandoned(true);
            dataSource.setRemoveAbandonedTimeout(180);  // 3分钟
            dataSource.setLogAbandoned(true);
            
            // 配置监控统计拦截的filters
            try {
                dataSource.setFilters("stat,wall,slf4j");
                // 打开PSCache
                dataSource.setPoolPreparedStatements(true);
                // 设置PSCache值
                dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
                // 设置连接池名称，便于监控识别
                dataSource.setName("TenantPool-" + tenantId);
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