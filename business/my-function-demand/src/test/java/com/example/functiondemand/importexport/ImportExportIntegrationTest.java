package com.example.functiondemand.importexport;

import com.example.functiondemand.FunctionDemandApplication;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.function.entity.FunctionPoint;
import com.example.functiondemand.requirement.entity.Requirement;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.common.enums.FunctionStatus;
import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.RequirementStatus;
import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.category.repository.CategoryRepository;
import com.example.functiondemand.function.repository.FunctionPointRepository;
import com.example.functiondemand.requirement.repository.RequirementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 导入导出集成测试
 * 测试Excel文件的导入导出功能，包括多租户支持
 */
@SpringBootTest(classes = FunctionDemandApplication.class)
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
public class ImportExportIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private FunctionPointRepository functionPointRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        requirementRepository.deleteAll();
        functionPointRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void testExportRequirements() throws Exception {
        // 创建测试数据
        Requirement req1 = new Requirement();
        req1.setId("req1");
        req1.setTitle("测试需求1");
        req1.setDescription("测试需求1描述");
        req1.setPriority(RequirementPriority.HIGH);
        req1.setStatus(RequirementStatus.DRAFT);
        req1.setSource("测试");
        req1.setLevel(1);
        req1.setCreatedTime(LocalDateTime.now());
        req1.setUpdatedTime(LocalDateTime.now());
        requirementRepository.save(req1);

        Requirement req2 = new Requirement();
        req2.setId("req2");
        req2.setTitle("测试需求2");
        req2.setDescription("测试需求2描述");
        req2.setPriority(RequirementPriority.MEDIUM);
        req2.setStatus(RequirementStatus.APPROVED);
        req2.setSource("测试");
        req2.setLevel(1);
        req2.setCreatedTime(LocalDateTime.now());
        req2.setUpdatedTime(LocalDateTime.now());
        requirementRepository.save(req2);

        // 测试导出
        mockMvc.perform(get("/api/export/requirements")
                .header("X-Tenant-ID", "tenant1")
                .param("format", "excel"))
                .andExpect(status().isOk());
    }

    @Test
    public void testExportFunctionPoints() throws Exception {
        // 创建测试数据
        FunctionPoint fp1 = new FunctionPoint();
        fp1.setId("fp1");
        fp1.setName("测试功能点1");
        fp1.setDescription("测试功能点1描述");
        fp1.setModule("测试模块");
        fp1.setComplexity(FunctionComplexity.HIGH);
        fp1.setStatus(FunctionStatus.PLANNING);
        fp1.setLevel(1);
        fp1.setCreatedTime(LocalDateTime.now());
        fp1.setUpdatedTime(LocalDateTime.now());
        functionPointRepository.save(fp1);

        FunctionPoint fp2 = new FunctionPoint();
        fp2.setId("fp2");
        fp2.setName("测试功能点2");
        fp2.setDescription("测试功能点2描述");
        fp2.setModule("测试模块");
        fp2.setComplexity(FunctionComplexity.MEDIUM);
        fp2.setStatus(FunctionStatus.DEVELOPING);
        fp2.setLevel(1);
        fp2.setCreatedTime(LocalDateTime.now());
        fp2.setUpdatedTime(LocalDateTime.now());
        functionPointRepository.save(fp2);

        // 测试导出
        mockMvc.perform(get("/api/export/function-points")
                .header("X-Tenant-ID", "tenant1")
                .param("format", "excel"))
                .andExpect(status().isOk());
    }

    @Test
    public void testExportCategories() throws Exception {
        // 创建测试数据
        Category cat1 = new Category();
        cat1.setId("cat1");
        cat1.setName("测试分类1");
        cat1.setDescription("测试分类1描述");
        cat1.setType(CategoryType.FUNCTION);
        cat1.setSortOrder(1);
        cat1.setLevel(1);
        cat1.setCreatedTime(LocalDateTime.now());
        cat1.setUpdatedTime(LocalDateTime.now());
        categoryRepository.save(cat1);

        // 测试导出
        mockMvc.perform(get("/api/export/categories")
                .header("X-Tenant-ID", "tenant1")
                .param("format", "excel"))
                .andExpect(status().isOk());
    }

    @Test
    public void testImportRequirements() throws Exception {
        // 创建模拟Excel文件
        String excelContent = "标题,描述,优先级,状态,来源\n测试需求,测试描述,HIGH,DRAFT,测试";
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "requirements.xlsx", 
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", 
                excelContent.getBytes());

        // 测试导入
        mockMvc.perform(multipart("/api/import/requirements")
                .file(file)
                .header("X-Tenant-ID", "tenant1"))
                .andExpect(status().isOk());
    }
}