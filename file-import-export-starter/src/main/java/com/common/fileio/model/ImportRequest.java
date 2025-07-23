package com.common.fileio.model;

import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 导入请求模型
 * 用于封装导入请求参数
 */
@Data
public class ImportRequest implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 文件信息列表
     */
    private List<FileInfo> files;
    
    /**
     * 文件类型（excel/csv/txt）
     */
    private String fileType;
    
    /**
     * 处理器名称
     */
    private String processorName;
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 导入参数
     */
    private Map<String, Object> params;
}