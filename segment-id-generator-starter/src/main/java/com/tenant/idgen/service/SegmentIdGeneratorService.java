package com.tenant.idgen.service;

import com.tenant.idgen.enums.IdType;

import java.util.List;

/**
 * 号段ID生成服务接口
 * 
 * @author system
 * @since 1.0.0
 */
public interface SegmentIdGeneratorService {
    
    /**
     * 生成单个ID
     * 
     * @param tenantId 租户ID
     * @param bizType 业务类型
     * @param idType ID类型
     * @return 生成的ID
     */
    String generateId(String tenantId, String bizType, IdType idType);
    
    /**
     * 批量生成ID
     * 
     * @param tenantId 租户ID
     * @param bizType 业务类型
     * @param idType ID类型
     * @param count 生成数量
     * @return ID列表
     */
    List<String> batchGenerateId(String tenantId, String bizType, IdType idType, int count);
    
    /**
     * 初始化序列
     * 
     * @param tenantId 租户ID
     * @param bizType 业务类型
     * @param idType ID类型
     * @param step 步进大小
     * @param length ID长度
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 是否成功
     */
    boolean initSequence(String tenantId, String bizType, IdType idType, 
                        int step, int length, String prefix, String suffix);
    
    /**
     * 重置序列
     * 
     * @param tenantId 租户ID
     * @param bizType 业务类型
     * @return 是否成功
     */
    boolean resetSequence(String tenantId, String bizType);
}