package com.tenant.controller.tenant;

import com.tenant.service.TenantDatabaseService;
import com.tenant.model.DatabaseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@Api(tags = "租户数据库管理")
@RestController
@RequestMapping("/api/tenant/database")
public class TenantDatabaseController {

    @Autowired
    private TenantDatabaseService tenantDatabaseService;

    @ApiOperation("创建租户数据库")
    @PostMapping("/create/{tenantId}")
    public ResponseEntity<?> createTenantDatabase(@PathVariable String tenantId) {
        try {
            tenantDatabaseService.createTenantDatabase(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "租户数据库创建成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("监控租户数据库状态")
    @GetMapping("/status/{tenantId}")
    public ResponseEntity<?> getDatabaseStatus(@PathVariable String tenantId) {
        try {
            DatabaseStatus status = tenantDatabaseService.monitorDatabase(tenantId);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("备份租户数据库")
    @PostMapping("/backup/{tenantId}")
    public ResponseEntity<?> backupDatabase(@PathVariable String tenantId) {
        try {
            String backupFile = tenantDatabaseService.backupDatabase(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("backupFile", backupFile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("恢复租户数据库")
    @PostMapping("/restore/{tenantId}")
    public ResponseEntity<?> restoreDatabase(@PathVariable String tenantId, @RequestParam String backupFile) {
        try {
            tenantDatabaseService.restoreDatabase(tenantId, backupFile);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "数据库恢复成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取所有租户数据库状态")
    @GetMapping("/status/all")
    public ResponseEntity<?> getAllDatabaseStatus() {
        try {
            Map<String, DatabaseStatus> allStatus = tenantDatabaseService.getAllDatabaseStatus();
            return ResponseEntity.ok(allStatus);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 