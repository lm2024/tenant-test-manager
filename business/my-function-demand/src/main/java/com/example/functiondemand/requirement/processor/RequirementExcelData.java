package com.example.functiondemand.requirement.processor;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class RequirementExcelData {
    
    @ExcelProperty("需求ID")
    private String id;
    
    @ExcelProperty("父需求ID")
    private String parentId;
    
    @ExcelProperty("分类目录")
    private String categoryPath;
    
    @ExcelProperty("需求标题")
    private String title;
    
    @ExcelProperty("需求描述")
    private String description;
    
    @ExcelProperty("优先级")
    private String priority;
    
    @ExcelProperty("状态")
    private String status;
    
    @ExcelProperty("需求来源")
    private String source;
    
    @ExcelProperty("负责人")
    private String assignee;
    
    @ExcelProperty("创建人")
    private String createdBy;
    
    @ExcelProperty("更新人")
    private String updatedBy;
}