package com.common.fileio.model;

import lombok.Data;
import java.io.Serializable;

/**
 * API响应模型
 * 用于统一API接口的返回格式
 */
@Data
public class ApiResponse<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 是否成功
     */
    private boolean success;
    
    /**
     * 响应数据
     */
    private T data;
    
    /**
     * 错误消息
     */
    private String error;
    
    /**
     * 错误代码
     */
    private String errorCode;
    
    /**
     * 创建一个成功的响应
     * 
     * @param data 响应数据
     * @return API响应对象
     */
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
    
    /**
     * 创建一个失败的响应
     * 
     * @param error 错误消息
     * @return API响应对象
     */
    public static <T> ApiResponse<T> fail(String error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setError(error);
        return response;
    }
    
    /**
     * 创建一个带错误代码的失败响应
     * 
     * @param error 错误消息
     * @param errorCode 错误代码
     * @return API响应对象
     */
    public static <T> ApiResponse<T> fail(String error, String errorCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setError(error);
        response.setErrorCode(errorCode);
        return response;
    }
}