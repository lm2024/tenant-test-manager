package com.tenant.fileio.service;

import com.tenant.fileio.dto.ImportTaskDTO;
import com.tenant.fileio.dto.ExportTaskDTO;
import com.tenant.fileio.dto.TaskProgressDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件导入导出服务接口
 * 
 * @author system
 * @since 1.0.0
 */
public interface FileImportExportService {
    
    /**
     * 批量上传文件并导入
     * 
     * @param files 上传的文件列表
     * @param entityClass 实体类
     * @param tenantId 租户ID
     * @return 任务ID列表
     */
    <T> List<String> batchImport(List<MultipartFile> files, Class<T> entityClass, String tenantId);
    
    /**
     * 导出数据到文件
     * 
     * @param exportTask 导出任务信息
     * @return 任务ID
     */
    <T> String export(ExportTaskDTO<T> exportTask);
    
    /**
     * 查询任务进度
     * 
     * @param taskId 任务ID
     * @return 任务进度信息
     */
    TaskProgressDTO getTaskProgress(String taskId);
    
    /**
     * 取消任务
     * 
     * @param taskId 任务ID
     * @return 是否成功
     */
    boolean cancelTask(String taskId);
    
    /**
     * 暂停任务
     * 
     * @param taskId 任务ID
     * @return 是否成功
     */
    boolean pauseTask(String taskId);
    
    /**
     * 恢复任务
     * 
     * @param taskId 任务ID
     * @return 是否成功
     */
    boolean resumeTask(String taskId);
    
    /**
     * 删除任务
     * 
     * @param taskId 任务ID
     * @return 是否成功
     */
    boolean deleteTask(String taskId);
}