package com.common.fileio.service;

import com.common.fileio.config.FileImportExportProperties;
import com.common.fileio.exception.DataProcessException;
import com.common.fileio.exception.ProcessorNotFoundException;
import com.common.fileio.exception.UnsupportedFileTypeException;
import com.common.fileio.model.FileInfo;
import com.common.fileio.model.ImportExportTask;
import com.common.fileio.model.ImportRequest;
import com.common.fileio.processor.DataProcessor;
import com.common.fileio.processor.ProcessorContext;
import com.common.fileio.queue.TaskQueueManager;
import com.common.fileio.util.FileUtils;
import com.tenant.routing.core.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 导入服务
 * 处理文件导入相关的业务逻辑
 */
@Slf4j
public class ImportService {
    
    private final TaskQueueManager taskQueueManager;
    private final ProcessorContext processorContext;
    private final FileUtils fileUtils;
    private final FileImportExportProperties properties;
    
    /**
     * 构造函数
     * 
     * @param taskQueueManager 任务队列管理器
     * @param processorContext 处理器上下文
     * @param fileUtils 文件工具类
     * @param properties 配置属性
     */
    public ImportService(TaskQueueManager taskQueueManager, 
                        ProcessorContext processorContext,
                        FileUtils fileUtils,
                        FileImportExportProperties properties) {
        this.taskQueueManager = taskQueueManager;
        this.processorContext = processorContext;
        this.fileUtils = fileUtils;
        this.properties = properties;
    }
    
    /**
     * 提交导入任务（多文件）
     * 
     * @param files 上传文件列表
     * @param fileType 文件类型
     * @param processorName 处理器名称
     * @param tenantId 租户ID
     * @return 任务ID
     */
    public String submitImportTask(List<MultipartFile> files, String fileType, String processorName, String tenantId) {
        log.info("提交导入任务: 文件数量={}, 文件类型={}, 处理器={}, 租户={}", 
                files.size(), fileType, processorName, tenantId);
        
        // 检查文件数量
        if (files.size() > properties.getImportConfig().getMaxFiles()) {
            throw new IllegalArgumentException("文件数量超过限制: " + properties.getImportConfig().getMaxFiles());
        }
        
        // 检查处理器是否存在
        if (!processorExists(processorName)) {
            throw new ProcessorNotFoundException(processorName);
        }
        
        // 保存文件
        List<FileInfo> fileInfos = new ArrayList<>();
        for (MultipartFile file : files) {
            FileInfo fileInfo = fileUtils.saveFile(file);
            fileInfos.add(fileInfo);
        }
        
        // 创建导入请求
        ImportRequest importRequest = new ImportRequest();
        importRequest.setFiles(fileInfos);
        importRequest.setFileType(fileType);
        importRequest.setProcessorName(processorName);
        importRequest.setTenantId(tenantId);
        
        return submitImportTask(importRequest);
    }
    
    /**
     * 提交导入任务
     * 
     * @param request 导入请求
     * @return 任务ID
     */
    public String submitImportTask(ImportRequest request) {
        log.info("提交导入任务: 处理器={}, 租户={}", request.getProcessorName(), request.getTenantId());
        
        // 创建任务
        String taskId = UUID.randomUUID().toString();
        ImportExportTask task = new ImportExportTask();
        task.setTaskId(taskId);
        task.setType(ImportExportTask.TaskType.IMPORT);
        task.setStatus(ImportExportTask.TaskStatus.WAITING);
        task.setProcessorName(request.getProcessorName());
        task.setTenantId(request.getTenantId());
        task.setCreateTime(new Date());
        
        // 设置文件路径
        List<String> filePaths = new ArrayList<>();
        for (FileInfo fileInfo : request.getFiles()) {
            filePaths.add(fileInfo.getSavedPath());
        }
        task.setFilePaths(filePaths);
        
        // 入队
        taskQueueManager.enqueueTask(task);
        
        return taskId;
    }
    
