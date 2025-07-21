package com.tenant.model;

import lombok.Data;

@Data
public class RateLimitRuleRequest {
    private String name;
    private String tenantId;
    private String apiPath;
    private int maxRequests;
    private int timeWindow;
    private String strategy; // TOKEN_BUCKET, LEAKY_BUCKET, SLIDING_WINDOW
} 