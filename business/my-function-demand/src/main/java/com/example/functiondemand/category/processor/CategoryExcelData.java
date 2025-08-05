package com.example.functiondemand.category.processor;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class CategoryExcelData {
    
    @ExcelProperty("分类ID")
    private String id;
    
    @ExcelProperty("父分类ID")
    private String parentId;
    
    @ExcelProperty("分类名称")
    private String name;
    
    @ExcelProperty("分类类型")
    private String type;
    
    @ExcelProperty("分类描述")
    private String description;
    
    @ExcelProperty("排序")
    private Integer sortOrder;
    
    @ExcelProperty("完整路径")
    private String fullPath;
    
    @ExcelProperty("创建人")
    private String createdBy;
    
    @ExcelProperty("更新人")
    private String updatedBy;
}