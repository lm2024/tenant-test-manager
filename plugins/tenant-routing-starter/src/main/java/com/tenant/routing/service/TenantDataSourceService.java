package com.tenant.routing.service;

import com.tenant.routing.config.TenantRoutingProperties;
import com.tenant.routing.core.DynamicDataSource;
import com.tenant.routing.core.TenantDataSourceCreator;
import com.tenant.routing.entity.TenantDbInfo;
import com.tenant.routing.repository.TenantDbInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 租户数据源服务
 */
@Service
public class TenantDataSourceService {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantDataSourceService.class);
    
    @Autowired
    private TenantDataSourceCreator dataSourceCreator;
    
    @Autowired
    private DynamicDataSource dynamicDataSource;
    
    @Autowired
    private TenantDbInfoRepository tenantDbInfoRepository;
    
    @Autowired(required = false)
    private TenantRoutingProperties properties;
    
    private final Map<String, Boolean> initializedTenants = new ConcurrentHashMap<>();
    
    /**
     * 初始化所有租户数据源
     */
    @PostConstruct
    public void initAllTenantDataSources() {
        try {
            logger.info("Initializing all tenant data sources...");
            List<TenantDbInfo> tenantDbInfos = tenantDbInfoRepository.findAll();
            
            if (tenantDbInfos == null || tenantDbInfos.isEmpty()) {
                logger.info("No tenant data sources found to initialize");
                return;
            }
            
            Map<Object, Object> targetDataSources = new HashMap<>();
            
            for (TenantDbInfo tenantDbInfo : tenantDbInfos) {
                try {
                    DataSource dataSource = dataSourceCreator.createDataSource(
                        tenantDbInfo.getTenantId(),
                        tenantDbInfo.getDbUrl(),
                        tenantDbInfo.getDbUser(),
                        tenantDbInfo.getDbPassword()
                    );
                    targetDataSources.put(tenantDbInfo.getTenantId(), dataSource);
                    initializedTenants.put(tenantDbInfo.getTenantId(), true);
                    logger.info("Initialized data source for tenant: {}", tenantDbInfo.getTenantId());
                } catch (Exception e) {
                    logger.error("Failed to initialize data source for tenant: {}", tenantDbInfo.getTenantId(), e);
                }
            }
            
            if (!targetDataSources.isEmpty()) {
                dynamicDataSource.setTargetDataSources(targetDataSources);
                dynamicDataSource.afterPropertiesSet();
                logger.info("Successfully initialized {} tenant data sources", targetDataSources.size());
            }
        } catch (Exception e) {
            logger.error("Failed to initialize tenant data sources", e);
        }
    }
    
    /**
     * 如果数据源不存在，则创建
     */
    public synchronized void createDataSourceIfNotExists(String tenantId, String url, String username, String password) {
        if (!initializedTenants.containsKey(tenantId)) {
            try {
                DataSource dataSource = dataSourceCreator.createDataSource(tenantId, url, username, password);
                
                // 使用新的addDataSource方法
                dynamicDataSource.addDataSource(tenantId, dataSource);
                
                initializedTenants.put(tenantId, true);
                logger.info("Created data source for tenant: {}", tenantId);
            } catch (Exception e) {
                logger.error("Failed to create data source for tenant: {}", tenantId, e);
            }
        }
    }
    
    /**
     * 移除租户数据源
     */
    public synchronized void removeDataSource(String tenantId) {
        if (initializedTenants.containsKey(tenantId)) {
            try {
                dataSourceCreator.removeDataSource(tenantId);
                initializedTenants.remove(tenantId);
                
                // 使用新的removeDataSource方法
                dynamicDataSource.removeDataSource(tenantId);
                
                logger.info("Removed data source for tenant: {}", tenantId);
            } catch (Exception e) {
                logger.error("Failed to remove data source for tenant: {}", tenantId, e);
            }
        }
    }
    
    /**
     * 检查租户数据源是否已初始化
     */
    public boolean isDataSourceInitialized(String tenantId) {
        return initializedTenants.containsKey(tenantId);
    }
}