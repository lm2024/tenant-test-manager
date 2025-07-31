package com.tenant.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(
    scanBasePackages = {
        "com.tenant.test",           // 您原有的业务包
        "com.example.elasticsearch", // Elasticsearch CRUD组件包
        "com.common.fileio",         // 文件导入导出组件包
        "com.common.segmentid"       // 号段ID生成组件包
    }
)
@EnableJpaRepositories(basePackages = {
    "com.tenant.test.repository",
    "com.common.segmentid.repository"
})
@EntityScan(basePackages = {
    "com.tenant.test.entity", 
    "com.common.segmentid.entity"
})
/**
 * 测试执行微服务启动类
 */
public class TestExecuteApp {

    public static void main(String[] args) {
        SpringApplication.run(TestExecuteApp.class, args);
    }
}