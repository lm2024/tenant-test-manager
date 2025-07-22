package com.tenant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // Swagger认证配置 - 从配置文件读取
    @Value("${swagger.auth.username:admin}")
    private String swaggerUsername;
    
    @Value("${swagger.auth.password:admin123}")
    private String swaggerPassword;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 只注册Swagger认证拦截器
        registry.addInterceptor(new SwaggerAuthInterceptor())
                .addPathPatterns("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs/**", "/webjars/**", "/doc.html");
    }
    
    /**
     * Swagger认证拦截器
     */
    public class SwaggerAuthInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 获取Authorization头
            String authHeader = request.getHeader("Authorization");
            
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                try {
                    // 解码Basic认证
                    String credentials = new String(java.util.Base64.getDecoder().decode(authHeader.substring(6)));
                    String[] parts = credentials.split(":");
                    
                    if (parts.length == 2) {
                        String username = parts[0];
                        String password = parts[1];
                        
                        if (swaggerUsername.equals(username) && swaggerPassword.equals(password)) {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    // 解码失败，继续执行认证失败逻辑
                }
            }
            
            // 认证失败，返回401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"Swagger API Documentation\"");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<html><body><h1>401 Unauthorized</h1><p>请使用正确的账号密码访问Swagger文档</p></body></html>");
            return false;
        }
    }
} 