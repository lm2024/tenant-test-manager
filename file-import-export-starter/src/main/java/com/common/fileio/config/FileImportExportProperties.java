package com.common.fileio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 文件导入导出配置属性
 * 用于从application.yml或application.properties中读取配置
 */
@Data
@ConfigurationProperties(prefix = "file.import.export")
public class FileImportExportProperties {
    
    /**
     * 是否启用文件导入导出功能
     */
    private boolean enabled = true;
    
    /**
     * 导入配置
     */
    private Import importConfig = new Import();
    
    /**
     * 导出配置
     */
    private Export exportConfig = new Export();
    
    /**
     * 路径配置
     */
    private Path path = new Path();
    
    /**
     * Redis配置
     */
    private Redis redis = new Redis();
    
    /**
     * 导入配置
     */
    @Data
    public static class Import {
        /**
         * 最大批处理大小
         */
        private int maxBatchSize = 20000;
        
        /**
         * 最大文件数
         */
        private int maxFiles = 20;
        
        /**
         * 并发数
         */
        private int concurrent = 1;
    }
    
    /**
     * 导出配置
     */
    @Data
    public static class Export {
        /**
         * 最大批处理大小
         */
        private int maxBatchSize = 50000;
        
        /**
         * 并发数
         */
        private int concurrent = 10;
    }
    
    /**
     * 路径配置
     */
    @Data
    public static class Path {
        /**
         * 上传目录
         * 默认根据操作系统自动选择
         */
        private String uploadDir = System.getProperty("os.name").toLowerCase().contains("win") 
            ? "C:\\Users\\lm\\Downloads\\uploads" 
            : "/tmp/uploads";
        
        /**
         * 导出目录
         * 默认根据操作系统自动选择
         */
        private String exportDir = System.getProperty("os.name").toLowerCase().contains("win") 
            ? "C:\\Users\\lm\\Downloads\\exports" 
            : "/tmp/exports";
    }
    
    /**
     * Redis配置
     */
    @Data
    public static class Redis {
        /**
         * Redis键前缀
         */
        private String keyPrefix = "file-io";
        
        /**
         * 任务TTL（秒）
         */
        private int taskTtl = 86400;
    }
}