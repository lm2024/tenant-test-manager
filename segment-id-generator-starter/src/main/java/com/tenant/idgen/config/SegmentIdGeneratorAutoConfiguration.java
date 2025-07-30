package com.tenant.idgen.config;

import com.tenant.idgen.properties.SegmentIdGeneratorProperties;
import com.tenant.idgen.service.SegmentIdGeneratorService;
import com.tenant.idgen.service.impl.SegmentIdGeneratorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 号段ID生成器自动配置类
 * 
 * @author system
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(SegmentIdGeneratorProperties.class)
@ConditionalOnProperty(prefix = "tenant.id-generator", name = "enabled", havingValue = "true", matchIfMissing = true)
@Slf4j
public class SegmentIdGeneratorAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public SegmentIdGeneratorService segmentIdGeneratorService(SegmentIdGeneratorProperties properties) {
        log.info("[SegmentIdGeneratorAutoConfiguration] 初始化号段ID生成服务");
        return new SegmentIdGeneratorServiceImpl(properties);
    }
}