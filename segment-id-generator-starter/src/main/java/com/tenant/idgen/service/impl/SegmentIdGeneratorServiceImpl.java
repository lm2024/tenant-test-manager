package com.tenant.idgen.service.impl;

import com.tenant.idgen.dto.SequenceInfo;
import com.tenant.idgen.enums.IdType;
import com.tenant.idgen.properties.SegmentIdGeneratorProperties;
import com.tenant.idgen.repository.SequenceRepository;
import com.tenant.idgen.service.SegmentIdGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 号段ID生成服务实现类
 * 
 * @author system
 * @since 1.0.0
 */
@Slf4j
public class SegmentIdGeneratorServiceImpl implements SegmentIdGeneratorService {
    
    private final SegmentIdGeneratorProperties properties;
    
    @Autowired(required = false)
    private SequenceRepository sequenceRepository;
    
    public SegmentIdGeneratorServiceImpl(SegmentIdGeneratorProperties properties) {
        this.properties = properties;
        log.info("[SegmentIdGeneratorServiceImpl] 号段ID生成服务初始化完成，配置: {}", properties);
    }
    
    @Override
    public String generateId(String tenantId, String bizType, IdType idType) {
        log.debug("[SegmentIdGeneratorServiceImpl] 生成ID，租户: {}, 业务类型: {}, ID类型: {}", 
                tenantId, bizType, idType);
        
        // 如果没有数据访问层实现，使用模拟数据
        if (sequenceRepository == null) {
            log.warn("[SegmentIdGeneratorServiceImpl] 未找到SequenceRepository实现，使用模拟ID生成");
            return generateMockId(idType);
        }
        
        // TODO: 实现真实的ID生成逻辑
        // 1. 从Redis缓存获取当前号段
        // 2. 如果缓存不存在或即将用完，从数据库获取新号段
        // 3. 根据ID类型格式化返回
        
        try {
            SequenceInfo sequenceInfo = sequenceRepository.getSequence(tenantId, bizType);
            if (sequenceInfo == null) {
                log.warn("[SegmentIdGeneratorServiceImpl] 序列不存在，使用模拟ID: {}-{}", tenantId, bizType);
                return generateMockId(idType);
            }
            
            // 简单实现：直接使用当前值+1
            long nextValue = sequenceRepository.updateCurrentValue(tenantId, bizType, sequenceInfo.getCurrentValue() + 1);
            return formatId(nextValue, sequenceInfo);
            
        } catch (Exception e) {
            log.error("[SegmentIdGeneratorServiceImpl] ID生成失败，使用模拟ID", e);
            return generateMockId(idType);
        }
    }
    
    /**
     * 生成模拟ID（当没有数据访问层时使用）
     */
    private String generateMockId(IdType idType) {
        switch (idType) {
            case NUMERIC:
                return String.format("%08d", System.currentTimeMillis() % 100000000);
            case PREFIX_NUMERIC:
                return "TEST" + String.format("%08d", System.currentTimeMillis() % 100000000);
            case NUMERIC_SUFFIX:
                return String.format("%08d", System.currentTimeMillis() % 100000000) + "END";
            case CUSTOM_LENGTH:
                return String.format("%012d", System.currentTimeMillis() % 1000000000000L);
            default:
                return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        }
    }
    
    /**
     * 格式化ID
     */
    private String formatId(long value, SequenceInfo sequenceInfo) {
        String numStr = String.format("%0" + sequenceInfo.getIdLength() + "d", value);
        
        StringBuilder result = new StringBuilder();
        if (sequenceInfo.getPrefix() != null) {
            result.append(sequenceInfo.getPrefix());
        }
        result.append(numStr);
        if (sequenceInfo.getSuffix() != null) {
            result.append(sequenceInfo.getSuffix());
        }
        
        return result.toString();
    }
    
    @Override
    public List<String> batchGenerateId(String tenantId, String bizType, IdType idType, int count) {
        log.info("[SegmentIdGeneratorServiceImpl] 批量生成ID，租户: {}, 业务类型: {}, 数量: {}", 
                tenantId, bizType, count);
        
        List<String> ids = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            ids.add(generateId(tenantId, bizType, idType));
        }
        
        return ids;
    }
    
    @Override
    public boolean initSequence(String tenantId, String bizType, IdType idType, 
                               int step, int length, String prefix, String suffix) {
        log.info("[SegmentIdGeneratorServiceImpl] 初始化序列，租户: {}, 业务类型: {}, 步进: {}", 
                tenantId, bizType, step);
        
        if (sequenceRepository == null) {
            log.warn("[SegmentIdGeneratorServiceImpl] 未找到SequenceRepository实现，无法初始化序列");
            return false;
        }
        
        try {
            SequenceInfo sequenceInfo = new SequenceInfo();
            sequenceInfo.setTenantId(tenantId);
            sequenceInfo.setBizType(bizType);
            sequenceInfo.setIdType(idType);
            sequenceInfo.setStepSize(step);
            sequenceInfo.setIdLength(length);
            sequenceInfo.setPrefix(prefix);
            sequenceInfo.setSuffix(suffix);
            sequenceInfo.setCurrentValue(0L);
            sequenceInfo.setEnabled(true);
            
            return sequenceRepository.createSequence(sequenceInfo);
        } catch (Exception e) {
            log.error("[SegmentIdGeneratorServiceImpl] 序列初始化失败", e);
            return false;
        }
    }
    
    @Override
    public boolean resetSequence(String tenantId, String bizType) {
        log.info("[SegmentIdGeneratorServiceImpl] 重置序列，租户: {}, 业务类型: {}", tenantId, bizType);
        
        if (sequenceRepository == null) {
            log.warn("[SegmentIdGeneratorServiceImpl] 未找到SequenceRepository实现，无法重置序列");
            return false;
        }
        
        try {
            return sequenceRepository.resetSequence(tenantId, bizType);
        } catch (Exception e) {
            log.error("[SegmentIdGeneratorServiceImpl] 序列重置失败", e);
            return false;
        }
    }
}