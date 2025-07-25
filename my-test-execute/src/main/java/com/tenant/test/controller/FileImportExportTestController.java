package com.tenant.test.controller;

import com.common.fileio.controller.FileImportExportController;
import com.common.fileio.model.ApiResponse;
import com.common.fileio.model.ExportRequest;
import com.common.fileio.model.TaskProgress;
import com.tenant.routing.annotation.TenantSwitchHeader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "文件导入导出测试", description = "用于测试文件导入导出功能")
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
    @ApiOperation(value = "导入测试数据", notes = "上传Excel文件并导入测试数据")
    public ResponseEntity<ApiResponse<String>> importTestData(
            @ApiParam(value = "上传文件列表", required = true) @RequestParam("files") List<MultipartFile> files,
            @ApiParam(value = "租户ID", required = true) @RequestHeader("X-Tenant-ID") String tenantId) {
        
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
    @ApiOperation(value = "导出测试数据", notes = "导出测试数据到Excel文件")
    public ResponseEntity<ApiResponse<String>> exportTestData(
            @RequestBody(required = false) Map<String, Object> queryParams,
            @ApiParam(value = "租户ID", required = true) @RequestHeader("X-Tenant-ID") String tenantId) {
        
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
    @ApiOperation(value = "导出测试数据", notes = "导出测试数据到Csv文件")
    public ResponseEntity<ApiResponse<String>> exportTestDataCsv(
            @RequestBody(required = false) Map<String, Object> queryParams,
            @ApiParam(value = "租户ID", required = true) @RequestHeader("X-Tenant-ID") String tenantId) {

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
    @ApiOperation(value = "查询任务进度", notes = "查询导入/导出任务的进度")
    public ResponseEntity<ApiResponse<TaskProgress>> getProgress(
            @ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        
        // 调用文件导入导出模块的进度查询接口
        return fileImportExportController.getProgress(taskId);
    }
    
    /**
     * 停止任务
     * 
     * @param taskId 任务ID
     * @return 操作结果
     */
    @PostMapping("/stop/{taskId}")
    @ApiOperation(value = "停止任务", notes = "停止正在执行的导入/导出任务")
    public ResponseEntity<ApiResponse<String>> stopTask(
            @ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        
        // 调用文件导入导出模块的任务停止接口
        return fileImportExportController.stopTask(taskId);
    }
    
    /**
     * 删除任务x
     * 
     * @param taskId 任务ID
     * @return 操作结果
     */
    @DeleteMapping("/delete/{taskId}")
    @ApiOperation(value = "删除任务", notes = "删除导入/导出任务及相关文件")
    public ResponseEntity<ApiResponse<String>> deleteTask(
            @ApiParam(value = "任务ID", required = true) @PathVariable String taskId,
            @RequestParam(value = "deleteFiles", required = false, defaultValue = "false") boolean deleteFiles) {
        
        // 调用文件导入导出模块的任务删除接口
        return fileImportExportController.deleteTask(taskId, deleteFiles);
    }
    
    /**
     * 下载导出文件
     * 
     * @param taskId 任务ID
     * @param response HTTP响应
     */
    @GetMapping("/download/{taskId}")
    @ApiOperation(value = "下载导出文件", notes = "下载导出任务生成的文件")
    public void downloadFile(
            @ApiParam(value = "任务ID", required = true) @PathVariable String taskId,
            HttpServletResponse response) throws IOException {
        
        // 调用文件导入导出模块的文件下载接口
        fileImportExportController.downloadFile(taskId, response);
    }
}