package com.example.elasticsearch.service;

import java.util.Map;

/**
 * 数据同步服务接口
 * 
 * @author Kiro
 */
public interface DataSyncService {
    
    /**
     * 获取同步状态
     * 
     * @return 同步状态信息
     */
    Map<String, Object> getSyncStatus();
    
    /**
     * 触发同步
     * 
     * @return 是否成功
     */
    boolean triggerSync();
    
    /**
     * 获取同步进度
     * 
     * @return 同步进度信息
     */
    Map<String, Object> getSyncProgress();
}