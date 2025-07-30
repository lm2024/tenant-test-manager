package com.tenant.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test-execute")
@Tag(name = "测试执行管理", description = "测试执行相关接口")
public class TestExecuteController {
    
    @Operation(summary = "执行测试")
    @PostMapping("/run")
    public Map<String, Object> runTest(@RequestParam String testCaseId) {
        Map<String, Object> result = new HashMap<>();
        result.put("testCaseId", testCaseId);
        result.put("status", "RUNNING");
        result.put("startTime", LocalDateTime.now());
        result.put("message", "测试开始执行");
        return result;
    }
    
    @Operation(summary = "获取测试执行状态")
    @GetMapping("/status/{testCaseId}")
    public Map<String, Object> getTestStatus(@PathVariable String testCaseId) {
        Map<String, Object> result = new HashMap<>();
        result.put("testCaseId", testCaseId);
        result.put("status", "COMPLETED");
        result.put("result", "PASS");
        result.put("endTime", LocalDateTime.now());
        return result;
    }
    
    @Operation(summary = "停止测试执行")
    @PostMapping("/stop/{testCaseId}")
    public Map<String, Object> stopTest(@PathVariable String testCaseId) {
        Map<String, Object> result = new HashMap<>();
        result.put("testCaseId", testCaseId);
        result.put("status", "STOPPED");
        result.put("message", "测试已停止");
        return result;
    }
}