package com.common.segmentid.service;

import java.util.List;

public interface SegmentIdService {
    String generateId(String tenantId, String bizType);
    List<String> generateBatchIds(String tenantId, String bizType, int count);
    void preloadCache(String tenantId, String bizType);
    Object getSequenceInfo(String tenantId, String bizType);
} 