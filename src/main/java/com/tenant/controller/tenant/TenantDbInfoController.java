package com.tenant.controller.tenant;

import com.tenant.config.dynamic.DataSourceContextHolder;
import com.tenant.config.dynamic.DynamicDataSourceManager;
import com.tenant.controller.BatchSqlRequest;
import com.tenant.entity.TenantDbInfo;
import com.tenant.repository.TenantDbInfoRepository;
import com.tenant.service.DbInitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * 租户库信息管理接口。
 * 提供租户库的增删改查及自动建库建表功能。
 */
@Api(tags = "租户库管理", description = "管理租户数据库信息，部分接口需在header中传递X-Tenant-ID。")
@RestController
@RequestMapping("/api/tenant")
public class TenantDbInfoController {

    @Autowired
    private TenantDbInfoRepository repository;
    @Autowired
    private DbInitService dbInitService;
    @Autowired
    private DynamicDataSourceManager dynamicDataSourceManager;

    /**
     * 创建租户库信息并自动建库建表。
     * @param info 租户库信息
     * @return 保存后的租户库信息
     */
    @ApiOperation(value = "创建租户库并自动建表", notes = "创建新租户数据库，自动建表。无需租户切换。")
    @ApiResponses({
        @ApiResponse(code = 200, message = "创建成功", response = TenantDbInfo.class),
        @ApiResponse(code = 400, message = "参数错误")
    })
    @PostMapping("/create")
    public TenantDbInfo create(
        @ApiParam(value = "租户库信息", required = true) @RequestBody TenantDbInfo info) throws Exception {
        String templateSql = new String(Files.readAllBytes(Paths.get("src/main/resources/db/template.sql")));
        dbInitService.createDatabaseAndTables(info.getDbName(), info.getDbUser(), info.getDbPassword(), templateSql);
        return repository.save(info);
    }

    /**
     * 查询所有租户库信息。
     * @return 租户库信息列表
     */
    @ApiOperation("查询所有租户库")
    @GetMapping("/list")
    public List<TenantDbInfo> list() {
        return repository.findAll();
    }

    /**
     * 更新租户库信息。
     * @param info 租户库信息
     * @return 更新后的租户库信息
     */
    @ApiOperation("更新租户库")
    @PostMapping("/update")
    public TenantDbInfo update(@RequestBody TenantDbInfo info) {
        return repository.save(info);
    }

    /**
     * 删除租户库信息。
     * @param id 租户库ID
     */
    @ApiOperation("删除租户库")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    /**
     * 批量执行SQL。
     * @param request 批量执行SQL请求
     * @return 批量执行结果
     */
    @ApiOperation("批量执行SQL")
    @PostMapping("/batch/execute-sql")
    public Map<String, Object> batchExecuteSql(@RequestBody BatchSqlRequest request) {
        Map<String, Object> result = new HashMap<>();
        for (String tenantId : request.getTenantIds()) {
            try {
                // 1. 查租户信息
                TenantDbInfo dbInfo = repository.findByTenantId(tenantId)
                    .orElseThrow(() -> new RuntimeException("租户不存在: " + tenantId));
                // 2. 注册并切换数据源
                DataSource ds = dynamicDataSourceManager.getOrCreateDataSource(dbInfo);
                DataSourceContextHolder.set(tenantId);
                try (Connection conn = DataSourceUtils.getConnection(ds);
                     Statement stmt = conn.createStatement()) {
                    for (String sql : request.getSqlList()) {
                        stmt.execute(sql);
                    }
                    result.put(tenantId, "success");
                } catch (Exception e) {
                    result.put(tenantId, "error: " + e.getMessage());
                } finally {
                    DataSourceContextHolder.clear();
                }
            } catch (Exception e) {
                result.put(tenantId, "error: " + e.getMessage());
            }
        }
        return result;
    }
} 