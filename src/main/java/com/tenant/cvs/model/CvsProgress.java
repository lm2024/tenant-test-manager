package com.tenant.cvs.model;

import lombok.Data;

@Data
public class CvsProgress {
    private String taskId;
    private int progress; // 0-100
    private String status;
    private String message;
} 