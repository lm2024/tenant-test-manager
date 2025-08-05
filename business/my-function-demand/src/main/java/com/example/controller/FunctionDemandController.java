package com.example.controller;

import com.common.fileio.controller.FileImportExportController;
import com.common.fileio.model.ApiResponse;
import com.common.segmentid.service.SegmentIdService;
import com.example.elasticsearch.service.ElasticsearchCrudService;
import com.example.elasticsearch.service.ElasticsearchIndexService;
import com.tenant.routing.annotation.TenantSwitchHeader;
import com.tenant.routing.core.TenantContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能需求管理控制器
 * 集成各组件功能的测试接口
 * 
 * @author Kiro
 */
@Slf4j
@RestController
@RequestMapping("/api/function-demand")
@Tag(name = "功能需求管理", description = "功能需求管理相关接口")
public class FunctionDemandController {

    @Autowired(required = false)
    private RedissonClient redissonClient;

    @Autowired(required = false)
    private SegmentIdService segmentIdService;

    @Autowired(required = false)
    private ElasticsearchCrudService<Object> elasticsearchCrudService;

    @Autowired(required = false)
    private ElasticsearchIndexService elasticsearchIndexService;

    @Autowired(required = false)
    private FileImportExportController fileImportExportController;

    // ================================
    // 基础功能测试
    // ================================

    @Operation(summary = "服务健康检查", description = "检查各组件服务状态")
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            Map<String, Object> services = new HashMap<>();
            
            // Redis服务状态
            services.put("redis", redissonClient != null ? "available" : "unavailable");
            
            // 号段ID生成器状态
            services.put("segmentId", segmentIdService != null ? "available" : "unavailable");
            
            // Elasticsearch服务状态
            services.put("elasticsearch", elasticsearchCrudService != null ? "available" : "unavailable");
            
            // 文件导入导出服务状态
            services.put("fileImportExport", fileImportExportController != null ? "available" : "unavailable");
            
            result.put("status", "success");
            result.put("message", "服务健康检查完成");
            result.put("services", services);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("功能需求管理服务健康检查完成");
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "服务健康检查失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("功能需求管理服务健康检查失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取当前租户", description = "获取当前请求的租户ID")
    @GetMapping("/current-tenant")
    public ResponseEntity<Map<String, Object>> getCurrentTenant() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            String tenantId = TenantContextHolder.getTenantId();
            
            result.put("status", "success");
            result.put("message", "获取当前租户成功");
            result.put("tenantId", tenantId);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("获取当前租户，ID: {}", tenantId);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取当前租户失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("获取当前租户失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // Redis功能测试
    // ================================

    @Operation(summary = "Redis连接测试", description = "测试Redis连接状态")
    @GetMapping("/redis/test")
    public ResponseEntity<Map<String, Object>> testRedisConnection() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (redissonClient != null) {
                // 测试Redis连接
                String testKey = "function_demand:test:" + System.currentTimeMillis();
                String testValue = "test_value_" + LocalDateTime.now();
                
                redissonClient.getBucket(testKey).set(testValue);
                String retrievedValue = (String) redissonClient.getBucket(testKey).get();
                
                // 清理测试数据
                redissonClient.getBucket(testKey).delete();
                
                boolean success = testValue.equals(retrievedValue);
                
                result.put("status", "success");
                result.put("message", "Redis连接测试" + (success ? "成功" : "失败"));
                result.put("connection", "available");
                result.put("testResult", success);
                result.put("timestamp", LocalDateTime.now());
                
                log.info("Redis连接测试完成，结果: {}", success);
                return ResponseEntity.ok(result);
                
            } else {
                result.put("status", "error");
                result.put("message", "Redis客户端未初始化");
                result.put("connection", "unavailable");
                result.put("timestamp", LocalDateTime.now());
                
                log.warn("Redis客户端未初始化");
                return ResponseEntity.status(500).body(result);
            }
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "Redis连接测试失败: " + e.getMessage());
            result.put("connection", "error");
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis连接测试失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // 号段ID生成器功能测试
    // ================================

    @Operation(summary = "生成号段ID", description = "测试号段ID生成功能")
    @GetMapping("/segment-id/generate")
    public ResponseEntity<Map<String, Object>> generateSegmentId(
            @Parameter(description = "租户ID") @RequestParam(defaultValue = "tenant001") String tenantId,
            @Parameter(description = "业务类型") @RequestParam(defaultValue = "function") String bizType) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (segmentIdService != null) {
                String id = segmentIdService.generateId(tenantId, bizType);
                
                result.put("status", "success");
                result.put("message", "号段ID生成成功");
                result.put("tenantId", tenantId);
                result.put("bizType", bizType);
                result.put("generatedId", id);
                result.put("timestamp", LocalDateTime.now());
                
                log.info("号段ID生成成功，租户: {}, 业务类型: {}, ID: {}", tenantId, bizType, id);
                return ResponseEntity.ok(result);
                
            } else {
                result.put("status", "error");
                result.put("message", "号段ID生成器未初始化");
                result.put("timestamp", LocalDateTime.now());
                
                log.warn("号段ID生成器未初始化");
                return ResponseEntity.status(500).body(result);
            }
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "号段ID生成失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("号段ID生成失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // Elasticsearch功能测试
    // ================================

