package com.tenant.routing.service;

import com.tenant.routing.entity.TenantDbInfo;

import java.util.List;

/**
 * 租户数据库信息服务接口
 */
public interface TenantDbInfoService {
    
    /**
     * 根据租户ID查询租户数据库信息
     */
    TenantDbInfo findByTenantId(String tenantId);
    
    /**
     * 获取所有租户数据库信息
     */
    List<TenantDbInfo> findAll();
    
    /**
     * 保存租户数据库信息
     */
    TenantDbInfo save(TenantDbInfo tenantDbInfo);
    
    /**
     * 删除租户数据库信息
     */
    void delete(String tenantId);
    
    /**
     * 刷新租户数据源
     */
    void refreshTenantDataSource(String tenantId);
    
    /**
     * 刷新所有租户数据源
     */
    void refreshAllTenantDataSources();
}