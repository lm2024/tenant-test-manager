package com.common.fileio.processor;

import com.common.fileio.exception.DataProcessException;
import com.common.fileio.exception.UnsupportedFileTypeException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象数据处理器
 * 提供一些通用功能
 * 
 * @param <T> 实体类型
 */
@Slf4j
public abstract class AbstractDataProcessor<T> implements DataProcessor<T> {
    
    @Override
    public List<T> parseFileData(String filePath, String fileType) {
        log.info("解析文件: {}, 类型: {}", filePath, fileType);
        
        if ("excel".equalsIgnoreCase(fileType) || filePath.endsWith(".xlsx") || filePath.endsWith(".xls")) {
            return parseExcelFile(filePath);
        } else if ("csv".equalsIgnoreCase(fileType) || filePath.endsWith(".csv")) {
            return parseCsvFile(filePath);
        } else if ("txt".equalsIgnoreCase(fileType) || filePath.endsWith(".txt")) {
            return parseTxtFile(filePath);
        } else {
            throw new UnsupportedFileTypeException(fileType);
        }
    }
    
    /**
     * 解析Excel文件
     * 
     * @param filePath 文件路径
     * @return 实体对象列表
     */
    protected abstract List<T> parseExcelFile(String filePath);
    
    /**
     * 解析CSV文件
     * 
     * @param filePath 文件路径
     * @return 实体对象列表
     */
    protected abstract List<T> parseCsvFile(String filePath);
    
    /**
     * 解析TXT文件
     * 
     * @param filePath 文件路径
     * @return 实体对象列表
     */
    protected abstract List<T> parseTxtFile(String filePath);
    
    @Override
    public List<Map<String, Object>> convertToExportFormat(List<T> entities) {
        List<Map<String, Object>> result = new ArrayList<>(entities.size());
        Map<String, String> fieldMapping = getFieldMapping();
        
        if (fieldMapping == null) {
            throw new DataProcessException("未定义字段映射");
        }
        
        for (T entity : entities) {
            Map<String, Object> row = new HashMap<>();
            
            // 使用反射获取实体属性值
            for (Map.Entry<String, String> entry : fieldMapping.entrySet()) {
                String header = entry.getKey();
                String field = entry.getValue();
                
                try {
                    // 获取属性值
                    Object value = getPropertyValue(entity, field);
                    
                    // 转换枚举或特殊类型
                    value = convertFieldValue(field, value);
                    
                    row.put(header, value);
                } catch (Exception e) {
                    log.warn("获取属性值失败: {}.{}", entity.getClass().getSimpleName(), field, e);
                    row.put(header, null);
                }
            }
            
            result.add(row);
        }
        
        return result;
    }
    
    /**
     * 获取属性值
     * 
     * @param entity 实体对象
     * @param field 属性名
     * @return 属性值
     */
    protected abstract Object getPropertyValue(T entity, String field);
    
    /**
     * 转换字段值
     * 用于处理枚举或特殊类型的转换
     * 
     * @param field 字段名
     * @param value 字段值
     * @return 转换后的值
     */
    protected Object convertFieldValue(String field, Object value) {
        return value;
    }
    
    @Override
    public List<String> getExportHeaders() {
        Map<String, String> fieldMapping = getFieldMapping();
        return fieldMapping != null ? new ArrayList<>(fieldMapping.keySet()) : new ArrayList<>();
    }
}