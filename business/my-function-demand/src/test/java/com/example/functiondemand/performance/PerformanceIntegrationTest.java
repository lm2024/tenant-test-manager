package com.example.functiondemand.performance;

import com.example.functiondemand.FunctionDemandApplication;
import com.example.functiondemand.requirement.dto.RequirementCreateDTO;
import com.example.functiondemand.function.dto.FunctionPointCreateDTO;
import com.example.functiondemand.category.dto.CategoryCreateDTO;
import com.example.functiondemand.common.enums.RequirementStatus;
import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.enums.FunctionStatus;
import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.requirement.repository.RequirementRepository;
import com.example.functiondemand.function.repository.FunctionPointRepository;
import com.example.functiondemand.category.repository.CategoryRepository;
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
 * 性能集成测试
 * 测试批量操作和高并发场景的性能表现
 */
@SpringBootTest(classes = FunctionDemandApplication.class)
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

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        requirementRepository.deleteAll();
        functionPointRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void testBatchCreateRequirements() throws Exception {
        // 测试批量创建需求的性能
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 10; i++) {
            RequirementCreateDTO dto = new RequirementCreateDTO();
            dto.setTitle("性能测试需求 " + i);
            dto.setDescription("性能测试需求描述 " + i);
            dto.setPriority(RequirementPriority.MEDIUM);
            dto.setStatus(RequirementStatus.DRAFT);
            dto.setSource("性能测试");

            mockMvc.perform(post("/api/requirements")
                    .header("X-Tenant-ID", "tenant1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk());
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("批量创建10个需求耗时: " + (endTime - startTime) + "ms");
    }

    @Test
    public void testBatchCreateFunctionPoints() throws Exception {
        // 测试批量创建功能点的性能
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 10; i++) {
            FunctionPointCreateDTO dto = new FunctionPointCreateDTO();
            dto.setName("性能测试功能点 " + i);
            dto.setDescription("性能测试功能点描述 " + i);
            dto.setModule("性能测试模块");
            dto.setComplexity(FunctionComplexity.MEDIUM);
            dto.setStatus(FunctionStatus.PLANNING);

            mockMvc.perform(post("/api/function-points")
                    .header("X-Tenant-ID", "tenant1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk());
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("批量创建10个功能点耗时: " + (endTime - startTime) + "ms");
    }

    @Test
    public void testBatchCreateCategories() throws Exception {
        // 测试批量创建分类的性能
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 10; i++) {
            CategoryCreateDTO dto = new CategoryCreateDTO();
            dto.setName("性能测试分类 " + i);
            dto.setDescription("性能测试分类描述 " + i);
            dto.setType(CategoryType.FUNCTION);
            dto.setSortOrder(i + 1);

            mockMvc.perform(post("/api/categories")
                    .header("X-Tenant-ID", "tenant1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk());
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("批量创建10个分类耗时: " + (endTime - startTime) + "ms");
    }
}