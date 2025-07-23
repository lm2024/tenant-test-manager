package com.common.fileio.exception;

/**
 * 文件操作异常
 * 文件读写过程中的异常
 */
public class FileOperationException extends FileProcessException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建一个文件操作异常
     * 
     * @param message 异常消息
     */
    public FileOperationException(String message) {
        super(message, "FILE_OPERATION_ERROR");
    }
    
    /**
     * 创建一个文件操作异常
     * 
     * @param message 异常消息
     * @param cause 原始异常
     */
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}