package com.tenant.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 测试文档实体类
 * 
 * @author Kiro
 */
@Data
public class TestDocument {

    private String id;

    private String title;

    private String content;

    private String category;

    private String status;

    private String tenantId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long version;

    private Integer priority;

    private Boolean active;
} 