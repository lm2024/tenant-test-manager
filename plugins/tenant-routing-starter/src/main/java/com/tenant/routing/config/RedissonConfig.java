package com.tenant.routing.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.tenant.routing.entity.TenantDbInfo;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redisson配置类
 * 使用Redisson连接Redis
 */
@Configuration
public class RedissonConfig {

    @Autowired
    private TenantRoutingProperties properties;

    /**
     * 配置RedissonClient
     */
    @Bean(name = "tenantRedissonClient")
    @Primary
    @ConditionalOnMissingBean(name = "tenantRedissonClient")
    public RedissonClient redissonClient() {
        Config config = new Config();
        
        // 使用单节点模式
        String redisHost = properties.getRedis().getHost();
        int redisPort = properties.getRedis().getPort();
        String password = properties.getRedis().getPassword();
        
        if (password != null && !password.isEmpty()) {
            config.useSingleServer()
                .setAddress("redis://" + redisHost + ":" + redisPort)
                .setPassword(password)
                .setDatabase(properties.getRedis().getDatabase())
                .setConnectionMinimumIdleSize(properties.getRedis().getPool().getMinIdle())
                .setConnectionPoolSize(properties.getRedis().getPool().getMaxActive());
        } else {
            config.useSingleServer()
                .setAddress("redis://" + redisHost + ":" + redisPort)
                .setDatabase(properties.getRedis().getDatabase())
                .setConnectionMinimumIdleSize(properties.getRedis().getPool().getMinIdle())
                .setConnectionPoolSize(properties.getRedis().getPool().getMaxActive());
        }
        
        return Redisson.create(config);
    }

    /**
     * 配置租户信息的RedisTemplate
     */
    @Bean
    @ConditionalOnMissingBean(name = "tenantRedisTemplate")
    public RedisTemplate<String, TenantDbInfo> tenantRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, TenantDbInfo> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        
        // 配置key的序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        
        // 配置value的序列化方式
        Jackson2JsonRedisSerializer<TenantDbInfo> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(TenantDbInfo.class);
        ObjectMapper om = new ObjectMapper();
        // 使用最新的可见性配置方法
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        // 使用最新的类型处理方法
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
    
    /**
     * 配置字符串的RedisTemplate
     */
    @Bean(name = "tenantStringRedisTemplate")
    @ConditionalOnMissingBean(name = "tenantStringRedisTemplate")
    public RedisTemplate<String, String> tenantStringRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        
        // 配置key和value的序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        
        template.afterPropertiesSet();
        return template;
    }
    
    /**
     * 配置通用的RedisTemplate，可以存储任何类型的对象
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        
        // 配置key的序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        
        // 配置value的序列化方式
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 使用最新的可见性配置方法
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        // 使用最新的类型处理方法
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(om);
        
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashValueSerializer(jsonRedisSerializer);
        
        template.afterPropertiesSet();
        return template;
    }
}