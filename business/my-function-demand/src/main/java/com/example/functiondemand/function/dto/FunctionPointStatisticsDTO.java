package com.example.functiondemand.function.dto;

import lombok.Data;

import java.util.Map;

@Data
public class FunctionPointStatisticsDTO {
    private Long totalCount;
    private Map<String, Long> statusCount;
    private Map<String, Long> complexityCount;
    private Map<String, Long> moduleCount;
    private Map<String, Long> ownerCount;
    private Map<Integer, Long> levelCount;
    private Long rootCount;
    private Long leafCount;
    private Double avgLevel;
    private Integer maxLevel;
}