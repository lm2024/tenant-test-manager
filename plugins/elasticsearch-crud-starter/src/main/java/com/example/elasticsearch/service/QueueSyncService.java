package com.example.elasticsearch.service;

import java.util.Map;

/**
 * 队列同步服务接口
 * 
 * @author Kiro
 */
public interface QueueSyncService {
    
    /**
     * 发送同步消息
     * 
     * @param message 同步消息
     * @return 是否成功
     */
    boolean sendSyncMessage(Map<String, Object> message);
    
    /**
     * 获取队列状态
     * 
     * @return 队列状态信息
     */
    Map<String, Object> getQueueStatus();
}