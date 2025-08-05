package com.example.functiondemand.category.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.functiondemand.category.dto.*;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.category.repository.CategoryRepository;
import com.example.functiondemand.category.service.CategoryService;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.common.exception.CategoryNotFoundException;
import com.example.functiondemand.common.exception.CircularReferenceException;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.util.TreePathUtil;
import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
@TenantSwitch
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO create(CategoryCreateDTO dto) {
        String tenantId = TenantContextHolder.getTenantId();
        log.debug("创建分类目录: {} - {} - 租户: {}", dto.getType(), dto.getName(), tenantId);
        
        // 检查同类型下名称是否重复
        if (categoryRepository.existsByTypeAndName(dto.getType(), dto.getName())) {
            throw new IllegalArgumentException("同类型下分类名称已存在: " + dto.getName());
        }
        
        Category category = new Category();
        category.setId(IdGenerator.generateCategoryId());
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setDescription(dto.getDescription());
        category.setSortOrder(dto.getSortOrder());
        category.setCreatedBy(dto.getCreatedBy());
        category.setUpdatedBy(dto.getCreatedBy());

        if (StrUtil.isNotBlank(dto.getParentId())) {
            Category parent = categoryRepository.findById(dto.getParentId())
                .orElseThrow(() -> new CategoryNotFoundException("父分类不存在: " + dto.getParentId()));
            
            // 验证父分类类型一致
            if (!parent.getType().equals(dto.getType())) {
                throw new IllegalArgumentException("父分类类型不一致");
            }
            
            validateCircularReference(dto.getParentId(), category.getId());
            
            category.setParentId(dto.getParentId());
            category.setLevel(parent.getLevel() + 1);
            category.setPath(TreePathUtil.generatePath(parent.getPath(), category.getId()));
            
            TreePathUtil.validateLevel(parent.getPath());
        } else {
            category.setLevel(1);
            category.setPath(TreePathUtil.generatePath(null, category.getId()));
        }

        category = categoryRepository.save(category);
        log.info("分类目录创建成功: {}", category.getId());
        
        return convertToDTO(category);
    }

    @Override
    public CategoryDTO update(String id, CategoryUpdateDTO dto) {
        log.debug("更新分类目录: {}", id);
        
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("分类目录不存在: " + id));

        // 检查同类型下名称是否重复（排除自己）
        Category existingCategory = categoryRepository.findByTypeAndName(dto.getType(), dto.getName())
            .filter(c -> !c.getId().equals(id))
            .orElse(null);
        if (existingCategory != null) {
            throw new IllegalArgumentException("同类型下分类名称已存在: " + dto.getName());
        }

        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setDescription(dto.getDescription());
        category.setSortOrder(dto.getSortOrder());
        category.setUpdatedBy(dto.getUpdatedBy());

        if (StrUtil.isNotBlank(dto.getParentId()) && !dto.getParentId().equals(category.getParentId())) {
            Category newParent = categoryRepository.findById(dto.getParentId())
                .orElseThrow(() -> new CategoryNotFoundException("父分类不存在: " + dto.getParentId()));
            
            if (!newParent.getType().equals(dto.getType())) {
                throw new IllegalArgumentException("父分类类型不一致");
            }
            
            validateCircularReference(dto.getParentId(), id);
            updateParentAndPath(category, dto.getParentId());
        }

        category = categoryRepository.save(category);
        log.info("分类目录更新成功: {}", id);
        
        return convertToDTO(category);
    }

    @Override
    public void delete(String id) {
        log.debug("删除分类目录: {}", id);
        
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("分类目录不存在: " + id);
        }

        if (categoryRepository.existsByParentId(id)) {
            throw new IllegalArgumentException("存在子分类，无法删除");
        }

        categoryRepository.deleteById(id);
        log.info("分类目录删除成功: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findById(String id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("分类目录不存在: " + id));
        return convertToDTO(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findByType(CategoryType type) {
        List<Category> categories = categoryRepository.findByTypeOrderByLevelAndSort(type);
        return categories.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> batchCreate(List<CategoryCreateDTO> dtos) {
        log.debug("批量创建分类目录，数量: {}", dtos.size());
        
        List<CategoryDTO> results = new ArrayList<>();
        for (CategoryCreateDTO dto : dtos) {
            results.add(create(dto));
        }
        
        log.info("批量创建分类目录完成，数量: {}", results.size());
        return results;
    }

    @Override
    public void batchUpdate(List<CategoryUpdateDTO> dtos) {
        log.debug("批量更新分类目录，数量: {}", dtos.size());
        
        for (CategoryUpdateDTO dto : dtos) {
            update(dto.getId(), dto);
        }
        
        log.info("批量更新分类目录完成，数量: {}", dtos.size());
    }

    @Override
    public void batchDelete(List<String> ids) {
        log.debug("批量删除分类目录，数量: {}", ids.size());
        
        for (String id : ids) {
            delete(id);
        }
        
        log.info("批量删除分类目录完成，数量: {}", ids.size());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryTreeDTO> getCategoryTree(CategoryType type, String parentId) {
        List<Category> categories;
        if (StrUtil.isBlank(parentId)) {
            categories = categoryRepository.findByTypeAndParentIdIsNull(type);
        } else {
            categories = categoryRepository.findByTypeAndParentIdOrderBySortOrder(type, parentId);
        }

        return categories.stream()
            .map(this::convertToTreeDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getChildren(String parentId) {
        List<Category> children = categoryRepository.findChildrenByParentIdOrderBySortOrder(parentId);
        return children.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public String generatePath(String parentId) {
        if (StrUtil.isBlank(parentId)) {
            return "/";
        }
        
        Category parent = categoryRepository.findById(parentId)
            .orElseThrow(() -> new CategoryNotFoundException("父分类不存在: " + parentId));
        
        return TreePathUtil.generatePath(parent.getPath(), "");
    }

    @Override
    @Transactional(readOnly = true)
    public void validateLevel(String parentId) {
        if (StrUtil.isBlank(parentId)) {
            return;
        }
        
        Category parent = categoryRepository.findById(parentId)
            .orElseThrow(() -> new CategoryNotFoundException("父分类不存在: " + parentId));
        
        TreePathUtil.validateLevel(parent.getPath());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getAncestors(String id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("分类目录不存在: " + id));

        List<CategoryDTO> ancestors = new ArrayList<>();
        String currentParentId = category.getParentId();
        
        while (StrUtil.isNotBlank(currentParentId)) {
            Category parent = categoryRepository.findById(currentParentId)
                .orElse(null);
            if (parent == null) {
                break;
            }
            ancestors.add(0, convertToDTO(parent));
            currentParentId = parent.getParentId();
        }
        
        return ancestors;
    }

    @Override
    public void updateSortOrder(String id, Integer sortOrder) {
        log.debug("更新分类排序: {} -> {}", id, sortOrder);
        
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("分类目录不存在: " + id));
        
        category.setSortOrder(sortOrder);
        categoryRepository.save(category);
        
        log.info("分类排序更新成功: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findByTypeAndKeyword(CategoryType type, String keyword) {
        List<Category> categories = categoryRepository.findByTypeAndNameContaining(type, keyword);
        return categories.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private void validateCircularReference(String parentId, String childId) {
        if (parentId.equals(childId)) {
            throw new CircularReferenceException("不能将自己设置为父节点");
        }

        Category parent = categoryRepository.findById(parentId).orElse(null);
        if (parent != null && TreePathUtil.isAncestor("/" + childId, parent.getPath())) {
            throw new CircularReferenceException("不能将子节点设置为父节点");
        }
    }

    private void updateParentAndPath(Category category, String newParentId) {
        if (StrUtil.isNotBlank(newParentId)) {
            Category newParent = categoryRepository.findById(newParentId)
                .orElseThrow(() -> new CategoryNotFoundException("父分类不存在: " + newParentId));
            
            TreePathUtil.validateLevel(newParent.getPath());
            
            category.setParentId(newParentId);
            category.setLevel(newParent.getLevel() + 1);
            category.setPath(TreePathUtil.generatePath(newParent.getPath(), category.getId()));
        } else {
            category.setParentId(null);
            category.setLevel(1);
            category.setPath(TreePathUtil.generatePath(null, category.getId()));
        }

        updateChildrenPaths(category);
    }

    private void updateChildrenPaths(Category parent) {
        List<Category> children = categoryRepository.findByParentId(parent.getId());
        for (Category child : children) {
            child.setLevel(parent.getLevel() + 1);
            child.setPath(TreePathUtil.generatePath(parent.getPath(), child.getId()));
            categoryRepository.save(child);
            updateChildrenPaths(child);
        }
    }

    private CategoryDTO convertToDTO(Category category) {
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

    private CategoryTreeDTO convertToTreeDTO(Category category) {
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

        List<Category> children = categoryRepository.findByTypeAndParentIdOrderBySortOrder(category.getType(), category.getId());
        dto.setChildren(children.stream()
            .map(this::convertToTreeDTO)
            .collect(Collectors.toList()));

        return dto;
    }
}