package com.example.functiondemand.controller;

import com.example.functiondemand.FunctionDemandApp;
import com.example.functiondemand.dto.category.CategoryCreateDTO;
import com.example.functiondemand.dto.category.CategoryUpdateDTO;
import com.example.functiondemand.entity.Category;
import com.example.functiondemand.enums.CategoryType;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 分类目录管理控制器集成测试
 * 测试REST API的完整功能，包括多租户支持和批量操作
 */
@SpringBootTest(classes = FunctionDemandApp.class)
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

    private static final String TENANT_ID_HEADER = "X-Tenant-Id";
    private static final String TENANT_1 = "tenant1";
    private static final String TENANT_2 = "tenant2";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // 清理测试数据
        categoryRepository.deleteAll();
    }

    @Test
    void testCreateCategory() throws Exception {
        CategoryCreateDTO createDTO = new CategoryCreateDTO();
        createDTO.setName("测试分类");
        createDTO.setDescription("这是一个测试分类");
        createDTO.setType(CategoryType.REQUIREMENT);

        mockMvc.perform(post("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("测试分类"))
                .andExpect(jsonPath("$.data.description").value("这是一个测试分类"))
                .andExpect(jsonPath("$.data.type").value("REQUIREMENT"));
    }

    @Test
    void testGetCategoryById() throws Exception {
        // 创建测试数据
        Category category = new Category();
        category.setName("测试分类");
        category.setDescription("测试描述");
        category.setType(CategoryType.REQUIREMENT);
        category = categoryRepository.save(category);

        mockMvc.perform(get("/api/categories/{id}", category.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(category.getId()))
                .andExpect(jsonPath("$.data.name").value("测试分类"));
    }

    @Test
    void testUpdateCategory() throws Exception {
        // 创建测试数据
        Category category = new Category();
        category.setName("原始分类");
        category.setDescription("原始描述");
        category.setType(CategoryType.REQUIREMENT);
        category = categoryRepository.save(category);

        CategoryUpdateDTO updateDTO = new CategoryUpdateDTO();
        updateDTO.setName("更新后的分类");
        updateDTO.setDescription("更新后的描述");

        mockMvc.perform(put("/api/categories/{id}", category.getId())
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("更新后的分类"))
                .andExpect(jsonPath("$.data.description").value("更新后的描述"));
    }

    @Test
    void testDeleteCategory() throws Exception {
        // 创建测试数据
        Category category = new Category();
        category.setName("待删除分类");
        category.setDescription("待删除描述");
        category.setType(CategoryType.REQUIREMENT);
        category = categoryRepository.save(category);

        mockMvc.perform(delete("/api/categories/{id}", category.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证数据已删除
        mockMvc.perform(get("/api/categories/{id}", category.getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllCategories() throws Exception {
        // 创建测试数据
        Category cat1 = new Category();
        cat1.setName("分类1");
        cat1.setDescription("描述1");
        cat1.setType(CategoryType.REQUIREMENT);

        Category cat2 = new Category();
        cat2.setName("分类2");
        cat2.setDescription("描述2");
        cat2.setType(CategoryType.FUNCTION_POINT);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));

        mockMvc.perform(get("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(2)))
                .andExpect(jsonPath("$.data.content[0].name", anyOf(is("分类1"), is("分类2"))))
                .andExpect(jsonPath("$.data.content[1].name", anyOf(is("分类1"), is("分类2"))));
    }

    @Test
    void testBatchCreateCategories() throws Exception {
        CategoryCreateDTO cat1 = new CategoryCreateDTO();
        cat1.setName("批量分类1");
        cat1.setDescription("批量描述1");
        cat1.setType(CategoryType.REQUIREMENT);

        CategoryCreateDTO cat2 = new CategoryCreateDTO();
        cat2.setName("批量分类2");
        cat2.setDescription("批量描述2");
        cat2.setType(CategoryType.FUNCTION_POINT);

        List<CategoryCreateDTO> batchCreateList = Arrays.asList(cat1, cat2);

        mockMvc.perform(post("/api/categories/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchCreateList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].name", anyOf(is("批量分类1"), is("批量分类2"))))
                .andExpect(jsonPath("$.data[1].name", anyOf(is("批量分类1"), is("批量分类2"))));
    }

    @Test
    void testBatchUpdateCategories() throws Exception {
        // 创建测试数据
        Category cat1 = new Category();
        cat1.setName("原始分类1");
        cat1.setDescription("原始描述1");
        cat1.setType(CategoryType.REQUIREMENT);

        Category cat2 = new Category();
        cat2.setName("原始分类2");
        cat2.setDescription("原始描述2");
        cat2.setType(CategoryType.FUNCTION_POINT);

        List<Category> savedCats = categoryRepository.saveAll(Arrays.asList(cat1, cat2));

        CategoryUpdateDTO update1 = new CategoryUpdateDTO();
        update1.setId(savedCats.get(0).getId());
        update1.setName("更新分类1");

        CategoryUpdateDTO update2 = new CategoryUpdateDTO();
        update2.setId(savedCats.get(1).getId());
        update2.setName("更新分类2");

        List<CategoryUpdateDTO> batchUpdateList = Arrays.asList(update1, update2);

        mockMvc.perform(put("/api/categories/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchUpdateList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    void testBatchDeleteCategories() throws Exception {
        // 创建测试数据
        Category cat1 = new Category();
        cat1.setName("待删除分类1");
        cat1.setDescription("待删除描述1");
        cat1.setType(CategoryType.REQUIREMENT);

        Category cat2 = new Category();
        cat2.setName("待删除分类2");
        cat2.setDescription("待删除描述2");
        cat2.setType(CategoryType.FUNCTION_POINT);

        List<Category> savedCats = categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        List<Long> idsToDelete = Arrays.asList(savedCats.get(0).getId(), savedCats.get(1).getId());

        mockMvc.perform(delete("/api/categories/batch")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(idsToDelete)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证数据已删除
        mockMvc.perform(get("/api/categories/{id}", savedCats.get(0).getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/categories/{id}", savedCats.get(1).getId())
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetCategoryTree() throws Exception {
        // 创建树形测试数据
        Category parent = new Category();
        parent.setName("父分类");
        parent.setDescription("父分类描述");
        parent.setType(CategoryType.REQUIREMENT);
        parent = categoryRepository.save(parent);

        Category child = new Category();
        child.setName("子分类");
        child.setDescription("子分类描述");
        child.setType(CategoryType.REQUIREMENT);
        child.setParentId(parent.getId());
        child.setPath(parent.getPath() + parent.getId() + "/");
        child.setLevel(parent.getLevel() + 1);
        categoryRepository.save(child);

        mockMvc.perform(get("/api/categories/tree")
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
        // 在租户1中创建分类
        CategoryCreateDTO tenant1Cat = new CategoryCreateDTO();
        tenant1Cat.setName("租户1分类");
        tenant1Cat.setDescription("租户1描述");
        tenant1Cat.setType(CategoryType.REQUIREMENT);

        mockMvc.perform(post("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tenant1Cat)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 在租户2中创建分类
        CategoryCreateDTO tenant2Cat = new CategoryCreateDTO();
        tenant2Cat.setName("租户2分类");
        tenant2Cat.setDescription("租户2描述");
        tenant2Cat.setType(CategoryType.FUNCTION_POINT);

        mockMvc.perform(post("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tenant2Cat)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 验证租户1只能看到自己的数据
        mockMvc.perform(get("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].name").value("租户1分类"));

        // 验证租户2只能看到自己的数据
        mockMvc.perform(get("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_2)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].name").value("租户2分类"));
    }

    @Test
    void testGetCategoriesByType() throws Exception {
        // 创建不同类型的分类
        Category reqCategory = new Category();
        reqCategory.setName("需求分类");
        reqCategory.setDescription("需求分类描述");
        reqCategory.setType(CategoryType.REQUIREMENT);

        Category fpCategory = new Category();
        fpCategory.setName("功能点分类");
        fpCategory.setDescription("功能点分类描述");
        fpCategory.setType(CategoryType.FUNCTION_POINT);

        categoryRepository.saveAll(Arrays.asList(reqCategory, fpCategory));

        // 按类型查询需求分类
        mockMvc.perform(get("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("type", "REQUIREMENT")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].type").value("REQUIREMENT"));

        // 按类型查询功能点分类
        mockMvc.perform(get("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("type", "FUNCTION_POINT")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].type").value("FUNCTION_POINT"));
    }

    @Test
    void testSearchCategories() throws Exception {
        // 创建测试数据
        Category cat1 = new Category();
        cat1.setName("用户管理分类");
        cat1.setDescription("用户相关功能分类");
        cat1.setType(CategoryType.REQUIREMENT);

        Category cat2 = new Category();
        cat2.setName("系统配置分类");
        cat2.setDescription("系统配置相关分类");
        cat2.setType(CategoryType.FUNCTION_POINT);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));

        // 按名称搜索
        mockMvc.perform(get("/api/categories/search")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("keyword", "用户")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(1)))
                .andExpect(jsonPath("$.data.content[0].name").value("用户管理分类"));
    }

    @Test
    void testCategoryValidation() throws Exception {
        // 测试必填字段验证
        CategoryCreateDTO invalidCat = new CategoryCreateDTO();
        // 不设置必填字段

        mockMvc.perform(post("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCat)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void testCategoryNotFound() throws Exception {
        mockMvc.perform(get("/api/categories/{id}", 99999L)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void testGetCategoriesByParent() throws Exception {
        // 创建父分类
        Category parent = new Category();
        parent.setName("父分类");
        parent.setDescription("父分类描述");
        parent.setType(CategoryType.REQUIREMENT);
        parent = categoryRepository.save(parent);

        // 创建子分类
        Category child1 = new Category();
        child1.setName("子分类1");
        child1.setDescription("子分类1描述");
        child1.setType(CategoryType.REQUIREMENT);
        child1.setParentId(parent.getId());
        child1.setPath(parent.getPath() + parent.getId() + "/");
        child1.setLevel(parent.getLevel() + 1);

        Category child2 = new Category();
        child2.setName("子分类2");
        child2.setDescription("子分类2描述");
        child2.setType(CategoryType.REQUIREMENT);
        child2.setParentId(parent.getId());
        child2.setPath(parent.getPath() + parent.getId() + "/");
        child2.setLevel(parent.getLevel() + 1);

        categoryRepository.saveAll(Arrays.asList(child1, child2));

        // 查询子分类
        mockMvc.perform(get("/api/categories/children/{parentId}", parent.getId())
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(2)));
    }

    @Test
    void testTreeHierarchyValidation() throws Exception {
        // 创建5层嵌套分类来测试层级限制
        Category level1 = new Category();
        level1.setName("一级分类");
        level1.setType(CategoryType.REQUIREMENT);
        level1 = categoryRepository.save(level1);

        Category level2 = new Category();
        level2.setName("二级分类");
        level2.setType(CategoryType.REQUIREMENT);
        level2.setParentId(level1.getId());
        level2.setPath(level1.getPath() + level1.getId() + "/");
        level2.setLevel(level1.getLevel() + 1);
        level2 = categoryRepository.save(level2);

        Category level3 = new Category();
        level3.setName("三级分类");
        level3.setType(CategoryType.REQUIREMENT);
        level3.setParentId(level2.getId());
        level3.setPath(level2.getPath() + level2.getId() + "/");
        level3.setLevel(level2.getLevel() + 1);
        level3 = categoryRepository.save(level3);

        Category level4 = new Category();
        level4.setName("四级分类");
        level4.setType(CategoryType.REQUIREMENT);
        level4.setParentId(level3.getId());
        level4.setPath(level3.getPath() + level3.getId() + "/");
        level4.setLevel(level3.getLevel() + 1);
        level4 = categoryRepository.save(level4);

        Category level5 = new Category();
        level5.setName("五级分类");
        level5.setType(CategoryType.REQUIREMENT);
        level5.setParentId(level4.getId());
        level5.setPath(level4.getPath() + level4.getId() + "/");
        level5.setLevel(level4.getLevel() + 1);
        level5 = categoryRepository.save(level5);

        // 尝试创建第6级分类，应该失败
        CategoryCreateDTO level6DTO = new CategoryCreateDTO();
        level6DTO.setName("六级分类");
        level6DTO.setType(CategoryType.REQUIREMENT);
        level6DTO.setParentId(level5.getId());

        mockMvc.perform(post("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(level6DTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
}