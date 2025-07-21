package com.tenant.controller.manager;

import com.tenant.service.MonitoringService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@Api(tags = "监控告警管理")
@RestController
@RequestMapping("/api/tenant/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @ApiOperation("获取系统监控数据")
    @GetMapping("/system")
    public ResponseEntity<?> getSystemMetrics() {
        try {
            Map<String, Object> metrics = monitoringService.getSystemMetrics();
            return ResponseEntity.ok(metrics);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @ApiOperation("获取租户数据库性能监控")
    @GetMapping("/performance/{tenantId}")
    public ResponseEntity<?> getPerformanceMetrics(@PathVariable String tenantId) {
        try {
            Map<String, Object> metrics = monitoringService.getPerformanceMetrics(tenantId);
            return ResponseEntity.ok(metrics);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @ApiOperation("获取告警列表")
    @GetMapping("/alerts")
    public ResponseEntity<?> getAlerts() {
        try {
            Map<String, Object> alerts = monitoringService.getAlerts();
            return ResponseEntity.ok(alerts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @ApiOperation("创建告警规则")
    @PostMapping("/alert-rules")
    public ResponseEntity<?> createAlertRule(@RequestBody com.tenant.model.AlertRuleRequest request) {
        try {
            monitoringService.createAlertRule(request);
            return ResponseEntity.ok(createSuccessResponse("告警规则创建成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @ApiOperation("获取监控面板数据")
    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardData() {
        try {
            Map<String, Object> dashboard = monitoringService.getDashboardData();
            return ResponseEntity.ok(dashboard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @ApiOperation("获取慢查询统计")
    @GetMapping("/slow-queries")
    public ResponseEntity<?> getSlowQueries() {
        try {
            Map<String, Object> slowQueries = monitoringService.getSlowQueries();
            return ResponseEntity.ok(slowQueries);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    private Map<String, Object> createErrorResponse(String error) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", error);
        return response;
    }

    private Map<String, Object> createSuccessResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        return response;
    }

    // 请求模型
    public static class AlertRuleRequest {
        private String name;
        private String condition;
        private String threshold;
        private String action;
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCondition() { return condition; }
        public void setCondition(String condition) { this.condition = condition; }
        public String getThreshold() { return threshold; }
        public void setThreshold(String threshold) { this.threshold = threshold; }
        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
    }
} 