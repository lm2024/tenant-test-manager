package com.tenant.es.dto;

import lombok.Data;
import java.util.Map;

/**
 * 搜索请求DTO
 * 
 * @author system
 * @since 1.0.0
 */
@Data
public class SearchRequestDTO {
    
    /**
     * 索引名称
     */
    private String indexName;
    
    /**
     * 查询条件
     */
    private Map<String, Object> query;
    
    /**
     * 排序字段
     */
    private Map<String, String> sort;
    
    /**
     * 页码（从0开始）
     */
    private int page = 0;
    
    /**
     * 每页大小
     */
    private int size = 10;
    
    /**
     * 高亮字段
     */
    private String[] highlightFields;
    
    /**
     * 聚合查询
     */
    private Map<String, Object> aggregations;
    
    /**
     * 返回字段
     */
    private String[] includeFields;
    
    /**
     * 排除字段
     */
    private String[] excludeFields;
    
    /**
     * 返回类型
     */
    private Class<?> resultType;
}