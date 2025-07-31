package com.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 应用启动入口。
 *
 * 访问接口文档：
 *   - Knife4j UI:   /doc.html
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tenant"})
public class TenentApp {
    public static void main(String[] args) {
        SpringApplication.run(TenentApp.class, args);
    }
} 