package com.example.functiondemand.category.service;

import com.example.functiondemand.category.dto.*;
import com.example.functiondemand.common.enums.CategoryType;

import java.util.List;

public interface CategoryService {

    CategoryDTO create(CategoryCreateDTO dto);

    CategoryDTO update(String id, CategoryUpdateDTO dto);

    void delete(String id);

    CategoryDTO findById(String id);

    List<CategoryDTO> findByType(CategoryType type);

    List<CategoryDTO> batchCreate(List<CategoryCreateDTO> dtos);

    void batchUpdate(List<CategoryUpdateDTO> dtos);

    void batchDelete(List<String> ids);

    List<CategoryTreeDTO> getCategoryTree(CategoryType type, String parentId);

    List<CategoryDTO> getChildren(String parentId);

    String generatePath(String parentId);

    void validateLevel(String parentId);

    List<CategoryDTO> getAncestors(String id);

    void updateSortOrder(String id, Integer sortOrder);

    List<CategoryDTO> findByTypeAndKeyword(CategoryType type, String keyword);
}