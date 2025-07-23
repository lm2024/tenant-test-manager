package com.common.fileio.model;

import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理结果模型
 * 用于存储数据处理的结果信息
 */
@Data
public class ProcessResult<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 是否成功
     */
    private boolean success;
    
    /**
     * 处理的数据总数
     */
    private int totalCount;
    
    /**
     * 成功处理的数据数量
     */
    private int successCount;
    
    /**
     * 失败处理的数据数量
     */
    private int failCount;
    
    /**
     * 错误消息
     */
    private String errorMessage;
    
    /**
     * 处理的数据列表
     */
    private List<T> data;
    
    /**
     * 错误详情列表
     */
    private List<String> errors;
    
    /**
     * 创建一个成功的处理结果
     * 
     * @param data 处理的数据列表
     * @return 处理结果对象
     */
    public static <T> ProcessResult<T> success(List<T> data) {
        ProcessResult<T> result = new ProcessResult<>();
        result.setSuccess(true);
        result.setData(data);
        result.setTotalCount(data != null ? data.size() : 0);
        result.setSuccessCount(data != null ? data.size() : 0);
        result.setFailCount(0);
        return result;
    }
    
    /**
     * 创建一个失败的处理结果
     * 
     * @param errorMessage 错误消息
     * @return 处理结果对象
     */
    public static <T> ProcessResult<T> fail(String errorMessage) {
        ProcessResult<T> result = new ProcessResult<>();
        result.setSuccess(false);
        result.setErrorMessage(errorMessage);
        result.setTotalCount(0);
        result.setSuccessCount(0);
        result.setFailCount(0);
        return result;
    }
    
    /**
     * 添加错误信息
     * 
     * @param error 错误信息
     */
    public void addError(String error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
        failCount++;
    }
}