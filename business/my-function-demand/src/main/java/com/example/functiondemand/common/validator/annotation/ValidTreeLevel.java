package com.example.functiondemand.common.validator.annotation;

import com.example.functiondemand.common.validator.TreeLevelValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TreeLevelValidator.class)
@Documented
public @interface ValidTreeLevel {
    
    String message() default "树形结构层级超过限制";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    int maxLevel() default 5;
}