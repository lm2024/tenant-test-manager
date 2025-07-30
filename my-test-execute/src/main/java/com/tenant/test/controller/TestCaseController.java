package com.tenant.test.controller;

import com.tenant.test.entity.TestCase;
import com.tenant.test.service.TestCaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/test-case")
@Tag(name = "测试用例管理", description = "测试用例的增删改查接口")
public class TestCaseController {
    
    @Autowired
    private TestCaseService testCaseService;
    
    @Operation(summary = "获取所有测试用例")
    @GetMapping
    public List<TestCase> getAllTestCases() {
        return testCaseService.findAll();
    }
    
    @Operation(summary = "根据ID获取测试用例")
    @GetMapping("/{id}")
    public TestCase getTestCase(@PathVariable Long id) {
        return testCaseService.findById(id);
    }
    
    @Operation(summary = "创建测试用例")
    @PostMapping
    public TestCase createTestCase(@RequestBody TestCase testCase) {
        testCase.setCreateTime(LocalDateTime.now());
        testCase.setUpdateTime(LocalDateTime.now());
        return testCaseService.save(testCase);
    }
    
    @Operation(summary = "更新测试用例")
    @PutMapping("/{id}")
    public TestCase updateTestCase(@PathVariable Long id, @RequestBody TestCase testCase) {
        testCase.setId(id);
        testCase.setUpdateTime(LocalDateTime.now());
        return testCaseService.save(testCase);
    }
    
    @Operation(summary = "删除测试用例")
    @DeleteMapping("/{id}")
    public void deleteTestCase(@PathVariable Long id) {
        testCaseService.deleteById(id);
    }
}