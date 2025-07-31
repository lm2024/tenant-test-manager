package com.tenant.service;

import com.tenant.routing.entity.TenantDbInfo;
import com.tenant.routing.repository.TenantDbInfoRepository;
import com.tenant.model.DatabaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TenantDatabaseService {

    @Autowired
    private TenantDbInfoRepository tenantDbInfoRepository;
    
    @Autowired
    private DbInitService dbInitService;

    // 数据库状态缓存
    private final Map<String, DatabaseStatus> statusCache = new ConcurrentHashMap<>();

    @Transactional
    public void createTenantDatabase(String tenantId) throws Exception {
        // 检查租户是否已存在
        if (tenantDbInfoRepository.findByTenantId(tenantId) != null) {
            throw new RuntimeException("租户已存在: " + tenantId);
        }

        // 创建租户数据库信息
        TenantDbInfo dbInfo = new TenantDbInfo();
        dbInfo.setTenantId(tenantId);
        dbInfo.setDbName("tenant_" + tenantId);
        dbInfo.setDbUser("tenant_" + tenantId);
        dbInfo.setDbPassword(generatePassword());
        dbInfo.setDbUrl("jdbc:mysql://localhost:3306/tenant_" + tenantId);
        
        // 保存租户信息 - 这里需要实现保存逻辑
        // tenantDbInfoRepository.save(dbInfo);
        
        // 创建数据库和表
        String templateSql = "CREATE DATABASE IF NOT EXISTS tenant_" + tenantId + 
                           " DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";
        dbInitService.createDatabaseAndTables(dbInfo.getDbName(), dbInfo.getDbUser(), 
                                           dbInfo.getDbPassword(), templateSql);
        
        // 初始化状态
        DatabaseStatus status = new DatabaseStatus();
        status.setTenantId(tenantId);
        status.setDatabaseName("tenant_" + tenantId);
        status.setStatus("ONLINE");
        status.setLastUpdateTime(LocalDateTime.now());
        statusCache.put(tenantId, status);
    }

    public DatabaseStatus monitorDatabase(String tenantId) {
        DatabaseStatus status = statusCache.get(tenantId);
        if (status == null) {
            status = new DatabaseStatus();
            status.setTenantId(tenantId);
            status.setStatus("OFFLINE");
            status.setLastUpdateTime(LocalDateTime.now());
        }
        
        // 模拟监控数据（实际应该从数据库获取）
        status.setConnectionCount(100);
        status.setActiveConnections(50);
        status.setMaxConnections(200);
        status.setCpuUsage(Math.random() * 100);
        status.setMemoryUsage(Math.random() * 100);
        status.setDiskUsage(1024 * 1024 * 1024L); // 1GB
        status.setMaxDiskSpace(10 * 1024 * 1024 * 1024L); // 10GB
        status.setSlowQueryCount((long) (Math.random() * 10));
        status.setAvgResponseTime(Math.random() * 100);
        status.setLastUpdateTime(LocalDateTime.now());
        
        statusCache.put(tenantId, status);
        return status;
    }

    public String backupDatabase(String tenantId) throws Exception {
        // 模拟备份过程
        String backupFile = "/backup/tenant_" + tenantId + "_" + 
                          System.currentTimeMillis() + ".sql";
        
        // 实际应该执行 mysqldump 命令
        // ProcessBuilder pb = new ProcessBuilder("mysqldump", "-u", "root", "-p", 
        //                                     "tenant_" + tenantId, ">", backupFile);
        // pb.start().waitFor();
        
        return backupFile;
    }

    public void restoreDatabase(String tenantId, String backupFile) throws Exception {
        // 模拟恢复过程
        // 实际应该执行 mysql 命令恢复数据
        // ProcessBuilder pb = new ProcessBuilder("mysql", "-u", "root", "-p", 
        //                                     "tenant_" + tenantId, "<", backupFile);
        // pb.start().waitFor();
    }

    public Map<String, DatabaseStatus> getAllDatabaseStatus() {
        Map<String, DatabaseStatus> allStatus = new HashMap<>();
        List<TenantDbInfo> allTenants = tenantDbInfoRepository.findAll();
        
        for (TenantDbInfo tenant : allTenants) {
            DatabaseStatus status = monitorDatabase(tenant.getTenantId());
            allStatus.put(tenant.getTenantId(), status);
        }
        
        return allStatus;
    }

    private String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 16);
    }
} 