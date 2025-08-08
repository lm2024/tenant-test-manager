package com.common.redis.cache.service;

import com.common.redis.cache.model.CacheStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 缓存监控指标服务
 * 
 * <p>收集和管理缓存相关的各种监控指标，提供实时的性能数据。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@Component
public class CacheMetrics {
    
    /**
     * 全局缓存统计
     */
    private final CacheStats globalStats = CacheStats.create();
    
    /**
     * 按操作类型分组的统计
     */
    private final ConcurrentHashMap<String, CacheStats> operationStats = new ConcurrentHashMap<>();
    
    /**
     * 按缓存名称分组的统计
     */
    private final ConcurrentHashMap<String, CacheStats> cacheNameStats = new ConcurrentHashMap<>();
    
    /**
     * 按租户分组的统计
     */
    private final ConcurrentHashMap<String, CacheStats> tenantStats = new ConcurrentHashMap<>();
    
    /**
     * 定时任务执行器
     */
    private ScheduledExecutorService scheduler;
    
    /**
     * 指标收集间隔（秒）
     */
    private static final int METRICS_INTERVAL = 60;
    
    /**
     * 慢查询阈值（毫秒）
     */
    private static final long SLOW_QUERY_THRESHOLD = 1000;
    
    /**
     * 慢查询计数器
     */
    private final LongAdder slowQueryCount = new LongAdder();
    
    /**
     * 错误计数器
     */
    private final LongAdder errorCount = new LongAdder();
    
    /**
     * 降级计数器
     */
    private final LongAdder fallbackCount = new LongAdder();
    
    /**
     * 最后一次指标报告时间
     */
    private volatile LocalDateTime lastReportTime = LocalDateTime.now();
    
    /**
     * 初始化监控服务
     */
    @PostConstruct
    public void init() {
        scheduler = Executors.newScheduledThreadPool(1, r -> {
            Thread t = new Thread(r, "cache-metrics-scheduler");
            t.setDaemon(true);
            return t;
        });
        
        // 启动定时指标报告
        startMetricsReporting();
        
        log.info("Cache metrics service initialized");
    }
    
    /**
     * 记录缓存命中
     * 
     * @param operation 操作类型
     * @param cacheName 缓存名称
     * @param tenantId 租户ID
     * @param responseTime 响应时间（毫秒）
     */
    public void recordHit(String operation, String cacheName, String tenantId, long responseTime) {
        // 全局统计
        globalStats.recordHit();
        globalStats.recordResponseTime(responseTime);
        
        // 按操作类型统计
        getOperationStats(operation).recordHit();
        getOperationStats(operation).recordResponseTime(responseTime);
        
        // 按缓存名称统计
        if (cacheName != null) {
            getCacheNameStats(cacheName).recordHit();
            getCacheNameStats(cacheName).recordResponseTime(responseTime);
        }
        
        // 按租户统计
        if (tenantId != null) {
            getTenantStats(tenantId).recordHit();
            getTenantStats(tenantId).recordResponseTime(responseTime);
        }
        
        // 检查是否为慢查询
        if (responseTime > SLOW_QUERY_THRESHOLD) {
            recordSlowQuery(operation, cacheName, tenantId, responseTime);
        }
        
        log.debug("Cache hit recorded: operation={}, cacheName={}, tenantId={}, responseTime={}ms", 
                operation, cacheName, tenantId, responseTime);
    }
    
    /**
     * 记录缓存未命中
     * 
     * @param operation 操作类型
     * @param cacheName 缓存名称
     * @param tenantId 租户ID
     * @param responseTime 响应时间（毫秒）
     */
    public void recordMiss(String operation, String cacheName, String tenantId, long responseTime) {
        // 全局统计
        globalStats.recordMiss();
        globalStats.recordResponseTime(responseTime);
        
        // 按操作类型统计
        getOperationStats(operation).recordMiss();
        getOperationStats(operation).recordResponseTime(responseTime);
        
        // 按缓存名称统计
        if (cacheName != null) {
            getCacheNameStats(cacheName).recordMiss();
            getCacheNameStats(cacheName).recordResponseTime(responseTime);
        }
        
        // 按租户统计
        if (tenantId != null) {
            getTenantStats(tenantId).recordMiss();
            getTenantStats(tenantId).recordResponseTime(responseTime);
        }
        
        // 检查是否为慢查询
        if (responseTime > SLOW_QUERY_THRESHOLD) {
            recordSlowQuery(operation, cacheName, tenantId, responseTime);
        }
        
        log.debug("Cache miss recorded: operation={}, cacheName={}, tenantId={}, responseTime={}ms", 
                operation, cacheName, tenantId, responseTime);
    }
    
