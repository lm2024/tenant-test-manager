package com.example.functiondemand.common.enums;

public enum RequirementPriority {
    LOW("低"),
    MEDIUM("中"),
    HIGH("高"),
    URGENT("紧急");

    private final String description;

    RequirementPriority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}