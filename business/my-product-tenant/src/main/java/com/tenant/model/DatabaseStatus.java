package com.tenant.model;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 数据库状态信息
 */
@Data
public class DatabaseStatus {
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 数据库名称
     */
    private String databaseName;
    
    /**
     * 数据库状态 (ONLINE/OFFLINE/MAINTENANCE)
     */
    private String status;
    
    /**
     * 连接数
     */
    private Integer connectionCount;
    
    /**
     * 活跃连接数
     */
    private Integer activeConnections;
    
    /**
     * 最大连接数
     */
    private Integer maxConnections;
    
    /**
     * CPU使用率 (%)
     */
    private Double cpuUsage;
    
    /**
     * 内存使用率 (%)
     */
    private Double memoryUsage;
    
    /**
     * 磁盘使用量 (bytes)
     */
    private Long diskUsage;
    
    /**
     * 最大磁盘空间 (bytes)
     */
    private Long maxDiskSpace;
    
    /**
     * 慢查询数量
     */
    private Long slowQueryCount;
    
    /**
     * 平均响应时间 (ms)
     */
    private Double avgResponseTime;
    
    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;
    
    /**
     * 数据库版本
     */
    private String version;
    
    /**
     * 字符集
     */
    private String charset;
    
    /**
     * 排序规则
     */
    private String collation;
    
    /**
     * 表数量
     */
    private Integer tableCount;
    
    /**
     * 索引数量
     */
    private Integer indexCount;
    
    /**
     * 数据大小 (bytes)
     */
    private Long dataSize;
    
    /**
     * 索引大小 (bytes)
     */
    private Long indexSize;
    
    /**
     * 是否只读
     */
    private Boolean readOnly;
    
    /**
     * 备份状态
     */
    private String backupStatus;
    
    /**
     * 最后备份时间
     */
    private LocalDateTime lastBackupTime;
} 