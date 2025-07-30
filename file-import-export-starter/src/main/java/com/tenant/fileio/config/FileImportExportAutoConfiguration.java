package com.tenant.fileio.config;

import com.tenant.fileio.properties.FileImportExportProperties;
import com.tenant.fileio.service.FileImportExportService;
import com.tenant.fileio.service.impl.FileImportExportServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件导入导出自动配置类
 * 
 * @author system
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(FileImportExportProperties.class)
@ConditionalOnProperty(prefix = "tenant.file-io", name = "enabled", havingValue = "true", matchIfMissing = true)
@Slf4j
public class FileImportExportAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public FileImportExportService fileImportExportService(FileImportExportProperties properties) {
        log.info("[FileImportExportAutoConfiguration] 初始化文件导入导出服务");
        return new FileImportExportServiceImpl(properties);
    }
}