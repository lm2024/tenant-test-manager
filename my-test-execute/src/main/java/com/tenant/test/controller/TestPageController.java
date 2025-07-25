package com.tenant.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试页面控制器
 * 提供测试页面的访问路径
 */
@Controller
public class TestPageController {
    
    /**
     * 文件导入导出测试页面
     * 
     * @return 页面路径
     */
    @GetMapping("/test/file-io")
    public String fileIoTestPage() {
        return "redirect:/file-io-test.html";
    }
}