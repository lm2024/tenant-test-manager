package com.example.functiondemand.relation.entity;

import com.example.functiondemand.common.enums.RelationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "requirement_function_relation", 
       uniqueConstraints = @UniqueConstraint(name = "uk_req_func", columnNames = {"requirementId", "functionId"}),
       indexes = {
           @Index(name = "idx_requirement_id", columnList = "requirementId"),
           @Index(name = "idx_function_id", columnList = "functionId")
       })
@EqualsAndHashCode(callSuper = false)
public class RequirementFunctionRelation {

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "requirement_id", nullable = false, length = 32)
    private String requirementId;

    @Column(name = "function_id", nullable = false, length = 32)
    private String functionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation_type", length = 20)
    private RelationType relationType = RelationType.IMPLEMENT;

    @Column(length = 500)
    private String description;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @PrePersist
    protected void onCreate() {
        if (createdTime == null) {
            createdTime = LocalDateTime.now();
        }
    }
}