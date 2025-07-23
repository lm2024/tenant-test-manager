package com.common.fileio.config;

import com.common.fileio.controller.FileImportExportController;
import com.common.fileio.processor.ProcessorContext;
import com.common.fileio.queue.TaskQueueManager;
import com.common.fileio.service.ExportService;
import com.common.fileio.service.ImportService;
import com.common.fileio.service.TaskManagementService;
import com.common.fileio.util.FileUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.File;

/**
 * 文件导入导出自动配置类
 * 用于自动配置文件导入导出模块的所有组件
 */
@Configuration
@EnableConfigurationProperties(FileImportExportProperties.class)
@ConditionalOnProperty(prefix = "file.import.export", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import(ThreadPoolConfiguration.class)
public class FileImportExportAutoConfiguration {
    
    /**
     * 初始化文件目录
     * 
     * @param properties 配置属性
     * @return 是否初始化成功
     */
    @Bean
    public boolean initDirectories(FileImportExportProperties properties) {
        // 创建上传目录
        File uploadDir = new File(properties.getPath().getUploadDir());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        // 创建导出目录
        File exportDir = new File(properties.getPath().getExportDir());
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        
        return true;
    }
    
    /**
     * Redis模板配置
     * 
     * @param connectionFactory Redis连接工厂
     * @return RedisTemplate
     */
    @Bean
    @ConditionalOnMissingBean(name = "fileIoRedisTemplate")
    public RedisTemplate<String, Object> fileIoRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
    
    /**
     * 任务队列管理器
     * 
     * @param redisTemplate Redis模板
     * @param properties 配置属性
     * @return TaskQueueManager
     */
    @Bean
    @ConditionalOnMissingBean
    public TaskQueueManager taskQueueManager(RedisTemplate<String, Object> fileIoRedisTemplate, 
                                            FileImportExportProperties properties) {
        return new TaskQueueManager(fileIoRedisTemplate, properties);
    }
    
    /**
     * 处理器上下文
     * 
     * @return ProcessorContext
     */
    @Bean
    @ConditionalOnMissingBean
    public ProcessorContext processorContext() {
        return new ProcessorContext();
    }
    
    /**
     * 文件工具类
     * 
     * @param properties 配置属性
     * @return FileUtils
     */
    @Bean
    @ConditionalOnMissingBean
    public FileUtils fileUtils(FileImportExportProperties properties) {
        return new FileUtils(properties);
    }
    
    /**
     * 导入服务
     * 
     * @param taskQueueManager 任务队列管理器
     * @param processorContext 处理器上下文
     * @param fileUtils 文件工具类
     * @param properties 配置属性
     * @return ImportService
     */
    @Bean
    @ConditionalOnMissingBean
    public ImportService importService(TaskQueueManager taskQueueManager,
                                      ProcessorContext processorContext,
                                      FileUtils fileUtils,
                                      FileImportExportProperties properties) {
        return new ImportService(taskQueueManager, processorContext, fileUtils, properties);
    }
    
    /**
     * 导出服务
     * 
     * @param taskQueueManager 任务队列管理器
     * @param processorContext 处理器上下文
     * @param fileUtils 文件工具类
     * @param properties 配置属性
     * @return ExportService
     */
    @Bean
    @ConditionalOnMissingBean
    public ExportService exportService(TaskQueueManager taskQueueManager,
                                      ProcessorContext processorContext,
                                      FileUtils fileUtils,
                                      FileImportExportProperties properties) {
        return new ExportService(taskQueueManager, processorContext, fileUtils, properties);
    }
    
    /**
     * 任务管理服务
     * 
     * @param taskQueueManager 任务队列管理器
     * @param fileUtils 文件工具类
     * @return TaskManagementService
     */
    @Bean
    @ConditionalOnMissingBean
    public TaskManagementService taskManagementService(TaskQueueManager taskQueueManager,
                                                     FileUtils fileUtils) {
        return new TaskManagementService(taskQueueManager, fileUtils);
    }
    
    /**
     * 文件导入导出控制器
     * 
     * @param importService 导入服务
     * @param exportService 导出服务
     * @param taskManagementService 任务管理服务
     * @param fileUtils 文件工具类
     * @return FileImportExportController
     */
    @Bean
    @ConditionalOnMissingBean
    public FileImportExportController fileImportExportController(ImportService importService,
                                                               ExportService exportService,
                                                               TaskManagementService taskManagementService,
                                                               FileUtils fileUtils) {
        return new FileImportExportController(importService, exportService, taskManagementService, fileUtils);
    }
}