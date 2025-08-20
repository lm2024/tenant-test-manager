package com.common.segmentid.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 号段ID生成工具类
 */
@Slf4j
public class SegmentIdUtils {

    /**
     * 格式化ID
     */
    public static String formatId(long value, int length, String prefix, String suffix, int type) {
        String num = String.format("%0" + length + "d", value);
        
        switch (type) {
            case 1: // 纯自增
                return num;
            case 2: // 前缀+自增
                return (prefix == null ? "" : prefix) + num;
            case 3: // 自增+后缀
                return num + (suffix == null ? "" : suffix);
            case 4: // 业务类型自定义长度
                return num;
            default:
                return num;
        }
    }

    /**
     * 批量格式化ID
     */
    public static List<String> formatBatchIds(long startValue, int count, int length, String prefix, String suffix, int type) {
        List<String> results = new ArrayList<>();
        
        for (int i = 0; i < count; i++) {
            long currentValue = startValue + i;
            results.add(formatId(currentValue, length, prefix, suffix, type));
        }
        
        return results;
    }

    /**
     * 计算号段使用率
     */
    public static double calculateUsageRate(long startValue, long endValue, long currentValue) {
        if (endValue <= startValue) {
            return 0.0;
        }
        
        long used = currentValue - startValue;
        long total = endValue - startValue + 1;
        
        return (double) used / total * 100;
    }

    /**
     * 检查号段是否即将耗尽
     */
    public static boolean isSegmentNearExhaustion(long startValue, long endValue, long currentValue, int threshold) {
        double usageRate = calculateUsageRate(startValue, endValue, currentValue);
        return usageRate >= threshold;
    }

    /**
     * 生成Redis键名
     */
    public static String generateRedisKey(String prefix, String tenantId, String bizType) {
        return String.format("%s:%s:%s", prefix, tenantId, bizType);
    }

    /**
     * 验证租户ID和业务类型
     */
    public static boolean validateParams(String tenantId, String bizType) {
        return tenantId != null && !tenantId.trim().isEmpty() 
            && bizType != null && !bizType.trim().isEmpty();
    }

    /**
     * 计算最优号段大小
     */
    public static int calculateOptimalStepSize(int qps, int maxStep) {
        // 根据QPS计算最优步长，确保号段能支撑至少1小时的业务量
        int optimalStep = qps * 3600; // 1小时 = 3600秒
        
        // 限制在最大步长范围内
        return Math.min(optimalStep, maxStep);
    }
}
