package com.example.functiondemand.function.processor;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class FunctionPointExcelData {
    
    @ExcelProperty("功能点ID")
    private String id;
    
    @ExcelProperty("父功能点ID")
    private String parentId;
    
    @ExcelProperty("分类目录")
    private String categoryPath;
    
    @ExcelProperty("功能点名称")
    private String name;
    
    @ExcelProperty("功能点描述")
    private String description;
    
    @ExcelProperty("所属模块")
    private String module;
    
    @ExcelProperty("复杂度")
    private String complexity;
    
    @ExcelProperty("状态")
    private String status;
    
    @ExcelProperty("负责人")
    private String owner;
    
    @ExcelProperty("创建人")
    private String createdBy;
    
    @ExcelProperty("更新人")
    private String updatedBy;
}