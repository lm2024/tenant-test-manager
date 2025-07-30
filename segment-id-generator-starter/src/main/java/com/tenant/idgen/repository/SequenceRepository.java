package com.tenant.idgen.repository;

import com.tenant.idgen.dto.SequenceInfo;

/**
 * 序列数据访问接口
 * 由上层应用实现具体的数据访问逻辑
 * 
 * @author system
 * @since 1.0.0
 */
public interface SequenceRepository {
    
    /**
     * 获取序列信息
     * 
     * @param tenantId 租户ID
     * @param bizType 业务类型
     * @return 序列信息
     */
    SequenceInfo getSequence(String tenantId, String bizType);
    
    /**
     * 更新序列当前值
     * 
     * @param tenantId 租户ID
     * @param bizType 业务类型
     * @param currentValue 当前值
     * @return 更新后的当前值
     */
    long updateCurrentValue(String tenantId, String bizType, long currentValue);
    
    /**
     * 创建序列
     * 
     * @param sequenceInfo 序列信息
     * @return 是否成功
     */
    boolean createSequence(SequenceInfo sequenceInfo);
    
    /**
     * 重置序列
     * 
     * @param tenantId 租户ID
     * @param bizType 业务类型
     * @return 是否成功
     */
    boolean resetSequence(String tenantId, String bizType);
}