package com.example.functiondemand.common.enums;

public enum RelationType {
    IMPLEMENT("实现"),
    DEPEND("依赖"),
    RELATE("关联"),
    CONFLICT("冲突");

    private final String description;

    RelationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}