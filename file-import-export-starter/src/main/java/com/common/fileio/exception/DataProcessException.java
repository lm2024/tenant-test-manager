package com.common.fileio.exception;

/**
 * 数据处理异常
 * 数据解析和处理过程中的异常
 */
public class DataProcessException extends FileProcessException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建一个数据处理异常
     * 
     * @param message 异常消息
     */
    public DataProcessException(String message) {
        super(message, "DATA_PROCESS_ERROR");
    }
    
    /**
     * 创建一个数据处理异常
     * 
     * @param message 异常消息
     * @param cause 原始异常
     */
    public DataProcessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 创建一个带行号的数据处理异常
     * 
     * @param message 异常消息
     * @param rowIndex 行号
     */
    public DataProcessException(String message, int rowIndex) {
        super("第" + rowIndex + "行: " + message, "DATA_PROCESS_ERROR");
    }
}