package com.common.segmentid.aspect;

import com.common.segmentid.config.SegmentIdProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 号段ID生成性能监控切面
 */
@Slf4j
@Aspect
@Component
public class PerformanceMonitorAspect {

    @Autowired
    private SegmentIdProperties properties;

    /**
     * 监控号段ID生成方法的性能
     */
    @Around("execution(* com.common.segmentid.service.impl.SegmentIdServiceImpl.generateSegment*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!properties.isPerformanceMonitoringEnabled()) {
            return joinPoint.proceed();
        }

        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();
        
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            
            // 记录性能日志
            if (duration > 100) { // 超过100ms记录警告
                log.warn("Slow segment ID generation: {} took {} ms", methodName, duration);
            } else {
                log.debug("Segment ID generation: {} took {} ms", methodName, duration);
            }
            
            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("Segment ID generation failed: {} took {} ms, error: {}", methodName, duration, e.getMessage());
            throw e;
        }
    }
}
