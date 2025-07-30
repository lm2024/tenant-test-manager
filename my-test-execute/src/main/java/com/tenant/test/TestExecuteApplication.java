package com.tenant.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tenant.test.repository")
@EntityScan(basePackages = "com.tenant.test.entity")
/**
 * 测试执行微服务启动类
 */
public class TestExecuteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestExecuteApplication.class, args);
    }
}