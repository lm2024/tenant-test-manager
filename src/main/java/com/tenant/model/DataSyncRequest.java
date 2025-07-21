package com.tenant.model;

import lombok.Data;
import java.util.List;

@Data
public class DataSyncRequest {
    private String sourceTenantId;
    private String targetTenantId;
    private String syncType; // BIDIRECTIONAL, UNIDIRECTIONAL
    private List<String> tables;
} 