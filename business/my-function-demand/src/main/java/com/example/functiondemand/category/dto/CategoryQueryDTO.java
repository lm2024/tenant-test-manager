package com.example.functiondemand.category.dto;

import com.example.functiondemand.common.enums.CategoryType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryQueryDTO {
    private String keyword;
    private CategoryType type;
    private String parentId;
    private Integer level;
    private LocalDateTime createdTimeStart;
    private LocalDateTime createdTimeEnd;
    private LocalDateTime updatedTimeStart;
    private LocalDateTime updatedTimeEnd;
}