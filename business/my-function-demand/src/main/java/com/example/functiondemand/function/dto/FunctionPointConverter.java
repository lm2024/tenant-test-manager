package com.example.functiondemand.function.dto;

import com.example.functiondemand.function.entity.FunctionPoint;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FunctionPointConverter {
    
    /**
     * 实体转DTO
     */
    public FunctionPointDTO toDTO(FunctionPoint functionPoint) {
        if (functionPoint == null) {
            return null;
        }
        
        FunctionPointDTO dto = new FunctionPointDTO();
        dto.setId(functionPoint.getId());
        dto.setParentId(functionPoint.getParentId());
        dto.setCategoryId(functionPoint.getCategoryId());
        dto.setName(functionPoint.getName());
        dto.setDescription(functionPoint.getDescription());
        dto.setModule(functionPoint.getModule());
        dto.setComplexity(functionPoint.getComplexity());
        dto.setStatus(functionPoint.getStatus());
        dto.setOwner(functionPoint.getOwner());
        dto.setLevel(functionPoint.getLevel());
        dto.setPath(functionPoint.getPath());
        dto.setCreatedTime(functionPoint.getCreatedTime());
        dto.setUpdatedTime(functionPoint.getUpdatedTime());
        dto.setCreatedBy(functionPoint.getCreatedBy());
        dto.setUpdatedBy(functionPoint.getUpdatedBy());
        
        return dto;
    }
    
    /**
     * 实体转树形DTO
     */
    public FunctionPointTreeDTO toTreeDTO(FunctionPoint functionPoint) {
        if (functionPoint == null) {
            return null;
        }
        
        FunctionPointTreeDTO dto = new FunctionPointTreeDTO();
        dto.setId(functionPoint.getId());
        dto.setParentId(functionPoint.getParentId());
        dto.setCategoryId(functionPoint.getCategoryId());
        dto.setName(functionPoint.getName());
        dto.setDescription(functionPoint.getDescription());
        dto.setModule(functionPoint.getModule());
        dto.setComplexity(functionPoint.getComplexity());
        dto.setStatus(functionPoint.getStatus());
        dto.setOwner(functionPoint.getOwner());
        dto.setLevel(functionPoint.getLevel());
        dto.setPath(functionPoint.getPath());
        dto.setCreatedTime(functionPoint.getCreatedTime());
        dto.setUpdatedTime(functionPoint.getUpdatedTime());
        dto.setCreatedBy(functionPoint.getCreatedBy());
        dto.setUpdatedBy(functionPoint.getUpdatedBy());
        
        return dto;
    }
    
    /**
     * 创建DTO转实体
     */
    public FunctionPoint fromCreateDTO(FunctionPointCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setParentId(dto.getParentId());
        functionPoint.setCategoryId(dto.getCategoryId());
        functionPoint.setName(dto.getName());
        functionPoint.setDescription(dto.getDescription());
        functionPoint.setModule(dto.getModule());
        functionPoint.setComplexity(dto.getComplexity());
        functionPoint.setStatus(dto.getStatus());
        functionPoint.setOwner(dto.getOwner());
        functionPoint.setCreatedBy(dto.getCreatedBy());
        functionPoint.setUpdatedBy(dto.getCreatedBy());
        
        return functionPoint;
    }
    
    /**
     * 更新DTO应用到实体
     */
    public void applyUpdateDTO(FunctionPointUpdateDTO dto, FunctionPoint functionPoint) {
        if (dto == null || functionPoint == null) {
            return;
        }
        
        if (dto.getParentId() != null) {
            functionPoint.setParentId(dto.getParentId());
        }
        if (dto.getCategoryId() != null) {
            functionPoint.setCategoryId(dto.getCategoryId());
        }
        if (dto.getName() != null) {
            functionPoint.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            functionPoint.setDescription(dto.getDescription());
        }
        if (dto.getModule() != null) {
            functionPoint.setModule(dto.getModule());
        }
        if (dto.getComplexity() != null) {
            functionPoint.setComplexity(dto.getComplexity());
        }
        if (dto.getStatus() != null) {
            functionPoint.setStatus(dto.getStatus());
        }
        if (dto.getOwner() != null) {
            functionPoint.setOwner(dto.getOwner());
        }
        if (dto.getUpdatedBy() != null) {
            functionPoint.setUpdatedBy(dto.getUpdatedBy());
        }
    }
    
    /**
     * 批量转换
     */
    public List<FunctionPointDTO> toDTOList(List<FunctionPoint> functionPoints) {
        if (functionPoints == null) {
            return null;
        }
        
        return functionPoints.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * 批量转换为树形DTO
     */
    public List<FunctionPointTreeDTO> toTreeDTOList(List<FunctionPoint> functionPoints) {
        if (functionPoints == null) {
            return null;
        }
        
        return functionPoints.stream()
            .map(this::toTreeDTO)
            .collect(Collectors.toList());
    }
}