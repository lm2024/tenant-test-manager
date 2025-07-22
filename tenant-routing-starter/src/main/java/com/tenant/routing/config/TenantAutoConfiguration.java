package com.tenant.routing.config;

import com.tenant.routing.core.DynamicDataSource;
import com.tenant.routing.core.TenantDataSourceCreator;
import com.tenant.routing.interceptor.GlobalTenantInterceptor;
import com.tenant.routing.interceptor.TenantHeaderInterceptor;
import com.tenant.routing.interceptor.TenantSwitchAspect;
import com.tenant.routing.service.TenantDbInfoService;
import com.tenant.routing.service.TenantRegistryService;
import com.tenant.routing.service.impl.TenantDbInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 租户路由自动配置
 */
@Configuration
@EnableConfigurationProperties(TenantRoutingProperties.class)
@EnableCaching
@ConditionalOnProperty(prefix = "tenant.routing", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TenantAutoConfiguration implements WebMvcConfigurer {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantAutoConfiguration.class);
    
    @Bean
    public DynamicDataSource dynamicDataSource(TenantDataSourceCreator creator) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        // 初始化租户中心库
        DataSource tenantCenterDataSource = creator.createDataSource(
            "tenant_center",
            "jdbc:mysql://localhost:3306/tenant_center?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
            "root",
            "password"
        );
        targetDataSources.put("tenant_center", tenantCenterDataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(tenantCenterDataSource);
        return dynamicDataSource;
    }
    
    @Bean
    public TenantDataSourceCreator tenantDataSourceCreator() {
        return new TenantDataSourceCreator();
    }
    
    @Bean
    public TenantHeaderInterceptor tenantHeaderInterceptor() {
        return new TenantHeaderInterceptor();
    }
    
    @Bean
    public GlobalTenantInterceptor globalTenantInterceptor() {
        return new GlobalTenantInterceptor();
    }
    
    @Bean
    public TenantSwitchAspect tenantSwitchAspect() {
        return new TenantSwitchAspect();
    }
    
    @Bean
    public TenantRegistryService tenantRegistryService() {
        return new TenantRegistryService();
    }
    
    @Bean
    public TenantDbInfoService tenantDbInfoService() {
        return new TenantDbInfoServiceImpl();
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    /**
     * 配置缓存管理器
     */
    @Bean
    public org.springframework.cache.CacheManager cacheManager() {
        return new org.springframework.cache.concurrent.ConcurrentMapCacheManager("tenantCache");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 使用全局租户拦截器替代原来的租户头拦截器
        registry.addInterceptor(globalTenantInterceptor()).addPathPatterns("/**");
    }
    
    @Bean
    public ApplicationListener<ContextRefreshedEvent> tenantInitializer() {
        return new ApplicationListener<ContextRefreshedEvent>() {
            @Override
            public void onApplicationEvent(ContextRefreshedEvent event) {
                try {
                    // 使用TenantDbInfoService加载所有租户
                    TenantDbInfoService tenantDbInfoService = event.getApplicationContext().getBean(TenantDbInfoService.class);
                    tenantDbInfoService.refreshAllTenantDataSources();
                    logger.info("Tenant configurations loaded successfully");
                } catch (Exception e) {
                    logger.error("Failed to load tenant configurations on startup", e);
                }
            }
        };
    }
}