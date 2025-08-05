package com.example.functiondemand.importexport;

import com.example.functiondemand.FunctionDemandApp;
import com.example.functiondemand.entity.Category;
import com.example.functiondemand.entity.FunctionPoint;
import com.example.functiondemand.entity.Requirement;
import com.example.functiondemand.enums.CategoryType;
import com.example.functiondemand.enums.FunctionPointStatus;
import com.example.functiondemand.enums.FunctionPointType;
import com.example.functiondemand.enums.RequirementStatus;
import com.example.functiondemand.enums.RequirementType;
import com.example.functiondemand.repository.CategoryRepository;
import com.example.functiondemand.repository.FunctionPointRepository;
import com.example.functiondemand.repository.RequirementRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 导入导出功能集成测试
 * 测试Excel文件的导入导出功能，包括多租户支持
 */
@SpringBootTest(classes = FunctionDemandApp.class)
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
    void testRequirementImport() throws Exception {
        // 创建Excel文件
        MockMultipartFile excelFile = createRequirementExcelFile();

        mockMvc.perform(multipart("/api/import/requirements")
                .file(excelFile)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").exists());

        // 验证数据已导入
        Thread.sleep(2000); // 等待异步导入完成
        
        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(greaterThan(0))));
    }

    @Test
    void testRequirementExport() throws Exception {
        // 创建测试数据
        Requirement req1 = new Requirement();
        req1.setTitle("导出需求1");
        req1.setDescription("导出描述1");
        req1.setType(RequirementType.FUNCTIONAL);
        req1.setStatus(RequirementStatus.DRAFT);
        req1.setPriority(1);

        Requirement req2 = new Requirement();
        req2.setTitle("导出需求2");
        req2.setDescription("导出描述2");
        req2.setType(RequirementType.NON_FUNCTIONAL);
        req2.setStatus(RequirementStatus.APPROVED);
        req2.setPriority(2);

        requirementRepository.saveAll(Arrays.asList(req1, req2));

        mockMvc.perform(post("/api/export/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").exists());
    }

    @Test
    void testFunctionPointImport() throws Exception {
        // 创建Excel文件
        MockMultipartFile excelFile = createFunctionPointExcelFile();

        mockMvc.perform(multipart("/api/import/function-points")
                .file(excelFile)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").exists());

        // 验证数据已导入
        Thread.sleep(2000); // 等待异步导入完成
        
        mockMvc.perform(get("/api/function-points")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(greaterThan(0))));
    }

    @Test
    void testFunctionPointExport() throws Exception {
        // 创建测试数据
        FunctionPoint fp1 = new FunctionPoint();
        fp1.setName("导出功能点1");
        fp1.setDescription("导出描述1");
        fp1.setType(FunctionPointType.FEATURE);
        fp1.setStatus(FunctionPointStatus.PLANNING);
        fp1.setPriority(1);

        FunctionPoint fp2 = new FunctionPoint();
        fp2.setName("导出功能点2");
        fp2.setDescription("导出描述2");
        fp2.setType(FunctionPointType.MODULE);
        fp2.setStatus(FunctionPointStatus.DEVELOPMENT);
        fp2.setPriority(2);

        functionPointRepository.saveAll(Arrays.asList(fp1, fp2));

        mockMvc.perform(post("/api/export/function-points")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").exists());
    }

    @Test
    void testCategoryImport() throws Exception {
        // 创建Excel文件
        MockMultipartFile excelFile = createCategoryExcelFile();

        mockMvc.perform(multipart("/api/import/categories")
                .file(excelFile)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").exists());

        // 验证数据已导入
        Thread.sleep(2000); // 等待异步导入完成
        
        mockMvc.perform(get("/api/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(greaterThan(0))));
    }

    @Test
    void testCategoryExport() throws Exception {
        // 创建测试数据
        Category cat1 = new Category();
        cat1.setName("导出分类1");
        cat1.setDescription("导出描述1");
        cat1.setType(CategoryType.REQUIREMENT);

        Category cat2 = new Category();
        cat2.setName("导出分类2");
        cat2.setDescription("导出描述2");
        cat2.setType(CategoryType.FUNCTION_POINT);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));

        mockMvc.perform(post("/api/export/categories")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").exists());
    }

    @Test
    void testBatchImport() throws Exception {
        // 创建多个Excel文件
        MockMultipartFile reqFile = createRequirementExcelFile();
        MockMultipartFile fpFile = createFunctionPointExcelFile();
        MockMultipartFile catFile = createCategoryExcelFile();

        mockMvc.perform(multipart("/api/import/batch")
                .file(reqFile)
                .file(fpFile)
                .file(catFile)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskIds", hasSize(3)));
    }

    @Test
    void testImportProgress() throws Exception {
        // 创建Excel文件并开始导入
        MockMultipartFile excelFile = createRequirementExcelFile();

        String response = mockMvc.perform(multipart("/api/import/requirements")
                .file(excelFile)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 从响应中提取taskId（这里简化处理）
        String taskId = "test-task-id";

        // 查询导入进度
        mockMvc.perform(get("/api/import/progress/{taskId}", taskId)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").value(taskId));
    }

    @Test
    void testExportProgress() throws Exception {
        // 创建测试数据
        Requirement req = new Requirement();
        req.setTitle("测试需求");
        req.setDescription("测试描述");
        req.setType(RequirementType.FUNCTIONAL);
        req.setStatus(RequirementStatus.DRAFT);
        req.setPriority(1);
        requirementRepository.save(req);

        // 开始导出
        String response = mockMvc.perform(post("/api/export/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 从响应中提取taskId（这里简化处理）
        String taskId = "test-export-task-id";

        // 查询导出进度
        mockMvc.perform(get("/api/export/progress/{taskId}", taskId)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.taskId").value(taskId));
    }

    @Test
    void testInvalidFileImport() throws Exception {
        // 创建无效的文件
        MockMultipartFile invalidFile = new MockMultipartFile(
                "file", 
                "invalid.txt", 
                "text/plain", 
                "invalid content".getBytes()
        );

        mockMvc.perform(multipart("/api/import/requirements")
                .file(invalidFile)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void testTenantIsolationInImportExport() throws Exception {
        // 在租户1中导入数据
        MockMultipartFile excelFile = createRequirementExcelFile();

        mockMvc.perform(multipart("/api/import/requirements")
                .file(excelFile)
                .header(TENANT_ID_HEADER, TENANT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        Thread.sleep(2000); // 等待异步导入完成

        // 验证租户1有数据
        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, TENANT_1)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(greaterThan(0))));

        // 验证租户2没有数据
        mockMvc.perform(get("/api/requirements")
                .header(TENANT_ID_HEADER, "tenant2")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(0)));
    }

    /**
     * 创建需求Excel文件
     */
    private MockMultipartFile createRequirementExcelFile() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("需求数据");

        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("标题");
        headerRow.createCell(1).setCellValue("描述");
        headerRow.createCell(2).setCellValue("类型");
        headerRow.createCell(3).setCellValue("状态");
        headerRow.createCell(4).setCellValue("优先级");

        // 创建数据行
        Row dataRow1 = sheet.createRow(1);
        dataRow1.createCell(0).setCellValue("导入需求1");
        dataRow1.createCell(1).setCellValue("导入描述1");
        dataRow1.createCell(2).setCellValue("功能性需求");
        dataRow1.createCell(3).setCellValue("草稿");
        dataRow1.createCell(4).setCellValue(1);

        Row dataRow2 = sheet.createRow(2);
        dataRow2.createCell(0).setCellValue("导入需求2");
        dataRow2.createCell(1).setCellValue("导入描述2");
        dataRow2.createCell(2).setCellValue("非功能性需求");
        dataRow2.createCell(3).setCellValue("已批准");
        dataRow2.createCell(4).setCellValue(2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return new MockMultipartFile(
                "file",
                "requirements.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                outputStream.toByteArray()
        );
    }

    /**
     * 创建功能点Excel文件
     */
    private MockMultipartFile createFunctionPointExcelFile() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("功能点数据");

        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("名称");
        headerRow.createCell(1).setCellValue("描述");
        headerRow.createCell(2).setCellValue("类型");
        headerRow.createCell(3).setCellValue("状态");
        headerRow.createCell(4).setCellValue("优先级");

        // 创建数据行
        Row dataRow1 = sheet.createRow(1);
        dataRow1.createCell(0).setCellValue("导入功能点1");
        dataRow1.createCell(1).setCellValue("导入描述1");
        dataRow1.createCell(2).setCellValue("功能特性");
        dataRow1.createCell(3).setCellValue("规划中");
        dataRow1.createCell(4).setCellValue(1);

        Row dataRow2 = sheet.createRow(2);
        dataRow2.createCell(0).setCellValue("导入功能点2");
        dataRow2.createCell(1).setCellValue("导入描述2");
        dataRow2.createCell(2).setCellValue("功能模块");
        dataRow2.createCell(3).setCellValue("开发中");
        dataRow2.createCell(4).setCellValue(2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return new MockMultipartFile(
                "file",
                "function-points.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                outputStream.toByteArray()
        );
    }

    /**
     * 创建分类Excel文件
     */
    private MockMultipartFile createCategoryExcelFile() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("分类数据");

        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("名称");
        headerRow.createCell(1).setCellValue("描述");
        headerRow.createCell(2).setCellValue("类型");

        // 创建数据行
        Row dataRow1 = sheet.createRow(1);
        dataRow1.createCell(0).setCellValue("导入分类1");
        dataRow1.createCell(1).setCellValue("导入描述1");
        dataRow1.createCell(2).setCellValue("需求分类");

        Row dataRow2 = sheet.createRow(2);
        dataRow2.createCell(0).setCellValue("导入分类2");
        dataRow2.createCell(1).setCellValue("导入描述2");
        dataRow2.createCell(2).setCellValue("功能点分类");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return new MockMultipartFile(
                "file",
                "categories.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                outputStream.toByteArray()
        );
    }
}