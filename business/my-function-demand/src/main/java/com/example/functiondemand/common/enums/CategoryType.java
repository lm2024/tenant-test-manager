package com.example.functiondemand.common.enums;

public enum CategoryType {
    REQUIREMENT("需求分类"),
    FUNCTION("功能分类");

    private final String description;

    CategoryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}