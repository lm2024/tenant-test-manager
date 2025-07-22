package com.tenant.routing.service.impl;

import com.tenant.routing.config.TenantRoutingProperties;
import com.tenant.routing.core.DynamicDataSource;
import com.tenant.routing.core.TenantDataSourceCreator;
import com.tenant.routing.entity.TenantDbInfo;
import com.tenant.routing.service.TenantDbInfoService;
import com.tenant.routing.service.TenantRegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 租户数据库信息服务实现
 */
public class TenantDbInfoServiceImpl implements TenantDbInfoService {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantDbInfoServiceImpl.class);
    
    @Autowired
    private TenantRoutingProperties properties;
    
    @Autowired
    private TenantDataSourceCreator dataSourceCreator;
    
    @Autowired
    private DynamicDataSource dynamicDataSource;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // 租户信息缓存
    private final Map<String, TenantDbInfo> tenantCache = new ConcurrentHashMap<>();
    
    /**
     * 租户数据库信息行映射器
     */
    private static class TenantDbInfoRowMapper implements RowMapper<TenantDbInfo> {
        @Override
        public TenantDbInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            TenantDbInfo tenantDbInfo = new TenantDbInfo();
            tenantDbInfo.setId(rs.getLong("id"));
            tenantDbInfo.setDbName(rs.getString("db_name"));
            tenantDbInfo.setDbPassword(rs.getString("db_password"));
            tenantDbInfo.setDbUrl(rs.getString("db_url"));
            tenantDbInfo.setDbUser(rs.getString("db_user"));
            tenantDbInfo.setTenantId(rs.getString("tenant_id"));
            tenantDbInfo.setTenantName(rs.getString("tenant_name"));
            return tenantDbInfo;
        }
    }
    
    @Override
    // 使用内存缓存，避免频繁查询数据库
    public TenantDbInfo findByTenantId(String tenantId) {
        // 先从缓存中获取
        TenantDbInfo cachedTenant = tenantCache.get(tenantId);
        if (cachedTenant != null) {
            return cachedTenant;
        }
        
        try {
            // 从数据库查询
            String sql = "SELECT * FROM tenant_db_info WHERE tenant_id = ?";
            List<TenantDbInfo> tenants = jdbcTemplate.query(sql, new TenantDbInfoRowMapper(), tenantId);
            
            if (!tenants.isEmpty()) {
                TenantDbInfo tenantDbInfo = tenants.get(0);
                // 放入缓存
                tenantCache.put(tenantId, tenantDbInfo);
                return tenantDbInfo;
            }
        } catch (Exception e) {
            logger.error("Failed to find tenant by ID: " + tenantId, e);
        }
        
        return null;
    }
    
    @Override
    public List<TenantDbInfo> findAll() {
        try {
            String sql = "SELECT * FROM tenant_db_info";
            List<TenantDbInfo> tenants = jdbcTemplate.query(sql, new TenantDbInfoRowMapper());
            
            // 更新缓存
            for (TenantDbInfo tenant : tenants) {
                tenantCache.put(tenant.getTenantId(), tenant);
            }
            
            return tenants;
        } catch (Exception e) {
            logger.error("Failed to find all tenants", e);
            return java.util.Collections.emptyList();
        }
    }
    
    @Override
    public TenantDbInfo save(TenantDbInfo tenantDbInfo) {
        try {
            // 检查是否已存在
            TenantDbInfo existingTenant = findByTenantId(tenantDbInfo.getTenantId());
            
            if (existingTenant != null) {
                // 更新
                String sql = "UPDATE tenant_db_info SET db_name = ?, db_password = ?, db_url = ?, db_user = ?, tenant_name = ? WHERE tenant_id = ?";
                jdbcTemplate.update(sql,
                        tenantDbInfo.getDbName(),
                        tenantDbInfo.getDbPassword(),
                        tenantDbInfo.getDbUrl(),
                        tenantDbInfo.getDbUser(),
                        tenantDbInfo.getTenantName(),
                        tenantDbInfo.getTenantId());
            } else {
                // 插入
                String sql = "INSERT INTO tenant_db_info (db_name, db_password, db_url, db_user, tenant_id, tenant_name) VALUES (?, ?, ?, ?, ?, ?)";
                jdbcTemplate.update(sql,
                        tenantDbInfo.getDbName(),
                        tenantDbInfo.getDbPassword(),
                        tenantDbInfo.getDbUrl(),
                        tenantDbInfo.getDbUser(),
                        tenantDbInfo.getTenantId(),
                        tenantDbInfo.getTenantName());
            }
            
            // 刷新数据源
            refreshTenantDataSource(tenantDbInfo.getTenantId());
            
            // 更新缓存
            tenantCache.put(tenantDbInfo.getTenantId(), tenantDbInfo);
            
            return tenantDbInfo;
        } catch (Exception e) {
            logger.error("Failed to save tenant: " + tenantDbInfo.getTenantId(), e);
            return null;
        }
    }
    
    @Override
    public void delete(String tenantId) {
        try {
            // 从数据库删除
            String sql = "DELETE FROM tenant_db_info WHERE tenant_id = ?";
            jdbcTemplate.update(sql, tenantId);
            
            // 移除数据源
            dataSourceCreator.removeDataSource(tenantId);
            
            // 更新动态数据源
            Map<Object, Object> targetDataSources = new HashMap<>(dataSourceCreator.getAllDataSources());
            dynamicDataSource.setTargetDataSources(targetDataSources);
            dynamicDataSource.afterPropertiesSet();
            
            // 从缓存中移除
            tenantCache.remove(tenantId);
            
            logger.info("Deleted tenant: {}", tenantId);
        } catch (Exception e) {
            logger.error("Failed to delete tenant: " + tenantId, e);
        }
    }
    
    @Override
    public void refreshTenantDataSource(String tenantId) {
        try {
            TenantDbInfo tenantDbInfo = findByTenantId(tenantId);
            
            if (tenantDbInfo != null) {
                // 创建数据源
                DataSource dataSource = dataSourceCreator.createDataSource(
                        tenantId,
                        tenantDbInfo.getDbUrl(),
                        tenantDbInfo.getDbUser(),
                        tenantDbInfo.getDbPassword()
                );
                
                // 更新动态数据源
                Map<Object, Object> targetDataSources = new HashMap<>(dataSourceCreator.getAllDataSources());
                dynamicDataSource.setTargetDataSources(targetDataSources);
                dynamicDataSource.afterPropertiesSet();
                
                logger.info("Refreshed data source for tenant: {}", tenantId);
            } else {
                logger.warn("Tenant not found: {}", tenantId);
            }
        } catch (Exception e) {
            logger.error("Failed to refresh data source for tenant: " + tenantId, e);
        }
    }
    
    @Override
    public void refreshAllTenantDataSources() {
        try {
            List<TenantDbInfo> tenants = findAll();
            
            Map<Object, Object> targetDataSources = new HashMap<>();
            
            for (TenantDbInfo tenant : tenants) {
                try {
                    // 创建数据源
                    DataSource dataSource = dataSourceCreator.createDataSource(
                            tenant.getTenantId(),
                            tenant.getDbUrl(),
                            tenant.getDbUser(),
                            tenant.getDbPassword()
                    );
                    
                    targetDataSources.put(tenant.getTenantId(), dataSource);
                    logger.info("Created data source for tenant: {}", tenant.getTenantId());
                } catch (Exception e) {
                    logger.error("Failed to create data source for tenant: " + tenant.getTenantId(), e);
                }
            }
            
            // 更新动态数据源
            dynamicDataSource.setTargetDataSources(targetDataSources);
            dynamicDataSource.afterPropertiesSet();
            
            logger.info("Refreshed all tenant data sources, count: {}", tenants.size());
        } catch (Exception e) {
            logger.error("Failed to refresh all tenant data sources", e);
        }
    }
}