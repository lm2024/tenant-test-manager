package com.tenant.es.service;

import com.tenant.es.dto.SearchRequestDTO;
import com.tenant.es.dto.SearchResponseDTO;
import com.tenant.es.dto.SyncTaskDTO;

import java.util.List;
import java.util.Map;

/**
 * Elasticsearch CRUD服务接口
 * 
 * @author system
 * @since 1.0.0
 */
public interface ElasticsearchCrudService {
    
    /**
     * 创建索引
     * 
     * @param indexName 索引名称
     * @param mapping 字段映射
     * @return 是否成功
     */
    boolean createIndex(String indexName, Map<String, Object> mapping);
    
    /**
     * 删除索引
     * 
     * @param indexName 索引名称
     * @return 是否成功
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
     * 保存文档
     * 
     * @param indexName 索引名称
     * @param id 文档ID
     * @param document 文档内容
     * @return 是否成功
     */
    boolean saveDocument(String indexName, String id, Object document);
    
    /**
     * 批量保存文档
     * 
     * @param indexName 索引名称
     * @param documents 文档列表
     * @return 成功数量
     */
    int batchSaveDocuments(String indexName, List<Map<String, Object>> documents);
    
    /**
     * 根据ID获取文档
     * 
     * @param indexName 索引名称
     * @param id 文档ID
     * @param clazz 返回类型
     * @return 文档对象
     */
    <T> T getDocument(String indexName, String id, Class<T> clazz);
    
    /**
     * 删除文档
     * 
     * @param indexName 索引名称
     * @param id 文档ID
     * @return 是否成功
     */
    boolean deleteDocument(String indexName, String id);
    
    /**
     * 搜索文档
     * 
     * @param request 搜索请求
     * @return 搜索结果
     */
    <T> SearchResponseDTO<T> search(SearchRequestDTO request);
    
    /**
     * 从MySQL同步数据到ES
     * 
     * @param syncTask 同步任务
     * @return 任务ID
     */
    String syncFromMySQL(SyncTaskDTO syncTask);
    
    /**
     * 从ES同步数据到MySQL
     * 
     * @param syncTask 同步任务
     * @return 任务ID
     */
    String syncToMySQL(SyncTaskDTO syncTask);
    
    /**
     * 数据一致性检查
     * 
     * @param tenantId 租户ID
     * @param tableName 表名
     * @param indexName 索引名
     * @return 检查结果
     */
    Map<String, Object> checkDataConsistency(String tenantId, String tableName, String indexName);
}