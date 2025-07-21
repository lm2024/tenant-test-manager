package com.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 应用启动入口。
 *
 * 访问接口文档：
 *   - Swagger UI:   /swagger-ui.html
 *   - Knife4j UI:   /doc.html
 */
@SpringBootApplication
@EnableKnife4j
@EnableSwagger2
@ComponentScan(basePackages = {"com.tenant", "com.com.tenant.cvs"})
public class TenantDynamicDatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TenantDynamicDatasourceApplication.class, args);
    }
} 