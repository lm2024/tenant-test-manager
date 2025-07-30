package com.tenant.fileio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 文件导入导出配置属性
 * 
 * @author system
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "tenant.file-io")
@Data
public class FileImportExportProperties {
    
    /**
     * 是否启用文件导入导出功能
     */
    private boolean enabled = true;
    
    /**
     * 文件存储路径
     */
    private String filePath = "/tmp/file-io";
    
    /**
     * Excel单次导入限制条数
     */
    private int excelImportLimit = 20000;
    
    /**
     * CSV/TXT单次导入限制条数
     */
    private int csvImportLimit = 50000;
    
    /**
     * 导入并发数
     */
    private int importConcurrency = 1;
    
    /**
     * 导出并发数
     */
    private int exportConcurrency = 10;
    
    /**
     * 最大上传文件数
     */
    private int maxUploadFiles = 20;
    
    /**
     * 队列类型：redis 或 activemq
     */
    private String queueType = "redis";
    
    /**
     * 批处理大小
     */
    private int batchSize = 1000;
    
    /**
     * 线程池核心线程数
     */
    private int corePoolSize = 5;
    
    /**
     * 线程池最大线程数
     */
    private int maxPoolSize = 20;
    
    /**
     * 线程池队列容量
     */
    private int queueCapacity = 100;
}