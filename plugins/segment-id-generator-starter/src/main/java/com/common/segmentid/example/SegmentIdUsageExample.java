package com.common.segmentid.example;

import com.common.segmentid.service.SegmentIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 号段ID生成器使用示例
 * 
 * 新方法优势：
 * 1. 高性能：使用Redis List缓存号段，减少数据库访问
 * 2. 批量支持：支持一次获取多个ID
 * 3. 自动管理：号段用完自动补充
 * 4. 分布式安全：Redis分布式锁保护
 * 5. 队列式消费：从List头部获取ID，用完自动删除
 */
@Slf4j
@Component
public class SegmentIdUsageExample {

    @Autowired
    private SegmentIdService segmentIdService;

    /**
     * 示例1：生成单个ID
     */
    public void generateSingleId() {
        String tenantId = "tenant001";
        String bizType = "order";
        
        // 使用新的高性能方法
        String id = segmentIdService.generateSegmentId(tenantId, bizType);
        log.info("Generated single ID: {}", id);
        
        // 查看剩余ID数量
        int remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
        log.info("Remaining IDs in cache: {}", remainingCount);
    }

    /**
     * 示例2：批量生成ID
     */
    public void generateBatchIds() {
        String tenantId = "tenant001";
        String bizType = "user";
        int count = 100;
        
        // 批量生成ID，性能更好
        List<String> ids = segmentIdService.generateSegmentBatchIds(tenantId, bizType, count);
        log.info("Generated {} IDs, first: {}, last: {}", count, ids.get(0), ids.get(ids.size() - 1));
        
        // 查看剩余ID数量
        int remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
        log.info("Remaining IDs in cache: {}", remainingCount);
    }

    /**
     * 示例3：预加载缓存
     */
    public void preloadCache() {
        String tenantId = "tenant001";
        String bizType = "product";
        
        // 预加载号段缓存，提高后续ID生成性能
        segmentIdService.preloadSegmentCache(tenantId, bizType);
        log.info("Preloaded cache for tenant: {}, bizType: {}", tenantId, bizType);
        
        // 查看预加载后的ID数量
        int remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
        log.info("Preloaded IDs count: {}", remainingCount);
    }

    /**
     * 示例4：性能对比测试
     */
    public void performanceTest() {
        String tenantId = "tenant001";
        String bizType = "test";
        int testCount = 1000;
        
        long startTime = System.currentTimeMillis();
        
        // 使用新方法批量生成
        List<String> ids = segmentIdService.generateSegmentBatchIds(tenantId, bizType, testCount);
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        log.info("Generated {} IDs in {} ms, avg: {} ms per ID", 
                testCount, duration, (double) duration / testCount);
        
        // 查看剩余ID数量
        int remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
        log.info("Remaining IDs in cache: {}", remainingCount);
    }

    /**
     * 示例5：多租户场景
     */
    public void multiTenantExample() {
        String[] tenantIds = {"tenant001", "tenant002", "tenant003"};
        String[] bizTypes = {"order", "user", "product"};
        
        for (String tenantId : tenantIds) {
            for (String bizType : bizTypes) {
                try {
                    // 为每个租户和业务类型预加载缓存
                    segmentIdService.preloadSegmentCache(tenantId, bizType);
                    log.info("Preloaded cache for tenant: {}, bizType: {}", tenantId, bizType);
                    
                    // 查看预加载后的ID数量
                    int remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
                    log.info("Preloaded IDs count for tenant: {}, bizType: {}: {}", tenantId, bizType, remainingCount);
                } catch (Exception e) {
                    log.warn("Failed to preload cache for tenant: {}, bizType: {}", tenantId, bizType, e);
                }
            }
        }
    }

    /**
     * 示例6：缓存管理
     */
    public void cacheManagement() {
        String tenantId = "tenant001";
        String bizType = "order";
        
        // 查看当前缓存状态
        int remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
        log.info("Current remaining IDs: {}", remainingCount);
        
        // 如果缓存不足，预加载
        if (remainingCount < 100) {
            log.info("Cache is running low, preloading...");
            segmentIdService.preloadSegmentCache(tenantId, bizType);
            remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
            log.info("After preload, remaining IDs: {}", remainingCount);
        }
        
        // 清空缓存（谨慎使用）
        // segmentIdService.clearSegmentCache(tenantId, bizType);
        // log.info("Cache cleared for tenant: {}, bizType: {}", tenantId, bizType);
    }

    /**
     * 示例7：监控号段消耗
     */
    public void monitorSegmentConsumption() {
        String tenantId = "tenant001";
        String bizType = "order";
        
        // 模拟业务场景，持续消耗ID
        for (int i = 0; i < 10; i++) {
            String id = segmentIdService.generateSegmentId(tenantId, bizType);
            int remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
            
            log.info("Generated ID: {}, Remaining: {}", id, remainingCount);
            
            // 如果剩余数量低于阈值，记录告警
            if (remainingCount < 50) {
                log.warn("Segment cache is running low! Remaining: {}", remainingCount);
            }
        }
    }
    
    /**
     * 示例8：测试扩展功能保持
     */
    public void testExtensionFeatures() {
        // 测试不同类型的ID生成，验证扩展功能是否保持
        
        // 1. 测试前缀功能
        String tenantId = "tenant001";
        String bizType = "order";
        
        // 假设数据库中配置了前缀"ORDER"
        String idWithPrefix = segmentIdService.generateSegmentId(tenantId, bizType);
        log.info("Generated ID with prefix: {}", idWithPrefix);
        
        // 2. 测试后缀功能
        bizType = "user";
        // 假设数据库中配置了后缀"_END"
        String idWithSuffix = segmentIdService.generateSegmentId(tenantId, bizType);
        log.info("Generated ID with suffix: {}", idWithSuffix);
        
        // 3. 测试自定义长度
        bizType = "product";
        // 假设数据库中配置了长度为6
        String idWithCustomLength = segmentIdService.generateSegmentId(tenantId, bizType);
        log.info("Generated ID with custom length: {}", idWithCustomLength);
        
        // 4. 测试批量生成保持扩展功能
        List<String> batchIds = segmentIdService.generateSegmentBatchIds(tenantId, "order", 5);
        log.info("Generated batch IDs with prefix: {}", batchIds);
        
        // 验证所有ID都保持了扩展功能
        for (String id : batchIds) {
            if (id.startsWith("ORDER")) {
                log.info("ID {} maintains prefix functionality", id);
            }
        }
    }
    
    /**
     * 示例9：验证扩展功能一致性
     */
    public void verifyExtensionConsistency() {
        String tenantId = "tenant001";
        String bizType = "order";
        
        // 使用旧方法生成ID
        String oldMethodId = segmentIdService.generateId(tenantId, bizType);
        log.info("Old method ID: {}", oldMethodId);
        
        // 使用新方法生成ID
        String newMethodId = segmentIdService.generateSegmentId(tenantId, bizType);
        log.info("New method ID: {}", newMethodId);
        
        // 验证两种方法生成的ID格式一致
        if (oldMethodId != null && newMethodId != null) {
            // 提取数字部分进行比较
            String oldNumber = oldMethodId.replaceAll("[^0-9]", "");
            String newNumber = newMethodId.replaceAll("[^0-9]", "");
            
            if (oldNumber.equals(newNumber)) {
                log.info("Extension features are consistent between old and new methods");
            } else {
                log.warn("Extension features may not be consistent");
            }
        }
        
        // 查看缓存状态
        int remainingCount = segmentIdService.getRemainingIdCount(tenantId, bizType);
        log.info("Remaining IDs in cache: {}", remainingCount);
    }
}
