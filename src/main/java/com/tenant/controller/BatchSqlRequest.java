package com.tenant.controller;

import lombok.Data;
import java.util.List;

@Data
public class BatchSqlRequest {
    private List<String> tenantIds;
    private List<String> sqlList;
} 