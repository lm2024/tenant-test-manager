package com.tenant.es.dto;

import lombok.Data;
import java.util.Map;

/**
 * 数据同步任务DTO
 * 
 * @author system
 * @since 1.0.0
 */
@Data
public class SyncTaskDTO {
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 表名
     */
    private String tableName;
    
    /**
     * 索引名
     */
    private String indexName;
    
    /**
     * 同步类型：FULL（全量）, INCREMENTAL（增量）
     */
    private String syncType;
    
    /**
     * 查询条件
     */
    private Map<String, Object> conditions;
    
    /**
     * 字段映射
     */
    private Map<String, String> fieldMapping;
    
    /**
     * 批处理大小
     */
    private int batchSize = 1000;
    
    /**
     * 是否删除目标数据
     */
    private boolean deleteTarget = false;
}