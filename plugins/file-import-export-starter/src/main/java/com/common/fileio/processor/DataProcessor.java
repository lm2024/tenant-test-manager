package com.common.fileio.processor;

import java.util.List;
import java.util.Map;

/**
 * 数据处理器接口
 * 用于处理导入导出的数据
 * 
 * @param <T> 实体类型
 */
public interface DataProcessor<T> {
    
    /**
     * 解析文件数据为实体对象
     * 
     * @param filePath 文件路径
     * @param fileType 文件类型（excel/csv/txt）
     * @return 实体对象列表
     */
    List<T> parseFileData(String filePath, String fileType);
    
    /**
     * 批量保存数据
     * 
     * @param entities 实体对象列表
     */
    void saveBatch(List<T> entities);
    
    /**
     * 查询导出数据
     * 
     * @param params 查询参数
     * @param page 页码（从0开始）
     * @param size 每页大小
     * @return 实体对象列表
     */
    List<T> queryExportData(Map<String, Object> params, int page, int size);
    
    /**
     * 转换为导出格式
     * 
     * @param entities 实体对象列表
     * @return 导出数据列表
     */
    List<Map<String, Object>> convertToExportFormat(List<T> entities);
    
    /**
     * 获取导出表头
     * 
     * @return 表头列表
     */
    List<String> getExportHeaders();
    
    /**
     * 获取处理器名称
     * 
     * @return 处理器名称
     */
    String getProcessorName();
    
    /**
     * 获取实体类型
     * 
     * @return 实体类型
     */
    Class<T> getEntityClass();
    
    /**
     * 获取总记录数
     * 
     * @param params 查询参数
     * @return 总记录数
     */
    default long count(Map<String, Object> params) {
        return 0;
    }
    
    /**
     * 验证数据
     * 
     * @param entity 实体对象
     * @return 错误消息，如果没有错误则返回null
     */
    default String validate(T entity) {
        return null;
    }
    
    /**
     * 获取字段映射
     * 
     * @return 字段映射（表头 -> 属性名）
     */
    default Map<String, String> getFieldMapping() {
        return null;
    }
}