package com.tenant.cvs.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CvsConfig {
    @Value("${com.tenant.cvs.import.max-batch-size:20000}")
    private int importMaxBatchSize;
    @Value("${com.tenant.cvs.export.max-batch-size:50000}")
    private int exportMaxBatchSize;
    @Value("${com.tenant.cvs.import.max-files:20}")
    private int importMaxFiles;
    @Value("${com.tenant.cvs.export.concurrent:10}")
    private int exportConcurrent;
    @Value("${com.tenant.cvs.import.concurrent:1}")
    private int importConcurrent;
    @Value("${com.tenant.cvs.file.upload-dir:C:\\Users\\lm\\Downloads\\上传}")
    private String uploadDir;
    @Value("${com.tenant.cvs.file.export-dir:C:\\Users\\lm\\Downloads\\下载}")
    private String exportDir;
    // getter
    public int getImportMaxBatchSize() { return importMaxBatchSize; }
    public int getExportMaxBatchSize() { return exportMaxBatchSize; }
    public int getImportMaxFiles() { return importMaxFiles; }
    public int getExportConcurrent() { return exportConcurrent; }
    public int getImportConcurrent() { return importConcurrent; }
    public String getUploadDir() { return uploadDir; }
    public String getExportDir() { return exportDir; }

    // @Bean
    // public ObjectMapper objectMapper() {
    //     ObjectMapper mapper = new ObjectMapper();
    //     mapper.registerModule(new JavaTimeModule());
    //     // 可选：设置时间格式
    //     // mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    //     return mapper;
    // }
} 