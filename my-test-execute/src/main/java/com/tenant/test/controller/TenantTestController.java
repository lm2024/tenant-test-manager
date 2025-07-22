package com.tenant.test.controller;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
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
public class TenantTestController {

    /**
     * 获取当前租户ID
     */
    @GetMapping("/current")
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
    public Map<String, Object> switchTenant(@PathVariable String tenantId) {
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