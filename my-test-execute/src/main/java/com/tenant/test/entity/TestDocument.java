package com.tenant.test.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * 测试文档实体类
 * 
 * @author Kiro
 */
@Data
@Document(indexName = "test_document")
public class TestDocument {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Keyword)
    private String content;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String status;

    @Field(type = FieldType.Keyword)
    private String tenantId;

    @Field(type = FieldType.Date)
    private LocalDateTime createTime;

    @Field(type = FieldType.Date)
    private LocalDateTime updateTime;

    @Field(type = FieldType.Long)
    private Long version;

    @Field(type = FieldType.Integer)
    private Integer priority;

    @Field(type = FieldType.Boolean)
    private Boolean active;
} 