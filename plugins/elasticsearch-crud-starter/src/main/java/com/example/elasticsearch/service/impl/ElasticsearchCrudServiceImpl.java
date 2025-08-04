package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.example.elasticsearch.service.ElasticsearchCrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticsearchCrudServiceImpl<T> implements ElasticsearchCrudService<T> {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElasticsearchCrudServiceImpl.class);

    private final RestHighLevelClient restHighLevelClient;
    private final ElasticsearchCrudProperties properties;
    private final ObjectMapper objectMapper;
    private final Class<T> entityClass;

    public ElasticsearchCrudServiceImpl(RestHighLevelClient restHighLevelClient,
                                       ElasticsearchCrudProperties properties,
                                       Class<T> entityClass) {
        this.restHighLevelClient = restHighLevelClient;
        this.properties = properties;
        this.entityClass = entityClass;
        this.objectMapper = new ObjectMapper();
    }
    
    /**
     * 创建包含认证信息的RequestOptions
     */
    private RequestOptions createAuthenticatedRequestOptions() {
        RequestOptions.Builder optionsBuilder = RequestOptions.DEFAULT.toBuilder();
        if (properties.getUsername() != null && !properties.getUsername().trim().isEmpty() 
            && properties.getPassword() != null && !properties.getPassword().trim().isEmpty()) {
            // 添加Basic认证头
            String auth = properties.getUsername() + ":" + properties.getPassword();
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            optionsBuilder.addHeader("Authorization", "Basic " + encodedAuth);
            log.debug("添加ES认证头信息");
        }
        return optionsBuilder.build();
    }

    @Override
    public T save(T entity) {
        log.info("保存实体: {}", entity);
        try {
            // 获取索引名称和ID
            String indexName = getIndexName(entity);
            String id = getId(entity);
            
            // 使用ES官方高级客户端API
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.id(id);
            indexRequest.source(objectMapper.writeValueAsString(entity), XContentType.JSON);
            
            IndexResponse response = restHighLevelClient.index(indexRequest, createAuthenticatedRequestOptions());
            
            if (response.status().getStatus() == 200 || response.status().getStatus() == 201) {
                log.info("实体保存成功，ID: {}, 状态: {}", id, response.status());
                return entity;
            } else {
                throw new RuntimeException("保存实体失败，状态: " + response.status());
            }
        } catch (Exception e) {
            log.error("保存实体失败", e);
            throw new RuntimeException("保存实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        log.info("批量保存实体，数量: {}", entities.size());
        try {
            // 使用ES官方高级客户端API
            BulkRequest bulkRequest = new BulkRequest();
            
            for (T entity : entities) {
                String indexName = getIndexName(entity);
                String id = getId(entity);
                
                IndexRequest indexRequest = new IndexRequest(indexName);
                indexRequest.id(id);
                indexRequest.source(objectMapper.writeValueAsString(entity), XContentType.JSON);
                bulkRequest.add(indexRequest);
            }
            
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, createAuthenticatedRequestOptions());
            
            if (response.hasFailures()) {
                log.error("批量保存失败: {}", response.buildFailureMessage());
                throw new RuntimeException("批量保存失败: " + response.buildFailureMessage());
            }
            
            log.info("批量保存成功，数量: {}", entities.size());
            return entities;
        } catch (Exception e) {
            log.error("批量保存实体失败", e);
            throw new RuntimeException("批量保存实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public T findById(String id) {
        log.info("根据ID查找实体: {}", id);
        try {
            String indexName = getIndexName();
            
            // 使用ES官方高级客户端API
            GetRequest getRequest = new GetRequest(indexName, id);
            GetResponse response = restHighLevelClient.get(getRequest, createAuthenticatedRequestOptions());
            
            if (response.isExists()) {
                T entity = objectMapper.readValue(response.getSourceAsString(), entityClass);
                log.info("找到实体，ID: {}", id);
                return entity;
            } else {
                log.warn("实体不存在，ID: {}", id);
                return null;
            }
        } catch (Exception e) {
            log.error("查找实体失败，ID: {}", id, e);
            throw new RuntimeException("查找实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteById(String id) {
        log.info("根据ID删除实体: {}", id);
        try {
            String indexName = getIndexName();
            
            // 使用ES官方高级客户端API
            DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
            DeleteResponse response = restHighLevelClient.delete(deleteRequest, createAuthenticatedRequestOptions());
            
            if (response.status().getStatus() == 200) {
                log.info("删除实体成功，ID: {}", id);
                return true;
            } else {
                log.warn("删除实体失败，ID: {}, 状态: {}", id, response.status());
                return false;
            }
        } catch (Exception e) {
            log.error("删除实体失败，ID: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteByIds(List<String> ids) {
        log.info("批量删除实体，数量: {}", ids.size());
        try {
            String indexName = getIndexName();
            
            // 使用ES官方高级客户端API
            BulkRequest bulkRequest = new BulkRequest();
            
            for (String id : ids) {
                DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
                bulkRequest.add(deleteRequest);
            }
            
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, createAuthenticatedRequestOptions());
            
            if (response.hasFailures()) {
                log.error("批量删除失败: {}", response.buildFailureMessage());
                return false;
            }
            
            log.info("批量删除成功，数量: {}", ids.size());
            return true;
        } catch (Exception e) {
            log.error("批量删除实体失败", e);
            return false;
        }
    }

    @Override
    public T update(T entity) {
        log.info("更新实体: {}", entity);
        try {
            String indexName = getIndexName(entity);
            String id = getId(entity);
            
            // 使用ES官方高级客户端API
            UpdateRequest updateRequest = new UpdateRequest(indexName, id);
            updateRequest.doc(objectMapper.writeValueAsString(entity), XContentType.JSON);
            updateRequest.docAsUpsert(true);
            
            UpdateResponse response = restHighLevelClient.update(updateRequest, createAuthenticatedRequestOptions());
            
            if (response.status().getStatus() == 200) {
                log.info("更新实体成功，ID: {}", id);
                return entity;
            } else {
                throw new RuntimeException("更新实体失败，状态: " + response.status());
            }
        } catch (Exception e) {
            log.error("更新实体失败", e);
            throw new RuntimeException("更新实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAll() {
        log.info("查找所有实体");
        try {
            String indexName = getIndexName();
            
            // 使用ES官方高级客户端API
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchSourceBuilder.size(10000); // 设置最大返回数量
            searchRequest.source(searchSourceBuilder);
            
            SearchResponse response = restHighLevelClient.search(searchRequest, createAuthenticatedRequestOptions());
            
            List<T> entities = new ArrayList<>();
            for (SearchHit hit : response.getHits().getHits()) {
                T entity = objectMapper.readValue(hit.getSourceAsString(), entityClass);
                entities.add(entity);
            }
            
            log.info("找到 {} 个实体", entities.size());
            return entities;
        } catch (Exception e) {
            log.error("查找所有实体失败", e);
            throw new RuntimeException("查找所有实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> search(String field, String value) {
        log.info("搜索实体，字段: {}, 值: {}", field, value);
        try {
            String indexName = getIndexName();
            
            // 使用ES官方高级客户端API
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery(field, value));
            searchRequest.source(searchSourceBuilder);
            
            SearchResponse response = restHighLevelClient.search(searchRequest, createAuthenticatedRequestOptions());
            
            List<T> entities = new ArrayList<>();
            for (SearchHit hit : response.getHits().getHits()) {
                T entity = objectMapper.readValue(hit.getSourceAsString(), entityClass);
                entities.add(entity);
            }
            
            log.info("搜索到 {} 个实体", entities.size());
            return entities;
        } catch (Exception e) {
            log.error("搜索实体失败", e);
            throw new RuntimeException("搜索实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public long count() {
        log.info("统计实体数量");
        try {
            String indexName = getIndexName();
            
            // 使用ES官方高级客户端API - 使用SearchRequest替代CountRequest
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchSourceBuilder.size(0); // 只统计，不返回数据
            searchRequest.source(searchSourceBuilder);
            
            SearchResponse response = restHighLevelClient.search(searchRequest, createAuthenticatedRequestOptions());
            long count = response.getHits().getTotalHits().value;
            
            log.info("实体数量: {}", count);
            return count;
        } catch (Exception e) {
            log.error("统计实体数量失败", e);
            throw new RuntimeException("统计实体数量失败: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existsById(String id) {
        log.info("检查实体是否存在，ID: {}", id);
        try {
            String indexName = getIndexName();
            
            // 使用ES官方高级客户端API
            GetRequest getRequest = new GetRequest(indexName, id);
            boolean exists = restHighLevelClient.exists(getRequest, createAuthenticatedRequestOptions());
            
            log.info("实体存在: {}, ID: {}", exists, id);
            return exists;
        } catch (Exception e) {
            log.error("检查实体是否存在失败，ID: {}", id, e);
            return false;
        }
    }

    /**
     * 获取索引名称
     */
    private String getIndexName() {
        return entityClass.getSimpleName().toLowerCase();
    }

    /**
     * 获取索引名称（从实体中）
     */
    private String getIndexName(T entity) {
        return getIndexName();
    }

    /**
     * 获取实体ID
     */
    private String getId(T entity) {
        try {
            // 尝试通过反射获取id字段
            java.lang.reflect.Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(entity);
            return idValue != null ? idValue.toString() : java.util.UUID.randomUUID().toString();
        } catch (Exception e) {
            // 如果无法获取id，生成一个UUID
            return java.util.UUID.randomUUID().toString();
        }
    }
}