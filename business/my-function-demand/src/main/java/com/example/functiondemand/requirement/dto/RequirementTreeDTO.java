package com.example.functiondemand.requirement.dto;

import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.enums.RequirementStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequirementTreeDTO {
    private String id;
    private String parentId;
    private String categoryId;
    private String title;
    private String description;
    private RequirementPriority priority;
    private RequirementStatus status;
    private String source;
    private String assignee;
    private Integer level;
    private String path;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;
    private List<RequirementTreeDTO> children;
}