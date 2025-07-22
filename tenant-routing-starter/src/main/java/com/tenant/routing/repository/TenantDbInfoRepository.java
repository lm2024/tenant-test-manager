package com.tenant.routing.repository;

import com.tenant.routing.entity.TenantDbInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 租户数据库信息仓库
 */
@Repository
public class TenantDbInfoRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    // 缓存租户信息，避免频繁查询数据库
    private final ConcurrentHashMap<String, TenantDbInfo> tenantCache = new ConcurrentHashMap<>();
    
    public TenantDbInfoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        String sql = "SELECT id, db_name, db_password, db_url, db_user, tenant_id, tenant_name FROM tenant_db_info WHERE tenant_id = ?";
        
        List<TenantDbInfo> results = jdbcTemplate.query(sql, new TenantDbInfoRowMapper(), tenantId);
        
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
        String sql = "SELECT id, db_name, db_password, db_url, db_user, tenant_id, tenant_name FROM tenant_db_info";
        
        List<TenantDbInfo> results = jdbcTemplate.query(sql, new TenantDbInfoRowMapper());
        
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