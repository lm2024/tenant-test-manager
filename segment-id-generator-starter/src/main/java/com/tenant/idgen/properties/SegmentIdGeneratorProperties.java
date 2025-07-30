package com.tenant.idgen.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 号段ID生成器配置属性
 * 
 * @author system
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "tenant.id-generator")
@Data
public class SegmentIdGeneratorProperties {
    
    /**
     * 是否启用ID生成器
     */
    private boolean enabled = true;
    
    /**
     * 默认步进大小
     */
    private int defaultStep = 1000;
    
    /**
     * 缓存预加载阈值（百分比）
     */
    private double preloadThreshold = 0.1;
    
    /**
     * Redis缓存前缀
     */
    private String cachePrefix = "tenant:id:segment:";
    
    /**
     * 数据库表名
     */
    private String tableName = "sequence";
    
    /**
     * 最大重试次数
     */
    private int maxRetryTimes = 3;
    
    /**
     * 重试间隔（毫秒）
     */
    private long retryInterval = 100;
}