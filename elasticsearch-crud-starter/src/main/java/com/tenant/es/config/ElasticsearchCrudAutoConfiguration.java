package com.tenant.es.config;

import com.tenant.es.properties.ElasticsearchCrudProperties;
import com.tenant.es.service.ElasticsearchCrudService;
import com.tenant.es.service.impl.ElasticsearchCrudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Elasticsearch CRUD自动配置类
 * 
 * @author system
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(ElasticsearchCrudProperties.class)
@ConditionalOnProperty(prefix = "tenant.elasticsearch", name = "enabled", havingValue = "true", matchIfMissing = true)
@Slf4j
public class ElasticsearchCrudAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchCrudService elasticsearchCrudService(ElasticsearchCrudProperties properties) {
        log.info("[ElasticsearchCrudAutoConfiguration] 初始化Elasticsearch CRUD服务");
        return new ElasticsearchCrudServiceImpl(properties);
    }
}