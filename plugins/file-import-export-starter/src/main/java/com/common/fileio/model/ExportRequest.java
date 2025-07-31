package com.common.fileio.model;

import lombok.Data;
import java.io.Serializable;
import java.util.Map;

/**
 * 导出请求模型
 * 用于封装导出请求参数
 */
@Data
public class ExportRequest implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 导出文件类型（excel/csv/txt）
     */
    private String fileType = "excel";
    
    /**
     * 处理器名称
     */
    private String processorName;
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 查询参数
     */
    private Map<String, Object> queryParams;
    
    /**
     * 导出文件名（不含扩展名）
     */
    private String fileName;
    
    /**
     * 是否包含表头
     */
    private boolean includeHeader = true;
}