package com.tenant.model;

import lombok.Data;

@Data
public class AlertRuleRequest {
    private String name;
    private String condition;
    private String threshold;
    private String action;
} 