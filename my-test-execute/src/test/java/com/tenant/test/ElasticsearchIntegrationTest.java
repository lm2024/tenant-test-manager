package com.tenant.test;

import com.example.elasticsearch.service.ElasticsearchCrudService;
import com.example.elasticsearch.service.ElasticsearchIndexService;
import com.tenant.test.entity.TestDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Elasticsearch 集成测试
 * 
 * @author Kiro
 */
@SpringBootTest
@ActiveProfiles("elasticsearch")
public class ElasticsearchIntegrationTest {

    @Autowired(required = false)
    private ElasticsearchCrudService<TestDocument> crudService;

    @Autowired(required = false)
    private ElasticsearchIndexService indexService;

    @Test
    public void testElasticsearchServicesInitialization() {
        // 测试服务是否正确初始化
        assertNotNull(crudService, "CRUD服务应该被正确初始化");
        assertNotNull(indexService, "索引服务应该被正确初始化");
    }

    @Test
    public void testCreateAndFindDocument() {
        if (crudService == null) {
            System.out.println("CRUD服务未初始化，跳过测试");
            return;
        }

        // 创建测试文档
        TestDocument document = new TestDocument();
        document.setId(UUID.randomUUID().toString());
        document.setTitle("测试文档标题");
        document.setContent("测试文档内容");
        document.setCategory("测试分类");
        document.setStatus("active");
        document.setTenantId("tenant001");
        document.setCreateTime(LocalDateTime.now());
        document.setUpdateTime(LocalDateTime.now());
        document.setVersion(1L);
        document.setPriority(1);
        document.setActive(true);

        // 保存文档
        TestDocument savedDocument = crudService.save(document);
        assertNotNull(savedDocument, "保存的文档不应该为空");
        assertEquals(document.getId(), savedDocument.getId(), "文档ID应该一致");

        // 查询文档
        Optional<TestDocument> foundDocument = crudService.findById(document.getId());
        assertTrue(foundDocument.isPresent(), "应该能找到保存的文档");
        assertEquals(document.getTitle(), foundDocument.get().getTitle(), "文档标题应该一致");

        // 清理测试数据
        crudService.deleteById(document.getId());
    }

    @Test
    public void testSearchDocuments() {
        if (crudService == null) {
            System.out.println("CRUD服务未初始化，跳过测试");
            return;
        }

        // 创建测试文档
        TestDocument document = new TestDocument();
        document.setId(UUID.randomUUID().toString());
        document.setTitle("搜索测试文档");
        document.setContent("这是一个用于搜索测试的文档");
        document.setCategory("测试分类");
        document.setStatus("active");
        document.setTenantId("tenant001");
        document.setCreateTime(LocalDateTime.now());
        document.setUpdateTime(LocalDateTime.now());
        document.setVersion(1L);
        document.setPriority(1);
        document.setActive(true);

        // 保存文档
        TestDocument savedDocument = crudService.save(document);

        // 搜索文档
        Page<TestDocument> searchResults = crudService.search("搜索测试", PageRequest.of(0, 10));
        assertTrue(searchResults.getTotalElements() > 0, "应该能找到搜索结果");

        // 清理测试数据
        crudService.deleteById(document.getId());
    }

    @Test
    public void testIndexOperations() {
        if (indexService == null) {
            System.out.println("索引服务未初始化，跳过测试");
            return;
        }

        String testIndexName = "test_index_" + System.currentTimeMillis();

        // 测试创建索引
        boolean createSuccess = indexService.createIndex(testIndexName, null);
        assertTrue(createSuccess, "索引创建应该成功");

        // 测试索引是否存在
        boolean exists = indexService.indexExists(testIndexName);
        assertTrue(exists, "索引应该存在");

        // 测试删除索引
        boolean deleteSuccess = indexService.deleteIndex(testIndexName);
        assertTrue(deleteSuccess, "索引删除应该成功");
    }

    @Test
    public void testClusterHealth() {
        if (indexService == null) {
            System.out.println("索引服务未初始化，跳过测试");
            return;
        }

        // 测试获取集群健康状态
        var health = indexService.getClusterHealth();
        assertNotNull(health, "集群健康状态不应该为空");
        System.out.println("集群健康状态: " + health);
    }
} 