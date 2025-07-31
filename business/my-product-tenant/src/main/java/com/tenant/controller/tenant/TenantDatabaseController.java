package com.tenant.controller.tenant;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import com.tenant.service.TenantDatabaseService;
import com.tenant.model.DatabaseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@Tag(name = "租户数据库管理", description = "管理租户数据库，支持自动租户路由")
@RestController
@RequestMapping("/api/tenant/database")
public class TenantDatabaseController {

    @Autowired
    private TenantDatabaseService tenantDatabaseService;

    @Operation(summary = "创建租户数据库")
    @PostMapping("/create")
    public ResponseEntity<?> createTenantDatabase() {
        try {
            String tenantId = TenantContextHolder.getTenantId();
            if (tenantId == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("error", "租户ID不能为空，请在请求头中设置X-Tenant-ID");
                return ResponseEntity.badRequest().body(error);
            }
            
            tenantDatabaseService.createTenantDatabase(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "租户数据库创建成功");
            response.put("tenantId", tenantId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(summary = "监控当前租户数据库状态")
    @GetMapping("/status")
    public ResponseEntity<?> getCurrentDatabaseStatus() {
        try {
            String tenantId = TenantContextHolder.getTenantId();
            if (tenantId == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("error", "租户ID不能为空，请在请求头中设置X-Tenant-ID");
                return ResponseEntity.badRequest().body(error);
            }
            
            DatabaseStatus status = tenantDatabaseService.monitorDatabase(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("tenantId", tenantId);
            response.put("status", status);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(summary = "监控指定租户数据库状态")
    @GetMapping("/status/{tenantId}")
    public ResponseEntity<?> getDatabaseStatus(@Parameter(description = "租户ID") @PathVariable String tenantId) {
        try {
            DatabaseStatus status = tenantDatabaseService.monitorDatabase(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("tenantId", tenantId);
            response.put("status", status);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(summary = "备份当前租户数据库")
    @PostMapping("/backup")
    public ResponseEntity<?> backupCurrentDatabase() {
        try {
            String tenantId = TenantContextHolder.getTenantId();
            if (tenantId == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("error", "租户ID不能为空，请在请求头中设置X-Tenant-ID");
                return ResponseEntity.badRequest().body(error);
            }
            
            String backupFile = tenantDatabaseService.backupDatabase(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "数据库备份成功");
            response.put("tenantId", tenantId);
            response.put("backupFile", backupFile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(summary = "备份指定租户数据库")
    @PostMapping("/backup/{tenantId}")
    public ResponseEntity<?> backupDatabase(@Parameter(description = "租户ID") @PathVariable String tenantId) {
        try {
            String backupFile = tenantDatabaseService.backupDatabase(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "数据库备份成功");
            response.put("tenantId", tenantId);
            response.put("backupFile", backupFile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(summary = "恢复当前租户数据库")
    @PostMapping("/restore")
    public ResponseEntity<?> restoreCurrentDatabase(@Parameter(description = "备份文件路径") @RequestParam String backupFile) {
        try {
            String tenantId = TenantContextHolder.getTenantId();
            if (tenantId == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("error", "租户ID不能为空，请在请求头中设置X-Tenant-ID");
                return ResponseEntity.badRequest().body(error);
            }
            
            tenantDatabaseService.restoreDatabase(tenantId, backupFile);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "数据库恢复成功");
            response.put("tenantId", tenantId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(summary = "恢复指定租户数据库")
    @PostMapping("/restore/{tenantId}")
    public ResponseEntity<?> restoreDatabase(@Parameter(description = "租户ID") @PathVariable String tenantId, 
                                           @Parameter(description = "备份文件路径") @RequestParam String backupFile) {
        try {
            tenantDatabaseService.restoreDatabase(tenantId, backupFile);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "数据库恢复成功");
            response.put("tenantId", tenantId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(summary = "获取所有租户数据库状态")
    @GetMapping("/status/all")
    public ResponseEntity<?> getAllDatabaseStatus() {
        try {
            Map<String, DatabaseStatus> allStatus = tenantDatabaseService.getAllDatabaseStatus();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", allStatus);
            response.put("count", allStatus.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(summary = "获取当前租户信息")
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentTenant() {
        try {
            String tenantId = TenantContextHolder.getTenantId();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("tenantId", tenantId);
            response.put("hasTenant", tenantId != null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 