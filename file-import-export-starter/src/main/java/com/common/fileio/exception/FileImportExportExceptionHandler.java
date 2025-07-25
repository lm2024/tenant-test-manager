package com.common.fileio.exception;

import com.common.fileio.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Locale;

/**
 * 文件导入导出异常处理器
 * 处理文件导入导出过程中的异常
 */
@RestControllerAdvice
@Slf4j
public class FileImportExportExceptionHandler {
    
    private final MessageSource messageSource;
    
    /**
     * 构造函数
     * 
     * @param messageSource 消息源
     */
    public FileImportExportExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    /**
     * 处理文件处理异常
     * 
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(FileProcessException.class)
    public ResponseEntity<ApiResponse<Void>> handleFileProcessException(FileProcessException e) {
        log.error("文件处理异常", e);
        
        String errorMessage = e.getMessage();
        String errorCode = e.getErrorCode();
        
        // 如果有错误代码，尝试从消息源获取国际化消息
        if (errorCode != null && !errorCode.isEmpty()) {
            errorMessage = getLocalizedMessage(errorCode, e.getArgs(), e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(errorMessage, errorCode));
    }
    
    /**
     * 处理任务未找到异常
     * 
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleTaskNotFoundException(TaskNotFoundException e) {
        log.error("任务未找到", e);
        
        String errorMessage = e.getMessage();
        String errorCode = e.getErrorCode();
        
        // 如果有错误代码，尝试从消息源获取国际化消息
        if (errorCode != null && !errorCode.isEmpty()) {
            errorMessage = getLocalizedMessage(errorCode, e.getArgs(), e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(errorMessage, errorCode));
    }
    
    /**
     * 处理不支持的文件类型异常
     * 
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(UnsupportedFileTypeException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnsupportedFileTypeException(UnsupportedFileTypeException e) {
        log.error("不支持的文件类型", e);
        
        String errorMessage = e.getMessage();
        String errorCode = e.getErrorCode();
        
        // 如果有错误代码，尝试从消息源获取国际化消息
        if (errorCode != null && !errorCode.isEmpty()) {
            errorMessage = getLocalizedMessage(errorCode, e.getArgs(), e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(errorMessage, errorCode));
    }
    
    /**
     * 处理处理器未找到异常
     * 
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(ProcessorNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProcessorNotFoundException(ProcessorNotFoundException e) {
        log.error("处理器未找到", e);
        
        String errorMessage = e.getMessage();
        String errorCode = e.getErrorCode();
        
        // 如果有错误代码，尝试从消息源获取国际化消息
        if (errorCode != null && !errorCode.isEmpty()) {
            errorMessage = getLocalizedMessage(errorCode, e.getArgs(), e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(errorMessage, errorCode));
    }
    
    /**
     * 处理数据处理异常
     * 
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(DataProcessException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataProcessException(DataProcessException e) {
        log.error("数据处理异常", e);
        
        String errorMessage = e.getMessage();
        String errorCode = e.getErrorCode();
        
        // 如果有错误代码，尝试从消息源获取国际化消息
        if (errorCode != null && !errorCode.isEmpty()) {
            errorMessage = getLocalizedMessage(errorCode, e.getArgs(), e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(errorMessage, errorCode));
    }
    
    /**
     * 处理文件操作异常
     * 
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(FileOperationException.class)
    public ResponseEntity<ApiResponse<Void>> handleFileOperationException(FileOperationException e) {
        log.error("文件操作异常", e);
        
        String errorMessage = e.getMessage();
        String errorCode = e.getErrorCode();
        
        // 如果有错误代码，尝试从消息源获取国际化消息
        if (errorCode != null && !errorCode.isEmpty()) {
            errorMessage = getLocalizedMessage(errorCode, e.getArgs(), e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(errorMessage, errorCode));
    }
    
    /**
     * 处理上传文件大小超出限制异常
     * 
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<Void>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("上传文件大小超出限制", e);
        
        String errorMessage = "上传文件大小超出限制";
        String errorCode = "FILE_SIZE_EXCEEDED";
        
        // 尝试从消息源获取国际化消息
        errorMessage = getLocalizedMessage(errorCode, null, errorMessage);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(errorMessage, errorCode));
    }
    
    /**
     * 处理其他异常
     * 
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("未处理的异常", e);
        
        String errorMessage = "服务器内部错误";
        String errorCode = "INTERNAL_SERVER_ERROR";
        
        // 尝试从消息源获取国际化消息
        errorMessage = getLocalizedMessage(errorCode, null, errorMessage);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(errorMessage, errorCode));
    }
    
    /**
     * 获取本地化消息
     * 
     * @param code 消息代码
     * @param args 消息参数
     * @param defaultMessage 默认消息
     * @return 本地化消息
     */
    private String getLocalizedMessage(String code, Object[] args, String defaultMessage) {
        if (messageSource == null) {
            return defaultMessage;
        }
        
        Locale locale = LocaleContextHolder.getLocale();
        
        try {
            return messageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            return defaultMessage;
        }
    }
}