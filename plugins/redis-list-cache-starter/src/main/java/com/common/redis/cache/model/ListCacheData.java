package com.common.redis.cache.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 列表缓存数据封装类
 * 
 * <p>用于封装缓存的列表数据，包含数据内容、分页信息、缓存时间戳等元数据。</p>
 * 
 * @param <T> 列表元素类型
 * @author Kiro
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCacheData<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 缓存的数据列表
     */
    private List<T> content;
    
    /**
     * 分页信息
     */
    private PageInfo pageInfo;
    
    /**
     * 缓存创建时间戳
     */
    private LocalDateTime cacheTime;
    
    /**
     * 数据版本号，用于缓存一致性控制
     */
    private String version;
    
    /**
     * 租户ID，用于多租户缓存隔离
     */
    private String tenantId;
    
    /**
     * 缓存键，用于调试和监控
     */
    private String cacheKey;
    
    /**
     * 数据来源，用于调试
     */
    private DataSource dataSource;
    
    /**
     * 缓存过期时间戳
     */
    private LocalDateTime expireTime;
    
    /**
     * 创建缓存数据实例
     * 
     * @param content 数据列表
     * @param pageInfo 分页信息
     * @param tenantId 租户ID
     * @param cacheKey 缓存键
     * @param expireSeconds 过期时间（秒）
     * @param <T> 数据类型
     * @return 缓存数据实例
     */
    public static <T> ListCacheData<T> create(List<T> content, PageInfo pageInfo, 
                                              String tenantId, String cacheKey, long expireSeconds) {
        LocalDateTime now = LocalDateTime.now();
        return ListCacheData.<T>builder()
                .content(content)
                .pageInfo(pageInfo)
                .cacheTime(now)
                .expireTime(now.plusSeconds(expireSeconds))
                .tenantId(tenantId)
                .cacheKey(cacheKey)
                .version(generateVersion())
                .dataSource(DataSource.DATABASE)
                .build();
    }
    
    /**
     * 创建来自缓存的数据实例
     * 
     * @param original 原始缓存数据
     * @param <T> 数据类型
     * @return 标记为来自缓存的数据实例
     */
    public static <T> ListCacheData<T> fromCache(ListCacheData<T> original) {
        return ListCacheData.<T>builder()
                .content(original.getContent())
                .pageInfo(original.getPageInfo())
                .cacheTime(original.getCacheTime())
                .expireTime(original.getExpireTime())
                .tenantId(original.getTenantId())
                .cacheKey(original.getCacheKey())
                .version(original.getVersion())
                .dataSource(DataSource.CACHE)
                .build();
    }
    
    /**
     * 检查缓存是否已过期
     * 
     * @return 是否已过期
     */
    public boolean isExpired() {
        return expireTime != null && LocalDateTime.now().isAfter(expireTime);
    }
    
    /**
     * 检查是否为空数据
     * 
     * @return 是否为空
     */
    public boolean isEmpty() {
        return content == null || content.isEmpty();
    }
    
    /**
     * 获取数据大小
     * 
     * @return 数据大小
     */
    public int size() {
        return content == null ? 0 : content.size();
    }
    
    /**
     * 检查是否来自缓存
     * 
     * @return 是否来自缓存
     */
    public boolean isFromCache() {
        return DataSource.CACHE.equals(dataSource);
    }
    
    /**
     * 检查是否来自数据库
     * 
     * @return 是否来自数据库
     */
    public boolean isFromDatabase() {
        return DataSource.DATABASE.equals(dataSource);
    }
    
    /**
     * 生成版本号
     * 
     * @return 版本号
     */
    private static String generateVersion() {
        return String.valueOf(System.currentTimeMillis());
    }
    
    /**
     * 数据来源枚举
     */
    public enum DataSource {
        /**
         * 来自数据库
         */
        DATABASE,
        
        /**
         * 来自缓存
         */
        CACHE
    }
    
    @Override
    public String toString() {
        return String.format("ListCacheData{size=%d, pageInfo=%s, cacheTime=%s, source=%s, tenantId=%s}", 
                size(), pageInfo, cacheTime, dataSource, tenantId);
    }
}