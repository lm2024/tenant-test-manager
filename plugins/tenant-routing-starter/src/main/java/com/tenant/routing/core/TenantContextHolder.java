package com.tenant.routing.core;

import java.util.Stack;

/**
 * 租户上下文持有者
 * 支持嵌套租户切换
 */
public class TenantContextHolder {
    
    private static final ThreadLocal<Stack<String>> TENANT_CONTEXT = new ThreadLocal<>();
    
    public static void setTenantId(String tenantId) {
        Stack<String> stack = TENANT_CONTEXT.get();
        if (stack == null) {
            stack = new Stack<>();
            TENANT_CONTEXT.set(stack);
        }
        stack.push(tenantId);
    }
    
    public static String getTenantId() {
        Stack<String> stack = TENANT_CONTEXT.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }
    
    public static void clear() {
        TENANT_CONTEXT.remove();
    }
    
    public static boolean hasTenant() {
        Stack<String> stack = TENANT_CONTEXT.get();
        return stack != null && !stack.isEmpty();
    }
    
    /**
     * 弹出当前租户ID，恢复到上一个租户ID
     */
    public static String popTenantId() {
        Stack<String> stack = TENANT_CONTEXT.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        String popped = stack.pop();
        if (stack.isEmpty()) {
            TENANT_CONTEXT.remove();
        }
        return popped;
    }
    
    /**
     * 获取租户栈的大小
     */
    public static int getStackSize() {
        Stack<String> stack = TENANT_CONTEXT.get();
        return stack == null ? 0 : stack.size();
    }
}