package com.example.functiondemand.category.processor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.common.fileio.processor.DataProcessor;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.category.repository.CategoryRepository;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.util.TreePathUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryDataProcessor implements DataProcessor<Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public String getProcessorName() {
        return "category";
    }

    @Override
    public Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    public List<Category> parseFileData(String filePath, String fileType) {
        log.info("解析分类目录文件: {} - {}", filePath, fileType);
        
        List<Category> categories = new ArrayList<>();
        
        if ("excel".equalsIgnoreCase(fileType)) {
            EasyExcel.read(filePath, CategoryExcelData.class, new ReadListener<CategoryExcelData>() {
                @Override
                public void invoke(CategoryExcelData data, AnalysisContext context) {
                    Category category = convertFromExcelData(data);
                    if (category != null) {
                        categories.add(category);
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info("分类目录Excel解析完成，共{}条记录", categories.size());
                }
            }).sheet().doRead();
        }
        
        return categories;
    }

    @Override
    public void saveBatch(List<Category> entities) {
        log.info("批量保存分类目录，数量: {}", entities.size());
        
        // 按层级排序，确保父分类先创建
        entities.sort(Comparator.comparing(Category::getLevel, Comparator.nullsLast(Integer::compareTo)));
        
        for (Category category : entities) {
            try {
                // 处理父子关系和路径
                handleParentChildRelation(category);
                
                // 设置默认值
                if (category.getId() == null) {
                    category.setId(IdGenerator.generateCategoryId());
                }
                if (category.getCreatedTime() == null) {
                    category.setCreatedTime(LocalDateTime.now());
                }
                if (category.getUpdatedTime() == null) {
                    category.setUpdatedTime(LocalDateTime.now());
                }
                if (category.getSortOrder() == null) {
                    category.setSortOrder(0);
                }
                
                categoryRepository.save(category);
                
            } catch (Exception e) {
                log.error("保存分类目录失败: {}", category.getName(), e);
            }
        }
        
        log.info("分类目录批量保存完成");
    }

    @Override
    public List<Category> queryExportData(Map<String, Object> params, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        // 根据参数构建查询条件
        if (params.containsKey("type")) {
            CategoryType type = CategoryType.valueOf((String) params.get("type"));
            return categoryRepository.findByTypeOrderByLevelAndSort(type);
        }
        
        if (params.containsKey("parentId")) {
            String parentId = (String) params.get("parentId");
            if (StrUtil.isBlank(parentId)) {
                return categoryRepository.findByParentIdIsNull();
            } else {
                return categoryRepository.findChildrenByParentIdOrderBySortOrder(parentId);
            }
        }
        
        return categoryRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Map<String, Object>> convertToExportFormat(List<Category> entities) {
        return entities.stream().map(category -> {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("分类ID", category.getId());
            data.put("父分类ID", category.getParentId());
            data.put("分类名称", category.getName());
            data.put("分类类型", category.getType() != null ? category.getType().getDescription() : "");
            data.put("分类描述", category.getDescription());
            data.put("层级", category.getLevel());
            data.put("路径", category.getPath());
            data.put("排序", category.getSortOrder());
            data.put("完整路径", getFullPath(category));
            data.put("创建时间", category.getCreatedTime());
            data.put("更新时间", category.getUpdatedTime());
            data.put("创建人", category.getCreatedBy());
            data.put("更新人", category.getUpdatedBy());
            return data;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getExportHeaders() {
        return Arrays.asList(
            "分类ID", "父分类ID", "分类名称", "分类类型", "分类描述", 
            "层级", "路径", "排序", "完整路径",
            "创建时间", "更新时间", "创建人", "更新人"
        );
    }

    @Override
    public long count(Map<String, Object> params) {
        if (params.containsKey("type")) {
            CategoryType type = CategoryType.valueOf((String) params.get("type"));
            return categoryRepository.findByType(type).size();
        }
        
        if (params.containsKey("parentId")) {
            String parentId = (String) params.get("parentId");
            if (StrUtil.isBlank(parentId)) {
                return categoryRepository.findByParentIdIsNull().size();
            } else {
                return categoryRepository.findByParentId(parentId).size();
            }
        }
        
        return categoryRepository.count();
    }

    @Override
    public String validate(Category entity) {
        if (StrUtil.isBlank(entity.getName())) {
            return "分类名称不能为空";
        }
        
        if (entity.getName().length() > 100) {
            return "分类名称长度不能超过100字符";
        }
        
        if (entity.getType() == null) {
            return "分类类型不能为空";
        }
        
        if (StrUtil.isNotBlank(entity.getParentId())) {
            Optional<Category> parent = categoryRepository.findById(entity.getParentId());
            if (!parent.isPresent()) {
                return "父分类不存在: " + entity.getParentId();
            }
            
            if (!parent.get().getType().equals(entity.getType())) {
                return "父分类类型不一致";
            }
        }
        
        // 检查同类型下名称是否重复
        if (categoryRepository.existsByTypeAndName(entity.getType(), entity.getName())) {
            return "同类型下分类名称已存在: " + entity.getName();
        }
        
        return null;
    }

    @Override
    public Map<String, String> getFieldMapping() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("分类ID", "id");
        mapping.put("父分类ID", "parentId");
        mapping.put("分类名称", "name");
        mapping.put("分类类型", "type");
        mapping.put("分类描述", "description");
        mapping.put("排序", "sortOrder");
        mapping.put("完整路径", "fullPath");
        mapping.put("创建人", "createdBy");
        mapping.put("更新人", "updatedBy");
        return mapping;
    }

    private Category convertFromExcelData(CategoryExcelData data) {
        try {
            Category category = new Category();
            
            if (StrUtil.isNotBlank(data.getId())) {
                category.setId(data.getId());
            }
            
            category.setParentId(data.getParentId());
            category.setName(data.getName());
            category.setDescription(data.getDescription());
            category.setSortOrder(data.getSortOrder());
            category.setCreatedBy(data.getCreatedBy());
            category.setUpdatedBy(data.getUpdatedBy());
            
            // 处理分类类型
            if (StrUtil.isNotBlank(data.getType())) {
                try {
                    category.setType(CategoryType.valueOf(data.getType().toUpperCase()));
                } catch (Exception e) {
                    category.setType(CategoryType.REQUIREMENT);
                }
            }
            
            // 处理完整路径（用于创建层级结构）
            if (StrUtil.isNotBlank(data.getFullPath())) {
                handleFullPath(category, data.getFullPath());
            }
            
            return category;
            
        } catch (Exception e) {
            log.error("转换Excel数据失败: {}", data.getName(), e);
            return null;
        }
    }

    private void handleFullPath(Category category, String fullPath) {
        if (StrUtil.isBlank(fullPath)) {
            return;
        }
        
        String[] pathParts = fullPath.split("/");
        if (pathParts.length <= 1) {
            return;
        }
        
        // 找到或创建父分类
        String parentId = null;
        for (int i = 0; i < pathParts.length - 1; i++) {
            String categoryName = pathParts[i].trim();
            if (StrUtil.isBlank(categoryName)) {
                continue;
            }
            
            Optional<Category> existingCategory = categoryRepository.findByTypeAndName(category.getType(), categoryName);
            
            if (existingCategory.isPresent()) {
                parentId = existingCategory.get().getId();
            } else {
                // 创建父分类
                Category parentCategory = new Category();
                parentCategory.setId(IdGenerator.generateCategoryId());
                parentCategory.setName(categoryName);
                parentCategory.setType(category.getType());
                parentCategory.setParentId(parentId);
                parentCategory.setLevel(i + 1);
                parentCategory.setPath(TreePathUtil.generatePath(
                    parentId != null ? categoryRepository.findById(parentId).map(Category::getPath).orElse(null) : null,
                    parentCategory.getId()
                ));
                parentCategory.setCreatedTime(LocalDateTime.now());
                parentCategory.setUpdatedTime(LocalDateTime.now());
                parentCategory.setSortOrder(0);
                
                categoryRepository.save(parentCategory);
                parentId = parentCategory.getId();
            }
        }
        
        category.setParentId(parentId);
    }

    private void handleParentChildRelation(Category category) {
        if (StrUtil.isNotBlank(category.getParentId())) {
            Optional<Category> parent = categoryRepository.findById(category.getParentId());
            if (parent.isPresent()) {
                category.setLevel(parent.get().getLevel() + 1);
                category.setPath(TreePathUtil.generatePath(parent.get().getPath(), category.getId()));
            } else {
                category.setLevel(1);
                category.setPath(TreePathUtil.generatePath(null, category.getId()));
            }
        } else {
            category.setLevel(1);
            category.setPath(TreePathUtil.generatePath(null, category.getId()));
        }
    }

    private String getFullPath(Category category) {
        List<String> pathParts = new ArrayList<>();
        Category current = category;
        
        while (current != null) {
            pathParts.add(0, current.getName());
            if (StrUtil.isBlank(current.getParentId())) {
                break;
            }
            current = categoryRepository.findById(current.getParentId()).orElse(null);
        }
        
        return String.join("/", pathParts);
    }
}