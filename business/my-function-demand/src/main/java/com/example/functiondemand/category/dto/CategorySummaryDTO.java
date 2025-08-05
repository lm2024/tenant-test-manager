package com.example.functiondemand.category.dto;

import com.example.functiondemand.common.enums.CategoryType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategorySummaryDTO {
    private String id;
    private String name;
    private CategoryType type;
    private Integer level;
    private Integer sortOrder;
    private Integer childrenCount;
    private Integer itemCount; // 该分类下的需求或功能点数量
    private String fullPath; // 完整路径名称
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}