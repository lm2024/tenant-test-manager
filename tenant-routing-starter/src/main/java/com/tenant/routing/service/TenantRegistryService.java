package com.tenant.routing.service;

import com.tenant.routing.config.TenantRoutingProperties;
import com.tenant.routing.core.DynamicDataSource;
import com.tenant.routing.core.TenantDataSourceCreator;
import com.tenant.routing.entity.TenantDbInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    /**
     * 从租户中心加载所有租户配置
     */
    public void loadAllTenants() {
        try {
            initTenantCenterDataSource();
            
            String sql = "SELECT tenant_id, db_url, db_user, db_password FROM tenant_db_info";
            
            try (Connection conn = tenantCenterDataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                
                Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
                
                while (rs.next()) {
                    String tenantId = rs.getString("tenant_id");
                    String url = rs.getString("db_url");
                    String username = rs.getString("db_user");
                    String password = rs.getString("db_password");
                    
                    try {
                        DataSource dataSource = dataSourceCreator.createDataSource(tenantId, url, username, password);
                        targetDataSources.put(tenantId, dataSource);
                        System.out.println("Loaded tenant: " + tenantId + " with URL: " + url);
                    } catch (Exception e) {
                        System.err.println("Failed to create data source for tenant: " + tenantId + ", error: " + e.getMessage());
                    }
                }
                
                if (!targetDataSources.isEmpty()) {
                    dynamicDataSource.setTargetDataSources(targetDataSources);
                    dynamicDataSource.afterPropertiesSet();
                    System.out.println("Successfully loaded " + targetDataSources.size() + " tenant data sources");
                }
                
            } catch (SQLException e) {
                System.err.println("Failed to query tenant_db_info table: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize tenant center data source: " + e.getMessage());
            System.err.println("Will continue with default data source only");
        }
    }
    
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