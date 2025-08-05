package com.example.functiondemand.function.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FunctionPointBatchOperationDTO {
    
    @NotEmpty(message = "功能点ID列表不能为空")
    private List<String> functionPointIds;
    
    @NotNull(message = "操作类型不能为空")
    private BatchOperationType operationType;
    
    private FunctionPointUpdateDTO updateData;
    
    private String operatorId;
    
    private String reason;
    
    public enum BatchOperationType {
        UPDATE_STATUS,
        UPDATE_OWNER,
        UPDATE_COMPLEXITY,
        UPDATE_MODULE,
        UPDATE_CATEGORY,
        DELETE,
        MOVE_TO_CATEGORY
    }
}