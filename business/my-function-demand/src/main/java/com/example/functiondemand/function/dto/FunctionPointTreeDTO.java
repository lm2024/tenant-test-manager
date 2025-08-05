package com.example.functiondemand.function.dto;

import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.FunctionStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FunctionPointTreeDTO {
    private String id;
    private String parentId;
    private String categoryId;
    private String name;
    private String description;
    private String module;
    private FunctionComplexity complexity;
    private FunctionStatus status;
    private String owner;
    private Integer level;
    private String path;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;
    private List<FunctionPointTreeDTO> children;
}