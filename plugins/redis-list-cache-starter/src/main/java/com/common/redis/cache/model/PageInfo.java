package com.common.redis.cache.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * 分页信息类
 * 
 * <p>封装分页查询的相关信息，用于缓存键生成和分页逻辑处理。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 当前页码（从0开始）
     */
    private int pageNumber;
    
    /**
     * 每页大小
     */
    private int pageSize;
    
    /**
     * 总元素数量
     */
    private long totalElements;
    
    /**
     * 总页数
     */
    private int totalPages;
    
    /**
     * 是否有下一页
     */
    private boolean hasNext;
    
    /**
     * 是否有上一页
     */
    private boolean hasPrevious;
    
    /**
     * 是否为第一页
     */
    private boolean isFirst;
    
    /**
     * 是否为最后一页
     */
    private boolean isLast;
    
    /**
     * 排序信息字符串
     */
    private String sortInfo;
    
    /**
     * 从Spring Data的Page对象创建PageInfo
     * 
     * @param page Spring Data的Page对象
     * @param <T> 页面元素类型
     * @return PageInfo实例
     */
    public static <T> PageInfo fromPage(Page<T> page) {
        return PageInfo.builder()
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .sortInfo(page.getSort().toString())
                .build();
    }
    
    /**
     * 从Pageable对象创建PageInfo（用于缓存键生成）
     * 
     * @param pageable Pageable对象
     * @return PageInfo实例
     */
    public static PageInfo fromPageable(Pageable pageable) {
        return PageInfo.builder()
                .pageNumber(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .sortInfo(pageable.getSort().toString())
                .build();
    }
    
    /**
     * 创建简单的分页信息
     * 
     * @param pageNumber 页码
     * @param pageSize 页大小
     * @return PageInfo实例
     */
    public static PageInfo of(int pageNumber, int pageSize) {
        return PageInfo.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
    
    /**
     * 创建完整的分页信息
     * 
     * @param pageNumber 页码
     * @param pageSize 页大小
     * @param totalElements 总元素数
     * @return PageInfo实例
     */
    public static PageInfo of(int pageNumber, int pageSize, long totalElements) {
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        return PageInfo.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .hasNext(pageNumber < totalPages - 1)
                .hasPrevious(pageNumber > 0)
                .isFirst(pageNumber == 0)
                .isLast(pageNumber == totalPages - 1)
                .build();
    }
    
    /**
     * 检查是否应该缓存此页面
     * 
     * @param maxCachePages 最大缓存页数
     * @return 是否应该缓存
     */
    public boolean shouldCache(int maxCachePages) {
        return pageNumber < maxCachePages;
    }
    
    /**
     * 获取偏移量
     * 
     * @return 偏移量
     */
    public long getOffset() {
        return (long) pageNumber * pageSize;
    }
    
    /**
     * 生成用于缓存键的字符串
     * 
     * @return 缓存键字符串
     */
    public String toCacheKeyString() {
        StringBuilder sb = new StringBuilder();
        sb.append("page_").append(pageNumber);
        sb.append("_size_").append(pageSize);
        
        if (sortInfo != null && !sortInfo.trim().isEmpty() && !"UNSORTED".equals(sortInfo)) {
            // 对排序信息进行哈希处理，避免键过长
            sb.append("_sort_").append(Math.abs(sortInfo.hashCode()));
        }
        
        return sb.toString();
    }
    
    /**
     * 检查分页参数是否有效
     * 
     * @return 是否有效
     */
    public boolean isValid() {
        return pageNumber >= 0 && pageSize > 0;
    }
    
    @Override
    public String toString() {
        return String.format("PageInfo{page=%d, size=%d, total=%d, totalPages=%d, hasNext=%s, hasPrevious=%s}", 
                pageNumber, pageSize, totalElements, totalPages, hasNext, hasPrevious);
    }
}