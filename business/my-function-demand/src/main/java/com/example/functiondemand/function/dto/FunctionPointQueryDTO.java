package com.example.functiondemand.function.dto;

import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.FunctionStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FunctionPointQueryDTO {
    private String keyword;
    private String categoryId;
    private String module;
    private List<String> modules;
    private FunctionComplexity complexity;
    private FunctionStatus status;
    private List<FunctionStatus> statuses;
    private String owner;
    private Integer level;
    private String parentId;
    private LocalDateTime createdTimeStart;
    private LocalDateTime createdTimeEnd;
    private LocalDateTime updatedTimeStart;
    private LocalDateTime updatedTimeEnd;
}