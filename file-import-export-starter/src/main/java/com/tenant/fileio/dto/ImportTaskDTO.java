package com.tenant.fileio.dto;

import lombok.Data;

/**
 * 导入任务DTO
 * 
 * @author system
 * @since 1.0.0
 */
@Data
public class ImportTaskDTO {
    
    /**
     * 任务ID
     */
    private String taskId;
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 文件类型：excel, csv, txt
     */
    private String fileType;
    
    /**
     * 实体类名
     */
    private String entityClassName;
    
    /**
     * 是否包含ID（用于更新操作）
     */
    private boolean hasId = false;
    
    /**
     * 批处理大小
     */
    private int batchSize = 1000;
}