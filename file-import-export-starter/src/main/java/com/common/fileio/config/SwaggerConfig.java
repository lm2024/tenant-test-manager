package com.common.fileio.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 * 配置Swagger API文档
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "file.import.export.swagger", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {
    
    /**
     * 创建API文档
     * 
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.common.fileio.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(globalRequestParameters());
    }
    
    /**
     * API信息
     * 
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文件导入导出API文档")
                .description("提供文件导入导出相关的API接口文档")
                .version("1.0.0")
                .contact(new Contact("开发团队", "https://example.com", "dev@example.com"))
                .build();
    }
    
    /**
     * 全局请求参数
     * 
     * @return 全局请求参数列表
     */
    private List<RequestParameter> globalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        
        // 添加租户ID请求头
        parameters.add(new RequestParameterBuilder()
                .name("X-Tenant-ID")
                .description("租户ID")
                .required(true)
                .in(ParameterType.HEADER)
                .build());
        
        return parameters;
    }
}