    /**
     * 处理导入任务
     * 
     * @param task 导入导出任务
     */
    @Async("importTaskExecutor")
    public void processImportTask(ImportExportTask task) {
        log.info("处理导入任务: {}, 处理器: {}, 租户: {}", 
                task.getTaskId(), task.getProcessorName(), task.getTenantId());
        
        try {
            // 设置租户上下文
            String tenantId = task.getTenantId();
            TenantContextHolder.setTenantId(tenantId);
            
            // 更新任务状态
            taskQueueManager.updateProgress(task.getTaskId(), 0, "RUNNING", "开始处理导入任务");
            
            // 处理文件
            AtomicInteger totalCount = new AtomicInteger(0);
            List<String> filePaths = task.getFilePaths();
            
            for (int i = 0; i < filePaths.size(); i++) {
                String filePath = filePaths.get(i);
                
                // 检查任务是否应该停止
                if (taskQueueManager.shouldStop(task.getTaskId())) {
                    log.info("任务已停止: {}", task.getTaskId());
                    return;
                }
                
                // 处理单个文件
                processFile(filePath, task.getProcessorName(), task);
                
                // 更新进度
                int progress = (i + 1) * 100 / filePaths.size();
                taskQueueManager.updateProgress(task.getTaskId(), progress, "RUNNING", 
                        "已处理 " + (i + 1) + "/" + filePaths.size() + " 个文件");
            }
            
            // 更新任务状态
            taskQueueManager.updateProgress(task.getTaskId(), 100, "SUCCESS", "导入完成");
            
            // 清理租户上下文
            TenantContextHolder.clear();
            
        } catch (Exception e) {
            log.error("处理导入任务失败: {}", task.getTaskId(), e);
            taskQueueManager.updateProgress(task.getTaskId(), 0, "FAILED", "导入失败: " + e.getMessage());
            
            // 清理租户上下文
            TenantContextHolder.clear();
        }
    }
    
    /**
     * 处理单个文件
     * 
     * @param filePath 文件路径
     * @param processorName 处理器名称
     * @param task 导入导出任务
     */
    @SuppressWarnings("unchecked")
    private void processFile(String filePath, String processorName, ImportExportTask task) {
        log.info("处理文件: {}, 处理器: {}", filePath, processorName);
        
        try {
            // 获取处理器
            DataProcessor<?> processor = processorContext.getProcessor(processorName);
            
            // 获取文件类型
            String fileType = fileUtils.getFileExtension(filePath);
            if (!fileUtils.isSupportedType(fileType)) {
                throw new UnsupportedFileTypeException(fileType);
            }
            
            // 解析文件数据
            List<?> entities = processor.parseFileData(filePath, fileType);
            log.info("解析到 {} 条数据", entities.size());
            
            // 批量保存数据
            int batchSize = properties.getImportConfig().getMaxBatchSize();
            int totalSize = entities.size();
            
            for (int i = 0; i < totalSize; i += batchSize) {
                // 检查任务是否应该停止
                if (taskQueueManager.shouldStop(task.getTaskId())) {
                    log.info("任务已停止: {}", task.getTaskId());
                    return;
                }
                
                // 获取当前批次
                int endIndex = Math.min(i + batchSize, totalSize);
                List<?> batch = entities.subList(i, endIndex);
                
                // 保存批次
                saveBatch(batch, processor, task);
                
                // 更新进度
                int progress = (i + batch.size()) * 100 / totalSize;
                taskQueueManager.updateProgress(task.getTaskId(), progress, "RUNNING", 
                        "已导入 " + (i + batch.size()) + "/" + totalSize + " 条数据");
            }
            
        } catch (Exception e) {
            log.error("处理文件失败: {}", filePath, e);
            throw new DataProcessException("处理文件失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 批量保存数据
     * 
     * @param batch 数据批次
     * @param processor 数据处理器
     * @param task 导入导出任务
     */
    @SuppressWarnings("unchecked")
    private void saveBatch(List<?> batch, DataProcessor<?> processor, ImportExportTask task) {
        if (batch == null || batch.isEmpty()) {
            return;
        }
        
        try {
            // 调用处理器保存数据
            ((DataProcessor<Object>) processor).saveBatch((List<Object>) batch);
            log.info("批量保存 {} 条数据", batch.size());
        } catch (Exception e) {
            log.error("批量保存数据失败", e);
            throw new DataProcessException("批量保存数据失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 检查处理器是否存在
     * 
     * @param processorName 处理器名称
     * @return 是否存在
     */
    private boolean processorExists(String processorName) {
        try {
            processorContext.getProcessor(processorName);
            return true;
        } catch (ProcessorNotFoundException e) {
            return false;
        }
    }
}