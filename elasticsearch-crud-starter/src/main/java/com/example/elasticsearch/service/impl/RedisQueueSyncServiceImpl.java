package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.example.elasticsearch.service.DataSyncService;
import com.example.elasticsearch.service.QueueSyncService;
import lombok.extern.slf4j.Slf4j;

/**
 * Redis队列同步服务实现类
 * 
 * @author Kiro
 */
@Slf4j
public class RedisQueueSyncServiceImpl implements QueueSyncService {

    private final DataSyncService dataSyncService;
    private final ElasticsearchCrudProperties properties;

    public RedisQueueSyncServiceImpl(DataSyncService dataSyncService,
                                    ElasticsearchCrudProperties properties) {
        this.dataSyncService = dataSyncService;
        this.properties = properties;
        log.info("RedisQueueSyncService 初始化完成");
    }
}