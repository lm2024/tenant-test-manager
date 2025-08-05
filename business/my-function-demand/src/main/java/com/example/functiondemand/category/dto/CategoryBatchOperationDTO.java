package com.example.functiondemand.category.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CategoryBatchOperationDTO {
    
    @NotEmpty(message = "分类ID列表不能为空")
    private List<String> categoryIds;
    
    @NotNull(message = "操作类型不能为空")
    private BatchOperationType operationType;
    
    private CategoryUpdateDTO updateData;
    
    private String operatorId;
    
    private String reason;
    
    public enum BatchOperationType {
        UPDATE_SORT_ORDER,
        UPDATE_DESCRIPTION,
        MOVE_TO_PARENT,
        DELETE,
        REORDER
    }
}