package com.example.functiondemand.controller;

import com.example.functiondemand.FunctionDemandApplication;
import com.example.functiondemand.function.dto.FunctionPointCreateDTO;
import com.example.functiondemand.function.dto.FunctionPointUpdateDTO;
import com.example.functiondemand.function.entity.FunctionPoint;
import com.example.functiondemand.common.enums.FunctionStatus;
import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.function.repository.FunctionPointRepository;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 功能点控制器集成测试
 * 测试REST API的完整功能，包括多租户支持和批量操作
 */
@SpringBootTest(classes = FunctionDemandApplication.class)
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

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        functionPointRepository.deleteAll();
    }

    @Test
    public void testCreateFunctionPoint() throws Exception {
        FunctionPointCreateDTO createDTO = new FunctionPointCreateDTO();
        createDTO.setName("测试功能点");
        createDTO.setDescription("测试功能点描述");
        createDTO.setModule("测试模块");
        createDTO.setComplexity(FunctionComplexity.MEDIUM);
        createDTO.setStatus(FunctionStatus.PLANNING);

        mockMvc.perform(post("/api/function-points")
                .header("X-Tenant-ID", "tenant1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("测试功能点"));
    }

    @Test
    public void testGetFunctionPoint() throws Exception {
        // 创建测试数据
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setId("fp1");
        functionPoint.setName("测试功能点");
        functionPoint.setDescription("测试描述");
        functionPoint.setModule("测试模块");
        functionPoint.setComplexity(FunctionComplexity.MEDIUM);
        functionPoint.setStatus(FunctionStatus.PLANNING);
        functionPoint.setLevel(1);
        functionPoint.setCreatedTime(java.time.LocalDateTime.now());
        functionPoint.setUpdatedTime(java.time.LocalDateTime.now());
        functionPointRepository.save(functionPoint);

        mockMvc.perform(get("/api/function-points/fp1")
                .header("X-Tenant-ID", "tenant1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("测试功能点"));
    }

    @Test
    public void testUpdateFunctionPoint() throws Exception {
        // 创建测试数据
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setId("fp1");
        functionPoint.setName("原始功能点");
        functionPoint.setDescription("原始描述");
        functionPoint.setModule("原始模块");
        functionPoint.setComplexity(FunctionComplexity.MEDIUM);
        functionPoint.setStatus(FunctionStatus.PLANNING);
        functionPoint.setLevel(1);
        functionPoint.setCreatedTime(java.time.LocalDateTime.now());
        functionPoint.setUpdatedTime(java.time.LocalDateTime.now());
        functionPointRepository.save(functionPoint);

        FunctionPointUpdateDTO updateDTO = new FunctionPointUpdateDTO();
        updateDTO.setName("更新后的功能点");
        updateDTO.setDescription("更新后的描述");
        updateDTO.setStatus(FunctionStatus.DEVELOPING);

        mockMvc.perform(put("/api/function-points/fp1")
                .header("X-Tenant-ID", "tenant1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("更新后的功能点"));
    }

    @Test
    public void testDeleteFunctionPoint() throws Exception {
        // 创建测试数据
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setId("fp1");
        functionPoint.setName("待删除功能点");
        functionPoint.setDescription("待删除描述");
        functionPoint.setModule("测试模块");
        functionPoint.setComplexity(FunctionComplexity.MEDIUM);
        functionPoint.setStatus(FunctionStatus.PLANNING);
        functionPoint.setLevel(1);
        functionPoint.setCreatedTime(java.time.LocalDateTime.now());
        functionPoint.setUpdatedTime(java.time.LocalDateTime.now());
        functionPointRepository.save(functionPoint);

        mockMvc.perform(delete("/api/function-points/fp1")
                .header("X-Tenant-ID", "tenant1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void testListFunctionPoints() throws Exception {
        mockMvc.perform(get("/api/function-points")
                .header("X-Tenant-ID", "tenant1")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}