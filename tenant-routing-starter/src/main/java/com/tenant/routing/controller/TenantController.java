package com.tenant.routing.controller;

import com.tenant.routing.entity.TenantDbInfo;
import com.tenant.routing.service.TenantDbInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租户管理控制器
 */
@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantController.class);
    
    @Autowired
    private TenantDbInfoService tenantDbInfoService;
    
    /**
     * 获取所有租户
     */
    @GetMapping
    public ResponseEntity<List<TenantDbInfo>> getAllTenants() {
        List<TenantDbInfo> tenants = tenantDbInfoService.findAll();
        return ResponseEntity.ok(tenants);
    }
    
    /**
     * 根据ID获取租户
     */
    @GetMapping("/{tenantId}")
    public ResponseEntity<?> getTenantById(@PathVariable String tenantId) {
        TenantDbInfo tenant = tenantDbInfoService.findByTenantId(tenantId);
        if (tenant != null) {
            return ResponseEntity.ok(tenant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 创建租户
     */
    @PostMapping
    public ResponseEntity<?> createTenant(@RequestBody TenantDbInfo tenantDbInfo) {
        try {
            TenantDbInfo savedTenant = tenantDbInfoService.save(tenantDbInfo);
            return ResponseEntity.ok(savedTenant);
        } catch (Exception e) {
            logger.error("Failed to create tenant", e);
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
    
    /**
     * 更新租户
     */
    @PutMapping("/{tenantId}")
    public ResponseEntity<?> updateTenant(@PathVariable String tenantId, @RequestBody TenantDbInfo tenantDbInfo) {
        try {
            tenantDbInfo.setTenantId(tenantId);
            TenantDbInfo updatedTenant = tenantDbInfoService.save(tenantDbInfo);
            return ResponseEntity.ok(updatedTenant);
        } catch (Exception e) {
            logger.error("Failed to update tenant: " + tenantId, e);
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
    
    /**
     * 删除租户
     */
    @DeleteMapping("/{tenantId}")
    public ResponseEntity<?> deleteTenant(@PathVariable String tenantId) {
        try {
            tenantDbInfoService.delete(tenantId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete tenant: " + tenantId, e);
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
    
    /**
     * 刷新租户数据源
     */
    @PostMapping("/{tenantId}/refresh")
    public ResponseEntity<?> refreshTenantDataSource(@PathVariable String tenantId) {
        try {
            tenantDbInfoService.refreshTenantDataSource(tenantId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to refresh tenant data source: " + tenantId, e);
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
    
    /**
     * 刷新所有租户数据源
     */
    @PostMapping("/refresh-all")
    public ResponseEntity<?> refreshAllTenantDataSources() {
        try {
            tenantDbInfoService.refreshAllTenantDataSources();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to refresh all tenant data sources", e);
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}