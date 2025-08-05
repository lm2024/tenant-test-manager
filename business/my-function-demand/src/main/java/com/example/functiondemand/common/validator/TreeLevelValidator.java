package com.example.functiondemand.common.validator;

import cn.hutool.core.util.StrUtil;
import com.example.functiondemand.common.util.TreePathUtil;
import com.example.functiondemand.common.validator.annotation.ValidTreeLevel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TreeLevelValidator implements ConstraintValidator<ValidTreeLevel, String> {
    
    private int maxLevel;
    
    @Override
    public void initialize(ValidTreeLevel constraintAnnotation) {
        this.maxLevel = constraintAnnotation.maxLevel();
    }
    
    @Override
    public boolean isValid(String parentPath, ConstraintValidatorContext context) {
        if (StrUtil.isBlank(parentPath)) {
            return true;
        }
        
        int level = TreePathUtil.getLevel(parentPath);
        return level < maxLevel;
    }
}