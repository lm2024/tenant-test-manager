package com.tenant.fileio.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 任务进度DTO
 * 
 * @author system
 * @since 1.0.0
 */
@Data
public class TaskProgressDTO {
    
    /**
     * 任务ID
     */
    private String taskId;
    
    /**
     * 任务状态：PENDING, RUNNING, PAUSED, COMPLETED, FAILED, CANCELLED
     */
    private String status;
    
    /**
     * 总记录数
     */
    private long totalCount;
    
    /**
     * 已处理记录数
     */
    private long processedCount;
    
    /**
     * 成功记录数
     */
    private long successCount;
    
    /**
     * 失败记录数
     */
    private long failedCount;
    
    /**
     * 进度百分比
     */
    private double progressPercent;
    
    /**
     * 错误信息
     */
    private String errorMessage;
    
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    
    /**
     * 文件下载路径（导出任务）
     */
    private String downloadPath;
}