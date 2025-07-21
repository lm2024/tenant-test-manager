package com.tenant.controller;

import com.tenant.config.tenant.TenantSwitchHeader;
import com.tenant.entity.TestCase;
import com.tenant.repository.TestCaseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日志测试控制器
 * 用于验证租户信息在SQL日志中的显示
 */
@Api(tags = "日志测试", description = "用于验证租户信息在SQL日志中的显示，需在header中传递X-Tenant-ID。")
@RestController
@RequestMapping("/api/log-test")
public class LogTestController {

    private static final Logger logger = LoggerFactory.getLogger(LogTestController.class);

    @Autowired
    private TestCaseRepository repository;

    /**
     * 测试租户日志 - 使用注解方式
     */
    @ApiOperation(value = "测试租户日志(注解方式)", notes = "需在header中传递X-Tenant-ID实现租户切换")
    @ApiResponses({
        @ApiResponse(code = 200, message = "查询成功", response = List.class),
        @ApiResponse(code = 401, message = "未授权/租户ID缺失")
    })
    @GetMapping("/test-annotation")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public List<TestCase> testAnnotation(
        @ApiParam(value = "租户ID", required = true, example = "tenant001") @RequestHeader("X-Tenant-ID") String tenantId) {
        logger.info("开始查询测试用例列表");
        List<TestCase> result = repository.findAll();
        logger.info("查询完成，共找到 {} 条记录", result.size());
        return result;
    }

    /**
     * 测试租户日志 - 使用全局拦截器
     */
    @ApiOperation("测试租户日志(全局拦截器)")
    @GetMapping("/test-global")
    public List<TestCase> testGlobal() {
        logger.info("开始查询测试用例列表(全局拦截器)");
        List<TestCase> result = repository.findAll();
        logger.info("查询完成，共找到 {} 条记录", result.size());
        return result;
    }

    /**
     * 测试创建数据
     */
    @ApiOperation("测试创建数据")
    @PostMapping("/create")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public TestCase create(@RequestBody TestCase testCase) {
        logger.info("开始创建测试用例: {}", testCase.getTitle());
        TestCase result = repository.save(testCase);
        logger.info("创建完成，ID: {}", result.getId());
        return result;
    }

    /**
     * 测试更新数据
     */
    @ApiOperation("测试更新数据")
    @PutMapping("/update")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public TestCase update(@RequestBody TestCase testCase) {
        logger.info("开始更新测试用例: {}", testCase.getTitle());
        TestCase result = repository.save(testCase);
        logger.info("更新完成，ID: {}", result.getId());
        return result;
    }

    /**
     * 测试删除数据
     */
    @ApiOperation("测试删除数据")
    @DeleteMapping("/{id}")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public void delete(@PathVariable Long id) {
        logger.info("开始删除测试用例，ID: {}", id);
        repository.deleteById(id);
        logger.info("删除完成，ID: {}", id);
    }

    /**
     * 测试复杂查询
     */
    @ApiOperation("测试复杂查询")
    @GetMapping("/complex-query")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public List<TestCase> complexQuery() {
        logger.info("开始执行复杂查询");
        
        // 执行多个查询操作来测试日志
        long count = repository.count();
        logger.info("总记录数: {}", count);
        
        List<TestCase> allCases = repository.findAll();
        logger.info("查询所有记录: {}", allCases.size());
        
        // 模拟一些复杂的查询操作
        if (!allCases.isEmpty()) {
            TestCase firstCase = allCases.get(0);
            logger.info("第一条记录: {}", firstCase.getTitle());
        }
        
        return allCases;
    }
} 