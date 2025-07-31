package com.tenant.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试页面控制器
 * 提供测试页面的访问路径
 */
@Controller
@Tag(name = "测试页面", description = "测试页面访问接口")
public class TestPageController {
    
    /**
     * 文件导入导出测试页面
     * 
     * @return 页面路径
     */
    @GetMapping("/test/file-io")
    @Operation(summary = "文件导入导出测试页面", description = "重定向到文件导入导出测试页面")
    public String fileIoTestPage() {
        return "redirect:/file-io-test.html";
    }
}