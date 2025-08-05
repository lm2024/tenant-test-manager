package com.common.segmentid.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class SegmentIdRedissonConfig {
    @Value("${spring.redis.redisson.config:}")
    private String redissonYamlConfig;

    @Value("${redisson.address:}")
    private String address;

    @Value("${redisson.password:}")
    private String password;

    @Bean(name = "segmentIdRedissonClient")
    @ConditionalOnMissingBean(name = "segmentIdRedissonClient")
    public RedissonClient segmentIdRedissonClient() throws Exception {
        Config config;
        if (StringUtils.hasText(redissonYamlConfig)) {
            // 兼容spring.redis.redisson.config的yaml字符串
            config = Config.fromYAML(redissonYamlConfig);
        } else if (StringUtils.hasText(address)) {
            config = new Config();
            config.useSingleServer().setAddress(address);
            if (StringUtils.hasText(password)) {
                config.useSingleServer().setPassword(password);
            }
        } else {
            // 使用默认配置
            config = new Config();
            config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setDatabase(0)
                .setConnectionMinimumIdleSize(8)
                .setConnectionPoolSize(64)
                .setConnectTimeout(10000)
                .setTimeout(3000);
        }
        
        // 设置序列化方式为JSON，这样Redis客户端就能正常显示
        config.setCodec(new JsonJacksonCodec());
        
        return Redisson.create(config);
    }
} 