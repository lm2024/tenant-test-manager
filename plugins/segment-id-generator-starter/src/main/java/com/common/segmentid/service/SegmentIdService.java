package com.common.segmentid.service;

import java.util.List;

public interface SegmentIdService {
    String generateId(String tenantId, String bizType);
    List<String> generateBatchIds(String tenantId, String bizType, int count);
    void preloadCache(String tenantId, String bizType);
    Object getSequenceInfo(String tenantId, String bizType);
    
    // 新增高性能号段ID生成方法
    String generateSegmentId(String tenantId, String bizType);
    List<String> generateSegmentBatchIds(String tenantId, String bizType, int count);
    void preloadSegmentCache(String tenantId, String bizType);
    
    // 新增管理方法
    int getRemainingIdCount(String tenantId, String bizType);
    void clearSegmentCache(String tenantId, String bizType);
} 