    @Operation(summary = "Elasticsearch连接测试", description = "测试Elasticsearch连接状态")
    @GetMapping("/elasticsearch/test")
    public ResponseEntity<Map<String, Object>> testElasticsearchConnection() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (elasticsearchIndexService != null) {
                // 获取集群健康状态
                Map<String, Object> health = elasticsearchIndexService.getClusterHealth();
                
                result.put("status", "success");
                result.put("message", "Elasticsearch连接测试成功");
                result.put("connection", "available");
                result.put("clusterHealth", health);
                result.put("timestamp", LocalDateTime.now());
                
                log.info("Elasticsearch连接测试成功");
                return ResponseEntity.ok(result);
                
            } else {
                result.put("status", "error");
                result.put("message", "Elasticsearch服务未初始化");
                result.put("connection", "unavailable");
                result.put("timestamp", LocalDateTime.now());
                
                log.warn("Elasticsearch服务未初始化");
                return ResponseEntity.status(500).body(result);
            }
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "Elasticsearch连接测试失败: " + e.getMessage());
            result.put("connection", "error");
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Elasticsearch连接测试失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // 文件导入导出功能测试
    // ================================

    @Operation(summary = "文件导入导出服务状态", description = "检查文件导入导出服务状态")
    @GetMapping("/file-io/status")
    public ResponseEntity<Map<String, Object>> getFileIoStatus() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (fileImportExportController != null) {
                result.put("status", "success");
                result.put("message", "文件导入导出服务可用");
                result.put("service", "available");
                result.put("timestamp", LocalDateTime.now());
                
                log.info("文件导入导出服务状态检查完成");
                return ResponseEntity.ok(result);
                
            } else {
                result.put("status", "error");
                result.put("message", "文件导入导出服务未初始化");
                result.put("service", "unavailable");
                result.put("timestamp", LocalDateTime.now());
                
                log.warn("文件导入导出服务未初始化");
                return ResponseEntity.status(500).body(result);
            }
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "文件导入导出服务状态检查失败: " + e.getMessage());
            result.put("service", "error");
            result.put("timestamp", LocalDateTime.now());
            
            log.error("文件导入导出服务状态检查失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // 综合功能测试
    // ================================

    @Operation(summary = "综合功能测试", description = "测试所有组件的综合功能")
    @GetMapping("/integration-test")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public ResponseEntity<Map<String, Object>> integrationTest(
            @Parameter(description = "租户ID") @RequestHeader(value = "X-Tenant-ID", required = false) String tenantId) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            Map<String, Object> testResults = new HashMap<>();
            
            // 1. 租户路由测试
            String currentTenant = TenantContextHolder.getTenantId();
            testResults.put("tenantRouting", currentTenant != null ? "success" : "failed");
            
            // 2. Redis测试
            if (redissonClient != null) {
                String testKey = "integration:test:" + System.currentTimeMillis();
                redissonClient.getBucket(testKey).set("test_value");
                boolean redisSuccess = redissonClient.getBucket(testKey).isExists();
                redissonClient.getBucket(testKey).delete();
                testResults.put("redis", redisSuccess ? "success" : "failed");
            } else {
                testResults.put("redis", "unavailable");
            }
            
            // 3. 号段ID生成测试
            if (segmentIdService != null) {
                try {
                    String id = segmentIdService.generateId(currentTenant != null ? currentTenant : "default", "integration");
                    testResults.put("segmentId", "success");
                    testResults.put("generatedId", id);
                } catch (Exception e) {
                    testResults.put("segmentId", "failed");
                }
            } else {
                testResults.put("segmentId", "unavailable");
            }
            
            // 4. Elasticsearch测试
            if (elasticsearchIndexService != null) {
                try {
                    Map<String, Object> health = elasticsearchIndexService.getClusterHealth();
                    testResults.put("elasticsearch", "success");
                    testResults.put("clusterHealth", health);
                } catch (Exception e) {
                    testResults.put("elasticsearch", "failed");
                }
            } else {
                testResults.put("elasticsearch", "unavailable");
            }
            
            // 5. 文件导入导出测试
            testResults.put("fileImportExport", fileImportExportController != null ? "available" : "unavailable");
            
            result.put("status", "success");
            result.put("message", "综合功能测试完成");
            result.put("tenantId", currentTenant);
            result.put("testResults", testResults);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("综合功能测试完成，租户: {}", currentTenant);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "综合功能测试失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("综合功能测试失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }
} 