package com.common.fileio.processor;

import com.common.fileio.exception.DataProcessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CSV数据处理器
 * 提供CSV文件的解析和转换功能
 * 
 * @param <T> 实体类型
 */
@Slf4j
public abstract class CsvDataProcessor<T> extends AbstractDataProcessor<T> {
    
    @Override
    protected List<T> parseExcelFile(String filePath) {
        // 默认实现，子类可以覆盖
        throw new UnsupportedOperationException("此处理器不支持Excel文件解析");
    }
    
    @Override
    protected List<T> parseCsvFile(String filePath) {
        log.info("解析CSV文件: {}", filePath);
        
        List<T> result = new ArrayList<>();
        
        try (Reader reader = new FileReader(filePath, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            
            Map<String, Integer> headerMap = csvParser.getHeaderMap();
            log.debug("解析到表头: {}", headerMap.keySet());
            
            int rowIndex = 1; // 从1开始，因为0是表头
            for (CSVRecord record : csvParser) {
                rowIndex++;
                
                try {
                    // 将CSV记录转换为字段映射
                    Map<String, String> rowData = new HashMap<>();
                    for (String header : headerMap.keySet()) {
                        rowData.put(header, record.get(header));
                    }
                    
                    // 将字段映射转换为实体对象
                    T entity = convertToEntity(rowData);
                    
                    // 验证实体对象
                    String error = validate(entity);
                    if (error != null) {
                        throw new DataProcessException("数据验证失败: " + error, rowIndex);
                    }
                    
                    result.add(entity);
                } catch (Exception e) {
                    log.error("解析第{}行数据失败: {}", rowIndex, e.getMessage());
                    throw new DataProcessException("解析第" + rowIndex + "行数据失败: " + e.getMessage(), e);
                }
            }
            
            log.info("CSV文件解析完成，共{}行数据", result.size());
            return result;
        } catch (Exception e) {
            log.error("解析CSV文件失败: {}", e.getMessage());
            throw new DataProcessException("解析CSV文件失败: " + e.getMessage(), e);
        }
    }
    
    @Override
    protected List<T> parseTxtFile(String filePath) {
        // 默认实现，子类可以覆盖
        throw new UnsupportedOperationException("此处理器不支持TXT文件解析");
    }
    
    @Override
    protected Object getPropertyValue(T entity, String field) {
        try {
            Field declaredField = entity.getClass().getDeclaredField(field);
            declaredField.setAccessible(true);
            return declaredField.get(entity);
        } catch (Exception e) {
            log.warn("获取属性值失败: {}.{}", entity.getClass().getSimpleName(), field, e);
            return null;
        }
    }
    
    /**
     * 将字段映射转换为实体对象
     * 
     * @param rowData 字段映射（表头 -> 值）
     * @return 实体对象
     */
    protected abstract T convertToEntity(Map<String, String> rowData);
}