package com.example.functiondemand.function.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.functiondemand.common.exception.CircularReferenceException;
import com.example.functiondemand.common.exception.FunctionPointNotFoundException;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.util.TreePathUtil;
import com.example.functiondemand.function.dto.*;
import com.example.functiondemand.function.entity.FunctionPoint;
import com.example.functiondemand.function.repository.FunctionPointRepository;
import com.example.functiondemand.function.service.FunctionPointService;
import com.example.functiondemand.relation.entity.RequirementFunctionRelation;
import com.example.functiondemand.relation.repository.RequirementFunctionRelationRepository;
import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class FunctionPointServiceImpl implements FunctionPointService {

    private final FunctionPointRepository functionPointRepository;
    private final RequirementFunctionRelationRepository relationRepository;

    @Override
    public FunctionPointDTO create(FunctionPointCreateDTO dto) {
        String tenantId = TenantContextHolder.getTenantId();
        log.debug("创建功能点: {} - 租户: {}", dto.getName(), tenantId);
        
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setId(IdGenerator.generateFunctionId());
        functionPoint.setName(dto.getName());
        functionPoint.setDescription(dto.getDescription());
        functionPoint.setModule(dto.getModule());
        functionPoint.setComplexity(dto.getComplexity());
        functionPoint.setStatus(dto.getStatus());
        functionPoint.setOwner(dto.getOwner());
        functionPoint.setCategoryId(dto.getCategoryId());
        functionPoint.setCreatedBy(dto.getCreatedBy());
        functionPoint.setUpdatedBy(dto.getCreatedBy());

        if (StrUtil.isNotBlank(dto.getParentId())) {
            FunctionPoint parent = functionPointRepository.findById(dto.getParentId())
                .orElseThrow(() -> new FunctionPointNotFoundException("父功能点不存在: " + dto.getParentId()));
            
            validateCircularReference(dto.getParentId(), functionPoint.getId());
            
            functionPoint.setParentId(dto.getParentId());
            functionPoint.setLevel(parent.getLevel() + 1);
            functionPoint.setPath(TreePathUtil.generatePath(parent.getPath(), functionPoint.getId()));
            
            TreePathUtil.validateLevel(parent.getPath());
        } else {
            functionPoint.setLevel(1);
            functionPoint.setPath(TreePathUtil.generatePath(null, functionPoint.getId()));
        }

        functionPoint = functionPointRepository.save(functionPoint);
        log.info("功能点创建成功: {}", functionPoint.getId());
        
        return convertToDTO(functionPoint);
    }

    @Override
    public FunctionPointDTO update(String id, FunctionPointUpdateDTO dto) {
        log.debug("更新功能点: {}", id);
        
        FunctionPoint functionPoint = functionPointRepository.findById(id)
            .orElseThrow(() -> new FunctionPointNotFoundException("功能点不存在: " + id));

        functionPoint.setName(dto.getName());
        functionPoint.setDescription(dto.getDescription());
        functionPoint.setModule(dto.getModule());
        functionPoint.setComplexity(dto.getComplexity());
        functionPoint.setStatus(dto.getStatus());
        functionPoint.setOwner(dto.getOwner());
        functionPoint.setCategoryId(dto.getCategoryId());
        functionPoint.setUpdatedBy(dto.getUpdatedBy());

        if (StrUtil.isNotBlank(dto.getParentId()) && !dto.getParentId().equals(functionPoint.getParentId())) {
            validateCircularReference(dto.getParentId(), id);
            updateParentAndPath(functionPoint, dto.getParentId());
        }

        functionPoint = functionPointRepository.save(functionPoint);
        log.info("功能点更新成功: {}", id);
        
        return convertToDTO(functionPoint);
    }

    @Override
    public void delete(String id) {
        log.debug("删除功能点: {}", id);
        
        if (!functionPointRepository.existsById(id)) {
            throw new FunctionPointNotFoundException("功能点不存在: " + id);
        }

        if (functionPointRepository.existsByParentId(id)) {
            throw new IllegalArgumentException("存在子功能点，无法删除");
        }

        relationRepository.deleteByFunctionId(id);
        functionPointRepository.deleteById(id);
        
        log.info("功能点删除成功: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public FunctionPointDTO findById(String id) {
        FunctionPoint functionPoint = functionPointRepository.findById(id)
            .orElseThrow(() -> new FunctionPointNotFoundException("功能点不存在: " + id));
        return convertToDTO(functionPoint);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FunctionPointDTO> findAll(FunctionPointQueryDTO query, Pageable pageable) {
        return functionPointRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Override
    public List<FunctionPointDTO> batchCreate(List<FunctionPointCreateDTO> dtos) {
        String tenantId = TenantContextHolder.getTenantId();
        log.debug("批量创建功能点，数量: {} - 租户: {}", dtos.size(), tenantId);
        
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("批量操作需要租户上下文");
        }
        
        List<FunctionPointDTO> results = new ArrayList<>();
        for (FunctionPointCreateDTO dto : dtos) {
            if (!TenantContextHolder.hasTenant()) {
                throw new IllegalStateException("租户上下文丢失");
            }
            results.add(create(dto));
        }
        
        log.info("批量创建功能点完成，数量: {} - 租户: {}", results.size(), tenantId);
        return results;
    }

    @Override
    public void batchUpdate(List<FunctionPointUpdateDTO> dtos) {
        log.debug("批量更新功能点，数量: {}", dtos.size());
        
        for (FunctionPointUpdateDTO dto : dtos) {
            update(dto.getId(), dto);
        }
        
        log.info("批量更新功能点完成，数量: {}", dtos.size());
    }

    @Override
    public void batchDelete(List<String> ids) {
        String tenantId = TenantContextHolder.getTenantId();
        log.debug("批量删除功能点，数量: {} - 租户: {}", ids.size(), tenantId);
        
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("批量操作需要租户上下文");
        }
        
        // 批量验证所有功能点是否存在于当前租户
        List<FunctionPoint> existingFunctionPoints = functionPointRepository.findAllById(ids);
        
        if (existingFunctionPoints.size() != ids.size()) {
            throw new FunctionPointNotFoundException("部分功能点不存在或不属于当前租户");
        }
        
        // 检查是否有子功能点
        for (String id : ids) {
            if (functionPointRepository.existsByParentId(id)) {
                throw new IllegalArgumentException("功能点 " + id + " 存在子功能点，无法删除");
            }
        }
        
        // 批量删除关联关系
        for (String id : ids) {
            relationRepository.deleteByFunctionId(id);
        }
        
        // 批量删除功能点
        functionPointRepository.deleteAllById(ids);
        
        log.info("批量删除功能点完成，数量: {} - 租户: {}", ids.size(), tenantId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FunctionPointTreeDTO> getFunctionTree(String parentId) {
        List<FunctionPoint> functionPoints;
        if (StrUtil.isBlank(parentId)) {
            functionPoints = functionPointRepository.findByParentIdIsNull();
        } else {
            functionPoints = functionPointRepository.findByParentId(parentId);
        }

        return functionPoints.stream()
            .map(this::convertToTreeDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FunctionPointDTO> getChildren(String parentId) {
        List<FunctionPoint> children = functionPointRepository.findChildrenByParentId(parentId);
        return children.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FunctionPointDTO> getAncestors(String id) {
        FunctionPoint functionPoint = functionPointRepository.findById(id)
            .orElseThrow(() -> new FunctionPointNotFoundException("功能点不存在: " + id));

        List<FunctionPointDTO> ancestors = new ArrayList<>();
        String currentParentId = functionPoint.getParentId();
        
        while (StrUtil.isNotBlank(currentParentId)) {
            FunctionPoint parent = functionPointRepository.findById(currentParentId)
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
    @Transactional(readOnly = true)
    public List<String> getAssociatedRequirementIds(String functionId) {
        List<RequirementFunctionRelation> relations = relationRepository.findByFunctionId(functionId);
        return relations.stream()
            .map(RequirementFunctionRelation::getRequirementId)
            .collect(Collectors.toList());
    }

    private void validateCircularReference(String parentId, String childId) {
        if (parentId.equals(childId)) {
            throw new CircularReferenceException("不能将自己设置为父节点");
        }

        FunctionPoint parent = functionPointRepository.findById(parentId).orElse(null);
        if (parent != null && TreePathUtil.isAncestor("/" + childId, parent.getPath())) {
            throw new CircularReferenceException("不能将子节点设置为父节点");
        }
    }

    private void updateParentAndPath(FunctionPoint functionPoint, String newParentId) {
        if (StrUtil.isNotBlank(newParentId)) {
            FunctionPoint newParent = functionPointRepository.findById(newParentId)
                .orElseThrow(() -> new FunctionPointNotFoundException("父功能点不存在: " + newParentId));
            
            TreePathUtil.validateLevel(newParent.getPath());
            
            functionPoint.setParentId(newParentId);
            functionPoint.setLevel(newParent.getLevel() + 1);
            functionPoint.setPath(TreePathUtil.generatePath(newParent.getPath(), functionPoint.getId()));
        } else {
            functionPoint.setParentId(null);
            functionPoint.setLevel(1);
            functionPoint.setPath(TreePathUtil.generatePath(null, functionPoint.getId()));
        }

        updateChildrenPaths(functionPoint);
    }

    private void updateChildrenPaths(FunctionPoint parent) {
        List<FunctionPoint> children = functionPointRepository.findByParentId(parent.getId());
        for (FunctionPoint child : children) {
            child.setLevel(parent.getLevel() + 1);
            child.setPath(TreePathUtil.generatePath(parent.getPath(), child.getId()));
            functionPointRepository.save(child);
            updateChildrenPaths(child);
        }
    }

    private FunctionPointDTO convertToDTO(FunctionPoint functionPoint) {
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

    private FunctionPointTreeDTO convertToTreeDTO(FunctionPoint functionPoint) {
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

        List<FunctionPoint> children = functionPointRepository.findByParentId(functionPoint.getId());
        dto.setChildren(children.stream()
            .map(this::convertToTreeDTO)
            .collect(Collectors.toList()));

        return dto;
    }
}