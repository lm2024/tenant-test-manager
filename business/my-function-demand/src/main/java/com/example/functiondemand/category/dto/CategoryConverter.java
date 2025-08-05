package com.example.functiondemand.category.dto;

import com.example.functiondemand.category.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {
    
    /**
     * 实体转DTO
     */
    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setParentId(category.getParentId());
        dto.setName(category.getName());
        dto.setType(category.getType());
        dto.setDescription(category.getDescription());
        dto.setLevel(category.getLevel());
        dto.setPath(category.getPath());
        dto.setSortOrder(category.getSortOrder());
        dto.setCreatedTime(category.getCreatedTime());
        dto.setUpdatedTime(category.getUpdatedTime());
        dto.setCreatedBy(category.getCreatedBy());
        dto.setUpdatedBy(category.getUpdatedBy());
        
        return dto;
    }
    
    /**
     * 实体转树形DTO
     */
    public CategoryTreeDTO toTreeDTO(Category category) {
        if (category == null) {
            return null;
        }
        
        CategoryTreeDTO dto = new CategoryTreeDTO();
        dto.setId(category.getId());
        dto.setParentId(category.getParentId());
        dto.setName(category.getName());
        dto.setType(category.getType());
        dto.setDescription(category.getDescription());
        dto.setLevel(category.getLevel());
        dto.setPath(category.getPath());
        dto.setSortOrder(category.getSortOrder());
        dto.setCreatedTime(category.getCreatedTime());
        dto.setUpdatedTime(category.getUpdatedTime());
        dto.setCreatedBy(category.getCreatedBy());
        dto.setUpdatedBy(category.getUpdatedBy());
        
        return dto;
    }
    
    /**
     * 创建DTO转实体
     */
    public Category fromCreateDTO(CategoryCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Category category = new Category();
        category.setParentId(dto.getParentId());
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setDescription(dto.getDescription());
        category.setSortOrder(dto.getSortOrder());
        category.setCreatedBy(dto.getCreatedBy());
        category.setUpdatedBy(dto.getCreatedBy());
        
        return category;
    }
    
    /**
     * 更新DTO应用到实体
     */
    public void applyUpdateDTO(CategoryUpdateDTO dto, Category category) {
        if (dto == null || category == null) {
            return;
        }
        
        if (dto.getParentId() != null) {
            category.setParentId(dto.getParentId());
        }
        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getType() != null) {
            category.setType(dto.getType());
        }
        if (dto.getDescription() != null) {
            category.setDescription(dto.getDescription());
        }
        if (dto.getSortOrder() != null) {
            category.setSortOrder(dto.getSortOrder());
        }
        if (dto.getUpdatedBy() != null) {
            category.setUpdatedBy(dto.getUpdatedBy());
        }
    }
    
    /**
     * 批量转换
     */
    public List<CategoryDTO> toDTOList(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        
        return categories.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * 批量转换为树形DTO
     */
    public List<CategoryTreeDTO> toTreeDTOList(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        
        return categories.stream()
            .map(this::toTreeDTO)
            .collect(Collectors.toList());
    }
}