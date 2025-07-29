package com.tenant.test.controller;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试执行控制器
 * 演示租户路由功能
 */
@RestController
@RequestMapping("/api/test")
@Tag(name = "测试执行", description = "测试执行相关接口")
public class TestExecuteController {

    /**
     * 自动从请求头获取租户ID
     */
    @GetMapping("/execute")
    @Operation(summary = "执行测试", description = "自动从请求头获取租户ID并执行测试")
    public Map<String, Object> executeTest() {
        String currentTenant = TenantContextHolder.getTenantId();
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("tenant", currentTenant);
        result.put("message", "测试执行成功，当前租户: " + currentTenant);
        result.put("timestamp", System.currentTimeMillis());
        
        return result;
    }
    
    /**
     * 使用注解指定特定租户
     */
    @TenantSwitch("tenant001")
    @GetMapping("/execute/fixed")
    @Operation(summary = "固定租户执行测试", description = "使用注解指定固定租户tenant001执行测试")
    public Map<String, Object> executeFixedTenantTest() {
        String currentTenant = TenantContextHolder.getTenantId();
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("tenant", currentTenant);
        result.put("message", "固定租户测试执行成功，当前租户: " + currentTenant);
        result.put("timestamp", System.currentTimeMillis());
        
        return result;
    }
    
    /**
     * 动态切换租户
     */
    @GetMapping("/execute/switch")
    @Operation(summary = "动态切换租户测试", description = "动态切换租户并执行测试")
    public Map<String, Object> executeSwitchTenantTest() {
        String originalTenant = TenantContextHolder.getTenantId();
        
        Map<String, Object> result = new HashMap<>();
        result.put("originalTenant", originalTenant);
        
        try {
            // 临时切换到tenant002
            TenantContextHolder.setTenantId("tenant002");
            String switchedTenant = TenantContextHolder.getTenantId();
            result.put("switchedTenant", switchedTenant);
            result.put("message", "租户切换测试成功");
        } finally {
            // 恢复原始租户
            if (originalTenant != null) {
                TenantContextHolder.setTenantId(originalTenant);
            } else {
                TenantContextHolder.clear();
            }
        }
        
        result.put("currentTenant", TenantContextHolder.getTenantId());
        result.put("timestamp", System.currentTimeMillis());
        
        return result;
    }
}