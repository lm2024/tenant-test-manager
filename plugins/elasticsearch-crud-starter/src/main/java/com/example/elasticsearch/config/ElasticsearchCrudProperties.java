package com.example.elasticsearch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Elasticsearch CRUD 配置属性
 * 
 * @author Kiro
 */
@ConfigurationProperties(prefix = "elasticsearch.crud")
public class ElasticsearchCrudProperties {

    private boolean enabled = true;
    private List<String> hosts;
    private String username;
    private String password;
    private int connectionTimeout = 5000;
    private int socketTimeout = 60000;
    private int requestTimeout = 60000;
    private int maxConnections = 100;
    private int maxConnectionsPerRoute = 100;
    private SyncConfig sync = new SyncConfig();
    private TenantConfig tenant = new TenantConfig();
    private IndexConfig index = new IndexConfig();
    private QueueConfig queue = new QueueConfig();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getMaxConnectionsPerRoute() {
        return maxConnectionsPerRoute;
    }

    public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
    }

    public SyncConfig getSync() {
        return sync;
    }

    public void setSync(SyncConfig sync) {
        this.sync = sync;
    }

    public TenantConfig getTenant() {
        return tenant;
    }

    public void setTenant(TenantConfig tenant) {
        this.tenant = tenant;
    }

    public IndexConfig getIndex() {
        return index;
    }

    public void setIndex(IndexConfig index) {
        this.index = index;
    }

    public QueueConfig getQueue() {
        return queue;
    }

    public void setQueue(QueueConfig queue) {
        this.queue = queue;
    }

    public static class SyncConfig {
        private int batchSize = 1000;
        private int threadPoolSize = 10;
        private int retryTimes = 3;
        private int retryInterval = 1000;
        private boolean enableCheckpoint = true;
        private int checkpointInterval = 1000;

        public int getBatchSize() {
            return batchSize;
        }

        public void setBatchSize(int batchSize) {
            this.batchSize = batchSize;
        }

        public int getThreadPoolSize() {
            return threadPoolSize;
        }

        public void setThreadPoolSize(int threadPoolSize) {
            this.threadPoolSize = threadPoolSize;
        }

        public int getRetryTimes() {
            return retryTimes;
        }

        public void setRetryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
        }

        public int getRetryInterval() {
            return retryInterval;
        }

        public void setRetryInterval(int retryInterval) {
            this.retryInterval = retryInterval;
        }

        public boolean isEnableCheckpoint() {
            return enableCheckpoint;
        }

        public void setEnableCheckpoint(boolean enableCheckpoint) {
            this.enableCheckpoint = enableCheckpoint;
        }

        public int getCheckpointInterval() {
            return checkpointInterval;
        }

        public void setCheckpointInterval(int checkpointInterval) {
            this.checkpointInterval = checkpointInterval;
        }
    }

    public static class TenantConfig {
        private boolean enabled = true;
        private String indexPrefix = "tenant_";
        private boolean autoCreateIndex = true;
        private int defaultShards = 3;
        private int defaultReplicas = 1;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getIndexPrefix() {
            return indexPrefix;
        }

        public void setIndexPrefix(String indexPrefix) {
            this.indexPrefix = indexPrefix;
        }

        public boolean isAutoCreateIndex() {
            return autoCreateIndex;
        }

        public void setAutoCreateIndex(boolean autoCreateIndex) {
            this.autoCreateIndex = autoCreateIndex;
        }

        public int getDefaultShards() {
            return defaultShards;
        }

        public void setDefaultShards(int defaultShards) {
            this.defaultShards = defaultShards;
        }

        public int getDefaultReplicas() {
            return defaultReplicas;
        }

        public void setDefaultReplicas(int defaultReplicas) {
            this.defaultReplicas = defaultReplicas;
        }
    }

    public static class IndexConfig {
        private boolean useKeywordAsDefault = true;
        private int defaultShards = 1;
        private int defaultReplicas = 0;
        private String refreshInterval = "1s";

        public boolean isUseKeywordAsDefault() {
            return useKeywordAsDefault;
        }

        public void setUseKeywordAsDefault(boolean useKeywordAsDefault) {
            this.useKeywordAsDefault = useKeywordAsDefault;
        }

        public int getDefaultShards() {
            return defaultShards;
        }

        public void setDefaultShards(int defaultShards) {
            this.defaultShards = defaultShards;
        }

        public int getDefaultReplicas() {
            return defaultReplicas;
        }

        public void setDefaultReplicas(int defaultReplicas) {
            this.defaultReplicas = defaultReplicas;
        }

        public String getRefreshInterval() {
            return refreshInterval;
        }

        public void setRefreshInterval(String refreshInterval) {
            this.refreshInterval = refreshInterval;
        }
    }

    public static class QueueConfig {
        private String type = "redis";
        private RedisConfig redis = new RedisConfig();

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public RedisConfig getRedis() {
            return redis;
        }

        public void setRedis(RedisConfig redis) {
            this.redis = redis;
        }

        public static class RedisConfig {
            private String keyPrefix = "es:sync:";
            private int maxRetry = 3;
            private int consumerThreads = 5;

            public String getKeyPrefix() {
                return keyPrefix;
            }

            public void setKeyPrefix(String keyPrefix) {
                this.keyPrefix = keyPrefix;
            }

            public int getMaxRetry() {
                return maxRetry;
            }

            public void setMaxRetry(int maxRetry) {
                this.maxRetry = maxRetry;
            }

            public int getConsumerThreads() {
                return consumerThreads;
            }

            public void setConsumerThreads(int consumerThreads) {
                this.consumerThreads = consumerThreads;
            }
        }
    }
}