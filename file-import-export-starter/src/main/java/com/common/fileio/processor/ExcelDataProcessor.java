package com.common.fileio.processor;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.common.fileio.exception.DataProcessException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Excel数据处理器
 * 提供Excel文件的解析和转换功能
 * 
 * @param <T> 实体类型
 */
@Slf4j
public abstract class ExcelDataProcessor<T> extends AbstractDataProcessor<T> {
    
    @Override
    protected List<T> parseExcelFile(String filePath) {
        log.info("解析Excel文件: {}", filePath);
        
        List<T> result = new ArrayList<>();
        AtomicInteger rowIndex = new AtomicInteger(0);
        
        try {
            // 使用EasyExcel解析Excel文件
            EasyExcel.read(filePath, new AnalysisEventListener<Map<Integer, String>>() {
                private Map<Integer, String> headMap = new HashMap<>();
                
                @Override
                public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                    this.headMap = headMap;
                    log.debug("解析到表头: {}", headMap);
                }
                
                @Override
                public void invoke(Map<Integer, String> data, AnalysisContext context) {
                    rowIndex.incrementAndGet();
                    
                    try {
                        // 将行数据转换为字段映射
                        Map<String, String> rowData = new HashMap<>();
                        for (Map.Entry<Integer, String> entry : data.entrySet()) {
                            String header = headMap.get(entry.getKey());
                            if (header != null) {
                                rowData.put(header, entry.getValue());
                            }
                        }
                        
                        // 将字段映射转换为实体对象
                        T entity = convertToEntity(rowData);
                        
                        // 验证实体对象
                        String error = validate(entity);
                        if (error != null) {
                            throw new DataProcessException("数据验证失败: " + error, rowIndex.get());
                        }
                        
                        result.add(entity);
                    } catch (Exception e) {
                        log.error("解析第{}行数据失败: {}", rowIndex.get(), e.getMessage());
                        throw new DataProcessException("解析第" + rowIndex.get() + "行数据失败: " + e.getMessage(), e);
                    }
                }
                
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info("Excel文件解析完成，共{}行数据", result.size());
                }
            }).sheet().doRead();
            
            return result;
        } catch (Exception e) {
            log.error("解析Excel文件失败: {}", e.getMessage());
            throw new DataProcessException("解析Excel文件失败: " + e.getMessage(), e);
        }
    }
    
    @Override
    protected List<T> parseCsvFile(String filePath) {
        // 默认实现，子类可以覆盖
        throw new UnsupportedOperationException("此处理器不支持CSV文件解析");
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