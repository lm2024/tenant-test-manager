package com.common.fileio.service;

import com.common.fileio.config.FileImportExportProperties;
import com.common.fileio.exception.DataProcessException;
import com.common.fileio.exception.ProcessorNotFoundException;
import com.common.fileio.model.ExportRequest;
import com.common.fileio.model.ImportExportTask;
import com.common.fileio.processor.DataProcessor;
import com.common.fileio.processor.ProcessorContext;
import com.common.fileio.queue.TaskQueueManager;
import com.common.fileio.util.CsvUtils;
import com.common.fileio.util.ExcelUtils;
import com.common.fileio.util.FileUtils;
import com.tenant.routing.core.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 导出服务
 * 处理数据导出相关的业务逻辑
 */
@Slf4j
public class ExportService {
    
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
    public ExportService(TaskQueueManager taskQueueManager, 
                        ProcessorContext processorContext,
                        FileUtils fileUtils,
                        FileImportExportProperties properties) {
        this.taskQueueManager = taskQueueManager;
        this.processorContext = processorContext;
        this.fileUtils = fileUtils;
        this.properties = properties;
    }
    
    /**
     * 提交导出任务
     * 
     * @param request 导出请求
     * @return 任务ID
     */
    public String submitExportTask(ExportRequest request) {
        log.info("提交导出任务: 处理器={}, 租户={}, 文件类型={}", 
                request.getProcessorName(), request.getTenantId(), request.getFileType());
        
        // 检查处理器是否存在
        if (!processorExists(request.getProcessorName())) {
            throw new ProcessorNotFoundException(request.getProcessorName());
        }
        
        // 创建任务
        String taskId = UUID.randomUUID().toString();
        ImportExportTask task = new ImportExportTask();
        task.setTaskId(taskId);
        task.setType(ImportExportTask.TaskType.EXPORT);
        task.setStatus(ImportExportTask.TaskStatus.WAITING);
        task.setProcessorName(request.getProcessorName());
        task.setTenantId(request.getTenantId());
        task.setParams(request.getQueryParams());
        task.setFileType(request.getFileType());
        task.setCreateTime(new Date());
        
        // 入队
        taskQueueManager.enqueueTask(task);
        
        return taskId;
    }
    
    /**
     * 处理导出任务
     * 
     * @param task 导入导出任务
     */
    @Async("exportTaskExecutor")
    public void processExportTask(ImportExportTask task) {
        log.info("处理导出任务: {}, 处理器: {}, 租户: {}", 
                task.getTaskId(), task.getProcessorName(), task.getTenantId());
        
        try {
            // 设置租户上下文
            String tenantId = task.getTenantId();
            TenantContextHolder.setTenantId(tenantId);
            
            // 更新任务状态
            taskQueueManager.updateProgress(task.getTaskId(), 0, "RUNNING", "开始处理导出任务");
            
            // 获取处理器
            DataProcessor<?> processor = processorContext.getProcessor(task.getProcessorName());
            
            // 获取查询参数
            Map<String, Object> params = task.getParams();
            
            // 获取总记录数
            long totalCount = processor.count(params);
            log.info("导出数据总量: {}", totalCount);
            
            if (totalCount == 0) {
                // 没有数据可导出
                taskQueueManager.updateProgress(task.getTaskId(), 100, "SUCCESS", "导出完成，没有符合条件的数据");
                TenantContextHolder.clear();
                return;
            }
            
            // 生成导出文件路径
            String fileName = "export_" + task.getTaskId();
            String fileType = task.getFileType(); // 默认导出Excel
            
            // 如果参数中指定了文件类型，则使用指定的类型
//            if (params != null && params.containsKey("fileType")) {
//                fileType = params.get("fileType").toString();
//            }
            
            // 生成导出文件路径
            String exportFilePath = fileUtils.generateExportPath(fileName, getFileExtension(fileType));
            
            // 导出数据
            exportToFile(exportFilePath, task.getProcessorName(), task);
            
            // 更新任务状态
            taskQueueManager.updateProgress(task.getTaskId(), 100, "SUCCESS", "导出完成", exportFilePath);
            
            // 清理租户上下文
            TenantContextHolder.clear();
            
        } catch (Exception e) {
            log.error("处理导出任务失败: {}", task.getTaskId(), e);
            taskQueueManager.updateProgress(task.getTaskId(), 0, "FAILED", "导出失败: " + e.getMessage());
            
            // 清理租户上下文
            TenantContextHolder.clear();
        }
    }
    
