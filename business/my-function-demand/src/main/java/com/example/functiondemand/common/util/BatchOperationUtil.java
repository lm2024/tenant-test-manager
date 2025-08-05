package com.example.functiondemand.common.util;

import cn.hutool.core.util.StrUtil;
import com.tenant.routing.core.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class BatchOperationUtil {
    
    private static final int DEFAULT_BATCH_SIZE = 100;
    
    public static <T> void processBatch(List<T> items, Consumer<List<T>> processor) {
        processBatch(items, DEFAULT_BATCH_SIZE, processor);
    }
    
    public static <T> void processBatch(List<T> items, int batchSize, Consumer<List<T>> processor) {
        String tenantId = TenantContextHolder.getTenantId();
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("批量操作需要租户上下文");
        }
        
        log.debug("开始批量处理，总数: {}, 批次大小: {}, 租户: {}", items.size(), batchSize, tenantId);
        
        for (int i = 0; i < items.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, items.size());
            List<T> batch = items.subList(i, endIndex);
            
            // 确保租户上下文在每个批次中都存在
            if (!TenantContextHolder.hasTenant()) {
                throw new IllegalStateException("租户上下文在批量处理中丢失");
            }
            
            try {
                processor.accept(batch);
                log.debug("批次处理完成: {}-{}/{}, 租户: {}", i + 1, endIndex, items.size(), tenantId);
            } catch (Exception e) {
                log.error("批次处理失败: {}-{}/{}, 租户: {}", i + 1, endIndex, items.size(), tenantId, e);
                throw e;
            }
        }
        
        log.info("批量处理完成，总数: {}, 租户: {}", items.size(), tenantId);
    }
    
    public static void validateTenantBatchOperation(int itemCount) {
        String tenantId = TenantContextHolder.getTenantId();
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("批量操作需要租户上下文");
        }
        
        if (itemCount <= 0) {
            throw new IllegalArgumentException("批量操作项目数量必须大于0");
        }
        
        if (itemCount > 1000) {
            log.warn("大批量操作警告: 项目数量 {}, 租户: {}", itemCount, tenantId);
        }
        
        log.debug("批量操作验证通过: 项目数量 {}, 租户: {}", itemCount, tenantId);
    }
}