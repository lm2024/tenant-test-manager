package com.example.functiondemand.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class TreePerformanceMonitor {
    
    private final ConcurrentHashMap<String, AtomicLong> queryCounters = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtomicLong> queryTimes = new ConcurrentHashMap<>();
    
    /**
     * 记录查询开始时间
     */
    public long startQuery(String queryType, String tenantId) {
        String key = queryType + ":" + tenantId;
        queryCounters.computeIfAbsent(key, k -> new AtomicLong(0)).incrementAndGet();
        return System.currentTimeMillis();
    }
    
    /**
     * 记录查询结束时间并计算耗时
     */
    public void endQuery(String queryType, String tenantId, long startTime, int resultCount) {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        String key = queryType + ":" + tenantId;
        queryTimes.computeIfAbsent(key, k -> new AtomicLong(0)).addAndGet(duration);
        
        // 记录慢查询
        if (duration > 1000) { // 超过1秒的查询
            log.warn("慢查询检测 - 类型: {}, 租户: {}, 耗时: {}ms, 结果数: {}", 
                queryType, tenantId, duration, resultCount);
        } else if (duration > 500) { // 超过500ms的查询
            log.info("查询性能监控 - 类型: {}, 租户: {}, 耗时: {}ms, 结果数: {}", 
                queryType, tenantId, duration, resultCount);
        }
    }
    
    /**
     * 获取查询统计信息
     */
    public String getQueryStats(String queryType, String tenantId) {
        String key = queryType + ":" + tenantId;
        AtomicLong count = queryCounters.get(key);
        AtomicLong totalTime = queryTimes.get(key);
        
        if (count == null || totalTime == null) {
            return "无统计数据";
        }
        
        long avgTime = totalTime.get() / count.get();
        return String.format("查询次数: %d, 总耗时: %dms, 平均耗时: %dms", 
            count.get(), totalTime.get(), avgTime);
    }
    
    /**
     * 清除统计数据
     */
    public void clearStats(String queryType, String tenantId) {
        String key = queryType + ":" + tenantId;
        queryCounters.remove(key);
        queryTimes.remove(key);
    }
    
    /**
     * 获取所有统计数据
     */
    public void logAllStats() {
        log.info("=== 树形查询性能统计 ===");
        queryCounters.forEach((key, count) -> {
            AtomicLong totalTime = queryTimes.get(key);
            if (totalTime != null) {
                long avgTime = totalTime.get() / count.get();
                log.info("{} - 查询次数: {}, 总耗时: {}ms, 平均耗时: {}ms", 
                    key, count.get(), totalTime.get(), avgTime);
            }
        });
        log.info("=== 统计结束 ===");
    }
}