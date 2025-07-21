package com.tenant.model;

import lombok.Data;
import java.util.Map;

@Data
public class ScheduledJobRequest {
    private String name;
    private String cronExpression;
    private String taskType;
    private String tenantId;
    private Map<String, Object> parameters;
} 