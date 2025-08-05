package com.example.functiondemand.function.processor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.common.fileio.processor.DataProcessor;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.category.repository.CategoryRepository;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.FunctionStatus;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.util.TreePathUtil;
import com.example.functiondemand.function.entity.FunctionPoint;
import com.example.functiondemand.function.repository.FunctionPointRepository;
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
public class FunctionPointDataProcessor implements DataProcessor<FunctionPoint> {

    private final FunctionPointRepository functionPointRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public String getProcessorName() {
        return "functionPoint";
    }

    @Override
    public Class<FunctionPoint> getEntityClass() {
        return FunctionPoint.class;
    }

    @Override
    public List<FunctionPoint> parseFileData(String filePath, String fileType) {
        log.info("解析功能点文件: {} - {}", filePath, fileType);
        
        List<FunctionPoint> functionPoints = new ArrayList<>();
        
        if ("excel".equalsIgnoreCase(fileType)) {
            EasyExcel.read(filePath, FunctionPointExcelData.class, new ReadListener<FunctionPointExcelData>() {
                @Override
                public void invoke(FunctionPointExcelData data, AnalysisContext context) {
                    FunctionPoint functionPoint = convertFromExcelData(data);
                    if (functionPoint != null) {
                        functionPoints.add(functionPoint);
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info("功能点Excel解析完成，共{}条记录", functionPoints.size());
                }
            }).sheet().doRead();
        }
        
        return functionPoints;
    }

    @Override
    public void saveBatch(List<FunctionPoint> entities) {
        log.info("批量保存功能点，数量: {}", entities.size());
        
        for (FunctionPoint functionPoint : entities) {
            try {
                // 处理分类目录
                if (StrUtil.isNotBlank(functionPoint.getCategoryId())) {
                    handleCategoryCreation(functionPoint.getCategoryId());
                }
                
                // 处理父子关系和路径
                handleParentChildRelation(functionPoint);
                
                // 设置默认值
                if (functionPoint.getId() == null) {
                    functionPoint.setId(IdGenerator.generateFunctionId());
                }
                if (functionPoint.getCreatedTime() == null) {
                    functionPoint.setCreatedTime(LocalDateTime.now());
                }
                if (functionPoint.getUpdatedTime() == null) {
                    functionPoint.setUpdatedTime(LocalDateTime.now());
                }
                
                functionPointRepository.save(functionPoint);
                
            } catch (Exception e) {
                log.error("保存功能点失败: {}", functionPoint.getName(), e);
            }
        }
        
        log.info("功能点批量保存完成");
    }

    @Override
    public List<FunctionPoint> queryExportData(Map<String, Object> params, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        // 根据参数构建查询条件
        if (params.containsKey("categoryId")) {
            String categoryId = (String) params.get("categoryId");
            return functionPointRepository.findByCategoryIdOrderByLevel(categoryId);
        }
        
        if (params.containsKey("module")) {
            String module = (String) params.get("module");
            return functionPointRepository.findByModule(module);
        }
        
        if (params.containsKey("status")) {
            FunctionStatus status = FunctionStatus.valueOf((String) params.get("status"));
            return functionPointRepository.findByStatus(status);
        }
        
        return functionPointRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Map<String, Object>> convertToExportFormat(List<FunctionPoint> entities) {
        return entities.stream().map(functionPoint -> {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("功能点ID", functionPoint.getId());
            data.put("父功能点ID", functionPoint.getParentId());
            data.put("分类目录", getCategoryPath(functionPoint.getCategoryId()));
            data.put("功能点名称", functionPoint.getName());
            data.put("功能点描述", functionPoint.getDescription());
            data.put("所属模块", functionPoint.getModule());
            data.put("复杂度", functionPoint.getComplexity() != null ? functionPoint.getComplexity().getDescription() : "");
            data.put("状态", functionPoint.getStatus() != null ? functionPoint.getStatus().getDescription() : "");
            data.put("负责人", functionPoint.getOwner());
            data.put("层级", functionPoint.getLevel());
            data.put("路径", functionPoint.getPath());
            data.put("创建时间", functionPoint.getCreatedTime());
            data.put("更新时间", functionPoint.getUpdatedTime());
            data.put("创建人", functionPoint.getCreatedBy());
            data.put("更新人", functionPoint.getUpdatedBy());
            return data;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getExportHeaders() {
        return Arrays.asList(
            "功能点ID", "父功能点ID", "分类目录", "功能点名称", "功能点描述", 
            "所属模块", "复杂度", "状态", "负责人", "层级", "路径",
            "创建时间", "更新时间", "创建人", "更新人"
        );
    }

    @Override
    public long count(Map<String, Object> params) {
        if (params.containsKey("categoryId")) {
            String categoryId = (String) params.get("categoryId");
            return functionPointRepository.findByCategoryIdOrderByLevel(categoryId).size();
        }
        
        if (params.containsKey("module")) {
            String module = (String) params.get("module");
            return functionPointRepository.findByModule(module).size();
        }
        
        if (params.containsKey("status")) {
            FunctionStatus status = FunctionStatus.valueOf((String) params.get("status"));
            return functionPointRepository.findByStatus(status).size();
        }
        
        return functionPointRepository.count();
    }

    @Override
    public String validate(FunctionPoint entity) {
        if (StrUtil.isBlank(entity.getName())) {
            return "功能点名称不能为空";
        }
        
        if (entity.getName().length() > 200) {
            return "功能点名称长度不能超过200字符";
        }
        
        if (StrUtil.isNotBlank(entity.getParentId())) {
            if (!functionPointRepository.existsById(entity.getParentId())) {
                return "父功能点不存在: " + entity.getParentId();
            }
        }
        
        return null;
    }

    @Override
    public Map<String, String> getFieldMapping() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("功能点ID", "id");
        mapping.put("父功能点ID", "parentId");
        mapping.put("分类目录", "categoryPath");
        mapping.put("功能点名称", "name");
        mapping.put("功能点描述", "description");
        mapping.put("所属模块", "module");
        mapping.put("复杂度", "complexity");
        mapping.put("状态", "status");
        mapping.put("负责人", "owner");
        mapping.put("创建人", "createdBy");
        mapping.put("更新人", "updatedBy");
        return mapping;
    }

    private FunctionPoint convertFromExcelData(FunctionPointExcelData data) {
        try {
            FunctionPoint functionPoint = new FunctionPoint();
            
            if (StrUtil.isNotBlank(data.getId())) {
                functionPoint.setId(data.getId());
            }
            
            functionPoint.setParentId(data.getParentId());
            functionPoint.setName(data.getName());
            functionPoint.setDescription(data.getDescription());
            functionPoint.setModule(data.getModule());
            functionPoint.setOwner(data.getOwner());
            functionPoint.setCreatedBy(data.getCreatedBy());
            functionPoint.setUpdatedBy(data.getUpdatedBy());
            
            // 处理复杂度
            if (StrUtil.isNotBlank(data.getComplexity())) {
                try {
                    functionPoint.setComplexity(FunctionComplexity.valueOf(data.getComplexity().toUpperCase()));
                } catch (Exception e) {
                    functionPoint.setComplexity(FunctionComplexity.MEDIUM);
                }
            }
            
            // 处理状态
            if (StrUtil.isNotBlank(data.getStatus())) {
                try {
                    functionPoint.setStatus(FunctionStatus.valueOf(data.getStatus().toUpperCase()));
                } catch (Exception e) {
                    functionPoint.setStatus(FunctionStatus.PLANNING);
                }
            }
            
            // 处理分类目录路径
            if (StrUtil.isNotBlank(data.getCategoryPath())) {
                String categoryId = handleCategoryPath(data.getCategoryPath());
                functionPoint.setCategoryId(categoryId);
            }
            
            return functionPoint;
            
        } catch (Exception e) {
            log.error("转换Excel数据失败: {}", data.getName(), e);
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
        category.setType(CategoryType.FUNCTION);
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
            Optional<Category> existingCategory = categoryRepository.findByTypeAndName(CategoryType.FUNCTION, categoryName);
            
            if (existingCategory.isPresent()) {
                currentCategoryId = existingCategory.get().getId();
            } else {
                // 创建新分类
                Category newCategory = new Category();
                newCategory.setId(IdGenerator.generateCategoryId());
                newCategory.setName(categoryName);
                newCategory.setType(CategoryType.FUNCTION);
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

    private void handleParentChildRelation(FunctionPoint functionPoint) {
        if (StrUtil.isNotBlank(functionPoint.getParentId())) {
            Optional<FunctionPoint> parent = functionPointRepository.findById(functionPoint.getParentId());
            if (parent.isPresent()) {
                functionPoint.setLevel(parent.get().getLevel() + 1);
                functionPoint.setPath(TreePathUtil.generatePath(parent.get().getPath(), functionPoint.getId()));
            } else {
                functionPoint.setLevel(1);
                functionPoint.setPath(TreePathUtil.generatePath(null, functionPoint.getId()));
            }
        } else {
            functionPoint.setLevel(1);
            functionPoint.setPath(TreePathUtil.generatePath(null, functionPoint.getId()));
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