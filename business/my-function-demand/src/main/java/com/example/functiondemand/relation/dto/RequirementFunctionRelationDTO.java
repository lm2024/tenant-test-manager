package com.example.functiondemand.relation.dto;

import com.example.functiondemand.common.enums.RelationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequirementFunctionRelationDTO {
    private String id;
    private String requirementId;
    private String functionId;
    private RelationType relationType;
    private String description;
    private LocalDateTime createdTime;
    private String createdBy;
    
    // 关联的需求和功能点信息
    private String requirementTitle;
    private String functionName;
}