package com.example.elasticsearch.service;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import org.springframework.stereotype.Component;

/**
 * 租户索引解析器
 * 
 * @author Kiro
 */
@Component
public class TenantIndexResolver {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TenantIndexResolver.class);

    private final ElasticsearchCrudProperties properties;

    public TenantIndexResolver(ElasticsearchCrudProperties properties) {
        this.properties = properties;
    }

    public String resolveIndexName(String tenantId, String baseIndexName) {
        if (!properties.getTenant().isEnabled()) {
            return baseIndexName;
        }
        
        String prefix = properties.getTenant().getIndexPrefix();
        return prefix + tenantId + "_" + baseIndexName;
    }

    public String extractTenantId(String indexName) {
        if (!properties.getTenant().isEnabled()) {
            return null;
        }
        
        String prefix = properties.getTenant().getIndexPrefix();
        if (indexName.startsWith(prefix)) {
            String remaining = indexName.substring(prefix.length());
            int underscoreIndex = remaining.indexOf('_');
            if (underscoreIndex > 0) {
                return remaining.substring(0, underscoreIndex);
            }
        }
        return null;
    }
}