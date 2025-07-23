package com.common.fileio.exception;

/**
 * 文件处理异常
 * 文件导入导出过程中的基础异常类
 */
public class FileProcessException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 错误代码
     */
    private String errorCode;
    
    /**
     * 错误参数
     */
    private Object[] args;
    
    /**
     * 创建一个文件处理异常
     * 
     * @param message 异常消息
     */
    public FileProcessException(String message) {
        super(message);
    }
    
    /**
     * 创建一个文件处理异常
     * 
     * @param message 异常消息
     * @param cause 原始异常
     */
    public FileProcessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 创建一个带错误代码的文件处理异常
     * 
     * @param message 异常消息
     * @param errorCode 错误代码
     */
    public FileProcessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * 创建一个带错误代码和参数的文件处理异常
     * 
     * @param message 异常消息
     * @param errorCode 错误代码
     * @param args 错误参数
     */
    public FileProcessException(String message, String errorCode, Object[] args) {
        super(message);
        this.errorCode = errorCode;
        this.args = args;
    }
    
    /**
     * 获取错误代码
     * 
     * @return 错误代码
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * 获取错误参数
     * 
     * @return 错误参数
     */
    public Object[] getArgs() {
        return args;
    }
}