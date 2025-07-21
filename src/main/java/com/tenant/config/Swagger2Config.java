package com.tenant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tenant"))
                .paths(PathSelectors.any())
                .build()
                // 全局header参数说明，便于租户切换测试
                .globalOperationParameters(Collections.singletonList(
                        new springfox.documentation.builders.ParameterBuilder()
                                .name("X-Tenant-ID")
                                .description("租户ID，支持动态切换租户数据库。部分接口必填。可在请求头中传递。")
                                .modelRef(new springfox.documentation.schema.ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()
                ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("租户动态数据源系统 API")
                .description("本系统支持2000+租户数据库的动态数据源切换。\n\n"
                        + "**租户切换说明：**\n"
                        + "1. 通过请求头 `X-Tenant-ID` 传递租户ID，实现动态路由到对应数据库。\n"
                        + "2. 部分接口需在Swagger页面右上角Authorize或每次请求时手动添加header。\n"
                        + "3. 若全局拦截器开启，则所有接口都需传递租户ID。\n"
                        + "4. 支持多产品、多租户高并发数据隔离。\n"
                        + "5. 如需自定义header名称，请参考接口注解说明。\n"
                        + "\n**接口测试方法：**\n"
                        + "- 在Swagger页面测试接口时，点击每个接口的 'Try it out'，在参数区添加header: X-Tenant-ID=你的租户ID。\n"
                        + "- 可通过租户管理接口动态创建/维护租户库。\n"
                        + "- 导入导出等高性能接口同样支持租户隔离。\n")
                .contact(new Contact("开发团队", "http://www.example.com", "dev@example.com"))
                .version("1.0.0")
                .build();
    }
} 