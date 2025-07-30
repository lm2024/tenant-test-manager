package com.tenant.test.entity;

import com.tenant.idgen.enums.IdType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sequence")
@Data
public class Sequence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;
    
    @Column(name = "biz_type", nullable = false, length = 64)
    private String bizType;
    
    @Column(name = "service_name", length = 64)
    private String serviceName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "id_type", nullable = false)
    private IdType idType;
    
    @Column(name = "current_value", nullable = false)
    private Long currentValue = 0L;
    
    @Column(name = "step_size", nullable = false)
    private Integer stepSize = 1000;
    
    @Column(name = "id_length", nullable = false)
    private Integer idLength = 8;
    
    @Column(name = "prefix", length = 32)
    private String prefix;
    
    @Column(name = "suffix", length = 32)
    private String suffix;
    
    @Column(name = "max_value")
    private Long maxValue;
    
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;
    
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
    
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
    
    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }
}