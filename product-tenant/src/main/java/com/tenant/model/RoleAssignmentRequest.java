package com.tenant.model;

import lombok.Data;

@Data
public class RoleAssignmentRequest {
    private String userId;
    private String roleId;
    private String tenantId;
} 