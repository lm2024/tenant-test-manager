package com.tenant.es.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * 搜索响应DTO
 * 
 * @author system
 * @since 1.0.0
 */
@Data
public class SearchResponseDTO<T> {
    
    /**
     * 总记录数
     */
    private long total;
    
    /**
     * 当前页数据
     */
    private List<T> data;
    
    /**
     * 聚合结果
     */
    private Map<String, Object> aggregations;
    
    /**
     * 查询耗时（毫秒）
     */
    private long took;
    
    /**
     * 是否超时
     */
    private boolean timedOut;
    
    /**
     * 分片信息
     */
    private Map<String, Object> shards;
}