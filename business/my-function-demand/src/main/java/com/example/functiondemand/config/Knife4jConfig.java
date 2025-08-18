package com.example.functiondemand.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Knife4j API文档配置
 * 提供完整的API文档和在线测试功能
 */
@Configuration
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
    public GroupedOpenApi requirementApi() {
        return GroupedOpenApi.builder()
                .group("需求管理")
                .pathsToMatch("/api/requirements", "/api/requirements/**")
                .packagesToScan("com.example.functiondemand.requirement.controller")
                .build();
    }

    /**
     * 功能点管理API文档
     */
    @Bean
    public GroupedOpenApi functionPointApi() {
        return GroupedOpenApi.builder()
                .group("功能点管理")
                .pathsToMatch("/api/function-points", "/api/function-points/**")
                .packagesToScan("com.example.functiondemand.function.controller")
                .build();
    }

    /**
     * 分类目录管理API文档
     */
    @Bean
    public GroupedOpenApi categoryApi() {
        return GroupedOpenApi.builder()
                .group("分类目录管理")
                .pathsToMatch("/api/categories", "/api/categories/**")
                .packagesToScan("com.example.functiondemand.category.controller")
                .build();
    }

    /**
     * 导入导出API文档
     */
    @Bean
    public GroupedOpenApi importExportApi() {
        return GroupedOpenApi.builder()
                .group("导入导出")
                .pathsToMatch("/api/import/**", "/api/export/**", "/api/file/**")
                .packagesToScan("com.common.fileio.controller")
                .build();
    }

    /**
     * 完整API文档
     */
    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("完整API")
                .pathsToMatch("/api/**")
                .packagesToScan("com.example.functiondemand")
                .build();
    }

    /**
     * OpenAPI配置
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
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
                        .contact(new Contact()
                                .name(appAuthor)
                                .url(contactUrl)
                                .email(contactEmail))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .addSecurityItem(new SecurityRequirement()
                        .addList("X-Tenant-Id")
                        .addList("Authorization"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("X-Tenant-Id",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name("X-Tenant-Id")
                                        .description("租户ID"))
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT Token")));
    }
}