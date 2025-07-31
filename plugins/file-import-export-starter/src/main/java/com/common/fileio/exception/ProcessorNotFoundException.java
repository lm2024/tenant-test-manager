package com.common.fileio.exception;

/**
 * 处理器未找到异常
 * 当指定的数据处理器不存在时抛出
 */
public class ProcessorNotFoundException extends FileProcessException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建一个处理器未找到异常
     * 
     * @param processorName 处理器名称
     */
    public ProcessorNotFoundException(String processorName) {
        super("数据处理器未找到: " + processorName, "PROCESSOR_NOT_FOUND");
    }
}