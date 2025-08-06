package com.example.functiondemand.function.entity;

import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.FunctionStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "function_point", indexes = {
    @Index(name = "idx_parent_id", columnList = "parent_id"),
    @Index(name = "idx_category_id", columnList = "category_id"),
    @Index(name = "idx_module", columnList = "module"),
    @Index(name = "idx_status", columnList = "status"),
    @Index(name = "idx_path", columnList = "path")
})
@EqualsAndHashCode(callSuper = false)
public class FunctionPoint {

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "parent_id", length = 32)
    private String parentId;

    @Column(name = "category_id", length = 32)
    private String categoryId;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String module;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private FunctionComplexity complexity = FunctionComplexity.MEDIUM;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private FunctionStatus status = FunctionStatus.PLANNING;

    @Column(length = 100)
    private String owner;

    @Column(nullable = false)
    private Integer level = 1;

    @Column(length = 500)
    private String path;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "updated_time", nullable = false)
    private LocalDateTime updatedTime;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdTime == null) {
            createdTime = now;
        }
        if (updatedTime == null) {
            updatedTime = now;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = LocalDateTime.now();
    }
}