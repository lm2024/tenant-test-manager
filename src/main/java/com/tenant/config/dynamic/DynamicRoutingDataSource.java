package com.tenant.config.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    private final Map<Object, Object> dynamicTargetDataSources = new ConcurrentHashMap<>();

    public void addDataSource(String tenantId, DataSource dataSource) {
        dynamicTargetDataSources.put(tenantId, dataSource);
        super.setTargetDataSources(new HashMap<>(dynamicTargetDataSources));
        super.afterPropertiesSet(); // 关键！让Spring重新感知数据源
        System.out.println("[DynamicRoutingDataSource] 新增数据源: " + tenantId);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String tenantId = DataSourceContextHolder.get();
        System.out.println("[DynamicRoutingDataSource] 当前线程: " + Thread.currentThread().getName() + ", 当前租户ID: " + tenantId);
        return tenantId;
    }
} 