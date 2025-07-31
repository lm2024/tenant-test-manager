package com.tenant.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "test_case")
@Data
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ExcelProperty(value = "ID")
    private Long id;

    @Column(name = "title", nullable = false)
    @ExcelProperty(value = "标题")
    private String title;

    @Column(name = "description", nullable = true)
    @ExcelProperty(value = "描述")
    private String description;
} 