package com.tenant.controller.manager;

import com.tenant.model.MigrationTaskRequest;
import com.tenant.model.DataSyncRequest;
import com.tenant.service.DataMigrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@Api(tags = "数据迁移管理")
@RestController
@RequestMapping("/api/tenant/migration")
public class DataMigrationController {

    @Autowired
    private DataMigrationService dataMigrationService;

    @ApiOperation("创建数据迁移任务")
    @PostMapping("/tasks")
    public ResponseEntity<?> createMigrationTask(@RequestBody MigrationTaskRequest request) {
        try {
            Map<String, Object> result = dataMigrationService.createMigrationTask(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("taskId", result.get("taskId"));
            response.put("message", "迁移任务创建成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取迁移任务详情")
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<?> getMigrationTaskDetail(@PathVariable String taskId) {
        try {
            Map<String, Object> detail = dataMigrationService.getMigrationTaskDetail(taskId);
            return ResponseEntity.ok(detail);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("启动迁移任务")
    @PostMapping("/tasks/{taskId}/start")
    public ResponseEntity<?> startMigrationTask(@PathVariable String taskId) {
        try {
            dataMigrationService.startMigrationTask(taskId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "迁移任务启动成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("暂停迁移任务")
    @PostMapping("/tasks/{taskId}/pause")
    public ResponseEntity<?> pauseMigrationTask(@PathVariable String taskId) {
        try {
            dataMigrationService.pauseMigrationTask(taskId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "迁移任务暂停成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("恢复迁移任务")
    @PostMapping("/tasks/{taskId}/resume")
    public ResponseEntity<?> resumeMigrationTask(@PathVariable String taskId) {
        try {
            dataMigrationService.resumeMigrationTask(taskId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "迁移任务恢复成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("取消迁移任务")
    @PostMapping("/tasks/{taskId}/cancel")
    public ResponseEntity<?> cancelMigrationTask(@PathVariable String taskId) {
        try {
            dataMigrationService.cancelMigrationTask(taskId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "迁移任务取消成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取迁移任务列表")
    @GetMapping("/tasks")
    public ResponseEntity<?> getMigrationTasks(@RequestParam(required = false) String tenantId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "20") int size) {
        try {
            Map<String, Object> tasks = dataMigrationService.getMigrationTasks(tenantId, page, size);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("数据同步")
    @PostMapping("/sync")
    public ResponseEntity<?> syncData(@RequestBody DataSyncRequest request) {
        try {
            Map<String, Object> result = dataMigrationService.syncData(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("syncId", result.get("syncId"));
            response.put("message", "数据同步任务创建成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取数据版本列表")
    @GetMapping("/versions")
    public ResponseEntity<?> getDataVersions(@RequestParam(required = false) String tenantId) {
        try {
            Map<String, Object> versions = dataMigrationService.getDataVersions(tenantId);
            return ResponseEntity.ok(versions);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("回滚到指定版本")
    @PostMapping("/rollback/{versionId}")
    public ResponseEntity<?> rollbackToVersion(@PathVariable String versionId) {
        try {
            Map<String, Object> result = dataMigrationService.rollbackToVersion(versionId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 