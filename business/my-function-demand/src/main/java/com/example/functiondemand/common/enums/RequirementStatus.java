package com.example.functiondemand.common.enums;

public enum RequirementStatus {
    DRAFT("草稿"),
    REVIEWING("评审中"),
    APPROVED("已批准"),
    DEVELOPING("开发中"),
    TESTING("测试中"),
    COMPLETED("已完成"),
    CANCELLED("已取消");

    private final String description;

    RequirementStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}