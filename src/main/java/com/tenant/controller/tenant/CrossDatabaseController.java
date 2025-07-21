package com.tenant.controller.tenant;

import com.tenant.service.CrossDatabaseQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Api(tags = "跨库查询管理")
@RestController
@RequestMapping("/api/tenant/cross-database")
public class CrossDatabaseController {

    @Autowired
    private CrossDatabaseQueryService crossDatabaseQueryService;

    @ApiOperation("跨库统计查询")
    @PostMapping("/aggregate")
    public ResponseEntity<?> aggregateAcrossTenants(@RequestParam List<String> tenantIds,
                                                  @RequestParam String sql) {
        try {
            Map<String, Object> result = crossDatabaseQueryService.aggregateAcrossTenants(tenantIds, sql);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("分布式查询")
    @PostMapping("/distributed")
    public ResponseEntity<?> distributedQuery(@RequestParam List<String> tenantIds,
                                           @RequestParam String sql) {
        try {
            List<Object> result = crossDatabaseQueryService.distributedQuery(tenantIds, sql);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("数据聚合统计")
    @GetMapping("/aggregate-data")
    public ResponseEntity<?> aggregateData(@RequestParam List<String> tenantIds,
                                         @RequestParam String aggregationType) {
        try {
            Object result = crossDatabaseQueryService.aggregateData(tenantIds, aggregationType);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取所有租户的待办列表")
    @GetMapping("/todo-list")
    public ResponseEntity<?> getTodoListAcrossTenants(@RequestParam List<String> tenantIds) {
        try {
            Map<String, Object> result = crossDatabaseQueryService.getTodoListAcrossTenants(tenantIds);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @ApiOperation("获取所有租户的需求列表")
    @GetMapping("/requirement-list")
    public ResponseEntity<?> getRequirementListAcrossTenants(@RequestParam List<String> tenantIds) {
        try {
            Map<String, Object> result = crossDatabaseQueryService.getRequirementListAcrossTenants(tenantIds);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 