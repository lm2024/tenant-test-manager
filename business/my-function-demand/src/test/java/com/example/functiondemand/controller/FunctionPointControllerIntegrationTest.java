package com.example.functiondemand.controller;

import com.example.functiondemand.FunctionDemandApp;
import com.example.functiondemand.dto.functionpoint.FunctionPointCreateDTO;
import com.example.functiondemand.dto.functionpoint.FunctionPointUpdateDTO;
import com.example.functiondemand.entity.FunctionPoint;
import com.example.functiondemand.enums.FunctionPointStatus;
import com.example.functiondemand.enums.FunctionPointType;
import com.example.functiondemand.repository.FunctionPointRepository;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 功能点管理控制器集成测试
 * 测试REST API的完整功能，包括多租户支持和批量操作
 */
@SpringBootTest(classes = FunctionDemandApp.class)
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
public class FunctionPointControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FunctionPointRepository functionPointRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private static final String TENANT_ID_HEADER = "X-Tenant-Id";
    private static final String TENANT_1 = "tenant1";
    private static final String TENANT_2 = "tenant2";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // 清理测试数据
        functionPointRepository.deleteAll();
    }

    @Test
    void testCreateFunctionPoint() throws Exception {
        FunctionPointCreateDTO createDTO = new FunctionPointCreateDTO();
        createDTO.setName("测试功能点");
        createDTO.setDescription("这是一个测试功能点");
        createDTO.setType(FunctionPointType.FEATURE);
        createDTO.setStatus(FunctionPointStatus.PLANNING);
        createDTO.setPriority(1);

        mockMvc.perform(post("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("测试功能点"))
                .andExpect(jsonPath("$.data.description").value("这是一个测试功能点"))
                .andExpect(jsonPath("$.data.type").value("FEATURE"))
                .andExpect(jsonPath("$.data.status").value("PLANNING"))
                .andExpect(jsonPath("$.data.priority").value(1));
    }

    @Test
    void testGetFunctionPointById() throws Exception {
        // 创建测试数据
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setName("测试功能点");
        functionPoint.setDescription("测试描述");
        functionPoint.setType(FunctionPointType.FEATURE);
        functionPoint.setStatus(FunctionPointStatus.PLANNING);
        functionPoint.setPriority(1);
        functionPoint = functionPointRepository.save(functionPoint);

        mockMvc.perform(get("/api/function-points/{id}", functionPoint.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(functionPoint.getId()))
                .andExpect(jsonPath("$.data.name").value("测试功能点"));
    }

    @Test
    void testUpdateFunctionPoint() throws Exception {
        // 创建测试数据
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setName("原始功能点");
        functionPoint.setDescription("原始描述");
        functionPoint.setType(FunctionPointType.FEATURE);
        functionPoint.setStatus(FunctionPointStatus.PLANNING);
        functionPoint.setPriority(1);
        functionPoint = functionPointRepository.save(functionPoint);

        FunctionPointUpdateDTO updateDTO = new FunctionPointUpdateDTO();
        updateDTO.setName("更新后的功能点");
        updateDTO.setDescription("更新后的描述");
        updateDTO.setStatus(FunctionPointStatus.DEVELOPMENT);
        updateDTO.setPriority(2);

        mockMvc.perform(put("/api/function-points/{id}", functionPoint.getId())
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("更新后的功能点"))
                .andExpect(jsonPath("$.data.description").value("更新后的描述"))
                .andExpect(jsonPath("$.data.status").value("DEVELOPMENT"))
                .andExpect(jsonPath("$.data.priority").value(2));
    }

    @Test
    void testDeleteFunctionPoint() throws Exception {
        // 创建测试数据
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setName("待删除功能点");
        functionPoint.setDescription("待删除描述");
        functionPoint.setType(FunctionPointType.FEATURE);
        functionPoint.setStatus(FunctionPointStatus.PLANNING);
        functionPoint.setPriority(1);
        functionPoint = functionPointRepository.save(functionPoint);

        mockMvc.perform(delete("/api/function-points/{id}", functionPoint.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证数据已删除
        mockMvc.perform(get("/api/function-points/{id}", functionPoint.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllFunctionPoints() throws Exception {
        // 创建测试数据
        FunctionPoint fp1 = new FunctionPoint();
        fp1.setName("功能点1");
        fp1.setDescription("描述1");
        fp1.setType(FunctionPointType.FEATURE);
        fp1.setStatus(FunctionPointStatus.PLANNING);
        fp1.setPriority(1);

        FunctionPoint fp2 = new FunctionPoint();
        fp2.setName("功能点2");
        fp2.setDescription("描述2");
        fp2.setType(FunctionPointType.MODULE);
        fp2.setStatus(FunctionPointStatus.DEVELOPMENT);
        fp2.setPriority(2);

        functionPointRepository.saveAll(Arrays.asList(fp1, fp2));

        mockMvc.perform(get("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(2)))
                .andExpect(jsonPath("$.data.content[0].name", anyOf(is("功能点1"), is("功能点2"))))
                .andExpect(jsonPath("$.data.content[1].name", anyOf(is("功能点1"), is("功能点2"))));
    }

    @Test
    void testBatchCreateFunctionPoints() throws Exception {
        FunctionPointCreateDTO fp1 = new FunctionPointCreateDTO();
        fp1.setName("批量功能点1");
        fp1.setDescription("批量描述1");
        fp1.setType(FunctionPointType.FEATURE);
        fp1.setStatus(FunctionPointStatus.PLANNING);
        fp1.setPriority(1);

        FunctionPointCreateDTO fp2 = new FunctionPointCreateDTO();
        fp2.setName("批量功能点2");
        fp2.setDescription("批量描述2");
        fp2.setType(FunctionPointType.MODULE);
        fp2.setStatus(FunctionPointStatus.DEVELOPMENT);
        fp2.setPriority(2);

        List<FunctionPointCreateDTO> batchCreateList = Arrays.asList(fp1, fp2);

        mockMvc.perform(post("/api/function-points/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchCreateList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].name", anyOf(is("批量功能点1"), is("批量功能点2"))))
                .andExpect(jsonPath("$.data[1].name", anyOf(is("批量功能点1"), is("批量功能点2"))));
    }

    @Test
    void testBatchUpdateFunctionPoints() throws Exception {
        // 创建测试数据
        FunctionPoint fp1 = new FunctionPoint();
        fp1.setName("原始功能点1");
        fp1.setDescription("原始描述1");
        fp1.setType(FunctionPointType.FEATURE);
        fp1.setStatus(FunctionPointStatus.PLANNING);
        fp1.setPriority(1);

        FunctionPoint fp2 = new FunctionPoint();
        fp2.setName("原始功能点2");
        fp2.setDescription("原始描述2");
        fp2.setType(FunctionPointType.MODULE);
        fp2.setStatus(FunctionPointStatus.PLANNING);
        fp2.setPriority(1);

        List<FunctionPoint> savedFps = functionPointRepository.saveAll(Arrays.asList(fp1, fp2));

        FunctionPointUpdateDTO update1 = new FunctionPointUpdateDTO();
        update1.setId(savedFps.get(0).getId());
        update1.setName("更新功能点1");
        update1.setStatus(FunctionPointStatus.DEVELOPMENT);

        FunctionPointUpdateDTO update2 = new FunctionPointUpdateDTO();
        update2.setId(savedFps.get(1).getId());
        update2.setName("更新功能点2");
        update2.setStatus(FunctionPointStatus.DEVELOPMENT);

        List<FunctionPointUpdateDTO> batchUpdateList = Arrays.asList(update1, update2);

        mockMvc.perform(put("/api/function-points/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchUpdateList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    void testBatchDeleteFunctionPoints() throws Exception {
        // 创建测试数据
        FunctionPoint fp1 = new FunctionPoint();
        fp1.setName("待删除功能点1");
        fp1.setDescription("待删除描述1");
        fp1.setType(FunctionPointType.FEATURE);
        fp1.setStatus(FunctionPointStatus.PLANNING);
        fp1.setPriority(1);

        FunctionPoint fp2 = new FunctionPoint();
        fp2.setName("待删除功能点2");
        fp2.setDescription("待删除描述2");
        fp2.setType(FunctionPointType.MODULE);
        fp2.setStatus(FunctionPointStatus.PLANNING);
        fp2.setPriority(1);

        List<FunctionPoint> savedFps = functionPointRepository.saveAll(Arrays.asList(fp1, fp2));
        List<Long> idsToDelete = Arrays.asList(savedFps.get(0).getId(), savedFps.get(1).getId());

        mockMvc.perform(delete("/api/function-points/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(idsToDelete)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证数据已删除
        mockMvc.perform(get("/api/function-points/{id}", savedFps.get(0).getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/function-points/{id}", savedFps.get(1).getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetFunctionPointTree() throws Exception {
        // 创建树形测试数据
        FunctionPoint parent = new FunctionPoint();
        parent.setName("父功能点");
        parent.setDescription("父功能点描述");
        parent.setType(FunctionPointType.MODULE);
        parent.setStatus(FunctionPointStatus.PLANNING);
        parent.setPriority(1);
        parent = functionPointRepository.save(parent);

        FunctionPoint child = new FunctionPoint();
        child.setName("子功能点");
        child.setDescription("子功能点描述");
        child.setType(FunctionPointType.FEATURE);
        child.setStatus(FunctionPointStatus.PLANNING);
        child.setPriority(1);
        child.setParentId(parent.getId());
        child.setPath(parent.getPath() + parent.getId() + "/");
        child.setLevel(parent.getLevel() + 1);
        functionPointRepository.save(child);

        mockMvc.perform(get("/api/function-points/tree")
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(greaterThan(0))));
    }

    /**
     * 测试多租户数据隔离
     * 验证不同租户的数据完全隔离
     */
    @Test
    void testTenantDataIsolation() throws Exception {
        // 在租户1中创建功能点
        FunctionPointCreateDTO tenant1Fp = new FunctionPointCreateDTO();
        tenant1Fp.setName("租户1功能点");
        tenant1Fp.setDescription("租户1描述");
        tenant1Fp.setType(FunctionPointType.FEATURE);
        tenant1Fp.setStatus(FunctionPointStatus.PLANNING);
        tenant1Fp.setPriority(1);

        mockMvc.perform(post("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tenant1Fp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 在租户2中创建功能点
        FunctionPointCreateDTO tenant2Fp = new FunctionPointCreateDTO();
        tenant2Fp.setName("租户2功能点");
        tenant2Fp.setDescription("租户2描述");
        tenant2Fp.setType(FunctionPointType.MODULE);
        tenant2Fp.setStatus(FunctionPointStatus.DEVELOPMENT);
        tenant2Fp.setPriority(2);

        mockMvc.perform(post("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tenant2Fp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证租户1只能看到自己的数据
        mockMvc.perform(get("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].name").value("租户1功能点"));

        // 验证租户2只能看到自己的数据
        mockMvc.perform(get("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_2)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].name").value("租户2功能点"));
    }

    @Test
    void testSearchFunctionPoints() throws Exception {
        // 创建测试数据
        FunctionPoint fp1 = new FunctionPoint();
        fp1.setName("用户管理功能");
        fp1.setDescription("实现用户管理功能");
        fp1.setType(FunctionPointType.FEATURE);
        fp1.setStatus(FunctionPointStatus.PLANNING);
        fp1.setPriority(1);

        FunctionPoint fp2 = new FunctionPoint();
        fp2.setName("系统监控模块");
        fp2.setDescription("系统监控相关功能");
        fp2.setType(FunctionPointType.MODULE);
        fp2.setStatus(FunctionPointStatus.DEVELOPMENT);
        fp2.setPriority(2);

        functionPointRepository.saveAll(Arrays.asList(fp1, fp2));

        // 按名称搜索
        mockMvc.perform(get("/api/function-points/search")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("keyword", "用户")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].name").value("用户管理功能"));

        // 按类型过滤
        mockMvc.perform(get("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("type", "MODULE")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].type").value("MODULE"));
    }

    @Test
    void testFunctionPointValidation() throws Exception {
        // 测试必填字段验证
        FunctionPointCreateDTO invalidFp = new FunctionPointCreateDTO();
        // 不设置必填字段

        mockMvc.perform(post("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidFp)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void testFunctionPointNotFound() throws Exception {
        mockMvc.perform(get("/api/function-points/{id}", 99999L)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void testGetFunctionPointsByParent() throws Exception {
        // 创建父功能点
        FunctionPoint parent = new FunctionPoint();
        parent.setName("父功能点");
        parent.setDescription("父功能点描述");
        parent.setType(FunctionPointType.MODULE);
        parent.setStatus(FunctionPointStatus.PLANNING);
        parent.setPriority(1);
        parent = functionPointRepository.save(parent);

        // 创建子功能点
        FunctionPoint child1 = new FunctionPoint();
        child1.setName("子功能点1");
        child1.setDescription("子功能点1描述");
        child1.setType(FunctionPointType.FEATURE);
        child1.setStatus(FunctionPointStatus.PLANNING);
        child1.setPriority(1);
        child1.setParentId(parent.getId());
        child1.setPath(parent.getPath() + parent.getId() + "/");
        child1.setLevel(parent.getLevel() + 1);

        FunctionPoint child2 = new FunctionPoint();
        child2.setName("子功能点2");
        child2.setDescription("子功能点2描述");
        child2.setType(FunctionPointType.FEATURE);
        child2.setStatus(FunctionPointStatus.PLANNING);
        child2.setPriority(2);
        child2.setParentId(parent.getId());
        child2.setPath(parent.getPath() + parent.getId() + "/");
        child2.setLevel(parent.getLevel() + 1);

        functionPointRepository.saveAll(Arrays.asList(child1, child2));

        // 查询子功能点
        mockMvc.perform(get("/api/function-points/children/{parentId}", parent.getId())
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(2)));
    }
}