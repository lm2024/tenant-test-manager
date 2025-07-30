package org.example.controller;

import com.example.elasticsearch.service.ElasticsearchCrudService;
import com.example.elasticsearch.service.ElasticsearchIndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.TestDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Elasticsearch 测试控制器
 * 
 * @author Kiro
 */
@Slf4j
@RestController
@RequestMapping("/api/elasticsearch/test")
@Tag(name = "Elasticsearch测试", description = "Elasticsearch CRUD功能测试接口")
public class ElasticsearchTestController {

    @Autowired(required = false)
    private ElasticsearchCrudService<TestDocument> crudService;

    @Autowired(required = false)
    private ElasticsearchIndexService indexService;

    @Operation(summary = "测试连接", description = "测试Elasticsearch连接状态")
    @GetMapping("/connection")
    public ResponseEntity<Map<String, Object>> testConnection() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (crudService != null && indexService != null) {
                result.put("status", "success");
                result.put("message", "Elasticsearch连接正常");
                result.put("timestamp", LocalDateTime.now());
                
                // 获取集群健康状态
                Map<String, Object> health = indexService.getClusterHealth();
                result.put("clusterHealth", health);
                
                log.info("Elasticsearch连接测试成功");
                return ResponseEntity.ok(result);
            } else {
                result.put("status", "error");
                result.put("message", "Elasticsearch服务未正确初始化");
                result.put("timestamp", LocalDateTime.now());
                
                log.warn("Elasticsearch服务未正确初始化");
                return ResponseEntity.status(500).body(result);
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "连接测试失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Elasticsearch连接测试失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "保存测试文档", description = "保存一个测试文档到Elasticsearch")
    @PostMapping("/document")
    public ResponseEntity<Map<String, Object>> saveDocument(@RequestBody TestDocument document) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (crudService == null) {
                result.put("status", "error");
                result.put("message", "CRUD服务未初始化");
                return ResponseEntity.status(500).body(result);
            }

            // 设置默认值
            if (document.getId() == null) {
                document.setId(UUID.randomUUID().toString());
            }
            if (document.getCreateTime() == null) {
                document.setCreateTime(LocalDateTime.now());
            }
            if (document.getUpdateTime() == null) {
                document.setUpdateTime(LocalDateTime.now());
            }
            if (document.getVersion() == null) {
                document.setVersion(1L);
            }
            if (document.getActive() == null) {
                document.setActive(true);
            }

            TestDocument savedDocument = crudService.save(document);
            
            result.put("status", "success");
            result.put("message", "文档保存成功");
            result.put("data", savedDocument);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("测试文档保存成功，ID: {}", savedDocument.getId());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "保存文档失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("保存测试文档失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "批量保存测试文档", description = "批量保存多个测试文档到Elasticsearch")
    @PostMapping("/documents/batch")
    public ResponseEntity<Map<String, Object>> saveDocuments(@RequestBody List<TestDocument> documents) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (crudService == null) {
                result.put("status", "error");
                result.put("message", "CRUD服务未初始化");
                return ResponseEntity.status(500).body(result);
            }

            // 为每个文档设置默认值
            for (TestDocument document : documents) {
                if (document.getId() == null) {
                    document.setId(UUID.randomUUID().toString());
                }
                if (document.getCreateTime() == null) {
                    document.setCreateTime(LocalDateTime.now());
                }
                if (document.getUpdateTime() == null) {
                    document.setUpdateTime(LocalDateTime.now());
                }
                if (document.getVersion() == null) {
                    document.setVersion(1L);
                }
                if (document.getActive() == null) {
                    document.setActive(true);
                }
            }

            List<TestDocument> savedDocuments = crudService.saveAll(documents);
            
            result.put("status", "success");
            result.put("message", "批量保存成功");
            result.put("count", savedDocuments.size());
            result.put("data", savedDocuments);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("批量保存测试文档成功，数量: {}", savedDocuments.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "批量保存失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("批量保存测试文档失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "根据ID查询文档", description = "根据ID查询测试文档")
    @GetMapping("/document/{id}")
    public ResponseEntity<Map<String, Object>> getDocument(
            @Parameter(description = "文档ID") @PathVariable String id) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (crudService == null) {
                result.put("status", "error");
                result.put("message", "CRUD服务未初始化");
                return ResponseEntity.status(500).body(result);
            }

            Optional<TestDocument> document = crudService.findById(id);
            
            if (document.isPresent()) {
                result.put("status", "success");
                result.put("message", "查询成功");
                result.put("data", document.get());
            } else {
                result.put("status", "not_found");
                result.put("message", "文档不存在");
                result.put("data", null);
            }
            
            result.put("timestamp", LocalDateTime.now());
            
