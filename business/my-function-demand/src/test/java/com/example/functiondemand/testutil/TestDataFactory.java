package com.example.functiondemand.testutil;

import com.example.functiondemand.category.dto.CategoryCreateDTO;
import com.example.functiondemand.function.dto.FunctionPointCreateDTO;
import com.example.functiondemand.requirement.dto.RequirementCreateDTO;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.function.entity.FunctionPoint;
import com.example.functiondemand.requirement.entity.Requirement;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.common.enums.FunctionStatus;
import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.RequirementStatus;
import com.example.functiondemand.common.enums.RequirementPriority;

import java.time.LocalDateTime;

/**
 * 测试数据工厂
 * 提供创建测试数据的便捷方法
 */
public class TestDataFactory {

    /**
     * 创建测试需求实体
     */
    public static Requirement createRequirement(String id, String title) {
        Requirement requirement = new Requirement();
        requirement.setId(id);
        requirement.setTitle(title);
        requirement.setDescription("测试需求描述");
        requirement.setPriority(RequirementPriority.MEDIUM);
        requirement.setStatus(RequirementStatus.DRAFT);
        requirement.setSource("测试");
        requirement.setLevel(1);
        requirement.setCreatedTime(LocalDateTime.now());
        requirement.setUpdatedTime(LocalDateTime.now());
        return requirement;
    }

    /**
     * 创建测试需求创建DTO
     */
    public static RequirementCreateDTO createRequirementCreateDTO(String title) {
        RequirementCreateDTO dto = new RequirementCreateDTO();
        dto.setTitle(title);
        dto.setDescription("测试需求描述");
        dto.setPriority(RequirementPriority.MEDIUM);
        dto.setStatus(RequirementStatus.DRAFT);
        dto.setSource("测试");
        return dto;
    }

    /**
     * 创建测试功能点实体
     */
    public static FunctionPoint createFunctionPoint(String id, String name) {
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setId(id);
        functionPoint.setName(name);
        functionPoint.setDescription("测试功能点描述");
        functionPoint.setModule("测试模块");
        functionPoint.setComplexity(FunctionComplexity.MEDIUM);
        functionPoint.setStatus(FunctionStatus.PLANNING);
        functionPoint.setLevel(1);
        functionPoint.setCreatedTime(LocalDateTime.now());
        functionPoint.setUpdatedTime(LocalDateTime.now());
        return functionPoint;
    }

    /**
     * 创建测试功能点创建DTO
     */
    public static FunctionPointCreateDTO createFunctionPointCreateDTO(String name) {
        FunctionPointCreateDTO dto = new FunctionPointCreateDTO();
        dto.setName(name);
        dto.setDescription("测试功能点描述");
        dto.setModule("测试模块");
        dto.setComplexity(FunctionComplexity.MEDIUM);
        dto.setStatus(FunctionStatus.PLANNING);
        return dto;
    }

    /**
     * 创建测试分类实体
     */
    public static Category createCategory(String id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription("测试分类描述");
        category.setType(CategoryType.FUNCTION);
        category.setSortOrder(1);
        category.setLevel(1);
        category.setCreatedTime(LocalDateTime.now());
        category.setUpdatedTime(LocalDateTime.now());
        return category;
    }

    /**
     * 创建测试分类创建DTO
     */
    public static CategoryCreateDTO createCategoryCreateDTO(String name) {
        CategoryCreateDTO dto = new CategoryCreateDTO();
        dto.setName(name);
        dto.setDescription("测试分类描述");
        dto.setType(CategoryType.FUNCTION);
        dto.setSortOrder(1);
        return dto;
    }
}