package com.example.functiondemand;

import com.example.functiondemand.common.util.BatchPerformanceOptimizer;
import com.example.functiondemand.common.validator.BusinessRuleChecker;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestConfig {
    
    @Bean
    @Primary
    public BatchPerformanceOptimizer mockBatchPerformanceOptimizer() {
        return mock(BatchPerformanceOptimizer.class);
    }
    
    @Bean
    @Primary
    public BusinessRuleChecker mockBusinessRuleChecker() {
        return mock(BusinessRuleChecker.class);
    }
}