package com.common.segmentid.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 号段ID生成器配置属性
 */
@Data
@ConfigurationProperties(prefix = "segment.id")
public class SegmentIdProperties {

    /**
     * 默认号段大小
     */
    private int defaultStep = 1000;

    /**
     * 缓存过期时间（小时）
     */
    private int cacheExpireHours = 24;

    /**
     * 是否启用预加载
     */
    private boolean preloadEnabled = true;

    /**
     * 预加载的业务类型列表
     */
    private List<String> preloadBusinessTypes;

    /**
     * 分布式锁超时时间（秒）
     */
    private int lockTimeoutSeconds = 10;

    /**
     * 是否启用性能监控
     */
    private boolean performanceMonitoringEnabled = false;

    /**
     * 号段耗尽告警阈值（百分比）
     */
    private int segmentExhaustionThreshold = 80;
}
