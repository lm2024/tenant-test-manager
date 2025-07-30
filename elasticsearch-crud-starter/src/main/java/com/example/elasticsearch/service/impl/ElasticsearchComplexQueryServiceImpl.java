package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.example.elasticsearch.service.ElasticsearchComplexQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * Elasticsearch 复杂查询服务实现类
 * 
 * @author Kiro
 */
@Slf4j
public class ElasticsearchComplexQueryServiceImpl implements ElasticsearchComplexQueryService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ElasticsearchCrudProperties properties;

    public ElasticsearchComplexQueryServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate,
                                               ElasticsearchCrudProperties properties) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.properties = properties;
        log.info("ElasticsearchComplexQueryService 初始化完成");
    }
}