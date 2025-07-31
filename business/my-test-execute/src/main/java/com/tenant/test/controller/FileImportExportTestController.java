package com.tenant.test.controller;

import com.common.fileio.controller.FileImportExportController;
import com.common.fileio.model.ApiResponse;
import com.common.fileio.model.ExportRequest;
import com.common.fileio.model.TaskProgress;
import com.tenant.routing.annotation.TenantSwitchHeader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件导入导出测试控制器
 * 用于测试文件导入导出功能
 */
@RestController
@RequestMapping("/api/test/file-io")
@Tag(name = "文件导入导出测试", description = "用于测试文件导入导出功能")
public class FileImportExportTestController {
    
    @Autowired
    private FileImportExportController fileImportExportController;
    
    /**
     * 导入测试数据
     * 
     * @param files 上传文件列表
     * @param tenantId 租户ID
     * @return 任务ID
     */
    @PostMapping("/import")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    @Operation(summary = "导入测试数据", description = "上传Excel文件并导入测试数据")
    public ResponseEntity<ApiResponse<String>> importTestData(
            @Parameter(description = "上传文件列表") @RequestParam("files") List<MultipartFile> files,
            @Parameter(description = "租户ID") @RequestHeader("X-Tenant-ID") String tenantId) {
        
        // 调用文件导入导出模块的导入接口
        return fileImportExportController.importFiles(files, "excel", "testData", tenantId);
    }
    
    /**
     * 导出测试数据
     * excel
     * @param tenantId 租户ID
     * @return 任务ID
     */
    @PostMapping("/export")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    @Operation(summary = "导出测试数据", description = "导出测试数据到Excel文件")
    public ResponseEntity<ApiResponse<String>> exportTestData(
            @RequestBody(required = false) Map<String, Object> queryParams,
            @Parameter(description = "租户ID") @RequestHeader("X-Tenant-ID") String tenantId) {
        
        // 创建导出请求
        ExportRequest request = new ExportRequest();
        request.setProcessorName("testData");
        request.setFileType("excel");
        request.setFileName("test_data_export");
        request.setIncludeHeader(true);
        request.setQueryParams(queryParams != null ? queryParams : new HashMap<>());
        
        // 调用文件导入导出模块的导出接口
        return fileImportExportController.exportData(request, tenantId);
    }



    /**
     * 导出测试数据
     * cvs
     * @param tenantId 租户ID
     * @return 任务ID
     */
    @PostMapping("/exportCsv")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    @Operation(summary = "导出测试数据CSV", description = "导出测试数据到Csv文件")
    public ResponseEntity<ApiResponse<String>> exportTestDataCsv(
            @RequestBody(required = false) Map<String, Object> queryParams,
            @Parameter(description = "租户ID") @RequestHeader("X-Tenant-ID") String tenantId) {

        // 创建导出请求
        ExportRequest request = new ExportRequest();
        request.setProcessorName("testData");
        request.setFileType("csv");
        request.setFileName("test_data_export");
        request.setIncludeHeader(true);
        request.setQueryParams(queryParams != null ? queryParams : new HashMap<>());

        // 调用文件导入导出模块的导出接口
        return fileImportExportController.exportData(request, tenantId);
    }

    /**
     * 查询任务进度
     * 
     * @param taskId 任务ID
     * @return 任务进度
     */
    @GetMapping("/progress/{taskId}")
    @Operation(summary = "查询任务进度", description = "查询导入/导出任务的进度")
    public ResponseEntity<ApiResponse<TaskProgress>> getProgress(
            @Parameter(description = "任务ID") @PathVariable String taskId) {
        
        return fileImportExportController.getProgress(taskId);
    }
    
    /**
     * 停止任务
     * 
     * @param taskId 任务ID
     * @return 操作结果
     */
    @PostMapping("/stop/{taskId}")
    @Operation(summary = "停止任务", description = "停止正在执行的导入/导出任务")
    public ResponseEntity<ApiResponse<String>> stopTask(
            @Parameter(description = "任务ID") @PathVariable String taskId) {
        
        return fileImportExportController.stopTask(taskId);
    }
    
    /**
     * 删除任务
     * 
     * @param taskId 任务ID
     * @param deleteFiles 是否删除文件
     * @return 操作结果
     */
    @DeleteMapping("/delete/{taskId}")
    @Operation(summary = "删除任务", description = "删除导入/导出任务及相关文件")
    public ResponseEntity<ApiResponse<String>> deleteTask(
            @Parameter(description = "任务ID") @PathVariable String taskId,
            @Parameter(description = "是否删除文件") @RequestParam(value = "deleteFiles", required = false, defaultValue = "false") boolean deleteFiles) {
        
        return fileImportExportController.deleteTask(taskId, deleteFiles);
    }
    
    /**
     * 下载导出文件
     * 
     * @param taskId 任务ID
     * @param response HTTP响应
     * @throws IOException IO异常
     */
    @GetMapping("/download/{taskId}")
    @Operation(summary = "下载导出文件", description = "下载导出任务生成的文件")
    public void downloadFile(
            @Parameter(description = "任务ID") @PathVariable String taskId,
            HttpServletResponse response) throws IOException {
        
        fileImportExportController.downloadFile(taskId, response);
    }
}