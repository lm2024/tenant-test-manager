package com.tenant.routing.config;

import com.tenant.routing.core.DynamicDataSource;
import com.tenant.routing.core.TenantDataSourceCreator;
import com.tenant.routing.interceptor.GlobalTenantInterceptor;
import com.tenant.routing.interceptor.TenantHeaderInterceptor;
import com.tenant.routing.interceptor.TenantSwitchAspect;
import com.tenant.routing.interceptor.TenantSwitchHeaderAspect;
import com.tenant.routing.service.TenantDbInfoService;
import com.tenant.routing.service.TenantRegistryService;
import com.tenant.routing.service.impl.TenantDbInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
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
@EnableScheduling
@ConditionalOnProperty(prefix = "tenant.routing", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import(RedissonConfig.class)
public class TenantAutoConfiguration implements WebMvcConfigurer {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantAutoConfiguration.class);
    
    @Bean
    public TenantDataSourceCreator tenantDataSourceCreator() {
        return new TenantDataSourceCreator();
    }
    
    @Bean(name = "tenantCenterDataSource")
    @Primary
    public DataSource tenantCenterDataSource(TenantDataSourceCreator creator) {
        return creator.createDataSource(
            "tenant_center",
            "jdbc:mysql://localhost:3306/tenant_center?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
            "root",
            "password"
        );
    }
    
    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("tenantCenterDataSource") DataSource tenantCenterDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        // 使用已创建的tenantCenterDataSource
        targetDataSources.put("tenant_center", tenantCenterDataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(tenantCenterDataSource);
        return dynamicDataSource;
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
    public JdbcTemplate jdbcTemplate(@Qualifier("tenantCenterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    /**
     * 配置Redisson租户缓存服务
     */
    @Bean
    public com.tenant.routing.service.RedissonTenantCacheService redissonTenantCacheService() {
        return new com.tenant.routing.service.RedissonTenantCacheService();
    }
    
    /**
     * 配置缓存管理器
     * 使用Redis缓存管理器替代内存缓存管理器
     */
    @Bean
    @ConditionalOnMissingBean(name = "cacheManager")
    public org.springframework.cache.CacheManager cacheManager() {
        return new org.springframework.cache.concurrent.ConcurrentMapCacheManager("tenantCache");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 使用全局租户拦截器替代原来的租户头拦截器
        registry.addInterceptor(globalTenantInterceptor()).addPathPatterns("/**");
    }
    
    /**
     * 租户初始化器
     * 在应用启动时加载所有租户信息，并预加载到Redis缓存
     */
    @Bean
    public ApplicationListener<ContextRefreshedEvent> tenantInitializer() {
        return new ApplicationListener<ContextRefreshedEvent>() {
            @Override
            public void onApplicationEvent(ContextRefreshedEvent event) {
                try {
                    logger.info("开始初始化租户配置...");
                    
                    // 使用TenantDbInfoService加载所有租户
                    TenantDbInfoService tenantDbInfoService = event.getApplicationContext().getBean(TenantDbInfoService.class);
                    
                    // 刷新所有租户数据源
                    tenantDbInfoService.refreshAllTenantDataSources();
                    logger.info("租户数据源初始化完成");
                    
                    // Redis缓存服务会在@PostConstruct中自动预加载租户信息
                    logger.info("租户配置加载成功，Redis缓存预加载将在后台进行");
                } catch (Exception e) {
                    logger.error("租户配置加载失败", e);
                }
            }
        };
    }
}