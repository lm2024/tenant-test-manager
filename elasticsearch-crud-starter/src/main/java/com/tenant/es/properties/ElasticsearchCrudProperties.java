package com.tenant.es.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Elasticsearch CRUD配置属性
 * 
 * @author system
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "tenant.elasticsearch")
@Data
public class ElasticsearchCrudProperties {
    
    /**
     * 是否启用Elasticsearch功能
     */
    private boolean enabled = true;
    
    /**
     * Elasticsearch集群地址
     */
    private String hosts = "localhost:9200";
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 连接超时时间（毫秒）
     */
    private int connectTimeout = 5000;
    
    /**
     * 读取超时时间（毫秒）
     */
    private int readTimeout = 30000;
    
    /**
     * 批量操作大小
     */
    private int batchSize = 1000;
    
    /**
     * 最大重试次数
     */
    private int maxRetryTimes = 3;
    
    /**
     * 索引前缀
     */
    private String indexPrefix = "tenant";
    
    /**
     * 是否使用keyword类型（不分词）
     */
    private boolean useKeywordType = true;
    
    /**
     * 同步队列类型：redis 或 activemq
     */
    private String syncQueueType = "redis";
    
    /**
     * 数据一致性检查间隔（小时）
     */
    private int consistencyCheckInterval = 24;
}