    /**
     * 导出数据到文件
     * 
     * @param filePath 文件路径
     * @param processorName 处理器名称
     * @param task 导入导出任务
     */
    @SuppressWarnings("unchecked")
    private void exportToFile(String filePath, String processorName, ImportExportTask task) {
        log.info("导出数据到文件: {}, 处理器: {}", filePath, processorName);
        
        try {
            // 获取处理器
            DataProcessor<?> processor = processorContext.getProcessor(processorName);
            
            // 获取查询参数
            Map<String, Object> params = task.getParams();
            
            // 获取总记录数
            long totalCount = processor.count(params);
            
            // 获取批处理大小
            int batchSize = properties.getExportConfig().getMaxBatchSize();
            
            // 获取表头
            List<String> headers = processor.getExportHeaders();
            
            // 获取文件类型
            String fileType = fileUtils.getFileExtension(filePath);
            
            // 根据文件类型选择不同的导出方式
            if ("csv".equalsIgnoreCase(fileType)) {
                // 导出为CSV
                exportToCsv(filePath, processor, params, headers, batchSize, totalCount, task);
            } else {
                // 导出为Excel
                exportToExcel(filePath, processor, params, headers, batchSize, totalCount, task);
            }
            
        } catch (Exception e) {
            log.error("导出数据到文件失败: {}", filePath, e);
            throw new DataProcessException("导出数据到文件失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 导出数据到Excel文件
     * 
     * @param filePath 文件路径
     * @param processor 数据处理器
     * @param params 查询参数
     * @param headers 表头列表
     * @param batchSize 批处理大小
     * @param totalCount 总记录数
     * @param task 导入导出任务
     */
    @SuppressWarnings("unchecked")
    private void exportToExcel(String filePath, DataProcessor<?> processor, Map<String, Object> params,
                              List<String> headers, int batchSize, long totalCount, ImportExportTask task) {
        
        // 使用ExcelUtils分批导出
        ExcelUtils.writeExcelInBatches(filePath, headers, (page, size) -> {
            // 检查任务是否应该停止
            if (taskQueueManager.shouldStop(task.getTaskId())) {
                log.info("任务已停止: {}", task.getTaskId());
                return null;
            }
            
            try {
                // 查询数据
                List<?> entities = processor.queryExportData(params, page, size);
                
                // 转换为导出格式
                List<Map<String, Object>> exportData = processor.convertToExportFormat((List) entities);
                
                // 更新进度
                int progress = (int) Math.min(100, (page + 1) * size * 100 / totalCount);
                taskQueueManager.updateProgress(task.getTaskId(), progress, "RUNNING", 
                        "已导出 " + Math.min((page + 1) * size, totalCount) + "/" + totalCount + " 条数据");
                
                return exportData;
            } catch (Exception e) {
                log.error("查询导出数据失败", e);
                throw new DataProcessException("查询导出数据失败: " + e.getMessage(), e);
            }
        }, batchSize);
    }
    
    /**
     * 导出数据到CSV文件
     * 
     * @param filePath 文件路径
     * @param processor 数据处理器
     * @param params 查询参数
     * @param headers 表头列表
     * @param batchSize 批处理大小
     * @param totalCount 总记录数
     * @param task 导入导出任务
     */
    @SuppressWarnings("unchecked")
    private void exportToCsv(String filePath, DataProcessor<?> processor, Map<String, Object> params,
                            List<String> headers, int batchSize, long totalCount, ImportExportTask task) {
        
        // 使用CsvUtils分批导出
        CsvUtils.writeCsvInBatches(filePath, headers, (page, size) -> {
            // 检查任务是否应该停止
            if (taskQueueManager.shouldStop(task.getTaskId())) {
                log.info("任务已停止: {}", task.getTaskId());
                return null;
            }
            
            try {
                // 查询数据
                List<?> entities = processor.queryExportData(params, page, size);
                
                // 转换为导出格式
                List<Map<String, Object>> exportData = processor.convertToExportFormat((List) entities);
                
                // 更新进度
                int progress = (int) Math.min(100, (page + 1) * size * 100 / totalCount);
                taskQueueManager.updateProgress(task.getTaskId(), progress, "RUNNING", 
                        "已导出 " + Math.min((page + 1) * size, totalCount) + "/" + totalCount + " 条数据");
                
                return exportData;
            } catch (Exception e) {
                log.error("查询导出数据失败", e);
                throw new DataProcessException("查询导出数据失败: " + e.getMessage(), e);
            }
        }, batchSize);
    }
    
    /**
     * 获取文件扩展名
     * 
     * @param fileType 文件类型
     * @return 文件扩展名
     */
    private String getFileExtension(String fileType) {
        if ("csv".equalsIgnoreCase(fileType)) {
            return "csv";
        } else if ("txt".equalsIgnoreCase(fileType)) {
            return "txt";
        } else {
            return "xlsx";
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