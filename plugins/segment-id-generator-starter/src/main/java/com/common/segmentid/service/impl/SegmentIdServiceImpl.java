package com.common.segmentid.service.impl;

import com.common.segmentid.entity.Sequence;
import com.common.segmentid.repository.SequenceRepository;
import com.common.segmentid.service.SegmentIdService;
import com.common.segmentid.service.SequenceManager;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SegmentIdServiceImpl implements SegmentIdService {
    @Autowired
    private SequenceManager sequenceManager;
    @Autowired
    @Qualifier("segmentIdRedissonClient")
    private RedissonClient redissonClient;

    private static final String REDIS_KEY_FORMAT = "segmentid:%s:%s"; // tenantId:bizType
    private static final String REDIS_LOCK_FORMAT = "segmentid:lock:%s:%s";
    
    // 新增号段缓存相关常量 - 使用Redis List
    private static final String SEGMENT_LIST_KEY_FORMAT = "segment:list:%s:%s"; // tenantId:bizType
    private static final String SEGMENT_LOCK_KEY_FORMAT = "segment:lock:%s:%s";

    @Override
    @Transactional
    public String generateId(String tenantId, String bizType) {
        List<String> ids = generateBatchIds(tenantId, bizType, 1);
        return ids.isEmpty() ? null : ids.get(0);
    }

    @Override
    @Transactional
    public List<String> generateBatchIds(String tenantId, String bizType, int count) {
        List<String> result = new ArrayList<>();
        String redisKey = String.format(REDIS_KEY_FORMAT, tenantId, bizType);
        String lockKey = String.format(REDIS_LOCK_FORMAT, tenantId, bizType);
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock(10, TimeUnit.SECONDS);
            RBucket<Long> bucket = redissonClient.getBucket(redisKey);
            Long current = bucket.get();
            Sequence sequence = sequenceManager.getSequence(tenantId, bizType)
                    .orElseThrow(() -> new RuntimeException("Sequence config not found for tenant=" + tenantId + ", bizType=" + bizType));
            int step = sequence.getStep();
            int length = sequence.getLength();
            long maxValue = sequence.getMaxValue();
            long nextValue;
            Long dbCurrentObj = sequence.getCurrentValue();
            long dbCurrent = dbCurrentObj == null ? 0L : dbCurrentObj;
            if (current == null) {
                // redis无缓存，分配新号段
                nextValue = dbCurrent + 1;
                long newCurrent = dbCurrent + step;
                if (newCurrent > maxValue) {
                    throw new RuntimeException("Sequence exhausted for tenant=" + tenantId + ", bizType=" + bizType);
                }
                // 更新数据库current_value
                sequence.setCurrentValue(newCurrent);
                sequenceManager.save(sequence);
                // 写入redis
                bucket.set(nextValue);
                current = nextValue;
            }
            for (int i = 0; i < count; i++) {
                Long valueObj = bucket.get();
                long value = valueObj == null ? 0L : valueObj;
                if (valueObj == null) {
                    // redis用完，递归调用重新分配
                    result.addAll(generateBatchIds(tenantId, bizType, count - i));
                    break;
                }
                Long seqCurrentObj = sequence.getCurrentValue();
                long seqCurrent = seqCurrentObj == null ? 0L : seqCurrentObj;
                if (value > seqCurrent) {
                    // redis缓存超出数据库步进，重新分配
                    result.addAll(generateBatchIds(tenantId, bizType, count - i));
                    break;
                }
                if (value > maxValue) {
                    throw new RuntimeException("Sequence exhausted for tenant=" + tenantId + ", bizType=" + bizType);
                }
                // 生成ID
                result.add(formatId(sequence, value, length));
                bucket.set(value + 1);
            }
        } finally {
            lock.unlock();
        }
        return result;
    }

    private String formatId(Sequence sequence, long value, int length) {
        String num = String.format("%0" + length + "d", value);
        switch (sequence.getType()) {
            case 1: // 纯自增
                return num;
            case 2: // 前缀+自增
                return (sequence.getPrefix() == null ? "" : sequence.getPrefix()) + num;
            case 3: // 自增+后缀
                return num + (sequence.getSuffix() == null ? "" : sequence.getSuffix());
            case 4: // 业务类型自定义长度
                return num;
            default:
                return num;
        }
    }

    @Override
    public void preloadCache(String tenantId, String bizType) {
        String redisKey = String.format(REDIS_KEY_FORMAT, tenantId, bizType);
        Sequence sequence = sequenceManager.getSequence(tenantId, bizType)
                .orElseThrow(() -> new RuntimeException("Sequence config not found for tenant=" + tenantId + ", bizType=" + bizType));
        int step = sequence.getStep();
        Long dbCurrentObj = sequence.getCurrentValue();
        long dbCurrent = dbCurrentObj == null ? 0L : dbCurrentObj;
        long nextValue = dbCurrent + 1;
        long newCurrent = dbCurrent + step;
        if (newCurrent > sequence.getMaxValue()) {
            throw new RuntimeException("Sequence exhausted for tenant=" + tenantId + ", bizType=" + bizType);
        }
        sequence.setCurrentValue(newCurrent);
        sequenceManager.save(sequence);
        redissonClient.getBucket(redisKey).set(nextValue);
    }

    @Override
    public Object getSequenceInfo(String tenantId, String bizType) {
        Optional<Sequence> seq = sequenceManager.getSequence(tenantId, bizType);
        return seq.orElse(null);
    }
    
    // ========== 重新实现高性能号段ID生成方法 ==========
    
    @Override
    public String generateSegmentId(String tenantId, String bizType) {
        List<String> ids = generateSegmentBatchIds(tenantId, bizType, 1);
        return ids.isEmpty() ? null : ids.get(0);
    }
    
    @Override
    public List<String> generateSegmentBatchIds(String tenantId, String bizType, int count) {
        List<String> result = new ArrayList<>();
        String lockKey = String.format(SEGMENT_LOCK_KEY_FORMAT, tenantId, bizType);
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            lock.lock(10, TimeUnit.SECONDS);
            
            // 获取Redis List缓存
            String listKey = String.format(SEGMENT_LIST_KEY_FORMAT, tenantId, bizType);
            RList<Long> idList = redissonClient.getList(listKey);
            
            // 如果缓存中没有足够的ID，则从数据库加载新号段
            if (idList.size() < count) {
                loadNewSegmentToRedisList(tenantId, bizType);
            }
            
            // 获取Sequence配置信息（用于格式化ID）
            Sequence sequence = sequenceManager.getSequence(tenantId, bizType)
                    .orElseThrow(() -> new RuntimeException("Sequence config not found for tenant=" + tenantId + ", bizType=" + bizType));
            
            // 从List头部获取指定数量的ID
            for (int i = 0; i < count; i++) {
                Long idValue = idList.remove(0); // 从头部移除并获取
                if (idValue == null) {
                    // 如果List为空，重新加载号段
                    loadNewSegmentToRedisList(tenantId, bizType);
                    idValue = idList.remove(0);
                    if (idValue == null) {
                        throw new RuntimeException("Failed to generate ID for tenant=" + tenantId + ", bizType=" + bizType);
                    }
                }
                
                // 使用完整的Sequence信息格式化ID，保持原有的前缀后缀等扩展功能
                result.add(formatId(sequence, idValue, sequence.getLength()));
            }
            
        } finally {
            lock.unlock();
        }
        
        return result;
    }

    /**
     * 从数据库加载新号段到Redis List，缓存完整的Sequence信息
     */
    private void loadNewSegmentToRedisList(String tenantId, String bizType) {
        Sequence sequence = sequenceManager.getSequence(tenantId, bizType)
                .orElseThrow(() -> new RuntimeException("Sequence config not found for tenant=" + tenantId + ", bizType=" + bizType));
        
        int step = sequence.getStep();
        Long dbCurrentObj = sequence.getCurrentValue();
        long dbCurrent = dbCurrentObj == null ? 0L : dbCurrentObj;
        
        // 计算新号段范围
        long startValue = dbCurrent + 1;
        long endValue = dbCurrent + step;
        
        // 检查是否超出最大值
        if (endValue > sequence.getMaxValue()) {
            throw new RuntimeException("Sequence exhausted for tenant=" + tenantId + ", bizType=" + bizType);
        }
        
        // 更新数据库
        sequence.setCurrentValue(endValue);
        sequenceManager.save(sequence);
        
        // 将号段ID添加到Redis List，缓存完整的Sequence信息
        String listKey = String.format(SEGMENT_LIST_KEY_FORMAT, tenantId, bizType);
        RList<Long> idList = redissonClient.getList(listKey);
        
        // 清空现有List（避免重复）
        idList.clear();
        
        // 按顺序添加号段ID到List
        for (long i = startValue; i <= endValue; i++) {
            idList.add(i);
        }
        
        // 设置过期时间（30天）
        idList.expire(24 * 30, TimeUnit.HOURS);
        
        log.info("Loaded new segment to Redis List for tenant={}, bizType={}, range=[{}, {}], size={}", 
                tenantId, bizType, startValue, endValue, idList.size());
    }
    
    @Override
    public void preloadSegmentCache(String tenantId, String bizType) {
        String lockKey = String.format(SEGMENT_LOCK_KEY_FORMAT, tenantId, bizType);
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            lock.lock(10, TimeUnit.SECONDS);
            loadNewSegmentToRedisList(tenantId, bizType);
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * 获取Redis List中剩余的ID数量
     */
    @Override
    public int getRemainingIdCount(String tenantId, String bizType) {
        String listKey = String.format(SEGMENT_LIST_KEY_FORMAT, tenantId, bizType);
        RList<Long> idList = redissonClient.getList(listKey);
        return idList.size();
    }
    
    /**
     * 清空号段缓存
     */
    @Override
    public void clearSegmentCache(String tenantId, String bizType) {
        String listKey = String.format(SEGMENT_LIST_KEY_FORMAT, tenantId, bizType);
        RList<Long> idList = redissonClient.getList(listKey);
        idList.clear();
        
        log.info("Cleared segment cache for tenant={}, bizType={}", tenantId, bizType);
    }

} 