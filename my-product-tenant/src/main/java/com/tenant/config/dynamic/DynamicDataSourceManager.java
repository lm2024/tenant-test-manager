package com.tenant.config.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import com.tenant.entity.TenantDbInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态数据源管理器：根据租户信息动态创建和缓存Druid数据源。
 * 支持高并发环境下的数据源复用。
 */
@Component
public class DynamicDataSourceManager {
    /**
     * 租户ID -> 数据源缓存
     */
    private final Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    @Autowired
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    /**
     * 获取或创建指定租户的数据源。
     * @param dbInfo 租户数据库信息
     * @return 数据源
     */
    public DataSource getOrCreateDataSource(TenantDbInfo dbInfo) {
        System.out.println("[DynamicDataSourceManager] 请求租户ID: " + dbInfo.getTenantId());
        if (dataSourceMap.containsKey(dbInfo.getTenantId())) {
            System.out.println("[DynamicDataSourceManager] 复用已存在数据源: " + dbInfo.getTenantId());
        } else {
            System.out.println("[DynamicDataSourceManager] 创建新数据源: " + dbInfo.getTenantId() + ", url: " + dbInfo.getDbUrl());
        }
        return dataSourceMap.computeIfAbsent(dbInfo.getTenantId(), k -> {
            DruidDataSource ds = new DruidDataSource();
            ds.setUrl(dbInfo.getDbUrl());
            ds.setUsername(dbInfo.getDbUser());
            ds.setPassword(dbInfo.getDbPassword());
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setInitialSize(5);
            ds.setMinIdle(5);
            ds.setMaxActive(20);
            ds.setMaxWait(60000);
            ds.setValidationQuery("SELECT 1");
            ds.setTestWhileIdle(true);
            ds.setTestOnBorrow(false);
            ds.setTestOnReturn(false);
            System.out.println("[DynamicDataSourceManager] 新数据源已创建: " + dbInfo.getTenantId());
            dynamicRoutingDataSource.addDataSource(dbInfo.getTenantId(), ds);
            return ds;
        });
    }
} 