    /**
     * 记录缓存加载
     * 
     * @param operation 操作类型
     * @param cacheName 缓存名称
     * @param tenantId 租户ID
     * @param dataSize 数据大小（字节）
     */
    public void recordLoad(String operation, String cacheName, String tenantId, long dataSize) {
        // 全局统计
        globalStats.recordLoad();
        globalStats.incrementCacheSize(dataSize);
        
        // 按操作类型统计
        getOperationStats(operation).recordLoad();
        
        // 按缓存名称统计
        if (cacheName != null) {
            getCacheNameStats(cacheName).recordLoad();
        }
        
        // 按租户统计
        if (tenantId != null) {
            getTenantStats(tenantId).recordLoad();
        }
        
        log.debug("Cache load recorded: operation={}, cacheName={}, tenantId={}, dataSize={} bytes", 
                operation, cacheName, tenantId, dataSize);
    }
    
    /**
     * 记录缓存失效
     * 
     * @param operation 操作类型
     * @param cacheName 缓存名称
     * @param tenantId 租户ID
     * @param evictedCount 失效数量
     */
    public void recordEviction(String operation, String cacheName, String tenantId, long evictedCount) {
        // 全局统计
        globalStats.recordEviction();
        
        // 按操作类型统计
        getOperationStats(operation).recordEviction();
        
        // 按缓存名称统计
        if (cacheName != null) {
            getCacheNameStats(cacheName).recordEviction();
        }
        
        // 按租户统计
        if (tenantId != null) {
            getTenantStats(tenantId).recordEviction();
        }
        
        log.debug("Cache eviction recorded: operation={}, cacheName={}, tenantId={}, evictedCount={}", 
                operation, cacheName, tenantId, evictedCount);
    }
    
    /**
     * 记录异常
     * 
     * @param operation 操作类型
     * @param cacheName 缓存名称
     * @param tenantId 租户ID
     * @param exception 异常对象
     */
    public void recordException(String operation, String cacheName, String tenantId, Exception exception) {
        // 全局统计
        globalStats.recordException();
        errorCount.increment();
        
        // 按操作类型统计
        getOperationStats(operation).recordException();
        
        // 按缓存名称统计
        if (cacheName != null) {
            getCacheNameStats(cacheName).recordException();
        }
        
        // 按租户统计
        if (tenantId != null) {
            getTenantStats(tenantId).recordException();
        }
        
        log.debug("Cache exception recorded: operation={}, cacheName={}, tenantId={}, exception={}", 
                operation, cacheName, tenantId, exception.getClass().getSimpleName());
    }
    
    /**
     * 记录降级
     * 
     * @param operation 操作类型
     * @param cacheName 缓存名称
     * @param tenantId 租户ID
     * @param fallbackTime 降级执行时间（毫秒）
     */
    public void recordFallback(String operation, String cacheName, String tenantId, long fallbackTime) {
        // 全局统计
        globalStats.recordFallback();
        fallbackCount.increment();
        
        // 按操作类型统计
        getOperationStats(operation).recordFallback();
        
        // 按缓存名称统计
        if (cacheName != null) {
            getCacheNameStats(cacheName).recordFallback();
        }
        
        // 按租户统计
        if (tenantId != null) {
            getTenantStats(tenantId).recordFallback();
        }
        
        log.debug("Cache fallback recorded: operation={}, cacheName={}, tenantId={}, fallbackTime={}ms", 
                operation, cacheName, tenantId, fallbackTime);
    }
    
    /**
     * 记录慢查询
     * 
     * @param operation 操作类型
     * @param cacheName 缓存名称
     * @param tenantId 租户ID
     * @param responseTime 响应时间（毫秒）
     */
    private void recordSlowQuery(String operation, String cacheName, String tenantId, long responseTime) {
        slowQueryCount.increment();
        
        log.warn("Slow cache query detected: operation={}, cacheName={}, tenantId={}, responseTime={}ms", 
                operation, cacheName, tenantId, responseTime);
    }
    
    /**
     * 获取全局统计信息
     * 
     * @return 全局统计
     */
    public CacheStats getGlobalStats() {
        return globalStats;
    }
    
    /**
     * 获取操作类型统计信息
     * 
     * @param operation 操作类型
     * @return 操作统计
     */
    public CacheStats getOperationStats(String operation) {
        return operationStats.computeIfAbsent(operation, k -> CacheStats.create());
    }
    
    /**
     * 获取缓存名称统计信息
     * 
     * @param cacheName 缓存名称
     * @return 缓存统计
     */
    public CacheStats getCacheNameStats(String cacheName) {
        return cacheNameStats.computeIfAbsent(cacheName, k -> CacheStats.create());
    }
    
