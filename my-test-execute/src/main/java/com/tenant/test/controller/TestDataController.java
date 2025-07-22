package com.tenant.test.controller;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import com.tenant.test.entity.TestData;
import com.tenant.test.service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试数据控制器
 */
@RestController
@RequestMapping("/api/test-data")
public class TestDataController {
    
    @Autowired
    private TestDataService testDataService;
    
    /**
     * 获取当前租户的所有数据
     */
    @GetMapping
    public List<TestData> getAllData() {
        return testDataService.getAllData();
    }
    
    /**
     * 创建测试数据
     */
    @PostMapping
    public TestData createData(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        return testDataService.createData(name);
    }
    
    /**
     * 使用指定租户创建数据
     */
    @TenantSwitch("tenant003")
    @PostMapping("/fixed")
    public TestData createDataWithFixedTenant(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        System.out.println("Controller - Before calling service: " + TenantContextHolder.getTenantId());
        TestData result = testDataService.createData(name);
        System.out.println("Controller - After calling service: " + TenantContextHolder.getTenantId());
        return result;
    }
    
    /**
     * 使用服务层的固定租户方法创建数据
     */
    @PostMapping("/service-fixed")
    public TestData createDataWithServiceFixedTenant(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        return testDataService.createDataWithFixedTenant(name);
    }

    /**
     * 根据请求头自动路由租户，查询当前租户的所有数据
     */
    @GetMapping("/auto-tenant")
    public Map<String, Object> getAllDataByAutoTenant() {
        String tenantId = com.tenant.routing.core.TenantContextHolder.getTenantId();
        List<TestData> dataList = testDataService.getAllData();
        Map<String, Object> result = new HashMap<>();
        result.put("tenantId", tenantId);
        result.put("data", dataList);
        return result;
    }
}