package com.common.fileio.service;

import com.common.fileio.exception.TaskNotFoundException;
import com.common.fileio.model.TaskProgress;
import com.common.fileio.queue.TaskQueueManager;
import com.common.fileio.util.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * 任务管理服务
 * 处理任务进度查询、暂停、终止、删除等功能
 */
@Slf4j
public class TaskManagementService {
    
    private final TaskQueueManager taskQueueManager;
    private final FileUtils fileUtils;
    
    /**
     * 构造函数
     * 
     * @param taskQueueManager 任务队列管理器
     * @param fileUtils 文件工具类
     */
    public TaskManagementService(TaskQueueManager taskQueueManager, FileUtils fileUtils) {
        this.taskQueueManager = taskQueueManager;
        this.fileUtils = fileUtils;
    }
    
    /**
     * 获取任务进度
     * 
     * @param taskId 任务ID
     * @return 任务进度
     * @throws TaskNotFoundException 如果任务不存在
     */
    public TaskProgress getTaskProgress(String taskId) {
        log.debug("获取任务进度: {}", taskId);
        return taskQueueManager.getProgress(taskId);
    }
    
    /**
     * 停止任务
     * 
     * @param taskId 任务ID
     * @throws TaskNotFoundException 如果任务不存在
     */
    public void stopTask(String taskId) {
        log.info("停止任务: {}", taskId);
        
        // 检查任务是否存在
        if (!taskQueueManager.taskExists(taskId)) {
            throw new TaskNotFoundException(taskId);
        }
        
        // 停止任务
        taskQueueManager.stopTask(taskId);
    }
    
    /**
     * 删除任务
     * 
     * @param taskId 任务ID
     * @param deleteFiles 是否删除关联文件
     * @throws TaskNotFoundException 如果任务不存在
     */
    public void deleteTask(String taskId, boolean deleteFiles) {
        log.info("删除任务: {}, 删除文件: {}", taskId, deleteFiles);
        
        // 检查任务是否存在
        if (!taskQueueManager.taskExists(taskId)) {
            throw new TaskNotFoundException(taskId);
        }
        
        // 获取任务进度
        TaskProgress progress = taskQueueManager.getProgress(taskId);
        
        // 删除关联文件
        if (deleteFiles) {
            // 删除结果文件
            if (progress.getResultFilePath() != null && !progress.getResultFilePath().isEmpty()) {
                fileUtils.deleteFile(progress.getResultFilePath());
            }
            
            // TODO: 删除源文件（需要从任务中获取源文件路径）
        }
        
        // 删除任务
        taskQueueManager.deleteTask(taskId);
    }
    
    /**
     * 获取导出文件
     * 
     * @param taskId 任务ID
     * @return 文件对象
     * @throws TaskNotFoundException 如果任务不存在
     * @throws IllegalStateException 如果任务未完成或文件不存在
     */
    public File getExportFile(String taskId) {
        log.info("获取导出文件: {}", taskId);
        
        // 获取任务进度
        TaskProgress progress = taskQueueManager.getProgress(taskId);
        
        // 检查任务状态
        if (!"SUCCESS".equals(progress.getStatus())) {
            throw new IllegalStateException("任务未完成或已失败: " + progress.getStatus());
        }
        
        // 检查结果文件
        String filePath = progress.getResultFilePath();
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalStateException("任务没有结果文件");
        }
        
        // 获取文件
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalStateException("结果文件不存在: " + filePath);
        }
        
        return file;
    }
    
    /**
     * 清理过期任务
     * 
     * @param expiredHours 过期小时数
     * @return 清理的任务数量
     */
    public int cleanupExpiredTasks(int expiredHours) {
        log.info("清理过期任务: {} 小时", expiredHours);
        
        // TODO: 实现过期任务清理逻辑
        
        return 0;
    }
    
    /**
     * 批量删除任务
     * 
     * @param taskIds 任务ID列表
     * @param deleteFiles 是否删除关联文件
     * @return 删除的任务数量
     */
    public int deleteTasks(List<String> taskIds, boolean deleteFiles) {
        log.info("批量删除任务: {}, 删除文件: {}", taskIds, deleteFiles);
        
        int count = 0;
        for (String taskId : taskIds) {
            try {
                deleteTask(taskId, deleteFiles);
                count++;
            } catch (Exception e) {
                log.warn("删除任务失败: {}", taskId, e);
            }
        }
        
        return count;
    }
}