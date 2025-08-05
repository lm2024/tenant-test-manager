package com.example.functiondemand.function.dto;

import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.FunctionStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FunctionPointSummaryDTO {
    private String id;
    private String name;
    private String module;
    private FunctionComplexity complexity;
    private FunctionStatus status;
    private String owner;
    private Integer level;
    private Integer childrenCount;
    private Integer requirementCount;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}