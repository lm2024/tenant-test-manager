package com.common.fileio.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 消息源配置
 * 配置国际化消息源
 */
@Configuration
public class MessageSourceConfig {
    
    /**
     * 消息源
     * 
     * @return 消息源
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/file-io-messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}