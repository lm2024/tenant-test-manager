package com.example.functiondemand.requirement.processor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.common.fileio.processor.DataProcessor;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.category.repository.CategoryRepository;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.enums.RequirementStatus;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.util.TreePathUtil;
import com.example.functiondemand.requirement.entity.Requirement;
import com.example.functiondemand.requirement.repository.RequirementRepository;
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
public class RequirementDataProcessor implements DataProcessor<Requirement> {

    private final RequirementRepository requirementRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public String getProcessorName() {
        return "requirement";
    }

    @Override
    public Class<Requirement> getEntityClass() {
        return Requirement.class;
    }

    @Override
    public List<Requirement> parseFileData(String filePath, String fileType) {
        log.info("解析需求文件: {} - {}", filePath, fileType);
        
        List<Requirement> requirements = new ArrayList<>();
        
        if ("excel".equalsIgnoreCase(fileType)) {
            EasyExcel.read(filePath, RequirementExcelData.class, new ReadListener<RequirementExcelData>() {
                @Override
                public void invoke(RequirementExcelData data, AnalysisContext context) {
                    Requirement requirement = convertFromExcelData(data);
                    if (requirement != null) {
                        requirements.add(requirement);
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info("需求Excel解析完成，共{}条记录", requirements.size());
                }
            }).sheet().doRead();
        }
        
        return requirements;
    }

    @Override
    public void saveBatch(List<Requirement> entities) {
        log.info("批量保存需求，数量: {} - 租户: {}", entities.size(), com.tenant.routing.core.TenantContextHolder.getTenantId());
        
        for (Requirement requirement : entities) {
            try {
                // 处理分类目录
                if (StrUtil.isNotBlank(requirement.getCategoryId())) {
                    handleCategoryCreation(requirement.getCategoryId());
                }
                
                // 处理父子关系和路径
                handleParentChildRelation(requirement);
                
                // 设置默认值
                if (requirement.getId() == null) {
                    requirement.setId(IdGenerator.generateRequirementId());
                }
                if (requirement.getCreatedTime() == null) {
                    requirement.setCreatedTime(LocalDateTime.now());
                }
                if (requirement.getUpdatedTime() == null) {
                    requirement.setUpdatedTime(LocalDateTime.now());
                }
                
                requirementRepository.save(requirement);
                
            } catch (Exception e) {
                log.error("保存需求失败: {}", requirement.getTitle(), e);
            }
        }
        
        log.info("需求批量保存完成");
    }

    @Override
    public List<Requirement> queryExportData(Map<String, Object> params, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        // 根据参数构建查询条件
        if (params.containsKey("categoryId")) {
            String categoryId = (String) params.get("categoryId");
            return requirementRepository.findByCategoryIdOrderByLevel(categoryId);
        }
        
        if (params.containsKey("status")) {
            RequirementStatus status = RequirementStatus.valueOf((String) params.get("status"));
            return requirementRepository.findByStatus(status);
        }
        
        return requirementRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Map<String, Object>> convertToExportFormat(List<Requirement> entities) {
        return entities.stream().map(requirement -> {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("需求ID", requirement.getId());
            data.put("父需求ID", requirement.getParentId());
            data.put("分类目录", getCategoryPath(requirement.getCategoryId()));
            data.put("需求标题", requirement.getTitle());
            data.put("需求描述", requirement.getDescription());
            data.put("优先级", requirement.getPriority() != null ? requirement.getPriority().getDescription() : "");
            data.put("状态", requirement.getStatus() != null ? requirement.getStatus().getDescription() : "");
            data.put("需求来源", requirement.getSource());
            data.put("负责人", requirement.getAssignee());
            data.put("层级", requirement.getLevel());
            data.put("路径", requirement.getPath());
            data.put("创建时间", requirement.getCreatedTime());
            data.put("更新时间", requirement.getUpdatedTime());
            data.put("创建人", requirement.getCreatedBy());
            data.put("更新人", requirement.getUpdatedBy());
            return data;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getExportHeaders() {
        return Arrays.asList(
            "需求ID", "父需求ID", "分类目录", "需求标题", "需求描述", 
            "优先级", "状态", "需求来源", "负责人", "层级", "路径",
            "创建时间", "更新时间", "创建人", "更新人"
        );
    }

    @Override
    public long count(Map<String, Object> params) {
        if (params.containsKey("categoryId")) {
            String categoryId = (String) params.get("categoryId");
            return requirementRepository.findByCategoryIdOrderByLevel(categoryId).size();
        }
        
        if (params.containsKey("status")) {
            RequirementStatus status = RequirementStatus.valueOf((String) params.get("status"));
            return requirementRepository.findByStatus(status).size();
        }
        
        return requirementRepository.count();
    }

    @Override
    public String validate(Requirement entity) {
        if (StrUtil.isBlank(entity.getTitle())) {
            return "需求标题不能为空";
        }
        
        if (entity.getTitle().length() > 200) {
            return "需求标题长度不能超过200字符";
        }
        
        if (StrUtil.isNotBlank(entity.getParentId())) {
            if (!requirementRepository.existsById(entity.getParentId())) {
                return "父需求不存在: " + entity.getParentId();
            }
        }
        
        return null;
    }

    @Override
    public Map<String, String> getFieldMapping() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("需求ID", "id");
        mapping.put("父需求ID", "parentId");
        mapping.put("分类目录", "categoryPath");
        mapping.put("需求标题", "title");
        mapping.put("需求描述", "description");
        mapping.put("优先级", "priority");
        mapping.put("状态", "status");
        mapping.put("需求来源", "source");
        mapping.put("负责人", "assignee");
        mapping.put("创建人", "createdBy");
        mapping.put("更新人", "updatedBy");
        return mapping;
    }

    private Requirement convertFromExcelData(RequirementExcelData data) {
        try {
            Requirement requirement = new Requirement();
            
            if (StrUtil.isNotBlank(data.getId())) {
                requirement.setId(data.getId());
            }
            
            requirement.setParentId(data.getParentId());
            requirement.setTitle(data.getTitle());
            requirement.setDescription(data.getDescription());
            requirement.setSource(data.getSource());
            requirement.setAssignee(data.getAssignee());
            requirement.setCreatedBy(data.getCreatedBy());
            requirement.setUpdatedBy(data.getUpdatedBy());
            
            // 处理优先级
            if (StrUtil.isNotBlank(data.getPriority())) {
                try {
                    requirement.setPriority(RequirementPriority.valueOf(data.getPriority().toUpperCase()));
                } catch (Exception e) {
                    requirement.setPriority(RequirementPriority.MEDIUM);
                }
            }
            
            // 处理状态
            if (StrUtil.isNotBlank(data.getStatus())) {
                try {
                    requirement.setStatus(RequirementStatus.valueOf(data.getStatus().toUpperCase()));
                } catch (Exception e) {
                    requirement.setStatus(RequirementStatus.DRAFT);
                }
            }
            
            // 处理分类目录路径
            if (StrUtil.isNotBlank(data.getCategoryPath())) {
                String categoryId = handleCategoryPath(data.getCategoryPath());
                requirement.setCategoryId(categoryId);
            }
            
            return requirement;
            
        } catch (Exception e) {
            log.error("转换Excel数据失败: {}", data.getTitle(), e);
            return null;
        }
    }

    private void handleCategoryCreation(String categoryId) {
        if (StrUtil.isBlank(categoryId) || categoryRepository.existsById(categoryId)) {
            return;
        }
        
        // 如果分类不存在，创建默认分类
        Category category = new Category();
        category.setId(categoryId);
        category.setName("默认分类");
        category.setType(CategoryType.REQUIREMENT);
        category.setLevel(1);
        category.setPath(TreePathUtil.generatePath(null, categoryId));
        category.setCreatedTime(LocalDateTime.now());
        category.setUpdatedTime(LocalDateTime.now());
        
        categoryRepository.save(category);
    }

    private String handleCategoryPath(String categoryPath) {
        if (StrUtil.isBlank(categoryPath)) {
            return null;
        }
        
        String[] pathParts = categoryPath.split("/");
        String parentId = null;
        String currentCategoryId = null;
        
        for (int i = 0; i < pathParts.length; i++) {
            String categoryName = pathParts[i].trim();
            if (StrUtil.isBlank(categoryName)) {
                continue;
            }
            
            // 查找或创建分类
            Optional<Category> existingCategory = categoryRepository.findByTypeAndName(CategoryType.REQUIREMENT, categoryName);
            
            if (existingCategory.isPresent()) {
                currentCategoryId = existingCategory.get().getId();
            } else {
                // 创建新分类
                Category newCategory = new Category();
                newCategory.setId(IdGenerator.generateCategoryId());
                newCategory.setName(categoryName);
                newCategory.setType(CategoryType.REQUIREMENT);
                newCategory.setParentId(parentId);
                newCategory.setLevel(i + 1);
                newCategory.setPath(TreePathUtil.generatePath(
                    parentId != null ? categoryRepository.findById(parentId).map(Category::getPath).orElse(null) : null,
                    newCategory.getId()
                ));
                newCategory.setCreatedTime(LocalDateTime.now());
                newCategory.setUpdatedTime(LocalDateTime.now());
                
                categoryRepository.save(newCategory);
                currentCategoryId = newCategory.getId();
            }
            
            parentId = currentCategoryId;
        }
        
        return currentCategoryId;
    }

    private void handleParentChildRelation(Requirement requirement) {
        if (StrUtil.isNotBlank(requirement.getParentId())) {
            Optional<Requirement> parent = requirementRepository.findById(requirement.getParentId());
            if (parent.isPresent()) {
                requirement.setLevel(parent.get().getLevel() + 1);
                requirement.setPath(TreePathUtil.generatePath(parent.get().getPath(), requirement.getId()));
            } else {
                requirement.setLevel(1);
                requirement.setPath(TreePathUtil.generatePath(null, requirement.getId()));
            }
        } else {
            requirement.setLevel(1);
            requirement.setPath(TreePathUtil.generatePath(null, requirement.getId()));
        }
    }

    private String getCategoryPath(String categoryId) {
        if (StrUtil.isBlank(categoryId)) {
            return "";
        }
        
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            return "";
        }
        
        List<String> pathParts = new ArrayList<>();
        Category current = category.get();
        
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