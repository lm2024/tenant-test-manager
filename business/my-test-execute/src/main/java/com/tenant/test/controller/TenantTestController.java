package com.tenant.test.controller;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 租户测试控制器
 */
@RestController
@RequestMapping("/api/tenant-test")
@Tag(name = "租户测试", description = "租户路由和切换测试接口")
public class TenantTestController {

    /**
     * 获取当前租户ID
     */
    @GetMapping("/current")
    @Operation(summary = "获取当前租户", description = "获取当前请求的租户ID")
    public Map<String, Object> getCurrentTenant() {
        String tenantId = TenantContextHolder.getTenantId();
        
        Map<String, Object> result = new HashMap<>();
        result.put("tenantId", tenantId);
        result.put("timestamp", System.currentTimeMillis());
        
        return result;
    }
    
    /**
     * 使用注解切换到指定租户
     */
    @TenantSwitch("tenant001")
    @GetMapping("/fixed")
    @Operation(summary = "固定租户测试", description = "使用注解切换到固定租户tenant001")
    public Map<String, Object> getFixedTenant() {
        String tenantId = TenantContextHolder.getTenantId();
        
        Map<String, Object> result = new HashMap<>();
        result.put("tenantId", tenantId);
        result.put("message", "Using fixed tenant: tenant001");
        result.put("timestamp", System.currentTimeMillis());
        
        return result;
    }
    
    /**
     * 动态切换租户
     */
    @GetMapping("/switch/{tenantId}")
    @Operation(summary = "动态切换租户", description = "动态切换到指定的租户ID")
    public Map<String, Object> switchTenant(@PathVariable @Parameter(description = "租户ID") String tenantId) {
        String originalTenant = TenantContextHolder.getTenantId();
        
        Map<String, Object> result = new HashMap<>();
        result.put("originalTenant", originalTenant);
        
        try {
            // 临时切换租户
            TenantContextHolder.setTenantId(tenantId);
            String switchedTenant = TenantContextHolder.getTenantId();
            
            result.put("switchedTenant", switchedTenant);
            result.put("message", "Successfully switched to tenant: " + tenantId);
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