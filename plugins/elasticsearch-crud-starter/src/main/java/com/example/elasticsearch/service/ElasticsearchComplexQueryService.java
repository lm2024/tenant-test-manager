package com.example.elasticsearch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Elasticsearch 复杂查询服务接口
 * 
 * @author Kiro
 */
public interface ElasticsearchComplexQueryService {
    
    /**
     * 根据条件搜索
     * 
     * @param conditions 搜索条件
     * @param pageable 分页参数
     * @return 搜索结果
     */
    Page<Object> searchByConditions(Map<String, Object> conditions, Pageable pageable);
    
    /**
     * 根据字段聚合
     * 
     * @param field 聚合字段
     * @param conditions 查询条件
     * @return 聚合结果
     */
    Map<String, Object> aggregateByField(String field, Map<String, Object> conditions);
}