package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 功能需求管理微服务启动类
 * 
 * @author Kiro
 */
@SpringBootApplication(
    scanBasePackages = {
        "com.example.functiondemand",    // 当前模块包
        "com.example.elasticsearch",     // Elasticsearch CRUD组件包
        "com.common.fileio",             // 文件导入导出组件包
        "com.tenant.routing",            // 租户路由组件包
        "com.common.segmentid"           // 号段ID生成组件包
    }
)
@EnableJpaRepositories(basePackages = {
    "com.example.functiondemand",
    "com.common.segmentid.repository"
})
@EntityScan(basePackages = {
    "com.example.functiondemand", 
    "com.common.segmentid.entity"
})
public class FunctionDemandApp {

    public static void main(String[] args) {
        SpringApplication.run(FunctionDemandApp.class, args);
    }
} 