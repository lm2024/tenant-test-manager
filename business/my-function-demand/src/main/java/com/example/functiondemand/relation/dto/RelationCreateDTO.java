package com.example.functiondemand.relation.dto;

import com.example.functiondemand.common.enums.RelationType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RelationCreateDTO {
    
    @NotBlank(message = "需求ID不能为空")
    private String requirementId;
    
    @NotBlank(message = "功能点ID不能为空")
    private String functionId;
    
    private RelationType relationType = RelationType.IMPLEMENT;
    
    private String description;
    
    private String createdBy;
}