package com.example.functiondemand.requirement.dto;

import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.enums.RequirementStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequirementQueryDTO {
    private String keyword;
    private String categoryId;
    private RequirementPriority priority;
    private RequirementStatus status;
    private List<RequirementStatus> statuses;
    private String assignee;
    private String source;
    private Integer level;
    private String parentId;
    private LocalDateTime createdTimeStart;
    private LocalDateTime createdTimeEnd;
    private LocalDateTime updatedTimeStart;
    private LocalDateTime updatedTimeEnd;
}