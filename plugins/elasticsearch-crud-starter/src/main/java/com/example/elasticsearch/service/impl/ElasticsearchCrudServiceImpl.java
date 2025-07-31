package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.example.elasticsearch.service.ElasticsearchCrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ElasticsearchCrudServiceImpl<T> implements ElasticsearchCrudService<T> {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElasticsearchCrudServiceImpl.class);

    private final RestHighLevelClient restHighLevelClient;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ElasticsearchCrudProperties properties;
    private final ObjectMapper objectMapper;

    public ElasticsearchCrudServiceImpl(RestHighLevelClient restHighLevelClient,
                                        ElasticsearchRestTemplate elasticsearchRestTemplate,
                                        ElasticsearchCrudProperties properties,
                                        ObjectMapper objectMapper) {
        this.restHighLevelClient = restHighLevelClient;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public T save(T entity) {
        log.info("保存实体: {}", entity);
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 获取实体ID
            String id = getEntityId(entity);
            
            // 构建Index请求
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.id(id);
            indexRequest.source(objectMapper.writeValueAsString(entity), XContentType.JSON);
            
            // 执行索引操作
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            
            if (response.status() == RestStatus.CREATED || response.status() == RestStatus.OK) {
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
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建批量请求
            BulkRequest bulkRequest = new BulkRequest();
            
            for (T entity : entities) {
                String id = getEntityId(entity);
                IndexRequest indexRequest = new IndexRequest(indexName);
                indexRequest.id(id);
                indexRequest.source(objectMapper.writeValueAsString(entity), XContentType.JSON);
                bulkRequest.add(indexRequest);
            }
            
            // 执行批量操作
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            
            if (response.hasFailures()) {
                log.error("批量保存有失败项: {}", response.buildFailureMessage());
                throw new RuntimeException("批量保存失败: " + response.buildFailureMessage());
            }
            
            log.info("批量保存成功，实际保存数量: {}", entities.size());
            return entities;
        } catch (Exception e) {
            log.error("批量保存实体失败", e);
            throw new RuntimeException("批量保存实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<T> findById(String id) {
        log.info("根据ID查询实体: {}", id);
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建Get请求
            GetRequest getRequest = new GetRequest(indexName, id);
            GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            
            if (response.isExists()) {
                // 将JSON字符串转换为实体对象
                T entity = objectMapper.readValue(response.getSourceAsString(), getEntityClass());
                log.info("找到实体，ID: {}", id);
                return Optional.of(entity);
            } else {
                log.info("未找到实体，ID: {}", id);
                return Optional.empty();
            }
        } catch (Exception e) {
            log.error("根据ID查询实体失败，ID: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(String id) {
        log.info("根据ID删除实体: {}", id);
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建Delete请求
            DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
            DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            
            if (response.status() == RestStatus.OK || response.status() == RestStatus.NOT_FOUND) {
                log.info("实体删除成功，ID: {}", id);
            } else {
                throw new RuntimeException("删除实体失败，状态: " + response.status());
            }
        } catch (Exception e) {
            log.error("删除实体失败，ID: {}", id, e);
            throw new RuntimeException("删除实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByIds(List<String> ids) {
        log.info("批量删除实体，数量: {}", ids.size());
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建批量删除请求
            BulkRequest bulkRequest = new BulkRequest();
            
            for (String id : ids) {
                DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
                bulkRequest.add(deleteRequest);
            }
            
            // 执行批量删除
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            
            if (response.hasFailures()) {
                log.error("批量删除有失败项: {}", response.buildFailureMessage());
                throw new RuntimeException("批量删除失败: " + response.buildFailureMessage());
            }
            
            log.info("批量删除成功，删除数量: {}", ids.size());
        } catch (Exception e) {
            log.error("批量删除实体失败", e);
            throw new RuntimeException("批量删除实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public T update(String id, T entity) {
        log.info("更新实体，ID: {}", id);
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建Update请求
            UpdateRequest updateRequest = new UpdateRequest(indexName, id);
            updateRequest.doc(objectMapper.writeValueAsString(entity), XContentType.JSON);
            updateRequest.docAsUpsert(true); // 如果不存在则创建
            
            UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            
            if (response.status() == RestStatus.OK) {
                log.info("实体更新成功，ID: {}", id);
                return entity;
            } else {
                throw new RuntimeException("更新实体失败，状态: " + response.status());
            }
        } catch (Exception e) {
            log.error("更新实体失败，ID: {}", id, e);
            throw new RuntimeException("更新实体失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        log.info("分页查询所有实体");
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建搜索请求
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            
            // 设置分页
            searchSourceBuilder.from(pageable.getPageNumber() * pageable.getPageSize());
            searchSourceBuilder.size(pageable.getPageSize());
            
            // 设置排序
            if (pageable.getSort().isSorted()) {
                pageable.getSort().forEach(sort -> {
                    searchSourceBuilder.sort(sort.getProperty(), 
                        sort.getDirection().isAscending() ? SortOrder.ASC : SortOrder.DESC);
                });
            }
            
            searchRequest.source(searchSourceBuilder);
            
            // 执行搜索
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            
            // 转换结果
            List<T> content = new ArrayList<>();
            for (SearchHit hit : response.getHits().getHits()) {
                try {
                    T entity = objectMapper.readValue(hit.getSourceAsString(), getEntityClass());
                    content.add(entity);
                } catch (Exception e) {
                    log.warn("转换搜索结果失败: {}", hit.getId(), e);
                }
            }
            
            Page<T> page = new PageImpl<>(content, pageable, response.getHits().getTotalHits().value);
            log.info("分页查询成功，总数: {}, 当前页数量: {}", page.getTotalElements(), page.getContent().size());
            return page;
        } catch (Exception e) {
            log.error("分页查询所有实体失败", e);
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
    }

    @Override
    public Page<T> search(String keyword, Pageable pageable) {
        log.info("搜索实体，关键词: {}", keyword);
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建搜索请求
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            
            // 设置查询条件
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyword)
                .field("title", 2.0f)
                .field("content")
                .field("category")
                .type(org.elasticsearch.index.query.MultiMatchQueryBuilder.Type.BEST_FIELDS));
            
            // 设置分页
            searchSourceBuilder.from(pageable.getPageNumber() * pageable.getPageSize());
            searchSourceBuilder.size(pageable.getPageSize());
            
            // 设置排序
            if (pageable.getSort().isSorted()) {
                pageable.getSort().forEach(sort -> {
                    searchSourceBuilder.sort(sort.getProperty(), 
                        sort.getDirection().isAscending() ? SortOrder.ASC : SortOrder.DESC);
                });
            }
            
            searchRequest.source(searchSourceBuilder);
            
            // 执行搜索
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            
            // 转换结果
            List<T> content = new ArrayList<>();
            for (SearchHit hit : response.getHits().getHits()) {
                try {
                    T entity = objectMapper.readValue(hit.getSourceAsString(), getEntityClass());
                    content.add(entity);
                } catch (Exception e) {
                    log.warn("转换搜索结果失败: {}", hit.getId(), e);
                }
            }
            
            Page<T> page = new PageImpl<>(content, pageable, response.getHits().getTotalHits().value);
            
            log.info("搜索成功，关键词: {}, 总数: {}, 当前页数量: {}", 
                keyword, page.getTotalElements(), page.getContent().size());
            return page;
        } catch (Exception e) {
            log.error("搜索实体失败，关键词: {}", keyword, e);
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
    }

    @Override
    public long count() {
        log.info("统计实体总数");
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建搜索请求
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0); // 只统计，不返回数据
            searchRequest.source(searchSourceBuilder);
            
            // 执行搜索
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            long count = response.getHits().getTotalHits().value;
            
            log.info("统计成功，总数: {}", count);
            return count;
        } catch (Exception e) {
            log.error("统计实体总数失败", e);
            return 0;
        }
    }

    @Override
    public boolean existsById(String id) {
        log.info("检查实体是否存在，ID: {}", id);
        try {
            // 获取索引名称
            String indexName = getIndexName();
            
            // 构建Get请求
            GetRequest getRequest = new GetRequest(indexName, id);
            GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            
            boolean exists = response.isExists();
            log.info("实体存在检查结果，ID: {}, 存在: {}", id, exists);
            return exists;
        } catch (Exception e) {
            log.error("检查实体是否存在失败，ID: {}", id, e);
            return false;
        }
    }

    /**
     * 获取实体类类型
     * 注意：这是一个简化的实现，实际使用时需要根据具体的泛型类型来处理
     */
    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass() {
        // 这里返回Object.class作为默认值
        // 在实际使用中，应该根据具体的实体类型来处理
        return (Class<T>) Object.class;
    }

    /**
     * 获取实体ID
     * 注意：这是一个简化的实现，实际使用时需要根据具体的实体类型来处理
     */
    private String getEntityId(T entity) {
        // 这里返回一个默认值
        // 在实际使用中，应该根据具体的实体类型来获取ID
        return entity != null ? entity.toString() : "unknown";
    }

    /**
     * 获取索引名称
     * 注意：这是一个简化的实现，实际使用时需要根据具体的实体类型来处理
     */
    private String getIndexName() {
        // 这里返回一个默认的索引名称
        // 在实际使用中，应该根据具体的实体类型来获取索引名称
        return "test_document";
    }
}