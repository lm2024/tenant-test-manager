package com.common.fileio.model;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 导入导出任务模型
 * 用于在Redis队列中存储任务信息
 */
@Data
public class ImportExportTask implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 任务ID
     */
    private String taskId;
    
    /**
     * 任务类型：导入/导出
     */
    private TaskType type;
    
    /**
     * 任务状态
     */
    private TaskStatus status;
    
    /**
     * 文件路径列表
     */
    private List<String> filePaths;
    
    /**
     * 处理器名称
     */
    private String processorName;
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 任务参数
     */
    private Map<String, Object> params;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 任务类型枚举
     */
    public enum TaskType {
        /**
         * 导入任务
         */
        IMPORT,
        
        /**
         * 导出任务
         */
        EXPORT
    }
    
    /**
     * 任务状态枚举
     */
    public enum TaskStatus {
        /**
         * 等待中
         */
        WAITING,
        
        /**
         * 运行中
         */
        RUNNING,
        
        /**
         * 已暂停
         */
        PAUSED,
        
        /**
         * 成功完成
         */
        SUCCESS,
        
        /**
         * 失败
         */
        FAILED,
        
        /**
         * 已停止
         */
        STOPPED,
        
        /**
         * 已删除
         */
        DELETED
    }
}