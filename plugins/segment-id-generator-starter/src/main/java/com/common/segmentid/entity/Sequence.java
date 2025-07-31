package com.common.segmentid.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sequence", uniqueConstraints = {@UniqueConstraint(columnNames = {"tenant_id", "biz_type"})})
public class Sequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "biz_type", nullable = false, length = 64)
    private String bizType;

    @Column(name = "service_name", nullable = false, length = 64)
    private String serviceName;

    @Column(name = "prefix", length = 32)
    private String prefix;

    @Column(name = "suffix", length = 32)
    private String suffix;

    @Column(name = "step", nullable = false)
    private Integer step;

    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "current_value", nullable = false)
    private Long currentValue;

    @Column(name = "max_value", nullable = false)
    private Long maxValue;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
} 