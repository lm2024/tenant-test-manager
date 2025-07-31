package com.tenant.controller.tenant;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import com.tenant.routing.service.TenantDataSourceService;
import com.tenant.routing.repository.TenantDbInfoRepository;
import com.tenant.routing.entity.TenantDbInfo;
import com.tenant.service.DbInitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * 租户库信息管理接口。
 * 提供租户库的增删改查及自动建库建表功能。
 */
@Tag(name = "租户库管理", description = "管理租户数据库信息，支持自动租户路由")
@RestController
@RequestMapping("/api/tenant")
public class TenantDbInfoController {

    @Autowired
    private TenantDbInfoRepository repository;
    @Autowired
    private DbInitService dbInitService;
    @Autowired
    private TenantDataSourceService tenantDataSourceService;

    /**
     * 创建租户库并自动建表。
     * @param info 租户库信息
     * @return 保存后的租户库信息
     */
    @Operation(summary = "创建租户库并自动建表", description = "创建新租户数据库，自动建表。无需租户切换。")
    @PostMapping("/create")
    @TenantSwitch("tenant_center")
    public Map<String, Object> create(
        @Parameter(description = "租户库信息", required = true) @RequestBody TenantDbInfo info) {
        try {
            // 读取模板SQL文件
            ClassPathResource resource = new ClassPathResource("db/template.sql");
            String templateSql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            // 保存租户信息到中央数据库
            TenantDbInfo savedInfo = repository.save(info);
            // 创建数据库和表
            dbInitService.createDatabaseAndTables(info.getDbName(), info.getDbUser(), info.getDbPassword(), templateSql);
            
            // 创建数据源
            tenantDataSourceService.createDataSourceIfNotExists(
                info.getTenantId(), 
                info.getDbUrl(), 
                info.getDbUser(), 
                info.getDbPassword()
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "租户库创建成功");
            response.put("data", savedInfo);
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }

    /**
     * 查询所有租户库信息。
     * @return 租户库信息列表
     */
    @Operation(summary = "查询所有租户库")
    @GetMapping("/list")
    @TenantSwitch("tenant_center")
    public Map<String, Object> list() {
        try {
            List<TenantDbInfo> list = repository.findAll();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", list);
            response.put("count", list.size());
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }

    /**
     * 根据ID查询租户库信息。
     * @param id 租户库ID
     * @return 租户库信息
     */
    @Operation(summary = "根据ID查询租户库")
    @GetMapping("/{id}")
    @TenantSwitch("tenant_center")
    public Map<String, Object> getById(@Parameter(description = "租户库ID") @PathVariable Long id) {
        try {
            TenantDbInfo info = repository.findById(id);
            Map<String, Object> response = new HashMap<>();
            if (info != null) {
                response.put("success", true);
                response.put("data", info);
            } else {
                response.put("success", false);
                response.put("error", "租户库不存在");
            }
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }

    /**
     * 根据租户ID查询租户库信息。
     * @param tenantId 租户ID
     * @return 租户库信息
     */
    @Operation(summary = "根据租户ID查询租户库")
    @GetMapping("/by-tenant/{tenantId}")
    @TenantSwitch("tenant_center")
    public Map<String, Object> getByTenantId(@Parameter(description = "租户ID") @PathVariable String tenantId) {
        try {
            TenantDbInfo info = repository.findByTenantId(tenantId);
            Map<String, Object> response = new HashMap<>();
            if (info != null) {
                response.put("success", true);
                response.put("data", info);
            } else {
                response.put("success", false);
                response.put("error", "租户库不存在");
            }
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }

    /**
     * 更新租户库信息。
     * @param info 租户库信息
     * @return 更新结果
     */
    @Operation(summary = "更新租户库")
    @PostMapping("/update")
    @TenantSwitch("tenant_center")
    public Map<String, Object> update(@Parameter(description = "租户库信息") @RequestBody TenantDbInfo info) {
        try {
            TenantDbInfo updatedInfo = repository.save(info);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "租户库更新成功");
            response.put("data", updatedInfo);
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }

    /**
     * 删除租户库信息。
     * @param id 租户库ID
     * @return 删除结果
     */
    @Operation(summary = "删除租户库")
    @DeleteMapping("/delete/{id}")
    @TenantSwitch("tenant_center")
    public Map<String, Object> delete(@Parameter(description = "租户库ID") @PathVariable Long id) {
        try {
            repository.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "租户库删除成功");
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }

    /**
     * 获取当前租户信息。
     * @return 当前租户信息
     */
    @Operation(summary = "获取当前租户信息")
    @GetMapping("/current")
    @TenantSwitch("tenant_center")
    public Map<String, Object> getCurrentTenant() {
        try {
            String tenantId = TenantContextHolder.getTenantId();
            if (tenantId == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("error", "当前无租户上下文");
                return error;
            }
            
            TenantDbInfo info = repository.findByTenantId(tenantId);
            Map<String, Object> response = new HashMap<>();
            if (info != null) {
                response.put("success", true);
                response.put("data", info);
            } else {
                response.put("success", false);
                response.put("error", "当前租户不存在");
            }
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }

    /**
     * 检查租户是否存在。
     * @param tenantId 租户ID
     * @return 检查结果
     */
    @Operation(summary = "检查租户是否存在")
    @GetMapping("/exists/{tenantId}")
    @TenantSwitch("tenant_center")
    public Map<String, Object> exists(@Parameter(description = "租户ID") @PathVariable String tenantId) {
        try {
            TenantDbInfo info = repository.findByTenantId(tenantId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("exists", info != null);
            response.put("data", info);
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }

    /**
     * 获取租户统计信息。
     * @return 统计信息
     */
    @Operation(summary = "获取租户统计信息")
    @GetMapping("/stats")
    @TenantSwitch("tenant_center")
    public Map<String, Object> getStats() {
        try {
            List<TenantDbInfo> allTenants = repository.findAll();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("totalTenants", allTenants.size());
            response.put("activeTenants", allTenants.stream().filter(t -> t.getTenantId() != null).count());
            response.put("data", allTenants);
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }
} 