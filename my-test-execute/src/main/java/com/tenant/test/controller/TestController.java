package com.tenant.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * 测试接口
     * 
     * @return 测试消息
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Test Execute Service!";
    }
}