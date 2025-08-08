package com.common.redis.cache.constants;

/**
 * Redis Key 统一管理常量类
 * 
 * <p>统一管理所有Redis缓存键的定义，避免key散落在各处难以维护。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
public final class RedisKeyConstants {
    
    private RedisKeyConstants() {
        // 工具类，禁止实例化
    }
    
    // ================================ 列表缓存相关 ================================
    
    /**
     * 测试用例列表缓存
     * 示例: default:cache_test:test_cases_list:no_params:page_0_size_10
     */
    public static final String TEST_CASES_LIST = "test_cases_list";
    
    /**
     * 缺陷列表缓存
     * 示例: default:cache_test:bugs_list:no_params:page_0_size_10
     */
    public static final String BUGS_LIST = "bugs_list";
    
    /**
     * 用户列表缓存
     * 示例: default:cache_test:users_list:no_params:page_0_size_10
     */
    public static final String USERS_LIST = "users_list";
    
    /**
     * 项目列表缓存
     * 示例: default:cache_test:projects_list:no_params:page_0_size_10
     */
    public static final String PROJECTS_LIST = "projects_list";
    
    // ================================ 任务进度相关 ================================
    
    /**
     * 任务队列
     * 示例: csv:task:queue
     */
    public static final String TASK_QUEUE = "task:queue";
    
    /**
     * 任务进度
     * 示例: csv:task:progress:005601c4-b087-4375-924e-a68d83a9de77
     */
    public static final String TASK_PROGRESS = "task:progress";
    
    /**
     * 任务状态
     * 示例: csv:task:status:005601c4-b087-4375-924e-a68d83a9de77
     */
    public static final String TASK_STATUS = "task:status";
    
    // ================================ 序号生成器相关 ================================
    
    /**
     * 序号生成器
     * 示例: segmentid:tenant123:test_case
     */
    public static final String SEGMENT_ID = "segmentid";
    
    /**
     * 序号生成器锁
     * 示例: segmentid:lock:tenant123:test_case
     */
    public static final String SEGMENT_ID_LOCK = "segmentid:lock";
    
    // ================================ 租户路由相关 ================================
    
    /**
     * 租户路由配置
     * 示例: tenant:routing:config
     */
    public static final String TENANT_ROUTING = "tenant:routing";
    
    // ================================ 用户会话相关 ================================
    
    /**
     * 用户会话
     * 示例: user:session:123, user:permissions:123
     */
    public static final String USER_SESSION = "user:session";
    public static final String USER_PERMISSIONS = "user:permissions";
    public static final String USER_ROLES = "user:roles";
    
    // ================================ 系统配置相关 ================================
    
    /**
     * 系统配置
     * 示例: system:config:app_name
     */
    public static final String SYSTEM_CONFIG = "system:config";
    
    /**
     * 字典数据
     * 示例: system:dict:status_type
     */
    public static final String SYSTEM_DICT = "system:dict";
    
    // ================================ 统计分析相关 ================================
    
    /**
     * 统计数据
     * 示例: stats:test_cases:2024-01, stats:bugs:2024-01
     */
    public static final String STATS_TEST_CASES = "stats:test_cases";
    public static final String STATS_BUGS = "stats:bugs";
    public static final String STATS_USER_ACTIVITY = "stats:user_activity";
    
    // ================================ 分布式锁相关 ================================
    
    /**
     * 通用分布式锁
     * 示例: lock:import:task_123, lock:export:task_456
     */
    public static final String LOCK = "lock";
    
    // ================================ 常用key组合示例 ================================
    
    /**
     * 构建完整的缓存key
     * 格式: {tenant}:{prefix}:{suffix}
     * 
     * @param tenant 租户ID，如果为空则使用"default"
     * @param prefix key前缀
     * @param suffix key后缀
     * @return 完整的缓存key
     */
    public static String buildKey(String tenant, String prefix, String suffix) {
        if (tenant == null || tenant.trim().isEmpty()) {
            tenant = "default";
        }
        return tenant + ":" + prefix + ":" + suffix;
    }
    
    /**
     * 构建列表缓存key
     * 示例: default:cache_test:test_cases_list:no_params:page_0_size_10
     */
    public static String buildListCacheKey(String tenant, String listType, String params, int page, int size) {
        return buildKey(tenant, "cache_test", listType + ":" + params + ":page_" + page + "_size_" + size);
    }
    
    /**
     * 构建任务进度key
     * 示例: csv:task:progress:005601c4-b087-4375-924e-a68d83a9de77
     */
    public static String buildTaskProgressKey(String tenant, String taskId) {
        return buildKey(tenant, TASK_PROGRESS, taskId);
    }
    
    /**
     * 构建任务队列key
     * 示例: csv:task:queue
     */
    public static String buildTaskQueueKey(String tenant) {
        return buildKey(tenant, TASK_QUEUE, "");
    }
    
    /**
     * 构建任务状态key
     * 示例: csv:task:status:005601c4-b087-4375-924e-a68d83a9de77
     */
    public static String buildTaskStatusKey(String tenant, String taskId) {
        return buildKey(tenant, TASK_STATUS, taskId);
    }
    
    /**
     * 构建序号生成器key
     * 示例: segmentid:tenant123:test_case
     */
    public static String buildSegmentIdKey(String tenant, String bizType) {
        return SEGMENT_ID + ":" + tenant + ":" + bizType;
    }
    
    /**
     * 构建序号生成器锁key
     * 示例: segmentid:lock:tenant123:test_case
     */
    public static String buildSegmentIdLockKey(String tenant, String bizType) {
        return SEGMENT_ID_LOCK + ":" + tenant + ":" + bizType;
    }
    
    /**
     * 构建用户会话key
     * 示例: tenant123:user:session:user456
     */
    public static String buildUserSessionKey(String tenant, String userId) {
        return buildKey(tenant, USER_SESSION, userId);
    }
    
    /**
     * 构建用户权限key
     * 示例: tenant123:user:permissions:user456
     */
    public static String buildUserPermissionsKey(String tenant, String userId) {
        return buildKey(tenant, USER_PERMISSIONS, userId);
    }
}