    /**
     * 获取租户统计信息
     * 
     * @param tenantId 租户ID
     * @return 租户统计
     */
    public CacheStats getTenantStats(String tenantId) {
        return tenantStats.computeIfAbsent(tenantId, k -> CacheStats.create());
    }
    
    /**
     * 获取慢查询数量
     * 
     * @return 慢查询数量
     */
    public long getSlowQueryCount() {
        return slowQueryCount.sum();
    }
    
    /**
     * 获取错误数量
     * 
     * @return 错误数量
     */
    public long getErrorCount() {
        return errorCount.sum();
    }
    
    /**
     * 获取降级数量
     * 
     * @return 降级数量
     */
    public long getFallbackCount() {
        return fallbackCount.sum();
    }
    
    /**
     * 重置所有统计信息
     */
    public void resetAllStats() {
        globalStats.reset();
        operationStats.clear();
        cacheNameStats.clear();
        tenantStats.clear();
        slowQueryCount.reset();
        errorCount.reset();
        fallbackCount.reset();
        
        log.info("All cache metrics reset");
    }
    
    /**
     * 重置指定操作的统计信息
     * 
     * @param operation 操作类型
     */
    public void resetOperationStats(String operation) {
        CacheStats stats = operationStats.get(operation);
        if (stats != null) {
            stats.reset();
            log.info("Cache metrics reset for operation: {}", operation);
        }
    }
    
    /**
     * 获取指标摘要
     * 
     * @return 指标摘要
     */
    public String getMetricsSummary() {
        CacheStats.CacheStatsSnapshot snapshot = globalStats.getSnapshot();
        
        return String.format(
                "Cache Metrics Summary: requests=%d, hitRate=%.2f%%, avgResponseTime=%.2fms, " +
                "slowQueries=%d, errors=%d, fallbacks=%d, cacheSize=%d",
                snapshot.getTotalRequestCount(),
                snapshot.getHitRate() * 100,
                snapshot.getAverageResponseTime(),
                getSlowQueryCount(),
                getErrorCount(),
                getFallbackCount(),
                snapshot.getCacheSize()
        );
    }
    
    /**
     * 启动指标报告
     */
    private void startMetricsReporting() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                reportMetrics();
            } catch (Exception e) {
                log.error("Failed to report cache metrics", e);
            }
        }, METRICS_INTERVAL, METRICS_INTERVAL, TimeUnit.SECONDS);
        
        log.info("Cache metrics reporting started with interval: {}s", METRICS_INTERVAL);
    }
    
    /**
     * 报告指标
     */
    private void reportMetrics() {
        LocalDateTime now = LocalDateTime.now();
        
        // 记录报告时间
        lastReportTime = now;
        
        // 输出指标摘要
        log.info("Cache Metrics Report: {}", getMetricsSummary());
        
        // 检查异常情况
        checkAnomalies();
        
        // 这里可以集成外部监控系统，如Prometheus、InfluxDB等
        // exportToExternalSystems();
    }
    
    /**
     * 检查异常情况
     */
    private void checkAnomalies() {
        CacheStats.CacheStatsSnapshot snapshot = globalStats.getSnapshot();
        
        // 检查命中率是否过低
        if (snapshot.getHitRate() < 0.5 && snapshot.getTotalRequestCount() > 100) {
            log.warn("Low cache hit rate detected: {:.2f}%", snapshot.getHitRate() * 100);
        }
        
        // 检查平均响应时间是否过高
        if (snapshot.getAverageResponseTime() > 500) {
            log.warn("High average response time detected: {:.2f}ms", snapshot.getAverageResponseTime());
        }
        
        // 检查错误率是否过高
        if (snapshot.getExceptionRate() > 0.1 && snapshot.getTotalRequestCount() > 50) {
            log.warn("High error rate detected: {:.2f}%", snapshot.getExceptionRate() * 100);
        }
        
        // 检查降级率是否过高
        if (snapshot.getFallbackRate() > 0.2 && snapshot.getTotalRequestCount() > 50) {
            log.warn("High fallback rate detected: {:.2f}%", snapshot.getFallbackRate() * 100);
        }
    }
    
    /**
     * 导出到外部监控系统（预留接口）
     */
    private void exportToExternalSystems() {
        // 这里可以实现向Prometheus、InfluxDB等监控系统导出指标的逻辑
        log.debug("Exporting metrics to external systems");
    }
    
    /**
     * 关闭监控服务
     */
    public void shutdown() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        
        log.info("Cache metrics service shutdown");
    }
}