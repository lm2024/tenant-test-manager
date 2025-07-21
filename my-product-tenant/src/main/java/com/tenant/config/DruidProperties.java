package com.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
} 