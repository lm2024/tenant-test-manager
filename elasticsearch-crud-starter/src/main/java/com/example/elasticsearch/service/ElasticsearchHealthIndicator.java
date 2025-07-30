package com.example.elasticsearch.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchHealthIndicator implements HealthIndicator {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElasticsearchHealthIndicator.class);

    @Override
    public Health health() {
        // 这里只是示例，实际应检测ES集群健康
        log.info("检查Elasticsearch健康状态");
        return Health.up().withDetail("elasticsearch", "ok").build();
    }
}