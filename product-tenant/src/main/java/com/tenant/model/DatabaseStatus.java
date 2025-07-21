package com.tenant.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DatabaseStatus {
    private String tenantId;
    private String databaseName;
    private String status; // ONLINE, OFFLINE, ERROR
    private long connectionCount;
    private long activeConnections;
    private long maxConnections;
    private double cpuUsage;
    private double memoryUsage;
    private long diskUsage;
    private long maxDiskSpace;
    private long slowQueryCount;
    private double avgResponseTime;
    private LocalDateTime lastUpdateTime;
    private String errorMessage;
} 