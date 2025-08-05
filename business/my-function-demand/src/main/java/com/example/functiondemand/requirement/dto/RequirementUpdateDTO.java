package com.example.functiondemand.requirement.dto;

import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.enums.RequirementStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequirementUpdateDTO {
    @NotBlank(message = "需求ID不能为空")
    private String id;
    
    private String parentId;
    private String categoryId;
    
    @NotBlank(message = "需求标题不能为空")
    private String title;
    
    private String description;
    private RequirementPriority priority;
    private RequirementStatus status;
    private String source;
    private String assignee;
    private String updatedBy;
}