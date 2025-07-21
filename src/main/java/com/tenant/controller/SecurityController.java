package com.tenant.controller;

import com.tenant.service.SecurityAuditService;
import com.tenant.model.PermissionRequest;
import com.tenant.model.RoleAssignmentRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@Api(tags = "安全审计管理")
@RestController
@RequestMapping("/api/tenant/security")
public class SecurityController {

    @Autowired
    private SecurityAuditService securityAuditService;

    @ApiOperation("获取安全日志")
    @GetMapping("/logs")
    public ResponseEntity<?> getSecurityLogs(@RequestParam(required = false) String tenantId,
                                           @RequestParam(required = false) String action,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size) {
        try {
            Map<String, Object> logs = securityAuditService.getSecurityLogs(tenantId, action, page, size);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取审计跟踪")
    @GetMapping("/audit-trail")
    public ResponseEntity<?> getAuditTrail(@RequestParam(required = false) String tenantId,
                                         @RequestParam(required = false) String userId,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "20") int size) {
        try {
            Map<String, Object> auditTrail = securityAuditService.getAuditTrail(tenantId, userId, page, size);
            return ResponseEntity.ok(auditTrail);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取权限列表")
    @GetMapping("/permissions")
    public ResponseEntity<?> getPermissions(@RequestParam(required = false) String tenantId) {
        try {
            Map<String, Object> permissions = securityAuditService.getPermissions(tenantId);
            return ResponseEntity.ok(permissions);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("创建权限")
    @PostMapping("/permissions")
    public ResponseEntity<?> createPermission(@RequestBody PermissionRequest request) {
        try {
            securityAuditService.createPermission(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "权限创建成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取用户角色")
    @GetMapping("/user-roles")
    public ResponseEntity<?> getUserRoles(@RequestParam String userId) {
        try {
            Map<String, Object> roles = securityAuditService.getUserRoles(userId);
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("分配用户角色")
    @PostMapping("/assign-role")
    public ResponseEntity<?> assignUserRole(@RequestBody RoleAssignmentRequest request) {
        try {
            securityAuditService.assignUserRole(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "角色分配成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取数据访问统计")
    @GetMapping("/access-stats")
    public ResponseEntity<?> getAccessStats(@RequestParam(required = false) String tenantId) {
        try {
            Map<String, Object> stats = securityAuditService.getAccessStats(tenantId);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 