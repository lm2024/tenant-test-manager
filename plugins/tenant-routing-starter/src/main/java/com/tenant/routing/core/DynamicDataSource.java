package com.tenant.routing.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源路由
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
    
    private final Map<Object, Object> targetDataSources = new HashMap<>();
    
    public DynamicDataSource() {
        // 构造函数中不做初始化，由TenantAutoConfiguration负责初始化
    }
    
    @Override
    protected Object determineCurrentLookupKey() {
        String tenantId = TenantContextHolder.getTenantId();
        logger.debug("Current tenant ID: {}", tenantId);
        
        // 如果没有租户ID，使用默认的tenant_center
        if (tenantId == null || tenantId.trim().isEmpty()) {
            logger.debug("No tenant ID found, using default tenant_center");
            return "tenant_center";
        }
        
        return tenantId;
    }
    
    /**
     * 添加数据源
     */
    public synchronized void addDataSource(String tenantId, DataSource dataSource) {
        targetDataSources.put(tenantId, dataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
        logger.info("Added data source for tenant: {}", tenantId);
    }
    
    /**
     * 移除数据源
     */
    public synchronized void removeDataSource(String tenantId) {
        targetDataSources.remove(tenantId);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
        logger.info("Removed data source for tenant: {}", tenantId);
    }
    
    /**
     * 获取所有数据源
     */
    public Map<Object, Object> getTargetDataSources() {
        return new HashMap<>(targetDataSources);
    }
    
    /**
     * 设置目标数据源
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSources.clear();
        if (targetDataSources != null) {
            this.targetDataSources.putAll(targetDataSources);
        }
        super.setTargetDataSources(new HashMap<>(this.targetDataSources));
    }
}