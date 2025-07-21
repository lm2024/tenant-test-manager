package com.tenant.model;

import lombok.Data;
import java.util.Map;

@Data
public class MigrationTaskRequest {
    private String name;
    private String sourceTenantId;
    private String targetTenantId;
    private String migrationType; // FULL, INCREMENTAL, SCHEMA
    private Map<String, Object> options;
} 