package com.common.redis.cache.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 缓存统计信息类
 * 
 * <p>记录缓存的各种统计指标，用于监控和性能分析。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheStats implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 缓存命中次数
     */
    private final LongAdder hitCount = new LongAdder();
    
    /**
     * 缓存未命中次数
     */
    private final LongAdder missCount = new LongAdder();
    
    /**
     * 缓存加载次数
     */
    private final LongAdder loadCount = new LongAdder();
    
    /**
     * 缓存失效次数
     */
    private final LongAdder evictionCount = new LongAdder();
    
    /**
     * 总响应时间（毫秒）
     */
    private final LongAdder totalResponseTime = new LongAdder();
    
    /**
     * 缓存异常次数
     */
    private final LongAdder exceptionCount = new LongAdder();
    
    /**
     * 降级次数
     */
    private final LongAdder fallbackCount = new LongAdder();
    
    /**
     * 统计开始时间
     */
    private LocalDateTime startTime;
    
    /**
     * 最后更新时间
     */
    private volatile LocalDateTime lastUpdateTime;
    
    /**
     * 当前缓存大小（估算）
     */
    private final AtomicLong cacheSize = new AtomicLong(0);
    
    /**
     * 最大响应时间
     */
    private volatile long maxResponseTime = 0;
    
    /**
     * 最小响应时间
     */
    private volatile long minResponseTime = Long.MAX_VALUE;
    
    /**
     * 创建统计实例
     * 
     * @return 新的统计实例
     */
    public static CacheStats create() {
        LocalDateTime now = LocalDateTime.now();
        return CacheStats.builder()
                .startTime(now)
                .lastUpdateTime(now)
                .build();
    }
    
    /**
     * 记录缓存命中
     */
    public void recordHit() {
        hitCount.increment();
        updateLastUpdateTime();
    }
    
    /**
     * 记录缓存未命中
     */
    public void recordMiss() {
        missCount.increment();
        updateLastUpdateTime();
    }
    
    /**
     * 记录缓存加载
     */
    public void recordLoad() {
        loadCount.increment();
        updateLastUpdateTime();
    }
    
    /**
     * 记录缓存失效
     */
    public void recordEviction() {
        evictionCount.increment();
        updateLastUpdateTime();
    }
    
    /**
     * 记录响应时间
     * 
     * @param responseTime 响应时间（毫秒）
     */
    public void recordResponseTime(long responseTime) {
        totalResponseTime.add(responseTime);
        
        // 更新最大最小响应时间
        synchronized (this) {
            if (responseTime > maxResponseTime) {
                maxResponseTime = responseTime;
            }
            if (responseTime < minResponseTime) {
                minResponseTime = responseTime;
            }
        }
        
        updateLastUpdateTime();
    }
    
    /**
     * 记录异常
     */
    public void recordException() {
        exceptionCount.increment();
        updateLastUpdateTime();
    }
    
    /**
     * 记录降级
     */
    public void recordFallback() {
        fallbackCount.increment();
        updateLastUpdateTime();
    }
    
    /**
     * 更新缓存大小
     * 
     * @param size 新的缓存大小
     */
    public void updateCacheSize(long size) {
        cacheSize.set(size);
        updateLastUpdateTime();
    }
    
    /**
     * 增加缓存大小
     * 
     * @param delta 增加的大小
     */
    public void incrementCacheSize(long delta) {
        cacheSize.addAndGet(delta);
        updateLastUpdateTime();
    }
    
    /**
     * 获取总请求次数
     * 
     * @return 总请求次数
     */
    public long getTotalRequestCount() {
        return hitCount.sum() + missCount.sum();
    }
    
    /**
     * 获取缓存命中率
     * 
     * @return 命中率（0.0-1.0）
     */
    public double getHitRate() {
        long totalRequests = getTotalRequestCount();
        return totalRequests == 0 ? 0.0 : (double) hitCount.sum() / totalRequests;
    }
    
    /**
     * 获取缓存未命中率
     * 
     * @return 未命中率（0.0-1.0）
     */
    public double getMissRate() {
        return 1.0 - getHitRate();
    }
    
    /**
     * 获取平均响应时间
     * 
     * @return 平均响应时间（毫秒）
     */
    public double getAverageResponseTime() {
        long totalRequests = getTotalRequestCount();
        return totalRequests == 0 ? 0.0 : (double) totalResponseTime.sum() / totalRequests;
    }
    
    /**
     * 获取异常率
     * 
     * @return 异常率（0.0-1.0）
     */
    public double getExceptionRate() {
        long totalRequests = getTotalRequestCount();
        return totalRequests == 0 ? 0.0 : (double) exceptionCount.sum() / totalRequests;
    }
    
    /**
     * 获取降级率
     * 
     * @return 降级率（0.0-1.0）
     */
    public double getFallbackRate() {
        long totalRequests = getTotalRequestCount();
        return totalRequests == 0 ? 0.0 : (double) fallbackCount.sum() / totalRequests;
    }
    
    /**
     * 重置统计信息
     */
    public void reset() {
        hitCount.reset();
        missCount.reset();
        loadCount.reset();
        evictionCount.reset();
        totalResponseTime.reset();
        exceptionCount.reset();
        fallbackCount.reset();
        cacheSize.set(0);
        maxResponseTime = 0;
        minResponseTime = Long.MAX_VALUE;
        
        LocalDateTime now = LocalDateTime.now();
        startTime = now;
        lastUpdateTime = now;
    }
    
    /**
     * 获取统计快照
     * 
     * @return 统计快照
     */
    public CacheStatsSnapshot getSnapshot() {
        return CacheStatsSnapshot.builder()
                .hitCount(hitCount.sum())
                .missCount(missCount.sum())
                .loadCount(loadCount.sum())
                .evictionCount(evictionCount.sum())
                .exceptionCount(exceptionCount.sum())
                .fallbackCount(fallbackCount.sum())
                .totalRequestCount(getTotalRequestCount())
                .hitRate(getHitRate())
                .missRate(getMissRate())
                .averageResponseTime(getAverageResponseTime())
                .maxResponseTime(maxResponseTime)
                .minResponseTime(minResponseTime == Long.MAX_VALUE ? 0 : minResponseTime)
                .exceptionRate(getExceptionRate())
                .fallbackRate(getFallbackRate())
                .cacheSize(cacheSize.get())
                .startTime(startTime)
                .lastUpdateTime(lastUpdateTime)
                .build();
    }
    
    /**
     * 更新最后更新时间
     */
    private void updateLastUpdateTime() {
        lastUpdateTime = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return String.format("CacheStats{requests=%d, hitRate=%.2f%%, avgResponseTime=%.2fms, cacheSize=%d}", 
                getTotalRequestCount(), getHitRate() * 100, getAverageResponseTime(), cacheSize.get());
    }
    
    /**
     * 缓存统计快照类
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CacheStatsSnapshot implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private long hitCount;
        private long missCount;
        private long loadCount;
        private long evictionCount;
        private long exceptionCount;
        private long fallbackCount;
        private long totalRequestCount;
        private double hitRate;
        private double missRate;
        private double averageResponseTime;
        private long maxResponseTime;
        private long minResponseTime;
        private double exceptionRate;
        private double fallbackRate;
        private long cacheSize;
        private LocalDateTime startTime;
        private LocalDateTime lastUpdateTime;
    }
}