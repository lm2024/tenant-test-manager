package com.common.fileio.exception;

/**
 * 不支持的文件类型异常
 * 当上传的文件类型不被支持时抛出
 */
public class UnsupportedFileTypeException extends FileProcessException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建一个不支持的文件类型异常
     * 
     * @param fileType 文件类型
     */
    public UnsupportedFileTypeException(String fileType) {
        super("不支持的文件类型: " + fileType, "UNSUPPORTED_FILE_TYPE");
    }
}