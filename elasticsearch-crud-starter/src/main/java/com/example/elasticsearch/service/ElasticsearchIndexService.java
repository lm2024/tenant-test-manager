package com.example.elasticsearch.service;

import java.util.List;
import java.util.Map;

/**
 * Elasticsearch 索引管理服务接口
 * 
 * @author Kiro
 */
public interface ElasticsearchIndexService {

    /**
     * 获取所有索引信息
     * 
     * @return 索引信息列表
     */
    List<Map<String, Object>> listIndices();

    /**
     * 创建索引
     * 
     * @param indexName 索引名称
     * @param mapping 索引映射配置
     * @return 是否创建成功
     */
    boolean createIndex(String indexName, Map<String, Object> mapping);

    /**
     * 删除索引
     * 
     * @param indexName 索引名称
     * @return 是否删除成功
     */
    boolean deleteIndex(String indexName);

    /**
     * 检查索引是否存在
     * 
     * @param indexName 索引名称
     * @return 是否存在
     */
    boolean indexExists(String indexName);

    /**
     * 获取索引映射信息
     * 
     * @param indexName 索引名称
     * @return 映射信息
     */
    Map<String, Object> getIndexMapping(String indexName);

    /**
     * 获取索引统计信息
     * 
     * @param indexName 索引名称
     * @return 统计信息
     */
    Map<String, Object> getIndexStats(String indexName);

    /**
     * 获取集群健康状态
     * 
     * @return 集群健康状态
     */
    Map<String, Object> getClusterHealth();

    /**
     * 刷新索引
     * 
     * @param indexName 索引名称
     * @return 是否刷新成功
     */
    boolean refreshIndex(String indexName);
}