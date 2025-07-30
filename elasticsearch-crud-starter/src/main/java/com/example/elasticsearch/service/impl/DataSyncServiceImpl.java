package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.example.elasticsearch.service.DataSyncService;
import com.example.elasticsearch.service.ElasticsearchCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * 数据同步服务实现类
 * 
 * @author Kiro
 */
@Slf4j
public class DataSyncServiceImpl implements DataSyncService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ElasticsearchCrudService elasticsearchCrudService;
    private final ElasticsearchCrudProperties properties;

    public DataSyncServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate,
                              ElasticsearchCrudService elasticsearchCrudService,
                              ElasticsearchCrudProperties properties) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.elasticsearchCrudService = elasticsearchCrudService;
        this.properties = properties;
        log.info("DataSyncService 初始化完成");
    }
}