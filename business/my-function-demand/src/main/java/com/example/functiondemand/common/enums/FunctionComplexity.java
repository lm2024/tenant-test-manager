package com.example.functiondemand.common.enums;

public enum FunctionComplexity {
    LOW("简单"),
    MEDIUM("中等"),
    HIGH("复杂"),
    VERY_HIGH("非常复杂");

    private final String description;

    FunctionComplexity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}