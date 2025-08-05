package com.example.functiondemand.requirement.dto;

import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.enums.RequirementStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequirementSummaryDTO {
    private String id;
    private String title;
    private RequirementPriority priority;
    private RequirementStatus status;
    private String assignee;
    private Integer level;
    private Integer childrenCount;
    private Integer functionCount;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}