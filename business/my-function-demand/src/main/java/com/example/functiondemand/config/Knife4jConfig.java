package com.example.functiondemand.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Knife4j API文档配置
 * 提供完整的API文档和在线测试功能
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
@Profile({"dev", "test"})  // 只在开发和测试环境启用
public class Knife4jConfig {

    @Value("${app.name:需求管理和功能管理模块}")
    private String appName;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${app.description:提供需求管理、功能点管理和分类目录管理的完整解决方案}")
    private String appDescription;

    @Value("${app.author:System Team}")
    private String appAuthor;

    @Value("${app.contact.email:support@example.com}")
    private String contactEmail;

    @Value("${app.contact.url:https://example.com}")
    private String contactUrl;

    /**
     * 需求管理API文档
     */
    @Bean
    public Docket requirementApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(requirementApiInfo())
                .groupName("需求管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.functiondemand.controller"))
                .paths(PathSelectors.ant("/api/requirements/**"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 功能点管理API文档
     */
    @Bean
    public Docket functionPointApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(functionPointApiInfo())
                .groupName("功能点管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.functiondemand.controller"))
                .paths(PathSelectors.ant("/api/function-points/**"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 分类目录管理API文档
     */
    @Bean
    public Docket categoryApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(categoryApiInfo())
                .groupName("分类目录管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.functiondemand.controller"))
                .paths(PathSelectors.ant("/api/categories/**"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 导入导出API文档
     */
    @Bean
    public Docket importExportApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(importExportApiInfo())
                .groupName("导入导出")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.functiondemand.controller"))
                .paths(PathSelectors.regex("/api/(import|export).*"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 完整API文档
     */
    @Bean
    public Docket allApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(allApiInfo())
                .groupName("完整API")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 需求管理API信息
     */
    private ApiInfo requirementApiInfo() {
        return new ApiInfoBuilder()
                .title(appName + " - 需求管理API")
                .description("提供需求的增删改查、批量操作、树形结构管理等功能")
                .version(appVersion)
                .contact(new Contact(appAuthor, contactUrl, contactEmail))
                .license("Apache License 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    /**
     * 功能点管理API信息
     */
    private ApiInfo functionPointApiInfo() {
        return new ApiInfoBuilder()
                .title(appName + " - 功能点管理API")
                .description("提供功能点的增删改查、批量操作、树形结构管理等功能")
                .version(appVersion)
                .contact(new Contact(appAuthor, contactUrl, contactEmail))
                .license("Apache License 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    /**
     * 分类目录管理API信息
     */
    private ApiInfo categoryApiInfo() {
        return new ApiInfoBuilder()
                .title(appName + " - 分类目录管理API")
                .description("提供分类目录的增删改查、批量操作、树形结构管理等功能")
                .version(appVersion)
                .contact(new Contact(appAuthor, contactUrl, contactEmail))
                .license("Apache License 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    /**
     * 导入导出API信息
     */
    private ApiInfo importExportApiInfo() {
        return new ApiInfoBuilder()
                .title(appName + " - 导入导出API")
                .description("提供Excel文件的导入导出、批量处理、进度查询等功能")
                .version(appVersion)
                .contact(new Contact(appAuthor, contactUrl, contactEmail))
                .license("Apache License 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    /**
     * 完整API信息
     */
    private ApiInfo allApiInfo() {
        return new ApiInfoBuilder()
                .title(appName + " - 完整API文档")
                .description(appDescription + "\n\n" +
                        "主要功能：\n" +
                        "• 需求管理：支持需求的全生命周期管理，包括创建、更新、删除、查询等\n" +
                        "• 功能点管理：支持功能点的层级管理和关联关系维护\n" +
                        "• 分类目录管理：支持多层级分类目录的管理和维护\n" +
                        "• 导入导出：支持Excel格式的批量数据导入导出\n" +
                        "• 多租户支持：完整的多租户数据隔离和路由功能\n" +
                        "• 高性能：支持大批量数据处理和高并发访问\n\n" +
                        "技术特性：\n" +
                        "• RESTful API设计\n" +
                        "• 完整的数据验证和异常处理\n" +
                        "• 树形结构优化查询\n" +
                        "• 批量操作性能优化\n" +
                        "• 多租户数据隔离\n" +
                        "• 异步导入导出处理")
                .version(appVersion)
                .contact(new Contact(appAuthor, contactUrl, contactEmail))
                .license("Apache License 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    /**
     * 安全配置
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        
        // 租户ID头部认证
        securitySchemes.add(new ApiKey("X-Tenant-Id", "X-Tenant-Id", "header"));
        
        // JWT Token认证（如果需要）
        securitySchemes.add(new ApiKey("Authorization", "Authorization", "header"));
        
        return securitySchemes;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/api/**"))
                .build());
        
        return securityContexts;
    }

    /**
     * 默认认证配置
     */
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> securityReferences = new ArrayList<>();
        
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        
        securityReferences.add(new SecurityReference("X-Tenant-Id", authorizationScopes));
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        
        return securityReferences;
    }
}