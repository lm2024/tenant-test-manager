package com.tenant.test.controller;

import com.tenant.es.service.ElasticsearchCrudService;
import com.tenant.fileio.dto.TaskProgressDTO;
import com.tenant.fileio.service.FileImportExportService;
import com.tenant.idgen.enums.IdType;
import com.tenant.idgen.service.SegmentIdGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Starter组件测试控制器
 * 
 * @author system
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/starter-test")
@Tag(name = "Starter组件测试", description = "测试各个starter组件功能")
@Slf4j
public class StarterTestController {
    
    @Autowired(required = false)
    private SegmentIdGeneratorService idGeneratorService;
    
    @Autowired(required = false)
    private FileImportExportService fileImportExportService;
    
    @Autowired(required = false)
    private ElasticsearchCrudService elasticsearchCrudService;
    
    @Operation(summary = "测试ID生成服务")
    @GetMapping("/id-generator/test")
    public Map<String, Object> testIdGenerator(@RequestParam String tenantId,
                                              @RequestParam String bizType,
                                              @RequestParam IdType idType,
                                              @RequestParam(defaultValue = "1") int count) {
        log.info("[StarterTestController] 测试ID生成服务，租户: {}, 业务类型: {}, ID类型: {}, 数量: {}", 
                tenantId, bizType, idType, count);
        
        Map<String, Object> result = new HashMap<>();
        
        if (idGeneratorService == null) {
            result.put("error", "ID生成服务未启用");
            return result;
        }
        
        try {
            if (count == 1) {
                String id = idGeneratorService.generateId(tenantId, bizType, idType);
                result.put("id", id);
            } else {
                List<String> ids = idGeneratorService.batchGenerateId(tenantId, bizType, idType, count);
                result.put("ids", ids);
            }
            result.put("success", true);
        } catch (Exception e) {
            log.error("[StarterTestController] ID生成测试失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
    
    @Operation(summary = "初始化ID序列")
    @PostMapping("/id-generator/init-sequence")
    public Map<String, Object> initSequence(@RequestParam String tenantId,
                                           @RequestParam String bizType,
                                           @RequestParam IdType idType,
                                           @RequestParam(defaultValue = "1000") int step,
                                           @RequestParam(defaultValue = "8") int length,
                                           @RequestParam(required = false) String prefix,
                                           @RequestParam(required = false) String suffix) {
        log.info("[StarterTestController] 初始化ID序列，租户: {}, 业务类型: {}", tenantId, bizType);
        
        Map<String, Object> result = new HashMap<>();
        
        if (idGeneratorService == null) {
            result.put("error", "ID生成服务未启用");
            return result;
        }
        
        try {
            boolean success = idGeneratorService.initSequence(tenantId, bizType, idType, step, length, prefix, suffix);
            result.put("success", success);
        } catch (Exception e) {
            log.error("[StarterTestController] 序列初始化失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
    
    @Operation(summary = "测试文件导入导出服务状态")
    @GetMapping("/file-io/status")
    public Map<String, Object> testFileImportExportStatus() {
        log.info("[StarterTestController] 测试文件导入导出服务状态");
        
        Map<String, Object> result = new HashMap<>();
        
        if (fileImportExportService == null) {
            result.put("enabled", false);
            result.put("message", "文件导入导出服务未启用");
        } else {
            result.put("enabled", true);
            result.put("message", "文件导入导出服务已启用");
        }
        
        return result;
    }
    
    @Operation(summary = "查询任务进度")
    @GetMapping("/file-io/progress/{taskId}")
    public Map<String, Object> getTaskProgress(@PathVariable String taskId) {
        log.info("[StarterTestController] 查询任务进度: {}", taskId);
        
        Map<String, Object> result = new HashMap<>();
        
        if (fileImportExportService == null) {
            result.put("error", "文件导入导出服务未启用");
            return result;
        }
        
        try {
            TaskProgressDTO progress = fileImportExportService.getTaskProgress(taskId);
            result.put("progress", progress);
            result.put("success", true);
        } catch (Exception e) {
            log.error("[StarterTestController] 查询任务进度失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
    
    @Operation(summary = "测试Elasticsearch服务")
    @GetMapping("/elasticsearch/test")
    public Map<String, Object> testElasticsearch(@RequestParam String indexName) {
        log.info("[StarterTestController] 测试Elasticsearch服务，索引: {}", indexName);
        
        Map<String, Object> result = new HashMap<>();
        
        if (elasticsearchCrudService == null) {
            result.put("error", "Elasticsearch服务未启用");
            return result;
        }
        
        try {
            // 测试索引是否存在
            boolean exists = elasticsearchCrudService.indexExists(indexName);
            result.put("indexExists", exists);
            
            // 如果不存在，尝试创建
            if (!exists) {
                Map<String, Object> mapping = new HashMap<>();
                Map<String, Object> properties = new HashMap<>();
                
                Map<String, Object> idField = new HashMap<>();
                idField.put("type", "keyword");
                properties.put("id", idField);
                
                Map<String, Object> nameField = new HashMap<>();
                nameField.put("type", "keyword");
                properties.put("name", nameField);
                
                Map<String, Object> createTimeField = new HashMap<>();
                createTimeField.put("type", "date");
                properties.put("createTime", createTimeField);
                
                mapping.put("properties", properties);
                
                boolean created = elasticsearchCrudService.createIndex(indexName, mapping);
                result.put("indexCreated", created);
            }
            
            result.put("success", true);
        } catch (Exception e) {
            log.error("[StarterTestController] Elasticsearch测试失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
    
    @Operation(summary = "测试数据一致性检查")
    @GetMapping("/elasticsearch/consistency-check")
    public Map<String, Object> testConsistencyCheck(@RequestParam String tenantId,
                                                   @RequestParam String tableName,
                                                   @RequestParam String indexName) {
        log.info("[StarterTestController] 测试数据一致性检查，租户: {}, 表: {}, 索引: {}", 
                tenantId, tableName, indexName);
        
        Map<String, Object> result = new HashMap<>();
        
        if (elasticsearchCrudService == null) {
            result.put("error", "Elasticsearch服务未启用");
            return result;
        }
        
        try {
            Map<String, Object> checkResult = elasticsearchCrudService.checkDataConsistency(tenantId, tableName, indexName);
            result.put("checkResult", checkResult);
            result.put("success", true);
        } catch (Exception e) {
            log.error("[StarterTestController] 数据一致性检查失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
    
    @Operation(summary = "获取所有Starter服务状态")
    @GetMapping("/status")
    public Map<String, Object> getAllStarterStatus() {
        log.info("[StarterTestController] 获取所有Starter服务状态");
        
        Map<String, Object> result = new HashMap<>();
        
        // ID生成服务状态
        result.put("idGeneratorService", idGeneratorService != null ? "已启用" : "未启用");
        
        // 文件导入导出服务状态
        result.put("fileImportExportService", fileImportExportService != null ? "已启用" : "未启用");
        
        // Elasticsearch服务状态
        result.put("elasticsearchCrudService", elasticsearchCrudService != null ? "已启用" : "未启用");
        
        return result;
    }
}