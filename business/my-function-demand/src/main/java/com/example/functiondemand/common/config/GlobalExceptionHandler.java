package com.example.functiondemand.common.config;

import com.example.functiondemand.common.exception.*;
import com.tenant.routing.core.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequirementNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleRequirementNotFound(RequirementNotFoundException e) {
        log.error("需求未找到: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, "需求未找到", e.getMessage());
    }

    @ExceptionHandler(FunctionPointNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleFunctionPointNotFound(FunctionPointNotFoundException e) {
        log.error("功能点未找到: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, "功能点未找到", e.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryNotFound(CategoryNotFoundException e) {
        log.error("分类目录未找到: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, "分类目录未找到", e.getMessage());
    }

    @ExceptionHandler(InvalidTreeStructureException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidTreeStructure(InvalidTreeStructureException e) {
        log.error("无效的树形结构: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "无效的树形结构", e.getMessage());
    }

    @ExceptionHandler(MaxLevelExceededException.class)
    public ResponseEntity<Map<String, Object>> handleMaxLevelExceeded(MaxLevelExceededException e) {
        log.error("超过最大层级限制: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "超过最大层级限制", e.getMessage());
    }

    @ExceptionHandler(CircularReferenceException.class)
    public ResponseEntity<Map<String, Object>> handleCircularReference(CircularReferenceException e) {
        log.error("循环引用错误: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "循环引用错误", e.getMessage());
    }

    @ExceptionHandler(DuplicateRelationException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateRelation(DuplicateRelationException e) {
        log.error("重复关联关系: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "重复关联关系", e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException e) {
        log.error("参数错误: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "参数错误", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.warn("参数验证失败 - 租户: {}", tenantId, e);
        
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        
        Map<String, Object> errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, "参数验证失败", "请求参数不符合要求");
        errorResponse.put("fieldErrors", fieldErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> handleBindException(BindException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.warn("数据绑定失败 - 租户: {}", tenantId, e);
        
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        
        Map<String, Object> errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, "数据绑定失败", "请求数据格式错误");
        errorResponse.put("fieldErrors", fieldErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.warn("约束验证失败 - 租户: {}", tenantId, e);
        
        String violations = e.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));
        
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "约束验证失败", violations);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.warn("请求体解析失败 - 租户: {}", tenantId, e);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "请求体解析失败", "请求数据格式错误");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.warn("参数类型不匹配 - 租户: {}", tenantId, e);
        String message = String.format("参数 '%s' 类型错误，期望类型: %s", 
            e.getName(), e.getRequiredType().getSimpleName());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "参数类型错误", message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingParameter(MissingServletRequestParameterException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.warn("缺少必需参数 - 租户: {}", tenantId, e);
        String message = String.format("缺少必需参数: %s", e.getParameterName());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "缺少必需参数", message);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.warn("不支持的请求方法 - 租户: {}", tenantId, e);
        String message = String.format("不支持的请求方法: %s", e.getMethod());
        return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "请求方法不支持", message);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.error("数据完整性违反 - 租户: {}", tenantId, e);
        
        String message = "数据操作违反完整性约束";
        if (e.getMessage().contains("Duplicate entry")) {
            message = "数据重复，违反唯一性约束";
        } else if (e.getMessage().contains("foreign key constraint")) {
            message = "违反外键约束，相关数据不存在";
        }
        
        return buildErrorResponse(HttpStatus.CONFLICT, "数据完整性错误", message);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalState(IllegalStateException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.error("非法状态异常 - 租户: {}", tenantId, e);
        
        // 特殊处理租户相关异常
        if (e.getMessage().contains("租户")) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "租户上下文错误", e.getMessage());
        }
        
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "操作状态错误", e.getMessage());
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Map<String, Object>> handleSecurity(SecurityException e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.error("安全异常 - 租户: {}", tenantId, e);
        return buildErrorResponse(HttpStatus.FORBIDDEN, "安全验证失败", "没有权限执行此操作");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception e) {
        String tenantId = TenantContextHolder.getTenantId();
        log.error("系统错误 - 租户: {}", tenantId, e);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "系统错误", "服务器内部错误");
    }

    private Map<String, Object> buildErrorResponse(HttpStatus status, String error, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", error);
        errorResponse.put("message", message);
        errorResponse.put("tenantId", TenantContextHolder.getTenantId());
        return errorResponse;
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String error, String message) {
        return new ResponseEntity<>(buildErrorResponse(status, error, message), status);
    }
}