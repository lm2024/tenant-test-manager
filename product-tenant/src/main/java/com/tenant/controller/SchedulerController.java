package com.tenant.controller;

import com.tenant.service.SchedulerService;
import com.tenant.model.ScheduledJobRequest;
import com.tenant.model.BatchTaskRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@Api(tags = "任务调度管理")
@RestController
@RequestMapping("/api/tenant/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @ApiOperation("创建定时任务")
    @PostMapping("/jobs")
    public ResponseEntity<?> createScheduledJob(@RequestBody ScheduledJobRequest request) {
        try {
            schedulerService.createScheduledJob(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "定时任务创建成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取所有定时任务")
    @GetMapping("/jobs")
    public ResponseEntity<?> getAllScheduledJobs() {
        try {
            Map<String, Object> jobs = schedulerService.getAllScheduledJobs();
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("暂停定时任务")
    @PostMapping("/jobs/{jobId}/pause")
    public ResponseEntity<?> pauseScheduledJob(@PathVariable String jobId) {
        try {
            schedulerService.pauseScheduledJob(jobId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "任务暂停成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("恢复定时任务")
    @PostMapping("/jobs/{jobId}/resume")
    public ResponseEntity<?> resumeScheduledJob(@PathVariable String jobId) {
        try {
            schedulerService.resumeScheduledJob(jobId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "任务恢复成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("删除定时任务")
    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<?> deleteScheduledJob(@PathVariable String jobId) {
        try {
            schedulerService.deleteScheduledJob(jobId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "任务删除成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("执行批量任务")
    @PostMapping("/batch-execute")
    public ResponseEntity<?> executeBatchTask(@RequestBody BatchTaskRequest request) {
        try {
            Map<String, Object> result = schedulerService.executeBatchTask(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取任务执行历史")
    @GetMapping("/execution-history")
    public ResponseEntity<?> getExecutionHistory(@RequestParam(required = false) String jobId,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "20") int size) {
        try {
            Map<String, Object> history = schedulerService.getExecutionHistory(jobId, page, size);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取任务统计信息")
    @GetMapping("/stats")
    public ResponseEntity<?> getSchedulerStats() {
        try {
            Map<String, Object> stats = schedulerService.getSchedulerStats();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 