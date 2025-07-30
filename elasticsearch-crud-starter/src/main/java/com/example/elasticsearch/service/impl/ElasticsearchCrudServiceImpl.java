package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.example.elasticsearch.service.ElasticsearchCrudService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchCrudServiceImpl implements ElasticsearchCrudService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElasticsearchCrudServiceImpl.class);

    private final RestHighLevelClient restHighLevelClient;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ElasticsearchCrudProperties properties;

    public ElasticsearchCrudServiceImpl(RestHighLevelClient restHighLevelClient,
                                        ElasticsearchRestTemplate elasticsearchRestTemplate,
                                        ElasticsearchCrudProperties properties) {
        this.restHighLevelClient = restHighLevelClient;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.properties = properties;
    }

    // TODO: 实现CRUD方法
}