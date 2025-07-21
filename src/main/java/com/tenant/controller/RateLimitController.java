package com.tenant.controller;

import com.tenant.service.RateLimitService;
import com.tenant.model.RateLimitRuleRequest;
import com.tenant.model.CircuitBreakerRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@Api(tags = "API限流熔断管理")
@RestController
@RequestMapping("/api/tenant/rate-limit")
public class RateLimitController {

    @Autowired
    private RateLimitService rateLimitService;

    @ApiOperation("创建限流规则")
    @PostMapping("/rules")
    public ResponseEntity<?> createRateLimitRule(@RequestBody RateLimitRuleRequest request) {
        try {
            rateLimitService.createRateLimitRule(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "限流规则创建成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取所有限流规则")
    @GetMapping("/rules")
    public ResponseEntity<?> getAllRateLimitRules() {
        try {
            Map<String, Object> rules = rateLimitService.getAllRateLimitRules();
            return ResponseEntity.ok(rules);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("更新限流规则")
    @PutMapping("/rules/{ruleId}")
    public ResponseEntity<?> updateRateLimitRule(@PathVariable String ruleId, 
                                               @RequestBody RateLimitRuleRequest request) {
        try {
            rateLimitService.updateRateLimitRule(ruleId, request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "限流规则更新成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("删除限流规则")
    @DeleteMapping("/rules/{ruleId}")
    public ResponseEntity<?> deleteRateLimitRule(@PathVariable String ruleId) {
        try {
            rateLimitService.deleteRateLimitRule(ruleId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "限流规则删除成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取限流统计")
    @GetMapping("/stats")
    public ResponseEntity<?> getRateLimitStats(@RequestParam(required = false) String tenantId) {
        try {
            Map<String, Object> stats = rateLimitService.getRateLimitStats(tenantId);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("创建熔断规则")
    @PostMapping("/circuit-breaker")
    public ResponseEntity<?> createCircuitBreakerRule(@RequestBody CircuitBreakerRequest request) {
        try {
            rateLimitService.createCircuitBreakerRule(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "熔断规则创建成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取熔断状态")
    @GetMapping("/circuit-breaker/status")
    public ResponseEntity<?> getCircuitBreakerStatus(@RequestParam(required = false) String tenantId) {
        try {
            Map<String, Object> status = rateLimitService.getCircuitBreakerStatus(tenantId);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("手动触发熔断")
    @PostMapping("/circuit-breaker/{tenantId}/trigger")
    public ResponseEntity<?> triggerCircuitBreaker(@PathVariable String tenantId) {
        try {
            rateLimitService.triggerCircuitBreaker(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "熔断触发成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("重置熔断器")
    @PostMapping("/circuit-breaker/{tenantId}/reset")
    public ResponseEntity<?> resetCircuitBreaker(@PathVariable String tenantId) {
        try {
            rateLimitService.resetCircuitBreaker(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "熔断器重置成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 