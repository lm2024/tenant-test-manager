package com.tenant.test.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_data")
@Data
public class TestData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "data_name")
    private String dataName;
    
    @Column(name = "data_value")
    private String dataValue;
    
    @Column(name = "data_type")
    private String dataType;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}