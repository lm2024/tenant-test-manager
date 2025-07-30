package com.example.elasticsearch.service;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.tenant.routing.core.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 租户索引解析器
 * 根据租户上下文解析索引名称
 * 
 * @author Kiro
 */
@Slf4j
public class TenantIndexResolver {

    private final ElasticsearchCrudProperties properties;

    public TenantIndexResolver(ElasticsearchCrudProperties properties) {
        this.properties = properties;
    }

    /**
     * 解析当前租户的默认索引名称
     * 
     * @return 索引名称
     */
    public String resolve() {
        return resolve("data");
    }

    /**
     * 解析当前租户的指定基础索引名称
     * 
     * @param baseIndexName 基础索引名称
     * @return 完整的租户索引名称
     */
    public String resolve(String baseIndexName) {
        if (!properties.getTenant().isEnabled()) {
            return baseIndexName;
        }

        String tenantId = TenantContextHolder.getTenantId();
        if (!StringUtils.hasText(tenantId)) {
            log.warn("租户ID为空，使用默认索引名称: {}", baseIndexName);
            return baseIndexName;
        }

        StringBuilder indexName = new StringBuilder();
        
        // 添加前缀
        if (StringUtils.hasText(properties.getTenant().getIndexPrefix())) {
            indexName.append(properties.getTenant().getIndexPrefix());
        }
        
        // 添加租户ID
        indexName.append(tenantId);
        
        // 添加基础索引名称
        if (StringUtils.hasText(baseIndexName)) {
            indexName.append("_").append(baseIndexName);
        }
        
        // 添加后缀
        if (StringUtils.hasText(properties.getTenant().getIndexSuffix())) {
            indexName.append("_").append(properties.getTenant().getIndexSuffix());
        }

        String finalIndexName = indexName.toString().toLowerCase();
        log.debug("解析租户索引名称: 租户ID={}, 基础名称={}, 最终名称={}", tenantId, baseIndexName, finalIndexName);
        
        return finalIndexName;
    }

    /**
     * 从完整索引名称中提取租户ID
     * 
     * @param fullIndexName 完整索引名称
     * @return 租户ID
     */
    public String extractTenantId(String fullIndexName) {
        if (!properties.getTenant().isEnabled() || !StringUtils.hasText(fullIndexName)) {
            return null;
        }

        String prefix = properties.getTenant().getIndexPrefix();
        if (StringUtils.hasText(prefix) && fullIndexName.startsWith(prefix)) {
            String remaining = fullIndexName.substring(prefix.length());
            int underscoreIndex = remaining.indexOf('_');
            if (underscoreIndex > 0) {
                return remaining.substring(0, underscoreIndex);
            } else {
                return remaining;
            }
        }

        return null;
    }

    /**
     * 检查索引名称是否属于指定租户
     * 
     * @param indexName 索引名称
     * @param tenantId 租户ID
     * @return 是否属于指定租户
     */
    public boolean belongsToTenant(String indexName, String tenantId) {
        if (!properties.getTenant().isEnabled()) {
            return true;
        }

        String extractedTenantId = extractTenantId(indexName);
        return tenantId != null && tenantId.equals(extractedTenantId);
    }
}