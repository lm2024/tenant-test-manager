package com.tenant.fileio.dto;

import lombok.Data;
import java.util.Map;

/**
 * 导出任务DTO
 * 
 * @author system
 * @since 1.0.0
 */
@Data
public class ExportTaskDTO<T> {
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 导出文件名
     */
    private String fileName;
    
    /**
     * 文件类型：excel, csv, txt
     */
    private String fileType;
    
    /**
     * 实体类
     */
    private Class<T> entityClass;
    
    /**
     * 查询条件
     */
    private Map<String, Object> queryConditions;
    
    /**
     * 字段转义映射
     */
    private Map<String, String> fieldMapping;
    
    /**
     * 批处理大小
     */
    private int batchSize = 10000;
}