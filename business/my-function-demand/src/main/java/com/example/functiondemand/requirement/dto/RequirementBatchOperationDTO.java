package com.example.functiondemand.requirement.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RequirementBatchOperationDTO {
    
    @NotEmpty(message = "需求ID列表不能为空")
    private List<String> requirementIds;
    
    @NotNull(message = "操作类型不能为空")
    private BatchOperationType operationType;
    
    private RequirementUpdateDTO updateData;
    
    private String operatorId;
    
    private String reason;
    
    public enum BatchOperationType {
        UPDATE_STATUS,
        UPDATE_ASSIGNEE,
        UPDATE_PRIORITY,
        UPDATE_CATEGORY,
        DELETE,
        MOVE_TO_CATEGORY
    }
}