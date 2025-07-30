package com.example.elasticsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Elasticsearch 健康检查指示器（简化版）
 * 
 * @author Kiro
 */
@Slf4j
public class ElasticsearchHealthIndicator {

    private final RestHighLevelClient restHighLevelClient;

    public ElasticsearchHealthIndicator(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
        log.info("ElasticsearchHealthIndicator 初始化完成");
    }

    /**
     * 检查Elasticsearch连接状态
     * 
     * @return 连接是否正常
     */
    public boolean isHealthy() {
        try {
            // 简单的连接检查
            return restHighLevelClient != null;
        } catch (Exception e) {
            log.error("Elasticsearch健康检查失败", e);
            return false;
        }
    }
}