package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Elasticsearch 简单测试控制器
 * 用于测试基础配置和Bean初始化
 * 
 * @author Kiro
 */
@Slf4j
@RestController
@RequestMapping("/api/elasticsearch/simple")
@Tag(name = "Elasticsearch简单测试", description = "Elasticsearch基础配置测试接口")
public class ElasticsearchSimpleTestController {

    @Autowired
    private ApplicationContext applicationContext;

    @Operation(summary = "检查配置", description = "检查Elasticsearch相关Bean是否正确初始化")
    @GetMapping("/check-config")
    public ResponseEntity<Map<String, Object>> checkConfig() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            result.put("timestamp", LocalDateTime.now());
            result.put("status", "success");
            result.put("message", "配置检查完成");
            
            // 检查各种Bean是否存在
            Map<String, Object> beans = new HashMap<>();
            
            // 检查RestHighLevelClient
            try {
                Object restClient = applicationContext.getBean("restHighLevelClient");
                beans.put("restHighLevelClient", restClient != null ? "已初始化" : "未初始化");
            } catch (Exception e) {
                beans.put("restHighLevelClient", "未找到: " + e.getMessage());
            }
            
            // 检查ElasticsearchRestTemplate
            try {
                Object template = applicationContext.getBean("elasticsearchRestTemplate");
                beans.put("elasticsearchRestTemplate", template != null ? "已初始化" : "未初始化");
            } catch (Exception e) {
                beans.put("elasticsearchRestTemplate", "未找到: " + e.getMessage());
            }
            
            // 检查CRUD服务
            try {
                Object crudService = applicationContext.getBean("elasticsearchCrudService");
                beans.put("elasticsearchCrudService", crudService != null ? "已初始化" : "未初始化");
            } catch (Exception e) {
                beans.put("elasticsearchCrudService", "未找到: " + e.getMessage());
            }
            
            // 检查索引服务
            try {
                Object indexService = applicationContext.getBean("elasticsearchIndexService");
                beans.put("elasticsearchIndexService", indexService != null ? "已初始化" : "未初始化");
            } catch (Exception e) {
                beans.put("elasticsearchIndexService", "未找到: " + e.getMessage());
            }
            
            // 检查健康检查器
            try {
                Object healthIndicator = applicationContext.getBean("elasticsearchHealthIndicator");
                beans.put("elasticsearchHealthIndicator", healthIndicator != null ? "已初始化" : "未初始化");
            } catch (Exception e) {
                beans.put("elasticsearchHealthIndicator", "未找到: " + e.getMessage());
            }
            
            result.put("beans", beans);
            
            log.info("Elasticsearch配置检查完成: {}", beans);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "配置检查失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Elasticsearch配置检查失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取应用信息", description = "获取应用基本信息")
    @GetMapping("/app-info")
    public ResponseEntity<Map<String, Object>> getAppInfo() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            result.put("timestamp", LocalDateTime.now());
            result.put("status", "success");
            result.put("applicationName", "my-test-execute");
            result.put("description", "测试执行微服务 - Elasticsearch功能测试");
            
            // 获取所有Bean名称（过滤Elasticsearch相关的）
            String[] beanNames = applicationContext.getBeanDefinitionNames();
            int elasticsearchBeanCount = 0;
            for (String beanName : beanNames) {
                if (beanName.toLowerCase().contains("elasticsearch")) {
                    elasticsearchBeanCount++;
                }
            }
            
            result.put("totalBeans", beanNames.length);
            result.put("elasticsearchBeans", elasticsearchBeanCount);
            
            log.info("应用信息获取成功，总Bean数: {}, ES相关Bean数: {}", beanNames.length, elasticsearchBeanCount);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取应用信息失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("获取应用信息失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "健康检查", description = "简单的健康检查接口")
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        result.put("timestamp", LocalDateTime.now());
        result.put("service", "my-test-execute");
        result.put("elasticsearch", "配置中");
        
        return ResponseEntity.ok(result);
    }
}