package com.example.elasticsearch.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Elasticsearch 监控指标收集器
 * 
 * @author Kiro
 */
@Slf4j
public class ElasticsearchMetrics {

    private MeterRegistry meterRegistry;

    // 操作计数器
    private Counter saveCounter;
    private Counter queryCounter;
    private Counter deleteCounter;
    private Counter syncCounter;
    private Counter errorCounter;

    // 性能计时器
    private Timer saveTimer;
    private Timer queryTimer;
    private Timer deleteTimer;
    private Timer syncTimer;

    @Autowired(required = false)
    public void setMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initializeMetrics();
    }

    /**
     * 初始化监控指标
     */
    private void initializeMetrics() {
        if (meterRegistry == null) {
            log.warn("MeterRegistry未配置，监控指标将不可用");
            return;
        }

        // 初始化计数器
        saveCounter = Counter.builder("elasticsearch.operations")
                .tag("operation", "save")
                .description("Elasticsearch保存操作计数")
                .register(meterRegistry);

        queryCounter = Counter.builder("elasticsearch.operations")
                .tag("operation", "query")
                .description("Elasticsearch查询操作计数")
                .register(meterRegistry);

        deleteCounter = Counter.builder("elasticsearch.operations")
                .tag("operation", "delete")
                .description("Elasticsearch删除操作计数")
                .register(meterRegistry);

        syncCounter = Counter.builder("elasticsearch.operations")
                .tag("operation", "sync")
                .description("Elasticsearch同步操作计数")
                .register(meterRegistry);

        errorCounter = Counter.builder("elasticsearch.errors")
                .description("Elasticsearch错误计数")
                .register(meterRegistry);

        // 初始化计时器
        saveTimer = Timer.builder("elasticsearch.operation.duration")
                .tag("operation", "save")
                .description("Elasticsearch保存操作耗时")
                .register(meterRegistry);

        queryTimer = Timer.builder("elasticsearch.operation.duration")
                .tag("operation", "query")
                .description("Elasticsearch查询操作耗时")
                .register(meterRegistry);

        deleteTimer = Timer.builder("elasticsearch.operation.duration")
                .tag("operation", "delete")
                .description("Elasticsearch删除操作耗时")
                .register(meterRegistry);

        syncTimer = Timer.builder("elasticsearch.operation.duration")
                .tag("operation", "sync")
                .description("Elasticsearch同步操作耗时")
                .register(meterRegistry);

        log.info("Elasticsearch监控指标初始化完成");
    }

    /**
     * 记录保存操作
     */
    public void recordSave() {
        if (saveCounter != null) {
            saveCounter.increment();
        }
    }

    /**
     * 记录查询操作
     */
    public void recordQuery() {
        if (queryCounter != null) {
            queryCounter.increment();
        }
    }

    /**
     * 记录删除操作
     */
    public void recordDelete() {
        if (deleteCounter != null) {
            deleteCounter.increment();
        }
    }

    /**
     * 记录同步操作
     */
    public void recordSync() {
        if (syncCounter != null) {
            syncCounter.increment();
        }
    }

    /**
     * 记录错误
     */
    public void recordError() {
        if (errorCounter != null) {
            errorCounter.increment();
        }
    }

    /**
     * 获取保存操作计时器
     */
    public Timer.Sample startSaveTimer() {
        return saveTimer != null ? Timer.start(meterRegistry) : null;
    }

    /**
     * 获取查询操作计时器
     */
    public Timer.Sample startQueryTimer() {
        return queryTimer != null ? Timer.start(meterRegistry) : null;
    }

    /**
     * 获取删除操作计时器
     */
    public Timer.Sample startDeleteTimer() {
        return deleteTimer != null ? Timer.start(meterRegistry) : null;
    }

    /**
     * 获取同步操作计时器
     */
    public Timer.Sample startSyncTimer() {
        return syncTimer != null ? Timer.start(meterRegistry) : null;
    }

    /**
     * 停止计时器并记录
     */
    public void stopTimer(Timer.Sample sample, Timer timer) {
        if (sample != null && timer != null) {
            sample.stop(timer);
        }
    }
}