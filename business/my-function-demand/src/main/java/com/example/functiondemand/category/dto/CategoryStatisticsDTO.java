package com.example.functiondemand.category.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CategoryStatisticsDTO {
    private Long totalCount;
    private Map<String, Long> typeCount;
    private Map<Integer, Long> levelCount;
    private Long rootCount;
    private Long leafCount;
    private Double avgLevel;
    private Integer maxLevel;
    private Long requirementCategoryCount;
    private Long functionCategoryCount;
    private Map<String, Long> itemCountByCategory; // 每个分类下的项目数量
}