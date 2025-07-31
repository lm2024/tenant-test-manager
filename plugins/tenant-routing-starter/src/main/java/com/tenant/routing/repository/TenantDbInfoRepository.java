package com.tenant.routing.repository;

import com.tenant.routing.entity.TenantDbInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 租户数据库信息仓库
 * 使用中心数据库连接，不参与租户路由
 */
@Repository
public class TenantDbInfoRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantDbInfoRepository.class);
    
    private final DataSource dataSource;
    
    // 缓存租户信息，避免频繁查询数据库
    private final ConcurrentHashMap<String, TenantDbInfo> tenantCache = new ConcurrentHashMap<>();
    
    public TenantDbInfoRepository(@Qualifier("tenantCenterDataSource") DataSource dataSource) {
        // 直接使用DataSource，避免被DynamicDataSource拦截
        this.dataSource = dataSource;
        logger.info("TenantDbInfoRepository initialized with tenantCenterDataSource: {}", dataSource.getClass().getName());
        
        // 测试连接
        try (Connection conn = dataSource.getConnection()) {
            String url = conn.getMetaData().getURL();
            logger.info("TenantDbInfoRepository connected to: {}", url);
        } catch (Exception e) {
            logger.error("Failed to test tenantCenterDataSource connection", e);
        }
    }
    
    /**
     * 保存租户信息
     */
    public TenantDbInfo save(TenantDbInfo tenantDbInfo) {
        logger.info("Saving tenant info: {}, current tenant context: {}", 
                   tenantDbInfo.getTenantId(), 
                   com.tenant.routing.core.TenantContextHolder.getTenantId());
        
        try (Connection connection = dataSource.getConnection()) {
            // 验证连接的数据库
            String currentDatabase = connection.getCatalog();
            String connectionUrl = connection.getMetaData().getURL();
            logger.info("Got connection from tenantCenterDataSource, database: {}, URL: {}", currentDatabase, connectionUrl);
            
            // 强制切换到tenant_center数据库
            if (!"tenant_center".equals(currentDatabase)) {
                logger.warn("Connection is not on tenant_center database, current: {}, switching...", currentDatabase);
                connection.setCatalog("tenant_center");
                currentDatabase = connection.getCatalog();
                logger.info("Switched to database: {}", currentDatabase);
            }
            
            if (tenantDbInfo.getId() == null) {
                // 插入新记录
                String sql = "INSERT INTO tenant_center.tenant_db_info (db_name, db_password, db_url, db_user, tenant_id, tenant_name) VALUES (?, ?, ?, ?, ?, ?)";
                logger.info("Executing INSERT SQL: {}", sql);
                
                try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, tenantDbInfo.getDbName());
                    ps.setString(2, tenantDbInfo.getDbPassword());
                    ps.setString(3, tenantDbInfo.getDbUrl());
                    ps.setString(4, tenantDbInfo.getDbUser());
                    ps.setString(5, tenantDbInfo.getTenantId());
                    ps.setString(6, tenantDbInfo.getTenantName());
                    
                    int affectedRows = ps.executeUpdate();
                    logger.info("INSERT affected rows: {}", affectedRows);
                    
                    if (affectedRows > 0) {
                        try (ResultSet rs = ps.getGeneratedKeys()) {
                            if (rs.next()) {
                                long generatedId = rs.getLong(1);
                                tenantDbInfo.setId(generatedId);
                                logger.info("Generated ID: {}", generatedId);
                            }
                        }
                    }
                }
            } else {
                // 更新现有记录
                String sql = "UPDATE tenant_center.tenant_db_info SET db_name = ?, db_password = ?, db_url = ?, db_user = ?, tenant_id = ?, tenant_name = ? WHERE id = ?";
                logger.info("Executing UPDATE SQL: {}", sql);
                
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, tenantDbInfo.getDbName());
                    ps.setString(2, tenantDbInfo.getDbPassword());
                    ps.setString(3, tenantDbInfo.getDbUrl());
                    ps.setString(4, tenantDbInfo.getDbUser());
                    ps.setString(5, tenantDbInfo.getTenantId());
                    ps.setString(6, tenantDbInfo.getTenantName());
                    ps.setLong(7, tenantDbInfo.getId());
                    
                    int affectedRows = ps.executeUpdate();
                    logger.info("UPDATE affected rows: {}", affectedRows);
                }
            }
            
            // 更新缓存
            tenantCache.put(tenantDbInfo.getTenantId(), tenantDbInfo);
            logger.info("Successfully saved tenant info: {}", tenantDbInfo);
            
            return tenantDbInfo;
        } catch (SQLException e) {
            logger.error("Error saving tenant info", e);
            throw new RuntimeException("Error saving tenant info", e);
        }
    }
    
    /**
     * 根据ID查询租户信息
     */
    public TenantDbInfo findById(Long id) {
        String sql = "SELECT id, db_name, db_password, db_url, db_user, tenant_id, tenant_name FROM tenant_center.tenant_db_info WHERE id = ?";
        
        List<TenantDbInfo> results = new ArrayList<>(); // Use ArrayList for direct execution
        try (Connection connection = dataSource.getConnection()) {
            // 强制切换到tenant_center数据库
            connection.setCatalog("tenant_center");
            
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        results.add(new TenantDbInfoRowMapper().mapRow(rs, 0)); // Use a new instance for each row
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding tenant by ID", e);
            throw new RuntimeException("Error finding tenant by ID", e);
        }
        
        if (results.isEmpty()) {
            return null;
        }
        
        TenantDbInfo tenantDbInfo = results.get(0);
        
        // 放入缓存
        tenantCache.put(tenantDbInfo.getTenantId(), tenantDbInfo);
        
        return tenantDbInfo;
    }
    
    /**
     * 根据租户ID查询租户信息
     */
    public TenantDbInfo findByTenantId(String tenantId) {
        // 先从缓存中查询
        if (tenantCache.containsKey(tenantId)) {
            return tenantCache.get(tenantId);
        }
        
        // 从数据库中查询
        String sql = "SELECT id, db_name, db_password, db_url, db_user, tenant_id, tenant_name FROM tenant_center.tenant_db_info WHERE tenant_id = ?";
        
        List<TenantDbInfo> results = new ArrayList<>(); // Use ArrayList for direct execution
        try (Connection connection = dataSource.getConnection()) {
            // 强制切换到tenant_center数据库
            connection.setCatalog("tenant_center");
            
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, tenantId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        results.add(new TenantDbInfoRowMapper().mapRow(rs, 0)); // Use a new instance for each row
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding tenant by Tenant ID", e);
            throw new RuntimeException("Error finding tenant by Tenant ID", e);
        }
        
        if (results.isEmpty()) {
            return null;
        }
        
        TenantDbInfo tenantDbInfo = results.get(0);
        
        // 放入缓存
        tenantCache.put(tenantId, tenantDbInfo);
        
        return tenantDbInfo;
    }
    
    /**
     * 查询所有租户信息
     */
    public List<TenantDbInfo> findAll() {
        String sql = "SELECT id, db_name, db_password, db_url, db_user, tenant_id, tenant_name FROM tenant_center.tenant_db_info";
        
        List<TenantDbInfo> results = new ArrayList<>(); // Use ArrayList for direct execution
        try (Connection connection = dataSource.getConnection()) {
            // 强制切换到tenant_center数据库
            connection.setCatalog("tenant_center");
            
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        results.add(new TenantDbInfoRowMapper().mapRow(rs, 0)); // Use a new instance for each row
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding all tenants", e);
            throw new RuntimeException("Error finding all tenants", e);
        }
        
        // 更新缓存
        for (TenantDbInfo tenantDbInfo : results) {
            tenantCache.put(tenantDbInfo.getTenantId(), tenantDbInfo);
        }
        
        return results;
    }
    
    /**
     * 清除缓存
     */
    public void clearCache() {
        tenantCache.clear();
    }
    
    /**
     * 清除指定租户的缓存
     */
    public void clearCache(String tenantId) {
        tenantCache.remove(tenantId);
    }
    
    /**
     * 根据ID删除租户信息
     */
    public void deleteById(Long id) {
        // 先查询要删除的租户信息，以便清除缓存
        TenantDbInfo tenantDbInfo = findById(id);
        if (tenantDbInfo != null) {
            // 清除缓存
            tenantCache.remove(tenantDbInfo.getTenantId());
        }
        
        // 从数据库中删除
        String sql = "DELETE FROM tenant_db_info WHERE id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error deleting tenant by ID", e);
            throw new RuntimeException("Error deleting tenant by ID", e);
        }
    }
    
    /**
     * 根据租户ID删除租户信息
     */
    public void deleteByTenantId(String tenantId) {
        // 清除缓存
        tenantCache.remove(tenantId);
        
        // 从数据库中删除
        String sql = "DELETE FROM tenant_db_info WHERE tenant_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, tenantId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error deleting tenant by Tenant ID", e);
            throw new RuntimeException("Error deleting tenant by Tenant ID", e);
        }
    }
    
    /**
     * 租户信息行映射器
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
}