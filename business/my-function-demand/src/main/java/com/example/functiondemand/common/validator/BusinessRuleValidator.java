package com.example.functiondemand.common.validator;

import cn.hutool.core.util.StrUtil;
import com.example.functiondemand.common.exception.CircularReferenceException;
import com.example.functiondemand.common.exception.MaxLevelExceededException;
import com.example.functiondemand.common.util.TreePathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Slf4j
@Component
public class BusinessRuleValidator {
    
    private static final int MAX_TREE_LEVEL = 5;
    private static final int MAX_TITLE_LENGTH = 200;
    private static final int MAX_DESCRIPTION_LENGTH = 2000;
    private static final int MAX_NAME_LENGTH = 100;
    
    /**
     * 验证树形结构层级
     */
    public void validateTreeLevel(String parentPath) {
        if (StrUtil.isBlank(parentPath)) {
            return;
        }
        
        int currentLevel = TreePathUtil.getLevel(parentPath);
        if (currentLevel >= MAX_TREE_LEVEL) {
            throw new MaxLevelExceededException("超过最大层级限制：" + MAX_TREE_LEVEL);
        }
        
        log.debug("树形层级验证通过，当前层级: {}", currentLevel);
    }
    
    /**
     * 验证循环引用
     */
    public <T> void validateCircularReference(String parentId, String childId, Function<String, T> finder, Function<T, String> parentGetter) {
        if (StrUtil.isBlank(parentId) || StrUtil.isBlank(childId)) {
            return;
        }
        
        if (parentId.equals(childId)) {
            throw new CircularReferenceException("不能将自己设置为父节点");
        }
        
        Set<String> visited = new HashSet<>();
        String currentId = parentId;
        
        while (StrUtil.isNotBlank(currentId) && !visited.contains(currentId)) {
            if (currentId.equals(childId)) {
                throw new CircularReferenceException("检测到循环引用");
            }
            
            visited.add(currentId);
            T entity = finder.apply(currentId);
            if (entity == null) {
                break;
            }
            
            currentId = parentGetter.apply(entity);
        }
        
        log.debug("循环引用验证通过，父节点: {}, 子节点: {}", parentId, childId);
    }
    
    /**
     * 验证字符串长度
     */
    public void validateStringLength(String value, String fieldName, int maxLength) {
        if (StrUtil.isNotBlank(value) && value.length() > maxLength) {
            throw new IllegalArgumentException(String.format("%s长度不能超过%d字符", fieldName, maxLength));
        }
    }
    
    /**
     * 验证必填字段
     */
    public void validateRequired(String value, String fieldName) {
        if (StrUtil.isBlank(value)) {
            throw new IllegalArgumentException(fieldName + "不能为空");
        }
    }
    
    /**
     * 验证需求标题
     */
    public void validateRequirementTitle(String title) {
        validateRequired(title, "需求标题");
        validateStringLength(title, "需求标题", MAX_TITLE_LENGTH);
        
        // 业务规则：标题不能包含特殊字符
        if (title.matches(".*[<>\"'&].*")) {
            throw new IllegalArgumentException("需求标题不能包含特殊字符：< > \" ' &");
        }
    }
    
    /**
     * 验证需求描述
     */
    public void validateRequirementDescription(String description) {
        if (StrUtil.isNotBlank(description)) {
            validateStringLength(description, "需求描述", MAX_DESCRIPTION_LENGTH);
        }
    }
    
    /**
     * 验证功能点名称
     */
    public void validateFunctionPointName(String name) {
        validateRequired(name, "功能点名称");
        validateStringLength(name, "功能点名称", MAX_TITLE_LENGTH);
        
        // 业务规则：名称不能包含特殊字符
        if (name.matches(".*[<>\"'&].*")) {
            throw new IllegalArgumentException("功能点名称不能包含特殊字符：< > \" ' &");
        }
    }
    
    /**
     * 验证分类名称
     */
    public void validateCategoryName(String name) {
        validateRequired(name, "分类名称");
        validateStringLength(name, "分类名称", MAX_NAME_LENGTH);
        
        // 业务规则：分类名称不能包含路径分隔符
        if (name.contains("/") || name.contains("\\")) {
            throw new IllegalArgumentException("分类名称不能包含路径分隔符：/ \\");
        }
    }
    
    /**
     * 验证排序值
     */
    public void validateSortOrder(Integer sortOrder) {
        if (sortOrder != null && sortOrder < 0) {
            throw new IllegalArgumentException("排序值不能为负数");
        }
    }
    
    /**
     * 验证批量操作数量
     */
    public void validateBatchSize(int size, int maxSize) {
        if (size <= 0) {
            throw new IllegalArgumentException("批量操作数量必须大于0");
        }
        
        if (size > maxSize) {
            throw new IllegalArgumentException(String.format("批量操作数量不能超过%d", maxSize));
        }
    }
    
    /**
     * 验证ID格式
     */
    public void validateId(String id, String fieldName) {
        if (StrUtil.isBlank(id)) {
            throw new IllegalArgumentException(fieldName + "不能为空");
        }
        
        // 业务规则：ID长度限制
        if (id.length() > 32) {
            throw new IllegalArgumentException(fieldName + "长度不能超过32字符");
        }
        
        // 业务规则：ID格式验证（只允许字母、数字、下划线、连字符）
        if (!id.matches("^[a-zA-Z0-9_-]+$")) {
            throw new IllegalArgumentException(fieldName + "只能包含字母、数字、下划线和连字符");
        }
    }
    
    /**
     * 验证枚举值
     */
    public <E extends Enum<E>> void validateEnum(String value, Class<E> enumClass, String fieldName) {
        if (StrUtil.isBlank(value)) {
            return;
        }
        
        try {
            Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("%s值无效：%s", fieldName, value));
        }
    }
}