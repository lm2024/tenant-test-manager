package com.tenant.cvs.controller;

import com.tenant.config.tenant.TenantSwitchHeader;
import com.tenant.cvs.config.CvsConfig;
import com.tenant.cvs.model.CvsTask;
import com.tenant.cvs.model.CvsProgress;
import com.tenant.cvs.queue.CvsTaskQueue;
import com.tenant.cvs.service.ImportService;
import com.tenant.cvs.util.FileUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import io.swagger.annotations.*;

@Api(tags = "高性能导入导出", description = "支持Excel/CSV/TXT多格式高性能导入导出，所有接口需在header中传递X-Tenant-ID实现租户数据隔离。支持多文件、进度查询、任务终止、下载等功能。")
@RestController
@RequestMapping("/api/com.tenant.cvs")
public class CvsImportExportController {
    @Autowired private CvsConfig config;
    @Autowired private ImportService importService;
    @Autowired private CvsTaskQueue taskQueue;

    private static final List<String> SUPPORTED_TYPES = Arrays.asList("xls", "xlsx", "csv", "txt");

    @ApiOperation(value = "批量导入数据", notes = "支持多文件上传，需在header中传递X-Tenant-ID实现租户切换。文件类型支持xls、xlsx、csv、txt。")
    // 导入接口（支持多文件）
    @PostMapping("/import")
    @TenantSwitchHeader(headerName = "X-Tenant-ID")
    public ResponseEntity<?> importData(
        @ApiParam(value = "上传文件列表，最多20个", required = true) @RequestParam("files") List<MultipartFile> files,
        @ApiParam(value = "导入类型：excel/csv/txt", required = false, defaultValue = "excel") @RequestParam(value = "type", required = false, defaultValue = "excel") String type,
        @ApiParam(value = "租户ID，必填", required = true, example = "tenant001") @RequestHeader(value = "X-Tenant-ID") String tenantId) {
        if (files == null || files.isEmpty()) return fail("未上传文件");
        if (files.size() > config.getImportMaxFiles()) return fail("最多支持" + config.getImportMaxFiles() + "个文件");
        for (MultipartFile file : files) {
            if (!FileUtil.isSupportedType(file.getOriginalFilename(), SUPPORTED_TYPES))
                return fail("不支持的文件类型: " + file.getOriginalFilename());
        }
        try {
            List<String> paths = FileUtil.saveFiles(files, config.getUploadDir());
            String taskId = UUID.randomUUID().toString();
            CvsTask task = new CvsTask();
            task.setTaskId(taskId);
            task.setType(CvsTask.TaskType.IMPORT);
            task.setStatus(CvsTask.TaskStatus.WAITING);
            task.setFilePaths(paths);
            task.setCreateTime(new Date());
            task.setTenantId(tenantId);
            importService.submitImportTask(task);
            return ok(taskId);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }

    @ApiOperation(value = "查询任务进度", notes = "根据任务ID查询导入/导出进度。")
    // 进度查询
    @GetMapping("/progress/{taskId}")
    public ResponseEntity<?> getProgress(
        @ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        CvsProgress progress = taskQueue.getProgress(taskId);
        if (progress == null) return fail("任务不存在");
        return ok(progress);
    }

    @ApiOperation(value = "暂停/终止任务", notes = "根据任务ID暂停或终止导入/导出任务。")
    // 暂停/终止任务
    @PostMapping("/stop/{taskId}")
    public ResponseEntity<?> stopTask(
        @ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        // TODO: 实现暂停/终止逻辑
        taskQueue.setStatus(taskId, "STOPPED");
        return ok("stopped");
    }

    @ApiOperation(value = "删除任务", notes = "根据任务ID删除导入/导出任务及相关文件。")
    // 删除任务
    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(
        @ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        // TODO: 删除任务和文件
        taskQueue.deleteProgress(taskId);
        taskQueue.deleteStatus(taskId);
        return ok("deleted");
    }

    // 统一返回结构
    private ResponseEntity<?> ok(Object data) {
        return ResponseEntity.ok(new ApiResponse(true, data, null));
    }
    private ResponseEntity<?> fail(String msg) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, null, msg));
    }
    @ApiModel(description = "统一API响应结构")
    @Data
    public static class ApiResponse {
        private boolean success;
        private Object data;
        private String error;
        public ApiResponse(boolean success, Object data, String error) {
            this.success = success; this.data = data; this.error = error;
        }
    }
} 