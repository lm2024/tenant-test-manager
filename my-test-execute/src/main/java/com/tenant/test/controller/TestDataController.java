package com.tenant.test.controller;

import com.tenant.test.entity.TestData;
import com.tenant.test.service.TestDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/test-data")
@Tag(name = "测试数据管理", description = "测试数据的增删改查接口")
public class TestDataController {
    
    @Autowired
    private TestDataService testDataService;
    
    @Operation(summary = "获取所有测试数据")
    @GetMapping
    public List<TestData> getAllTestData() {
        return testDataService.findAll();
    }
    
    @Operation(summary = "根据ID获取测试数据")
    @GetMapping("/{id}")
    public TestData getTestData(@PathVariable Long id) {
        return testDataService.findById(id);
    }
    
    @Operation(summary = "创建测试数据")
    @PostMapping
    public TestData createTestData(@RequestBody TestData testData) {
        testData.setCreateTime(LocalDateTime.now());
        testData.setUpdateTime(LocalDateTime.now());
        return testDataService.save(testData);
    }
    
    @Operation(summary = "更新测试数据")
    @PutMapping("/{id}")
    public TestData updateTestData(@PathVariable Long id, @RequestBody TestData testData) {
        testData.setId(id);
        testData.setUpdateTime(LocalDateTime.now());
        return testDataService.save(testData);
    }
    
    @Operation(summary = "删除测试数据")
    @DeleteMapping("/{id}")
    public void deleteTestData(@PathVariable Long id) {
        testDataService.deleteById(id);
    }
}