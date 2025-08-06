package com.example.elasticsearch.config;

import com.example.elasticsearch.service.ElasticsearchCrudService;
import com.example.elasticsearch.service.ElasticsearchIndexService;
import com.example.elasticsearch.service.impl.ElasticsearchCrudServiceImpl;
import com.example.elasticsearch.service.impl.ElasticsearchIndexServiceImpl;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Arrays;

@Configuration
@EnableConfigurationProperties(ElasticsearchCrudProperties.class)
@ConditionalOnProperty(prefix = "elasticsearch.crud", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ElasticsearchCrudAutoConfiguration {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElasticsearchCrudAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient(ElasticsearchCrudProperties properties) {
        log.info("创建 Elasticsearch RestHighLevelClient");
        
        // 检查hosts配置
        if (properties.getHosts() == null || properties.getHosts().isEmpty()) {
            log.warn("Elasticsearch hosts未配置，使用默认配置 localhost:9200");
            properties.setHosts(Arrays.asList("localhost:9200"));
        }
        
        // 构建RestClient.Builder
        String host = properties.getHosts().get(0);
        String[] hostParts = host.split(":");
        String hostname = hostParts[0];
        int port = hostParts.length > 1 ? Integer.parseInt(hostParts[1]) : 9200;
        
        RestClientBuilder builder = RestClient.builder(
            new HttpHost(hostname, port, "http")
        );
        
        // 设置认证
        log.info("ES配置信息 - 用户名: {}, 密码: {}", 
                properties.getUsername() != null ? "已配置" : "未配置",
                properties.getPassword() != null ? "已配置" : "未配置");
        
        if (properties.getUsername() != null && !properties.getUsername().trim().isEmpty() 
            && properties.getPassword() != null && !properties.getPassword().trim().isEmpty()) {
            log.info("配置ES认证信息: 用户名={}", properties.getUsername());
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
            
            builder.setHttpClientConfigCallback(httpClientBuilder ->
                    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        } else {
            log.info("ES未配置认证信息，使用无认证模式");
        }
        
        // 设置超时和连接池配置
        builder.setRequestConfigCallback(requestConfigBuilder ->
                requestConfigBuilder
                        .setConnectTimeout(properties.getConnectionTimeout())
                        .setSocketTimeout(properties.getSocketTimeout())
                        .setConnectionRequestTimeout(properties.getRequestTimeout()));
        
        builder.setHttpClientConfigCallback(httpClientBuilder ->
                httpClientBuilder
                        .setMaxConnTotal(properties.getMaxConnections())
                        .setMaxConnPerRoute(properties.getMaxConnectionsPerRoute()));
        
        return new RestHighLevelClient(builder);
    }

    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchIndexService elasticsearchIndexService(RestHighLevelClient restHighLevelClient,
                                                              ElasticsearchCrudProperties properties) {
        log.info("创建 ElasticsearchIndexService");
        return new ElasticsearchIndexServiceImpl(restHighLevelClient, properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchCrudService<Object> elasticsearchCrudService(RestHighLevelClient restHighLevelClient,
                                                                    ElasticsearchCrudProperties properties) {
        log.info("创建 ElasticsearchCrudService");
        // 注意：这里返回的是通用实现，实际使用时需要根据具体的实体类型来创建
        return new ElasticsearchCrudServiceImpl<>(restHighLevelClient, properties, Object.class);
    }
}