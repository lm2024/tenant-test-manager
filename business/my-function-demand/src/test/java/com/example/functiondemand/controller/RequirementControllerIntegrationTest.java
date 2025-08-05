package com.example.functiondemand.controller;

import com.example.functiondemand.FunctionDemandApp;
import com.example.functiondemand.dto.requirement.RequirementCreateDTO;
import com.example.functiondemand.dto.requirement.RequirementDTO;
import com.example.functiondemand.dto.requirement.RequirementUpdateDTO;
import com.example.functiondemand.entity.Requirement;
import com.example.functiondemand.enums.RequirementStatus;
import com.example.functiondemand.enums.RequirementType;
import com.example.functiondemand.repository.RequirementRepository;
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
 * 需求管理控制器集成测试
 * 测试REST API的完整功能，包括多租户支持和批量操作
 */
@SpringBootTest(classes = FunctionDemandApp.class)
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
public class RequirementControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RequirementRepository requirementRepository;

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
        requirementRepository.deleteAll();
    }

    @Test
    void testCreateRequirement() throws Exception {
        RequirementCreateDTO createDTO = new RequirementCreateDTO();
        createDTO.setTitle("测试需求");
        createDTO.setDescription("这是一个测试需求");
        createDTO.setType(RequirementType.FUNCTIONAL);
        createDTO.setStatus(RequirementStatus.DRAFT);
        createDTO.setPriority(1);

        mockMvc.perform(post("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").value("测试需求"))
                .andExpect(jsonPath("$.data.description").value("这是一个测试需求"))
                .andExpect(jsonPath("$.data.type").value("FUNCTIONAL"))
                .andExpect(jsonPath("$.data.status").value("DRAFT"))
                .andExpect(jsonPath("$.data.priority").value(1));
    }

    @Test
    void testGetRequirementById() throws Exception {
        // 创建测试数据
        Requirement requirement = new Requirement();
        requirement.setTitle("测试需求");
        requirement.setDescription("测试描述");
        requirement.setType(RequirementType.FUNCTIONAL);
        requirement.setStatus(RequirementStatus.DRAFT);
        requirement.setPriority(1);
        requirement = requirementRepository.save(requirement);

        mockMvc.perform(get("/api/requirements/{id}", requirement.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(requirement.getId()))
                .andExpect(jsonPath("$.data.title").value("测试需求"));
    }

    @Test
    void testUpdateRequirement() throws Exception {
        // 创建测试数据
        Requirement requirement = new Requirement();
        requirement.setTitle("原始需求");
        requirement.setDescription("原始描述");
        requirement.setType(RequirementType.FUNCTIONAL);
        requirement.setStatus(RequirementStatus.DRAFT);
        requirement.setPriority(1);
        requirement = requirementRepository.save(requirement);

        RequirementUpdateDTO updateDTO = new RequirementUpdateDTO();
        updateDTO.setTitle("更新后的需求");
        updateDTO.setDescription("更新后的描述");
        updateDTO.setStatus(RequirementStatus.APPROVED);
        updateDTO.setPriority(2);

        mockMvc.perform(put("/api/requirements/{id}", requirement.getId())
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").value("更新后的需求"))
                .andExpect(jsonPath("$.data.description").value("更新后的描述"))
                .andExpect(jsonPath("$.data.status").value("APPROVED"))
                .andExpect(jsonPath("$.data.priority").value(2));
    }

    @Test
    void testDeleteRequirement() throws Exception {
        // 创建测试数据
        Requirement requirement = new Requirement();
        requirement.setTitle("待删除需求");
        requirement.setDescription("待删除描述");
        requirement.setType(RequirementType.FUNCTIONAL);
        requirement.setStatus(RequirementStatus.DRAFT);
        requirement.setPriority(1);
        requirement = requirementRepository.save(requirement);

        mockMvc.perform(delete("/api/requirements/{id}", requirement.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证数据已删除
        mockMvc.perform(get("/api/requirements/{id}", requirement.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllRequirements() throws Exception {
        // 创建测试数据
        Requirement req1 = new Requirement();
        req1.setTitle("需求1");
        req1.setDescription("描述1");
        req1.setType(RequirementType.FUNCTIONAL);
        req1.setStatus(RequirementStatus.DRAFT);
        req1.setPriority(1);

        Requirement req2 = new Requirement();
        req2.setTitle("需求2");
        req2.setDescription("描述2");
        req2.setType(RequirementType.NON_FUNCTIONAL);
        req2.setStatus(RequirementStatus.APPROVED);
        req2.setPriority(2);

        requirementRepository.saveAll(Arrays.asList(req1, req2));

        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(2)))
                .andExpect(jsonPath("$.data.content[0].title", anyOf(is("需求1"), is("需求2"))))
                .andExpect(jsonPath("$.data.content[1].title", anyOf(is("需求1"), is("需求2"))));
    }

    @Test
    void testBatchCreateRequirements() throws Exception {
        RequirementCreateDTO req1 = new RequirementCreateDTO();
        req1.setTitle("批量需求1");
        req1.setDescription("批量描述1");
        req1.setType(RequirementType.FUNCTIONAL);
        req1.setStatus(RequirementStatus.DRAFT);
        req1.setPriority(1);

        RequirementCreateDTO req2 = new RequirementCreateDTO();
        req2.setTitle("批量需求2");
        req2.setDescription("批量描述2");
        req2.setType(RequirementType.NON_FUNCTIONAL);
        req2.setStatus(RequirementStatus.APPROVED);
        req2.setPriority(2);

        List<RequirementCreateDTO> batchCreateList = Arrays.asList(req1, req2);

        mockMvc.perform(post("/api/requirements/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchCreateList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].title", anyOf(is("批量需求1"), is("批量需求2"))))
                .andExpect(jsonPath("$.data[1].title", anyOf(is("批量需求1"), is("批量需求2"))));
    }

    @Test
    void testBatchUpdateRequirements() throws Exception {
        // 创建测试数据
        Requirement req1 = new Requirement();
        req1.setTitle("原始需求1");
        req1.setDescription("原始描述1");
        req1.setType(RequirementType.FUNCTIONAL);
        req1.setStatus(RequirementStatus.DRAFT);
        req1.setPriority(1);

        Requirement req2 = new Requirement();
        req2.setTitle("原始需求2");
        req2.setDescription("原始描述2");
        req2.setType(RequirementType.NON_FUNCTIONAL);
        req2.setStatus(RequirementStatus.DRAFT);
        req2.setPriority(1);

        List<Requirement> savedReqs = requirementRepository.saveAll(Arrays.asList(req1, req2));

        RequirementUpdateDTO update1 = new RequirementUpdateDTO();
        update1.setId(savedReqs.get(0).getId());
        update1.setTitle("更新需求1");
        update1.setStatus(RequirementStatus.APPROVED);

        RequirementUpdateDTO update2 = new RequirementUpdateDTO();
        update2.setId(savedReqs.get(1).getId());
        update2.setTitle("更新需求2");
        update2.setStatus(RequirementStatus.APPROVED);

        List<RequirementUpdateDTO> batchUpdateList = Arrays.asList(update1, update2);

        mockMvc.perform(put("/api/requirements/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchUpdateList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    void testBatchDeleteRequirements() throws Exception {
        // 创建测试数据
        Requirement req1 = new Requirement();
        req1.setTitle("待删除需求1");
        req1.setDescription("待删除描述1");
        req1.setType(RequirementType.FUNCTIONAL);
        req1.setStatus(RequirementStatus.DRAFT);
        req1.setPriority(1);

        Requirement req2 = new Requirement();
        req2.setTitle("待删除需求2");
        req2.setDescription("待删除描述2");
        req2.setType(RequirementType.NON_FUNCTIONAL);
        req2.setStatus(RequirementStatus.DRAFT);
        req2.setPriority(1);

        List<Requirement> savedReqs = requirementRepository.saveAll(Arrays.asList(req1, req2));
        List<Long> idsToDelete = Arrays.asList(savedReqs.get(0).getId(), savedReqs.get(1).getId());

        mockMvc.perform(delete("/api/requirements/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(idsToDelete)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证数据已删除
        mockMvc.perform(get("/api/requirements/{id}", savedReqs.get(0).getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/requirements/{id}", savedReqs.get(1).getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetRequirementTree() throws Exception {
        // 创建树形测试数据
        Requirement parent = new Requirement();
        parent.setTitle("父需求");
        parent.setDescription("父需求描述");
        parent.setType(RequirementType.FUNCTIONAL);
        parent.setStatus(RequirementStatus.DRAFT);
        parent.setPriority(1);
        parent = requirementRepository.save(parent);

        Requirement child = new Requirement();
        child.setTitle("子需求");
        child.setDescription("子需求描述");
        child.setType(RequirementType.FUNCTIONAL);
        child.setStatus(RequirementStatus.DRAFT);
        child.setPriority(1);
        child.setParentId(parent.getId());
        child.setPath(parent.getPath() + parent.getId() + "/");
        child.setLevel(parent.getLevel() + 1);
        requirementRepository.save(child);

        mockMvc.perform(get("/api/requirements/tree")
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
        // 在租户1中创建需求
        RequirementCreateDTO tenant1Req = new RequirementCreateDTO();
        tenant1Req.setTitle("租户1需求");
        tenant1Req.setDescription("租户1描述");
        tenant1Req.setType(RequirementType.FUNCTIONAL);
        tenant1Req.setStatus(RequirementStatus.DRAFT);
        tenant1Req.setPriority(1);

        mockMvc.perform(post("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tenant1Req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 在租户2中创建需求
        RequirementCreateDTO tenant2Req = new RequirementCreateDTO();
        tenant2Req.setTitle("租户2需求");
        tenant2Req.setDescription("租户2描述");
        tenant2Req.setType(RequirementType.NON_FUNCTIONAL);
        tenant2Req.setStatus(RequirementStatus.APPROVED);
        tenant2Req.setPriority(2);

        mockMvc.perform(post("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tenant2Req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证租户1只能看到自己的数据
        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].title").value("租户1需求"));

        // 验证租户2只能看到自己的数据
        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_2)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].title").value("租户2需求"));
    }

    @Test
    void testSearchRequirements() throws Exception {
        // 创建测试数据
        Requirement req1 = new Requirement();
        req1.setTitle("用户登录需求");
        req1.setDescription("实现用户登录功能");
        req1.setType(RequirementType.FUNCTIONAL);
        req1.setStatus(RequirementStatus.DRAFT);
        req1.setPriority(1);

        Requirement req2 = new Requirement();
        req2.setTitle("性能优化需求");
        req2.setDescription("提升系统性能");
        req2.setType(RequirementType.NON_FUNCTIONAL);
        req2.setStatus(RequirementStatus.APPROVED);
        req2.setPriority(2);

        requirementRepository.saveAll(Arrays.asList(req1, req2));

        // 按标题搜索
        mockMvc.perform(get("/api/requirements/search")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("keyword", "登录")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].title").value("用户登录需求"));

        // 按类型过滤
        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("type", "FUNCTIONAL")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].type").value("FUNCTIONAL"));
    }

    @Test
    void testRequirementValidation() throws Exception {
        // 测试必填字段验证
        RequirementCreateDTO invalidReq = new RequirementCreateDTO();
        // 不设置必填字段

        mockMvc.perform(post("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidReq)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void testRequirementNotFound() throws Exception {
        mockMvc.perform(get("/api/requirements/{id}", 99999L)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }
}