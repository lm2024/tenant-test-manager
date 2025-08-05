package com.example.functiondemand.category.dto;

import com.example.functiondemand.common.enums.CategoryType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryUpdateDTO {
    @NotBlank(message = "分类ID不能为空")
    private String id;
    
    private String parentId;
    
    @NotBlank(message = "分类名称不能为空")
    private String name;
    
    @NotNull(message = "分类类型不能为空")
    private CategoryType type;
    
    private String description;
    private Integer sortOrder;
    private String updatedBy;
}