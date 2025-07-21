package com.tenant.model;

import lombok.Data;

@Data
public class CircuitBreakerRequest {
    private String name;
    private String tenantId;
    private String apiPath;
    private int failureThreshold;
    private int recoveryTime;
    private double errorRate;
} 