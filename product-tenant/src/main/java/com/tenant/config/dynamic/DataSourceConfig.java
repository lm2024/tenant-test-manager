package com.tenant.config.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DataSourceConfig {

    @Bean
    public DynamicRoutingDataSource dynamicRoutingDataSource(Environment env) {
        DruidDataSource defaultDataSource = new DruidDataSource();
        defaultDataSource.setUrl(env.getProperty("spring.datasource.url"));
        defaultDataSource.setUsername(env.getProperty("spring.datasource.username"));
        defaultDataSource.setPassword(env.getProperty("spring.datasource.password"));
        defaultDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));

        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(defaultDataSource);
        routingDataSource.setTargetDataSources(new HashMap<>());
        return routingDataSource;
    }

    @Bean
    public DataSource dataSource(DynamicRoutingDataSource dynamicRoutingDataSource) {
        return dynamicRoutingDataSource;
    }
} 