package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.example.elasticsearch.service.DataSyncService;
import com.example.elasticsearch.service.QueueSyncService;
import org.springframework.stereotype.Service;

/**
 * Redis队列同步服务实现类
 * 
 * @author Kiro
 */
@Service
public class RedisQueueSyncServiceImpl {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RedisQueueSyncServiceImpl.class);
    private final DataSyncService dataSyncService;
    private final ElasticsearchCrudProperties properties;

    public RedisQueueSyncServiceImpl(DataSyncService dataSyncService,
                                    ElasticsearchCrudProperties properties) {
        this.dataSyncService = dataSyncService;
        this.properties = properties;
        log.info("RedisQueueSyncService 初始化完成");
    }
}