package com.tenant.controller.tenant;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 示例控制器 - 展示租户切换功能
 */
@Api(tags = "示例接口", description = "演示多租户动态切换，所有接口需在header中传递X-Tenant-ID。")
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    private TestCaseRepository repository;

    /**
     * 使用TenantSwitchHeader注解的示例
     */
    @ApiOperation("使用TenantSwitchHeader注解")
    @GetMapping("/with-annotation")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public Map<String, Object> withAnnotation() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "使用TenantSwitchHeader注解成功");
        result.put("data", repository.findAll());
        return result;
    }

    /**
     * 使用自定义请求头名称的示例
     */
    @ApiOperation("使用自定义请求头名称")
    @GetMapping("/custom-header")
    @TenantSwitchHeader(headerName = "Custom-Tenant-ID")
    public Map<String, Object> withCustomHeader() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "使用自定义请求头成功");
        result.put("data", repository.findAll());
        return result;
    }

    /**
     * 非必需租户ID的示例
     */
    @ApiOperation("非必需租户ID示例")
    @GetMapping("/optional-tenant")
    @TenantSwitchHeader(headerName = "X-Tenant-ID", required = false)
    public Map<String, Object> withOptionalTenant() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "可选租户ID示例");
        result.put("data", repository.findAll());
        return result;
    }

    /**
     * 依赖全局拦截器的示例（需要启用全局拦截器）
     */
    @ApiOperation("依赖全局拦截器的示例")
    @GetMapping("/global-interceptor")
    public Map<String, Object> withGlobalInterceptor() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "全局拦截器示例");
        result.put("data", repository.findAll());
        return result;
    }

    /**
     * 创建测试用例
     */
    @ApiOperation("创建测试用例")
    @PostMapping("/create")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public TestCase create(@RequestBody TestCase testCase) {
        return repository.save(testCase);
    }

    /**
     * 查询测试用例列表
     */
    @ApiOperation(value = "示例查询", notes = "需在header中传递X-Tenant-ID实现租户切换")
    @ApiResponses({
        @ApiResponse(code = 200, message = "查询成功", response = List.class),
        @ApiResponse(code = 401, message = "未授权/租户ID缺失")
    })
    @GetMapping("/list")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public List<?> list(
        @ApiParam(value = "租户ID", required = true, example = "tenant001") @RequestHeader("X-Tenant-ID") String tenantId) {
        return repository.findAll();
    }

    /**
     * 根据ID查询测试用例
     */
    @ApiOperation("根据ID查询测试用例")
    @GetMapping("/{id}")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public TestCase getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * 更新测试用例
     */
    @ApiOperation("更新测试用例")
    @PutMapping("/update")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public TestCase update(@RequestBody TestCase testCase) {
        return repository.save(testCase);
    }

    /**
     * 删除测试用例
     */
    @ApiOperation("删除测试用例")
    @DeleteMapping("/{id}")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
} 