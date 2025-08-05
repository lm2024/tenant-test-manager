package com.example.functiondemand.requirement.dto;

import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.enums.RequirementStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequirementCreateDTO {
    private String parentId;
    private String categoryId;
    
    @NotBlank(message = "需求标题不能为空")
    private String title;
    
    private String description;
    private RequirementPriority priority = RequirementPriority.MEDIUM;
    private RequirementStatus status = RequirementStatus.DRAFT;
    private String source;
    private String assignee;
    private String createdBy;
}