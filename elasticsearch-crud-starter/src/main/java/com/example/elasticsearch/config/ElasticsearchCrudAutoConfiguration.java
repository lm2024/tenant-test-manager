package com.example.elasticsearch.config;

import com.example.elasticsearch.service.*;
import com.example.elasticsearch.service.impl.*;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Elasticsearch CRUD 自动配置类
 * 
 * @author Kiro
 */
@Configuration
@EnableRetry
@EnableAsync
@ConditionalOnClass({RestHighLevelClient.class, ElasticsearchRestTemplate.class})
@EnableConfigurationProperties(ElasticsearchCrudProperties.class)
@ConditionalOnProperty(prefix = "elasticsearch.crud", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ElasticsearchCrudAutoConfiguration {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElasticsearchCrudAutoConfiguration.class);

    private final ElasticsearchCrudProperties properties;

    public ElasticsearchCrudAutoConfiguration(ElasticsearchCrudProperties properties) {
        this.properties = properties;
        log.info("Elasticsearch CRUD Starter 自动配置开始初始化...");
    }

    /**
     * 配置Elasticsearch RestHighLevelClient
     */
    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient() {
        log.info("创建 Elasticsearch RestHighLevelClient，节点: {}", properties.getHosts());
        
        HttpHost[] httpHosts = properties.getHosts().stream()
                .map(host -> {
                    String[] parts = host.split(":");
                    return new HttpHost(parts[0], Integer.parseInt(parts[1]), "http");
                })
                .toArray(HttpHost[]::new);

        RestClientBuilder builder = RestClient.builder(httpHosts);

        // 设置认证
        if (properties.getUsername() != null && properties.getPassword() != null) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
            
            builder.setHttpClientConfigCallback(httpClientBuilder ->
                    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }

        // 设置超时时间
        builder.setRequestConfigCallback(requestConfigBuilder ->
                requestConfigBuilder
                        .setConnectTimeout(properties.getConnectionTimeout())
                        .setSocketTimeout(properties.getSocketTimeout())
                        .setConnectionRequestTimeout(properties.getRequestTimeout()));

        // 设置连接池
        builder.setHttpClientConfigCallback(httpClientBuilder ->
                httpClientBuilder
                        .setMaxConnTotal(properties.getMaxConnections())
                        .setMaxConnPerRoute(properties.getMaxConnectionsPerRoute()));

        return new RestHighLevelClient(builder);
    }

    /**
     * 配置ElasticsearchRestTemplate
     */
    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient restHighLevelClient) {
        return new ElasticsearchRestTemplate(restHighLevelClient);
    }

    /**
     * 配置异步任务执行器
     */
    @Bean(name = "elasticsearchTaskExecutor")
    @ConditionalOnMissingBean(name = "elasticsearchTaskExecutor")
    public Executor elasticsearchTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getSync().getThreadPoolSize());
        executor.setMaxPoolSize(properties.getSync().getThreadPoolSize() * 2);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("es-crud-");
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 配置基础CRUD服务
     */
    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchCrudService elasticsearchCrudService(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        return new ElasticsearchCrudServiceImpl(elasticsearchRestTemplate, properties);
    }

    /**
     * 配置复杂查询服务
     */
    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchComplexQueryService elasticsearchComplexQueryService(
            ElasticsearchRestTemplate elasticsearchRestTemplate) {
        return new ElasticsearchComplexQueryServiceImpl(elasticsearchRestTemplate, properties);
    }

    /**
     * 配置索引管理服务
     */
    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchIndexService elasticsearchIndexService(
            RestHighLevelClient restHighLevelClient,
            ElasticsearchRestTemplate elasticsearchRestTemplate) {
        return new ElasticsearchIndexServiceImpl(restHighLevelClient, elasticsearchRestTemplate, properties);
    }

    /**
     * 配置数据同步服务
     */
    @Bean
    @ConditionalOnMissingBean
    public DataSyncService dataSyncService(
            ElasticsearchRestTemplate elasticsearchRestTemplate,
            ElasticsearchCrudService elasticsearchCrudService) {
        return new DataSyncServiceImpl(elasticsearchRestTemplate, elasticsearchCrudService, properties);
    }

    /**
     * 配置队列同步服务
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "elasticsearch.crud.queue", name = "type", havingValue = "redis", matchIfMissing = true)
    public QueueSyncService queueSyncService(DataSyncService dataSyncService) {
        return new RedisQueueSyncServiceImpl(dataSyncService, properties);
    }

    /**
     * 配置租户索引解析器
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "elasticsearch.crud.tenant", name = "enabled", havingValue = "true", matchIfMissing = true)
    public TenantIndexResolver tenantIndexResolver() {
        return new TenantIndexResolver(properties);
    }

    /**
     * 配置监控指标收集器
     */
    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchMetrics elasticsearchMetrics() {
        return new ElasticsearchMetrics();
    }

    /**
     * 配置健康检查指示器
     */
    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchHealthIndicator elasticsearchHealthIndicator(RestHighLevelClient restHighLevelClient) {
        return new ElasticsearchHealthIndicator(restHighLevelClient);
    }

    /**
     * 配置重试操作执行器
     */
    @Bean
    @ConditionalOnMissingBean
    public RetryableOperationExecutor retryableOperationExecutor() {
        return new RetryableOperationExecutor();
    }
}