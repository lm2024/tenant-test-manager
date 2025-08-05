package com.example.functiondemand.requirement.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RequirementStatisticsDTO {
    private Long totalCount;
    private Map<String, Long> statusCount;
    private Map<String, Long> priorityCount;
    private Map<String, Long> assigneeCount;
    private Map<Integer, Long> levelCount;
    private Long rootCount;
    private Long leafCount;
    private Double avgLevel;
    private Integer maxLevel;
}