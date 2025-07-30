package com.example.elasticsearch.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Elasticsearch 指标统计
 * 
 * @author Kiro
 */
@Component
public class ElasticsearchMetrics {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElasticsearchMetrics.class);

    private final AtomicLong totalRequests = new AtomicLong(0);
    private final AtomicLong successfulRequests = new AtomicLong(0);
    private final AtomicLong failedRequests = new AtomicLong(0);
    private final AtomicLong totalResponseTime = new AtomicLong(0);

    public void incrementTotalRequests() {
        totalRequests.incrementAndGet();
    }

    public void incrementSuccessfulRequests() {
        successfulRequests.incrementAndGet();
    }

    public void incrementFailedRequests() {
        failedRequests.incrementAndGet();
    }

    public void addResponseTime(long responseTime) {
        totalResponseTime.addAndGet(responseTime);
    }

    public long getTotalRequests() {
        return totalRequests.get();
    }

    public long getSuccessfulRequests() {
        return successfulRequests.get();
    }

    public long getFailedRequests() {
        return failedRequests.get();
    }

    public long getTotalResponseTime() {
        return totalResponseTime.get();
    }

    public double getAverageResponseTime() {
        long total = totalRequests.get();
        return total > 0 ? (double) totalResponseTime.get() / total : 0.0;
    }

    public double getSuccessRate() {
        long total = totalRequests.get();
        return total > 0 ? (double) successfulRequests.get() / total * 100 : 0.0;
    }

    public void reset() {
        totalRequests.set(0);
        successfulRequests.set(0);
        failedRequests.set(0);
        totalResponseTime.set(0);
        log.info("Elasticsearch metrics reset");
    }
}