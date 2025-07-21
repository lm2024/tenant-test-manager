package com.tenant.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class BatchTaskRequest {
    private String taskType;
    private List<String> tenantIds;
    private Map<String, Object> parameters;
} 