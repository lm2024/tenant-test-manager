package com.example.functiondemand.controller;

import com.example.functiondemand.FunctionDemandApplication;
import com.example.functiondemand.category.dto.CategoryCreateDTO;
import com.example.functiondemand.category.dto.CategoryUpdateDTO;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.common.enums.CategoryType;
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
 * 分类控制器集成测试
 */
@SpringBootTest(classes = FunctionDemandApplication.class)
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
public class CategoryControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        categoryRepository.deleteAll();
    }

    @Test
    public void testCreateCategory() throws Exception {
        CategoryCreateDTO createDTO = new CategoryCreateDTO();
        createDTO.setName("测试分类");
        createDTO.setDescription("测试分类描述");
        createDTO.setType(CategoryType.FUNCTION);
        createDTO.setSortOrder(1);

        mockMvc.perform(post("/api/categories")
                .header("X-Tenant-ID", "tenant1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("测试分类"));
    }

    @Test
    public void testGetCategory() throws Exception {
        // 创建测试数据
        Category category = new Category();
        category.setId("cat1");
        category.setName("测试分类");
        category.setDescription("测试描述");
        category.setType(CategoryType.FUNCTION);
        category.setSortOrder(1);
        category.setLevel(1);
        category.setCreatedTime(java.time.LocalDateTime.now());
        category.setUpdatedTime(java.time.LocalDateTime.now());
        categoryRepository.save(category);

        mockMvc.perform(get("/api/categories/cat1")
                .header("X-Tenant-ID", "tenant1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("测试分类"));
    }

    @Test
    public void testListCategories() throws Exception {
        mockMvc.perform(get("/api/categories")
                .header("X-Tenant-ID", "tenant1")
                .param("type", "FUNCTION"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}