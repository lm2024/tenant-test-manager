package com.tenant.controller;

import com.tenant.config.tenant.TenantSwitchHeader;
import com.tenant.entity.TestCase;
import com.tenant.repository.TestCaseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试用例管理接口
 */
@Api(tags = "测试用例管理", description = "支持多租户动态切换，所有接口需在header中传递X-Tenant-ID。")
@RestController
@RequestMapping("/api/testcase")
public class TestCaseController {

    @Autowired
    private TestCaseRepository repository;

    /**
     * 创建测试用例 - 使用请求头中的租户ID
     */
    @ApiOperation(value = "创建测试用例", notes = "需在header中传递X-Tenant-ID实现租户切换")
    @ApiResponses({
        @ApiResponse(code = 200, message = "创建成功", response = TestCase.class),
        @ApiResponse(code = 401, message = "未授权/租户ID缺失")
    })
    @PostMapping("/create")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public TestCase create(@RequestBody TestCase testCase) {
        return repository.save(testCase);
    }

    /**
     * 查询测试用例列表 - 使用请求头中的租户ID
     */
    @ApiOperation(value = "查询所有测试用例", notes = "需在header中传递X-Tenant-ID实现租户切换")
    @ApiResponses({
        @ApiResponse(code = 200, message = "查询成功", response = List.class),
        @ApiResponse(code = 401, message = "未授权/租户ID缺失")
    })
    @GetMapping("/list")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public List<TestCase> listAll(
        @ApiParam(value = "租户ID", required = true, example = "tenant001") @RequestHeader("X-Tenant-ID") String tenantId) {
        return repository.findAll();
    }

    /**
     * 根据ID查询测试用例 - 使用请求头中的租户ID
     */
    @ApiOperation("根据ID查询测试用例(请求头租户ID)")
    @GetMapping("/{id}")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public TestCase getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * 更新测试用例 - 使用请求头中的租户ID
     */
    @ApiOperation("更新测试用例(请求头租户ID)")
    @PutMapping("/update")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public TestCase update(@RequestBody TestCase testCase) {
        return repository.save(testCase);
    }

    /**
     * 删除测试用例 - 使用请求头中的租户ID
     */
    @ApiOperation("删除测试用例(请求头租户ID)")
    @DeleteMapping("/{id}")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    /**
     * 使用自定义请求头名称的示例
     */
    @ApiOperation("使用自定义请求头名称")
    @GetMapping("/custom-header")
    @TenantSwitchHeader(headerName = "Custom-Tenant-ID")
    public List<TestCase> listWithCustomHeader() {
        return repository.findAll();
    }

    /**
     * 非必需租户ID的示例（可选租户）
     */
    @ApiOperation("非必需租户ID示例")
    @GetMapping("/optional-tenant")
    @TenantSwitchHeader(headerName = "X-Tenant-ID", required = false)
    public List<TestCase> listWithOptionalTenant() {
        return repository.findAll();
    }
} 