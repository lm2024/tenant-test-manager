package com.tenant.service;

import com.tenant.model.PermissionRequest;
import com.tenant.model.RoleAssignmentRequest;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.LocalDateTime;

@Service
public class SecurityAuditService {

    // 获取安全日志
    public Map<String, Object> getSecurityLogs(String tenantId, String action, int page, int size) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> logs = new ArrayList<>();
        
        // 模拟安全日志数据
        for (int i = 0; i < size; i++) {
            Map<String, Object> log = new HashMap<>();
            log.put("id", page * size + i + 1);
            log.put("tenantId", tenantId != null ? tenantId : "tenant_" + (i % 10 + 1));
            log.put("userId", "user_" + (i % 20 + 1));
            log.put("action", action != null ? action : getRandomAction());
            log.put("resource", "/api/test/" + (i % 5 + 1));
            log.put("ipAddress", "192.168.1." + (i % 255 + 1));
            log.put("userAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            log.put("status", i % 10 == 0 ? "FAILED" : "SUCCESS");
            log.put("timestamp", LocalDateTime.now().minusMinutes(i * 5));
            logs.add(log);
        }
        
        result.put("logs", logs);
        result.put("totalCount", 1000);
        result.put("page", page);
        result.put("size", size);
        
        return result;
    }

    // 获取审计跟踪
    public Map<String, Object> getAuditTrail(String tenantId, String userId, int page, int size) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> auditTrail = new ArrayList<>();
        
        // 模拟审计跟踪数据
        for (int i = 0; i < size; i++) {
            Map<String, Object> audit = new HashMap<>();
            audit.put("id", page * size + i + 1);
            audit.put("tenantId", tenantId != null ? tenantId : "tenant_" + (i % 10 + 1));
            audit.put("userId", userId != null ? userId : "user_" + (i % 20 + 1));
            audit.put("action", getRandomAuditAction());
            audit.put("oldValue", "旧值" + (i + 1));
            audit.put("newValue", "新值" + (i + 1));
            audit.put("tableName", "test_case");
            audit.put("recordId", i + 1);
            audit.put("timestamp", LocalDateTime.now().minusMinutes(i * 3));
            auditTrail.add(audit);
        }
        
        result.put("auditTrail", auditTrail);
        result.put("totalCount", 500);
        result.put("page", page);
        result.put("size", size);
        
        return result;
    }

    // 获取权限列表
    public Map<String, Object> getPermissions(String tenantId) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> permissions = new ArrayList<>();
        
        // 模拟权限数据
        String[] resources = {"test_case", "tenant_db_info", "user", "role"};
        String[] actions = {"READ", "WRITE", "DELETE", "ADMIN"};
        
        for (int i = 0; i < 16; i++) {
            Map<String, Object> permission = new HashMap<>();
            permission.put("id", i + 1);
            permission.put("name", "权限" + (i + 1));
            permission.put("description", "描述" + (i + 1));
            permission.put("resource", resources[i % resources.length]);
            permission.put("action", actions[i % actions.length]);
            permission.put("tenantId", tenantId != null ? tenantId : "tenant_" + (i % 10 + 1));
            permission.put("createdAt", LocalDateTime.now().minusDays(i));
            permissions.add(permission);
        }
        
        result.put("permissions", permissions);
        result.put("totalCount", permissions.size());
        
        return result;
    }

    // 创建权限
    public void createPermission(PermissionRequest request) {
        // 实际应该保存权限到数据库
        System.out.println("创建权限: " + request.getName() + " for tenant: " + request.getTenantId());
    }

    // 获取用户角色
    public Map<String, Object> getUserRoles(String userId) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> roles = new ArrayList<>();
        
        // 模拟用户角色数据
        String[] roleNames = {"ADMIN", "USER", "MANAGER", "VIEWER"};
        
        for (int i = 0; i < 3; i++) {
            Map<String, Object> role = new HashMap<>();
            role.put("id", i + 1);
            role.put("name", roleNames[i]);
            role.put("description", "角色描述" + (i + 1));
            role.put("tenantId", "tenant_" + (i % 10 + 1));
            role.put("assignedAt", LocalDateTime.now().minusDays(i * 10));
            roles.add(role);
        }
        
        result.put("userId", userId);
        result.put("roles", roles);
        result.put("totalCount", roles.size());
        
        return result;
    }

    // 分配用户角色
    public void assignUserRole(RoleAssignmentRequest request) {
        // 实际应该保存角色分配到数据库
        System.out.println("分配角色: " + request.getRoleId() + " to user: " + request.getUserId());
    }

    // 获取数据访问统计
    public Map<String, Object> getAccessStats(String tenantId) {
        Map<String, Object> result = new HashMap<>();
        
        // 模拟访问统计数据
        result.put("totalAccess", 15000);
        result.put("successfulAccess", 14800);
        result.put("failedAccess", 200);
        result.put("uniqueUsers", 150);
        result.put("avgResponseTime", 45.2);
        result.put("peakConcurrentUsers", 25);
        result.put("tenantId", tenantId);
        result.put("lastUpdated", LocalDateTime.now());
        
        return result;
    }

    private String getRandomAction() {
        String[] actions = {"LOGIN", "LOGOUT", "CREATE", "UPDATE", "DELETE", "READ", "EXPORT", "IMPORT"};
        return actions[(int) (Math.random() * actions.length)];
    }

    private String getRandomAuditAction() {
        String[] actions = {"INSERT", "UPDATE", "DELETE", "SELECT"};
        return actions[(int) (Math.random() * actions.length)];
    }
} 