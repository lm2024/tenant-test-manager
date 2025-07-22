package com.tenant.test;

import com.tenant.routing.core.TenantContextHolder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 测试租户路由功能
 */
@SpringBootTest
public class TenantRoutingTest {

    @Test
    public void testTenantContextHolder() {
        // 初始状态应该为null
        assertNull(TenantContextHolder.getTenantId());
        
        // 设置租户ID
        TenantContextHolder.setTenantId("test-tenant");
        assertEquals("test-tenant", TenantContextHolder.getTenantId());
        
        // 清除租户ID
        TenantContextHolder.clear();
        assertNull(TenantContextHolder.getTenantId());
    }
}