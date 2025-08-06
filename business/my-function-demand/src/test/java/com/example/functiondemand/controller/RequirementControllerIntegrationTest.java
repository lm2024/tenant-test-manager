package com.example.functiondemand.controller;

import com.example.functiondemand.FunctionDemandApplication;
import com.example.functiondemand.requirement.dto.RequirementCreateDTO;
import com.example.functiondemand.requirement.dto.RequirementUpdateDTO;
import com.example.functiondemand.requirement.entity.Requirement;
import com.example.functiondemand.common.enums.RequirementStatus;
import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.requirement.repository.RequirementRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 需求控制器集成测试
 */
@SpringBootTest(classes = FunctionDemandApplication.class)
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

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        requirementRepository.deleteAll();
    }

    @Test
    public void testCreateRequirement() throws Exception {
        RequirementCreateDTO createDTO = new RequirementCreateDTO();
        createDTO.setTitle("测试需求");
        createDTO.setDescription("测试需求描述");
        createDTO.setPriority(RequirementPriority.HIGH);
        createDTO.setStatus(RequirementStatus.DRAFT);
        createDTO.setSource("用户反馈");

        mockMvc.perform(post("/api/requirements")
                .header("X-Tenant-ID", "tenant1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").value("测试需求"));
    }

    @Test
    public void testGetRequirement() throws Exception {
        // 创建测试数据
        Requirement requirement = new Requirement();
        requirement.setId("req1");
        requirement.setTitle("测试需求");
        requirement.setDescription("测试描述");
        requirement.setPriority(RequirementPriority.HIGH);
        requirement.setStatus(RequirementStatus.DRAFT);
        requirement.setSource("用户反馈");
        requirement.setLevel(1);
        requirement.setCreatedTime(java.time.LocalDateTime.now());
        requirement.setUpdatedTime(java.time.LocalDateTime.now());
        requirementRepository.save(requirement);

        mockMvc.perform(get("/api/requirements/req1")
                .header("X-Tenant-ID", "tenant1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").value("测试需求"));
    }

    @Test
    public void testListRequirements() throws Exception {
        mockMvc.perform(get("/api/requirements")
                .header("X-Tenant-ID", "tenant1")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}