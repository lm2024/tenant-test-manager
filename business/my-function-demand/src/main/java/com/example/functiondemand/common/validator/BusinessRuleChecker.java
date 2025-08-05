package com.example.functiondemand.common.validator;

import cn.hutool.core.util.StrUtil;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.category.repository.CategoryRepository;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.function.entity.FunctionPoint;
import com.example.functiondemand.function.repository.FunctionPointRepository;
import com.example.functiondemand.requirement.entity.Requirement;
import com.example.functiondemand.requirement.repository.RequirementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BusinessRuleChecker {
    
    private final RequirementRepository requirementRepository;
    private final FunctionPointRepository functionPointRepository;
    private final CategoryRepository categoryRepository;
    private final BusinessRuleValidator validator;
    
    /**
     * 检查需求业务规则
     */
    public void checkRequirementRules(Requirement requirement) {
        // 验证基本字段
        validator.validateRequirementTitle(requirement.getTitle());
        validator.validateRequirementDescription(requirement.getDescription());
        
        // 验证父子关系
        if (StrUtil.isNotBlank(requirement.getParentId())) {
            checkRequirementParentChild(requirement.getId(), requirement.getParentId());
        }
        
        // 验证分类关系
        if (StrUtil.isNotBlank(requirement.getCategoryId())) {
            checkCategoryExists(requirement.getCategoryId(), CategoryType.REQUIREMENT);
        }
        
        log.debug("需求业务规则检查通过: {}", requirement.getId());
    }
    
    /**
     * 检查功能点业务规则
     */
    public void checkFunctionPointRules(FunctionPoint functionPoint) {
        // 验证基本字段
        validator.validateFunctionPointName(functionPoint.getName());
        validator.validateRequirementDescription(functionPoint.getDescription());
        
        // 验证父子关系
        if (StrUtil.isNotBlank(functionPoint.getParentId())) {
            checkFunctionPointParentChild(functionPoint.getId(), functionPoint.getParentId());
        }
        
        // 验证分类关系
        if (StrUtil.isNotBlank(functionPoint.getCategoryId())) {
            checkCategoryExists(functionPoint.getCategoryId(), CategoryType.FUNCTION);
        }
        
        log.debug("功能点业务规则检查通过: {}", functionPoint.getId());
    }
    
    /**
     * 检查分类业务规则
     */
    public void checkCategoryRules(Category category) {
        // 验证基本字段
        validator.validateCategoryName(category.getName());
        validator.validateSortOrder(category.getSortOrder());
        
        // 验证父子关系
        if (StrUtil.isNotBlank(category.getParentId())) {
            checkCategoryParentChild(category.getId(), category.getParentId(), category.getType());
        }
        
        // 验证名称唯一性
        checkCategoryNameUnique(category.getId(), category.getName(), category.getType());
        
        log.debug("分类业务规则检查通过: {}", category.getId());
    }
    
    /**
     * 检查需求父子关系
     */
    private void checkRequirementParentChild(String childId, String parentId) {
        Optional<Requirement> parent = requirementRepository.findById(parentId);
        if (!parent.isPresent()) {
            throw new IllegalArgumentException("父需求不存在: " + parentId);
        }
        
        // 验证循环引用
        validator.validateCircularReference(parentId, childId, 
            requirementRepository::findById, 
            req -> req.map(Requirement::getParentId).orElse(null));
        
        // 验证层级限制
        validator.validateTreeLevel(parent.get().getPath());
    }
    
    /**
     * 检查功能点父子关系
     */
    private void checkFunctionPointParentChild(String childId, String parentId) {
        Optional<FunctionPoint> parent = functionPointRepository.findById(parentId);
        if (!parent.isPresent()) {
            throw new IllegalArgumentException("父功能点不存在: " + parentId);
        }
        
        // 验证循环引用
        validator.validateCircularReference(parentId, childId, 
            functionPointRepository::findById, 
            func -> func.map(FunctionPoint::getParentId).orElse(null));
        
        // 验证层级限制
        validator.validateTreeLevel(parent.get().getPath());
    }
    
    /**
     * 检查分类父子关系
     */
    private void checkCategoryParentChild(String childId, String parentId, CategoryType type) {
        Optional<Category> parent = categoryRepository.findById(parentId);
        if (!parent.isPresent()) {
            throw new IllegalArgumentException("父分类不存在: " + parentId);
        }
        
        // 验证类型一致性
        if (!parent.get().getType().equals(type)) {
            throw new IllegalArgumentException("父分类类型不一致");
        }
        
        // 验证循环引用
        validator.validateCircularReference(parentId, childId, 
            categoryRepository::findById, 
            cat -> cat.map(Category::getParentId).orElse(null));
        
        // 验证层级限制
        validator.validateTreeLevel(parent.get().getPath());
    }
    
    /**
     * 检查分类是否存在
     */
    private void checkCategoryExists(String categoryId, CategoryType expectedType) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            throw new IllegalArgumentException("分类不存在: " + categoryId);
        }
        
        if (!category.get().getType().equals(expectedType)) {
            throw new IllegalArgumentException("分类类型不匹配，期望: " + expectedType + ", 实际: " + category.get().getType());
        }
    }
    
    /**
     * 检查分类名称唯一性
     */
    private void checkCategoryNameUnique(String categoryId, String name, CategoryType type) {
        Optional<Category> existing = categoryRepository.findByTypeAndName(type, name);
        if (existing.isPresent() && !existing.get().getId().equals(categoryId)) {
            throw new IllegalArgumentException("同类型下分类名称已存在: " + name);
        }
    }
    
    /**
     * 检查批量操作规则
     */
    public void checkBatchOperationRules(int itemCount, String operationType) {
        // 根据操作类型设置不同的限制
        int maxSize;
        switch (operationType.toLowerCase()) {
            case "create":
                maxSize = 500;
                break;
            case "update":
                maxSize = 1000;
                break;
            case "delete":
                maxSize = 200;
                break;
            default:
                maxSize = 100;
        }
        
        validator.validateBatchSize(itemCount, maxSize);
        
        log.debug("批量操作规则检查通过: {} - 数量: {}", operationType, itemCount);
    }
    
    /**
     * 检查删除规则
     */
    public void checkDeleteRules(String entityId, String entityType) {
        switch (entityType.toLowerCase()) {
            case "requirement":
                if (requirementRepository.existsByParentId(entityId)) {
                    throw new IllegalArgumentException("存在子需求，无法删除");
                }
                break;
            case "functionpoint":
                if (functionPointRepository.existsByParentId(entityId)) {
                    throw new IllegalArgumentException("存在子功能点，无法删除");
                }
                break;
            case "category":
                if (categoryRepository.existsByParentId(entityId)) {
                    throw new IllegalArgumentException("存在子分类，无法删除");
                }
                break;
        }
        
        log.debug("删除规则检查通过: {} - {}", entityType, entityId);
    }
}