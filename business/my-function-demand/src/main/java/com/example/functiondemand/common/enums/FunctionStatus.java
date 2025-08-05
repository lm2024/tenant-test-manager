package com.example.functiondemand.common.enums;

public enum FunctionStatus {
    PLANNING("规划中"),
    DESIGNING("设计中"),
    DEVELOPING("开发中"),
    TESTING("测试中"),
    COMPLETED("已完成"),
    CANCELLED("已取消");

    private final String description;

    FunctionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}