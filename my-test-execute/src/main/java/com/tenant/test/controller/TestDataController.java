package com.tenant.test.controller;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import com.tenant.test.entity.TestData;
import com.tenant.test.service.TestDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "测试数据管理", description = "测试数据的增删改查接口")
public class TestDataController {
    
    @Autowired
    private TestDataService testDataService;
    
    /**
     * 获取当前租户的所有数据
     */
    @GetMapping
    @Operation(summary = "获取所有测试数据", description = "根据当前租户获取所有测试数据")
    public List<TestData> getAllData() {
        return testDataService.getAllData();
    }
    
    /**
     * 创建测试数据
     */
    @PostMapping
    @Operation(summary = "创建测试数据", description = "创建新的测试数据")
    public TestData createData(@RequestBody @Parameter(description = "请求参数") Map<String, String> request) {
        String name = request.get("name");
        return testDataService.createData(name);
    }
    
    /**
     * 使用指定租户创建数据
     */
    @TenantSwitch("tenant003")
    @PostMapping("/fixed")
    @Operation(summary = "固定租户创建数据", description = "使用固定租户tenant003创建测试数据")
    public TestData createDataWithFixedTenant(@RequestBody @Parameter(description = "请求参数") Map<String, String> request) {
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
    @Operation(summary = "服务层固定租户创建数据", description = "通过服务层使用固定租户创建测试数据")
    public TestData createDataWithServiceFixedTenant(@RequestBody @Parameter(description = "请求参数") Map<String, String> request) {
        String name = request.get("name");
        return testDataService.createDataWithFixedTenant(name);
    }

    /**
     * 根据请求头自动路由租户，查询当前租户的所有数据
     */
    @GetMapping("/auto-tenant")
    @Operation(summary = "自动租户查询", description = "根据请求头自动路由租户并查询数据")
    public Map<String, Object> getAllDataByAutoTenant() {
        String tenantId = com.tenant.routing.core.TenantContextHolder.getTenantId();
        List<TestData> dataList = testDataService.getAllData();
        Map<String, Object> result = new HashMap<>();
        result.put("tenantId", tenantId);
        result.put("data", dataList);
        return result;
    }
}