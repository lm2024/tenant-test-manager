package com.common.fileio.controller;

import com.common.fileio.model.ApiResponse;
import com.common.fileio.model.ExportRequest;
import com.common.fileio.model.TaskProgress;
import com.common.fileio.service.ExportService;
import com.common.fileio.service.ImportService;
import com.common.fileio.service.TaskManagementService;
import com.common.fileio.util.FileUtils;
import com.tenant.routing.annotation.TenantSwitchHeader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 文件导入导出控制器
 * 提供文件导入导出相关的REST API
 */
@RestController
@RequestMapping("/api/file-io")
@Api(tags = "文件导入导出", description = "提供文件导入导出相关的API")
@Slf4j
public class FileImportExportController {
    
    private final ImportService importService;
    private final ExportService exportService;
    private final TaskManagementService taskManagementService;
    private final FileUtils fileUtils;
    
    /**
     * 构造函数
     * 
     * @param importService 导入服务
     * @param exportService 导出服务
     * @param taskManagementService 任务管理服务
     * @param fileUtils 文件工具类
     */
    public FileImportExportController(ImportService importService, 
                                     ExportService exportService,
                                     TaskManagementService taskManagementService,
                                     FileUtils fileUtils) {
        this.importService = importService;
        this.exportService = exportService;
        this.taskManagementService = taskManagementService;
        this.fileUtils = fileUtils;
    }
    
    /**
     * 导入文件
     * 
     * @param files 上传文件列表
     * @param fileType 文件类型
     * @param processorName 处理器名称
     * @param tenantId 租户ID
     * @return 任务ID
     */
    @PostMapping("/import")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    @ApiOperation(value = "导入文件", notes = "上传文件并异步导入数据，返回任务ID")
    public ResponseEntity<ApiResponse<String>> importFiles(
            @ApiParam(value = "上传文件列表，最多20个", required = true) @RequestParam("files") List<MultipartFile> files,
            @ApiParam(value = "文件类型：excel/csv/txt", required = false, defaultValue = "excel") @RequestParam(value = "type", required = false, defaultValue = "excel") String fileType,
            @ApiParam(value = "处理器名称", required = true) @RequestParam("processor") String processorName,
            @ApiParam(value = "租户ID", required = true) @RequestHeader("X-Tenant-ID") String tenantId) {
        
        try {
            // 提交导入任务
            String taskId = importService.submitImportTask(files, fileType, processorName, tenantId);
            
            return ResponseEntity.ok(ApiResponse.success(taskId));
        } catch (Exception e) {
            log.error("导入文件失败", e);
            return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
        }
    }
    
    /**
     * 导出数据
     * 
     * @param request 导出请求
     * @param tenantId 租户ID
     * @return 任务ID
     */
    @PostMapping("/export")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    @ApiOperation(value = "导出数据", notes = "异步导出数据，返回任务ID")
    public ResponseEntity<ApiResponse<String>> exportData(
            @ApiParam(value = "导出请求", required = true) @RequestBody ExportRequest request,
            @ApiParam(value = "租户ID", required = true) @RequestHeader("X-Tenant-ID") String tenantId) {
        
        try {
            // 设置租户ID
            request.setTenantId(tenantId);
            
            // 提交导出任务
            String taskId = exportService.submitExportTask(request);
            
            return ResponseEntity.ok(ApiResponse.success(taskId));
        } catch (Exception e) {
            log.error("导出数据失败", e);
            return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
        }
    }
    
    /**
     * 获取任务进度
     * 
     * @param taskId 任务ID
     * @return 任务进度
     */
    @GetMapping("/progress/{taskId}")
    @ApiOperation(value = "获取任务进度", notes = "根据任务ID获取导入/导出任务的进度")
    public ResponseEntity<ApiResponse<TaskProgress>> getProgress(
            @ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        
        try {
            // 获取任务进度
            TaskProgress progress = taskManagementService.getTaskProgress(taskId);
            
            return ResponseEntity.ok(ApiResponse.success(progress));
        } catch (Exception e) {
            log.error("获取任务进度失败", e);
            return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
        }
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
        
        try {
            // 停止任务
            taskManagementService.stopTask(taskId);
            
            return ResponseEntity.ok(ApiResponse.success("任务已停止"));
        } catch (Exception e) {
            log.error("停止任务失败", e);
            return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
        }
    }
    
    /**
     * 删除任务
     * 
     * @param taskId 任务ID
     * @param deleteFiles 是否删除关联文件
     * @return 操作结果
     */
    @DeleteMapping("/delete/{taskId}")
    @ApiOperation(value = "删除任务", notes = "删除导入/导出任务及相关文件")
    public ResponseEntity<ApiResponse<String>> deleteTask(
            @ApiParam(value = "任务ID", required = true) @PathVariable String taskId,
            @ApiParam(value = "是否删除关联文件", required = false, defaultValue = "false") @RequestParam(value = "deleteFiles", required = false, defaultValue = "false") boolean deleteFiles) {
        
        try {
            // 删除任务
            taskManagementService.deleteTask(taskId, deleteFiles);
            
            return ResponseEntity.ok(ApiResponse.success("任务已删除"));
        } catch (Exception e) {
            log.error("删除任务失败", e);
            return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
        }
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
            HttpServletResponse response) {
        
        try {
            // 获取导出文件
            File file = taskManagementService.getExportFile(taskId);
            
            // 设置响应头
            response.setContentType(getContentType(file.getName()));
            response.setHeader("Content-Disposition", "attachment; filename=" + 
                    URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.name()));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            
            // 写入响应
            java.nio.file.Files.copy(file.toPath(), response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("下载文件失败", e);
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            } catch (IOException ex) {
                log.error("发送错误响应失败", ex);
            }
        }
    }
    
    /**
     * 下载导出文件（使用ResponseEntity）
     * 
     * @param taskId 任务ID
     * @return 文件资源
     */
    @GetMapping("/download2/{taskId}")
    @ApiOperation(value = "下载导出文件（使用ResponseEntity）", notes = "下载导出任务生成的文件")
    public ResponseEntity<Resource> downloadFile2(
            @ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        
        try {
            // 获取导出文件
            File file = taskManagementService.getExportFile(taskId);
            
            // 创建资源
            Resource resource = new FileSystemResource(file);
            
            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + 
                    URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.name()));
            headers.add(HttpHeaders.CONTENT_TYPE, getContentType(file.getName()));
            headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            log.error("下载文件失败", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 获取内容类型
     * 
     * @param fileName 文件名
     * @return 内容类型
     */
    private String getContentType(String fileName) {
        String fileExtension = fileUtils.getFileExtension(fileName);
        
        switch (fileExtension.toLowerCase()) {
            case "xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "xls":
                return "application/vnd.ms-excel";
            case "csv":
                return "text/csv";
            case "txt":
                return "text/plain";
            default:
                return "application/octet-stream";
        }
    }
}