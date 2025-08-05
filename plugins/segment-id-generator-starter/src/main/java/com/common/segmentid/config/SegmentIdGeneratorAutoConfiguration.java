package com.common.segmentid.config;

import com.common.segmentid.service.SegmentIdService;
import com.common.segmentid.service.impl.SegmentIdServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 号段ID生成器自动配置类
 * 
 * 注意：JPA配置由主应用类负责，这里不重复配置
 */
@Configuration
@ComponentScan(basePackages = "com.common.segmentid")
@ConditionalOnProperty(name = "segment-id.enabled", havingValue = "true", matchIfMissing = true)
public class SegmentIdGeneratorAutoConfiguration {
    
    /**
     * 确保SegmentIdService Bean被正确创建
     */
    @Bean
    @ConditionalOnMissingBean(SegmentIdService.class)
    public SegmentIdService segmentIdService() {
        return new SegmentIdServiceImpl();
    }
} 