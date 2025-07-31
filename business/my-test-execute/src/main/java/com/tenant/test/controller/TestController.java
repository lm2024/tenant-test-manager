package com.tenant.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@RestController
@RequestMapping("/api/test")
@Tag(name = "测试接口", description = "测试执行微服务基础接口")
public class TestController {

    /**
     * 测试接口
     * 
     * @return 测试消息
     */
    @GetMapping("/hello")
    @Operation(summary = "测试接口", description = "返回测试消息")
    public String hello() {
        return "Hello, Test Execute Service!";
    }
}