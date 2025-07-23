package com.tenant.routing.service;

import com.tenant.routing.config.TenantRoutingProperties;
import com.tenant.routing.core.DynamicDataSource;
import com.tenant.routing.core.TenantDataSourceCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 租户注册服务
 */
public class TenantRegistryService {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantRegistryService.class);

    @Autowired
    private TenantDataSourceCreator dataSourceCreator;
    
    @Autowired(required = false)
    private TenantRoutingProperties properties;
    
    @Autowired
    private DynamicDataSource dynamicDataSource;
    
    private DataSource tenantCenterDataSource;
    
    /**
     * 初始化租户中心数据源
     */
    public void initTenantCenterDataSource() {
        if (tenantCenterDataSource == null) {
            if (properties != null) {
                TenantRoutingProperties.TenantCenterConfig config = properties.getTenantCenter();
                tenantCenterDataSource = dataSourceCreator.createDataSource(
                    "tenant_center", 
                    config.getUrl(), 
                    config.getUsername(), 
                    config.getPassword()
                );
            } else {
                // 使用默认配置
                tenantCenterDataSource = dataSourceCreator.createDataSource(
                    "tenant_center", 
                    "jdbc:mysql://localhost:3306/tenant_center?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                    "root", 
                    "password"
                );
            }
        }
    }
    
    /**
     * 注册租户数据源
     */
    public void registerTenant(String tenantId, String url, String username, String password) {
        DataSource dataSource = dataSourceCreator.createDataSource(tenantId, url, username, password);
        
        // 更新动态数据源的目标数据源映射
        Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
        targetDataSources.putAll(dataSourceCreator.getAllDataSources());
        
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();
    }
    
    // 已移除未使用的 registerAndCacheTenant 和 loadAllTenants 方法
    
    /**
     * 移除租户
     */
    public void removeTenant(String tenantId) {
        dataSourceCreator.removeDataSource(tenantId);
        
        Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
        targetDataSources.putAll(dataSourceCreator.getAllDataSources());
        
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();
    }
}