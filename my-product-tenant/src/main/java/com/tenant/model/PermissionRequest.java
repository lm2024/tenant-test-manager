package com.tenant.model;

import lombok.Data;

@Data
public class PermissionRequest {
    private String name;
    private String description;
    private String resource;
    private String action;
    private String tenantId;
} 