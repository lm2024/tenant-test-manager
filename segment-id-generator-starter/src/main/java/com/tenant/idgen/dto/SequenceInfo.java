package com.tenant.idgen.dto;

import com.tenant.idgen.enums.IdType;
import lombok.Data;

/**
 * 序列信息DTO
 * 
 * @author system
 * @since 1.0.0
 */
@Data
public class SequenceInfo {
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 业务类型
     */
    private String bizType;
    
    /**
     * 服务名
     */
    private String serviceName;
    
    /**
     * ID生成类型
     */
    private IdType idType;
    
    /**
     * 当前值
     */
    private Long currentValue = 0L;
    
    /**
     * 步进大小
     */
    private Integer stepSize = 1000;
    
    /**
     * ID长度
     */
    private Integer idLength = 8;
    
    /**
     * 前缀
     */
    private String prefix;
    
    /**
     * 后缀
     */
    private String suffix;
    
    /**
     * 最大值
     */
    private Long maxValue;
    
    /**
     * 是否启用
     */
    private Boolean enabled = true;
}