            log.info("查询测试文档，ID: {}, 结果: {}", id, document.isPresent() ? "找到" : "未找到");
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "查询失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("查询测试文档失败，ID: {}", id, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "分页查询文档", description = "分页查询所有测试文档")
    @GetMapping("/documents")
    public ResponseEntity<Map<String, Object>> getDocuments(
            @Parameter(description = "页码，从0开始") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (crudService == null) {
                result.put("status", "error");
                result.put("message", "CRUD服务未初始化");
                return ResponseEntity.status(500).body(result);
            }

            Pageable pageable = PageRequest.of(page, size);
            Page<TestDocument> documents = crudService.findAll(pageable);
            
            result.put("status", "success");
            result.put("message", "查询成功");
            result.put("data", documents.getContent());
            result.put("totalElements", documents.getTotalElements());
            result.put("totalPages", documents.getTotalPages());
            result.put("currentPage", documents.getNumber());
            result.put("pageSize", documents.getSize());
            result.put("timestamp", LocalDateTime.now());
            
            log.info("分页查询测试文档，页码: {}, 大小: {}, 总数: {}", page, size, documents.getTotalElements());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "查询失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("分页查询测试文档失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "搜索文档", description = "根据关键词搜索测试文档")
    @GetMapping("/documents/search")
    public ResponseEntity<Map<String, Object>> searchDocuments(
            @Parameter(description = "搜索关键词") @RequestParam String keyword,
            @Parameter(description = "页码，从0开始") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (crudService == null) {
                result.put("status", "error");
                result.put("message", "CRUD服务未初始化");
                return ResponseEntity.status(500).body(result);
            }

            Pageable pageable = PageRequest.of(page, size);
            Page<TestDocument> documents = crudService.search(keyword, pageable);
            
            result.put("status", "success");
            result.put("message", "搜索成功");
            result.put("keyword", keyword);
            result.put("data", documents.getContent());
            result.put("totalElements", documents.getTotalElements());
            result.put("totalPages", documents.getTotalPages());
            result.put("currentPage", documents.getNumber());
            result.put("pageSize", documents.getSize());
            result.put("timestamp", LocalDateTime.now());
            
            log.info("搜索测试文档，关键词: {}, 页码: {}, 大小: {}, 结果数: {}", 
                    keyword, page, size, documents.getTotalElements());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "搜索失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("搜索测试文档失败，关键词: {}", keyword, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "删除文档", description = "根据ID删除测试文档")
    @DeleteMapping("/document/{id}")
    public ResponseEntity<Map<String, Object>> deleteDocument(
            @Parameter(description = "文档ID") @PathVariable String id) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (crudService == null) {
                result.put("status", "error");
                result.put("message", "CRUD服务未初始化");
                return ResponseEntity.status(500).body(result);
            }

            crudService.deleteById(id);
            
            result.put("status", "success");
            result.put("message", "删除成功");
            result.put("deletedId", id);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("删除测试文档成功，ID: {}", id);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "删除失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("删除测试文档失败，ID: {}", id, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取索引列表", description = "获取所有Elasticsearch索引信息")
    @GetMapping("/indices")
    public ResponseEntity<Map<String, Object>> getIndices() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (indexService == null) {
                result.put("status", "error");
                result.put("message", "索引服务未初始化");
                return ResponseEntity.status(500).body(result);
            }

            List<Map<String, Object>> indices = indexService.listIndices();
            
            result.put("status", "success");
            result.put("message", "获取索引列表成功");
            result.put("count", indices.size());
            result.put("data", indices);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("获取索引列表成功，数量: {}", indices.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取索引列表失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("获取索引列表失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "创建索引", description = "创建新的Elasticsearch索引")
    @PostMapping("/index/{indexName}")
    public ResponseEntity<Map<String, Object>> createIndex(
            @Parameter(description = "索引名称") @PathVariable String indexName) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (indexService == null) {
                result.put("status", "error");
                result.put("message", "索引服务未初始化");
                return ResponseEntity.status(500).body(result);
            }

            // 创建默认的keyword类型映射
            Map<String, Object> mapping = new HashMap<>();
            Map<String, Object> properties = new HashMap<>();
            
            // 添加基础字段映射
            Map<String, Object> titleMapping = new HashMap<>();
            titleMapping.put("type", "keyword");
            properties.put("title", titleMapping);
            
            Map<String, Object> contentMapping = new HashMap<>();
            contentMapping.put("type", "keyword");
            properties.put("content", contentMapping);
            
            mapping.put("properties", properties);

            boolean success = indexService.createIndex(indexName, mapping);
            
            if (success) {
                result.put("status", "success");
                result.put("message", "索引创建成功");
            } else {
                result.put("status", "error");
                result.put("message", "索引创建失败");
            }
            
            result.put("indexName", indexName);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("创建索引，名称: {}, 结果: {}", indexName, success ? "成功" : "失败");
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "创建索引失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("创建索引失败，名称: {}", indexName, e);
            return ResponseEntity.status(500).body(result);
        }
    }
}