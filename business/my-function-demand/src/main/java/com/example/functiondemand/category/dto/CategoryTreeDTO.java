package com.example.functiondemand.category.dto;

import com.example.functiondemand.common.enums.CategoryType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryTreeDTO {
    private String id;
    private String parentId;
    private String name;
    private CategoryType type;
    private String description;
    private Integer level;
    private String path;
    private Integer sortOrder;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;
    private List<CategoryTreeDTO> children;
}