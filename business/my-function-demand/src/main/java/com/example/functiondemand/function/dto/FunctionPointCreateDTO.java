package com.example.functiondemand.function.dto;

import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.FunctionStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FunctionPointCreateDTO {
    private String parentId;
    private String categoryId;
    
    @NotBlank(message = "功能点名称不能为空")
    private String name;
    
    private String description;
    private String module;
    private FunctionComplexity complexity = FunctionComplexity.MEDIUM;
    private FunctionStatus status = FunctionStatus.PLANNING;
    private String owner;
    private String createdBy;
}