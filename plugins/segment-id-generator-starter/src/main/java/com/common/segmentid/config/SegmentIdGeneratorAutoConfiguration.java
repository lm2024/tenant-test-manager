package com.common.segmentid.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.common.segmentid.repository")
@EntityScan(basePackages = "com.common.segmentid.entity")
public class SegmentIdGeneratorAutoConfiguration {
    // 保持为空，自动装配由@Service注解的实现类完成
} 