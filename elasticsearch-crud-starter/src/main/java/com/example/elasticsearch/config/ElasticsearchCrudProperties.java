package com.example.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * Elasticsearch CRUD 配置属性
 * 
 * @author Kiro
 */
@Data
@ConfigurationProperties(prefix = "elasticsearch.crud")
public class ElasticsearchCrudProperties {

    /**
     * Elasticsearch集群节点地址
     */
    private List<String> hosts = Arrays.asList("localhost:9200");

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
    private int connectionTimeout = 5000;

    /**
     * Socket超时时间（毫秒）
     */
    private int socketTimeout = 60000;

    /**
     * 请求超时时间（毫秒）
     */
    private int requestTimeout = 60000;

    /**
     * 最大连接数
     */
    private int maxConnections = 100;

    /**
     * 每个路由的最大连接数
     */
    private int maxConnectionsPerRoute = 100;

    /**
     * 同步配置
     */
    private SyncConfig sync = new SyncConfig();

    /**
     * 队列配置
     */
    private QueueConfig queue = new QueueConfig();

    /**
     * 租户配置
     */
    private TenantConfig tenant = new TenantConfig();

    /**
     * 索引配置
     */
    private IndexConfig index = new IndexConfig();

    /**
     * 同步配置类
     */
    @Data
    public static class SyncConfig {
        /**
         * 批处理大小
         */
        private int batchSize = 1000;

        /**
         * 线程池大小
         */
        private int threadPoolSize = 10;

        /**
         * 重试次数
         */
        private int retryTimes = 3;

        /**
         * 重试间隔（毫秒）
         */
        private long retryInterval = 1000;

        /**
         * 是否启用断点续传
         */
        private boolean enableCheckpoint = true;

        /**
         * 检查点保存间隔
         */
        private int checkpointInterval = 1000;
    }

    /**
     * 队列配置类
     */
    @Data
    public static class QueueConfig {
        /**
         * 队列类型：redis, activemq
         */
        private String type = "redis";

        /**
         * Redis队列配置
         */
        private RedisQueueConfig redis = new RedisQueueConfig();

        @Data
        public static class RedisQueueConfig {
            /**
             * 队列键前缀
             */
            private String keyPrefix = "es:sync:";

            /**
             * 最大重试次数
             */
            private int maxRetry = 3;

            /**
             * 消费者线程数
             */
            private int consumerThreads = 5;

            /**
             * 批量消费大小
             */
            private int batchSize = 10;

            /**
             * 消费超时时间（秒）
             */
            private int consumeTimeout = 30;

            /**
             * 死信队列键
             */
            private String deadLetterKey = "es:sync:dead";
        }
    }

    /**
     * 租户配置类
     */
    @Data
    public static class TenantConfig {
        /**
         * 是否启用租户支持
         */
        private boolean enabled = true;

        /**
         * 索引前缀
         */
        private String indexPrefix = "tenant_";

        /**
         * 索引后缀
         */
        private String indexSuffix = "";

        /**
         * 是否自动创建租户索引
         */
        private boolean autoCreateIndex = true;

        /**
         * 默认分片数
         */
        private int defaultShards = 3;

        /**
         * 默认副本数
         */
        private int defaultReplicas = 1;
    }

    /**
     * 索引配置类
     */
    @Data
    public static class IndexConfig {
        /**
         * 默认分片数
         */
        private int defaultShards = 3;

        /**
         * 默认副本数
         */
        private int defaultReplicas = 1;

        /**
         * 刷新间隔
         */
        private String refreshInterval = "1s";

        /**
         * 是否使用keyword类型作为默认字符串类型
         */
        private boolean useKeywordAsDefault = true;

        /**
         * 最大结果窗口大小
         */
        private int maxResultWindow = 10000;

        /**
         * 索引模板名称
         */
        private String templateName = "elasticsearch-crud-template";
    }
}