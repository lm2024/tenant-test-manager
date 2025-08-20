package com.common.segmentid.config;

import com.common.segmentid.service.SegmentIdService;
import com.common.segmentid.service.impl.SegmentIdServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 号段ID生成器自动配置
 */
@Configuration
@ConditionalOnClass(SegmentIdService.class)
@EnableConfigurationProperties(SegmentIdProperties.class)
public class SegmentIdAutoConfiguration {

    /**
     * 配置号段ID服务
     */
    @Bean
    @ConditionalOnMissingBean
    public SegmentIdService segmentIdService() {
        return new SegmentIdServiceImpl();
    }
}
