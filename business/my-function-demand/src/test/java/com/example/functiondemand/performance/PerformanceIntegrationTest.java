package com.example.functiondemand.performance;

import com.example.functiondemand.FunctionDemandApp;
import com.example.functiondemand.dto.requirement.RequirementCreateDTO;
import com.example.functiondemand.dto.functionpoint.FunctionPointCreateDTO;
import com.example.functiondemand.dto.category.CategoryCreateDTO;
import com.example.functiondemand.enums.RequirementStatus;
import com.example.functiondemand.enums.RequirementType;
import com.example.functiondemand.enums.FunctionPointStatus;
import com.example.functiondemand.enums.FunctionPointType;
import com.example.functiondemand.enums.CategoryType;
import com.example.functiondemand.repository.RequirementRepository;
import com.example.functiondemand.repository.FunctionPointRepository;
import com.example.functiondemand.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 性能集成测试
 * 测试批量操作和高并发场景的性能表现
 */
@SpringBootTest(classes = FunctionDemandApp.class)
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
public class PerformanceIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private FunctionPointRepository functionPointRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private static final String TENANT_ID_HEADER = "X-Tenant-Id";
    private static final String TENANT_1 = "tenant1";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // 清理测试数据
        requirementRepository.deleteAll();
        functionPointRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void testBatchCreateRequirementsPerformance() throws Exception {
        // 创建1000个需求的批量创建请求
        List<RequirementCreateDTO> batchCreateList = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            RequirementCreateDTO req = new RequirementCreateDTO();
            req.setTitle("批量需求" + i);
            req.setDescription("批量描述" + i);
            req.setType(RequirementType.FUNCTIONAL);
            req.setStatus(RequirementStatus.DRAFT);
            req.setPriority(i % 5 + 1);
            batchCreateList.add(req);
        }

        long startTime = System.currentTimeMillis();

        mockMvc.perform(post("/api/requirements/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchCreateList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(1000)));

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("批量创建1000个需求耗时: " + duration + "ms");
        
        // 验证性能要求：1000个记录应在10秒内完成
        assert duration < 10000 : "批量创建性能不达标，耗时: " + duration + "ms";
    }

    @Test
    void testBatchCreateFunctionPointsPerformance() throws Exception {
        // 创建1000个功能点的批量创建请求
        List<FunctionPointCreateDTO> batchCreateList = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            FunctionPointCreateDTO fp = new FunctionPointCreateDTO();
            fp.setName("批量功能点" + i);
            fp.setDescription("批量描述" + i);
            fp.setType(FunctionPointType.FEATURE);
            fp.setStatus(FunctionPointStatus.PLANNING);
            fp.setPriority(i % 5 + 1);
            batchCreateList.add(fp);
        }

        long startTime = System.currentTimeMillis();

        mockMvc.perform(post("/api/function-points/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchCreateList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(1000)));

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("批量创建1000个功能点耗时: " + duration + "ms");
        
        // 验证性能要求：1000个记录应在10秒内完成
        assert duration < 10000 : "批量创建性能不达标，耗时: " + duration + "ms";
    }

    @Test
    void testConcurrentRequirementCreation() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        // 10个线程并发创建需求，每个线程创建100个需求
        for (int thread = 0; thread < 10; thread++) {
            final int threadId = thread;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    for (int i = 1; i <= 100; i++) {
                        RequirementCreateDTO req = new RequirementCreateDTO();
                        req.setTitle("并发需求" + threadId + "-" + i);
                        req.setDescription("并发描述" + threadId + "-" + i);
                        req.setType(RequirementType.FUNCTIONAL);
                        req.setStatus(RequirementStatus.DRAFT);
                        req.setPriority(i % 5 + 1);

                        mockMvc.perform(post("/api/requirements")
                                .header(TENANT_ID_HEADER, TENANT_1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(req)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, executor);
            futures.add(future);
        }

        // 等待所有线程完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("并发创建1000个需求耗时: " + duration + "ms");

        // 验证数据完整性
        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1000)));

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
    }

    @Test
    void testLargeDatasetQuery() throws Exception {
        // 先创建大量测试数据
        List<RequirementCreateDTO> batchCreateList = new ArrayList<>();
        for (int i = 1; i <= 5000; i++) {
            RequirementCreateDTO req = new RequirementCreateDTO();
            req.setTitle("大数据需求" + i);
            req.setDescription("大数据描述" + i);
            req.setType(i % 2 == 0 ? RequirementType.FUNCTIONAL : RequirementType.NON_FUNCTIONAL);
            req.setStatus(i % 3 == 0 ? RequirementStatus.APPROVED : RequirementStatus.DRAFT);
            req.setPriority(i % 5 + 1);
            batchCreateList.add(req);
        }

        // 批量创建数据
        mockMvc.perform(post("/api/requirements/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchCreateList)))
                .andExpect(status().isOk());

        // 测试分页查询性能
        long startTime = System.currentTimeMillis();

        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(100)))
                .andExpect(jsonPath("$.data.totalElements").value(5000));

        long endTime = System.currentTimeMillis();
        long queryDuration = endTime - startTime;

        System.out.println("查询5000条记录中的前100条耗时: " + queryDuration + "ms");

        // 验证查询性能：应在1秒内完成
        assert queryDuration < 1000 : "查询性能不达标，耗时: " + queryDuration + "ms";

        // 测试搜索性能
        startTime = System.currentTimeMillis();

        mockMvc.perform(get("/api/requirements/search")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("keyword", "大数据需求1")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        endTime = System.currentTimeMillis();
        long searchDuration = endTime - startTime;

        System.out.println("在5000条记录中搜索耗时: " + searchDuration + "ms");

        // 验证搜索性能：应在2秒内完成
        assert searchDuration < 2000 : "搜索性能不达标，耗时: " + searchDuration + "ms";
    }

    @Test
    void testTreeQueryPerformance() throws Exception {
        // 创建深层树形结构数据
        List<CategoryCreateDTO> categories = new ArrayList<>();
        
        // 创建5层树形结构，每层10个节点
        for (int level1 = 1; level1 <= 10; level1++) {
            CategoryCreateDTO cat1 = new CategoryCreateDTO();
            cat1.setName("一级分类" + level1);
            cat1.setDescription("一级描述" + level1);
            cat1.setType(CategoryType.REQUIREMENT);
            categories.add(cat1);
        }

        // 批量创建分类
        mockMvc.perform(post("/api/categories/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categories)))
                .andExpect(status().isOk());

        // 测试树形查询性能
        long startTime = System.currentTimeMillis();

        mockMvc.perform(get("/api/categories/tree")
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(10)));

        long endTime = System.currentTimeMillis();
        long treeDuration = endTime - startTime;

        System.out.println("树形查询耗时: " + treeDuration + "ms");

        // 验证树形查询性能：应在1秒内完成
        assert treeDuration < 1000 : "树形查询性能不达标，耗时: " + treeDuration + "ms";
    }

    @Test
    void testMultiTenantConcurrentAccess() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        // 模拟10个租户，每个租户2个线程并发操作
        for (int tenant = 1; tenant <= 10; tenant++) {
            final String tenantId = "tenant" + tenant;
            
            for (int thread = 0; thread < 2; thread++) {
                final int threadId = thread;
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        // 每个线程创建50个需求
                        for (int i = 1; i <= 50; i++) {
                            RequirementCreateDTO req = new RequirementCreateDTO();
                            req.setTitle(tenantId + "-需求" + threadId + "-" + i);
                            req.setDescription(tenantId + "-描述" + threadId + "-" + i);
                            req.setType(RequirementType.FUNCTIONAL);
                            req.setStatus(RequirementStatus.DRAFT);
                            req.setPriority(i % 5 + 1);

                            mockMvc.perform(post("/api/requirements")
                                    .header(TENANT_ID_HEADER, tenantId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(req)))
                                    .andExpect(status().isOk())
                                    .andExpect(jsonPath("$.success").value(true));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }, executor);
                futures.add(future);
            }
        }

        // 等待所有线程完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("多租户并发创建1000个需求耗时: " + duration + "ms");

        // 验证每个租户的数据隔离
        for (int tenant = 1; tenant <= 10; tenant++) {
            final String tenantId = "tenant" + tenant;
            
            mockMvc.perform(get("/api/requirements")
                    .header(TENANT_ID_HEADER, tenantId)
                    .param("page", "0")
                    .param("size", "100"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.data.content", hasSize(100)))
                    .andExpect(jsonPath("$.data.totalElements").value(100));
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
    }

    @Test
    void testMemoryUsageUnderLoad() throws Exception {
        // 记录初始内存使用
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        // 创建大量数据
        List<RequirementCreateDTO> batchCreateList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            RequirementCreateDTO req = new RequirementCreateDTO();
            req.setTitle("内存测试需求" + i);
            req.setDescription("内存测试描述" + i + " - 这是一个较长的描述用于测试内存使用情况");
            req.setType(RequirementType.FUNCTIONAL);
            req.setStatus(RequirementStatus.DRAFT);
            req.setPriority(i % 5 + 1);
            batchCreateList.add(req);
        }

        mockMvc.perform(post("/api/requirements/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchCreateList)))
                .andExpect(status().isOk());

        // 执行多次查询操作
        for (int i = 0; i < 100; i++) {
            mockMvc.perform(get("/api/requirements")
                    .header(TENANT_ID_HEADER, TENANT_1)
                    .param("page", String.valueOf(i % 100))
                    .param("size", "100"))
                    .andExpect(status().isOk());
        }

        // 强制垃圾回收
        System.gc();
        Thread.sleep(1000);

        // 记录最终内存使用
        long finalMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = finalMemory - initialMemory;

        System.out.println("初始内存使用: " + (initialMemory / 1024 / 1024) + "MB");
        System.out.println("最终内存使用: " + (finalMemory / 1024 / 1024) + "MB");
        System.out.println("内存增长: " + (memoryIncrease / 1024 / 1024) + "MB");

        // 验证内存使用合理（增长不超过500MB）
        assert memoryIncrease < 500 * 1024 * 1024 : "内存使用过多，增长: " + (memoryIncrease / 1024 / 1024) + "MB";
    }
}