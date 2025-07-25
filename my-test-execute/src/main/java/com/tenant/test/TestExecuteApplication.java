package com.tenant.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.tenant.test", "com.common.fileio"})
/**
 * 测试执行微服务启动类
 */
public class TestExecuteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestExecuteApplication.class, args);
    }
}