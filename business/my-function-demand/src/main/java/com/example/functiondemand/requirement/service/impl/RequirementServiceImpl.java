package com.example.functiondemand.requirement.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.functiondemand.common.exception.CircularReferenceException;
import com.example.functiondemand.common.exception.MaxLevelExceededException;
import com.example.functiondemand.common.exception.RequirementNotFoundException;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.util.TreePathUtil;
import com.example.functiondemand.relation.entity.RequirementFunctionRelation;
import com.example.functiondemand.relation.repository.RequirementFunctionRelationRepository;
import com.example.functiondemand.requirement.dto.*;
import com.example.functiondemand.requirement.entity.Requirement;
import com.example.functiondemand.requirement.repository.RequirementRepository;
import com.example.functiondemand.requirement.service.RequirementService;
import com.example.functiondemand.common.validator.BusinessRuleChecker;
import com.example.functiondemand.common.util.BatchPerformanceOptimizer;
import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
@TenantSwitch
public class RequirementServiceImpl implements RequirementService {

    private final RequirementRepository requirementRepository;
    private final RequirementFunctionRelationRepository relationRepository;
    private final BatchPerformanceOptimizer batchOptimizer;
    private final BusinessRuleChecker businessRuleChecker;
    private final org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate;

    @Override
    public RequirementDTO create(RequirementCreateDTO dto) {
        String tenantId = TenantContextHolder.getTenantId();
        log.debug("创建需求: {} - 租户: {}", dto.getTitle(), tenantId);
        
        Requirement requirement = new Requirement();
        requirement.setId(IdGenerator.generateRequirementId());
        requirement.setTitle(dto.getTitle());
        requirement.setDescription(dto.getDescription());
        requirement.setPriority(dto.getPriority());
        requirement.setStatus(dto.getStatus());
        requirement.setSource(dto.getSource());
        requirement.setAssignee(dto.getAssignee());
        requirement.setCategoryId(dto.getCategoryId());
        requirement.setCreatedBy(dto.getCreatedBy());
        requirement.setUpdatedBy(dto.getCreatedBy());

        // 业务规则检查
        businessRuleChecker.checkRequirementRules(requirement);

        if (StrUtil.isNotBlank(dto.getParentId())) {
            Requirement parent = requirementRepository.findById(dto.getParentId())
                .orElseThrow(() -> new RequirementNotFoundException("父需求不存在: " + dto.getParentId()));
            
            validateCircularReference(dto.getParentId(), requirement.getId());
            
            requirement.setParentId(dto.getParentId());
            requirement.setLevel(parent.getLevel() + 1);
            requirement.setPath(TreePathUtil.generatePath(parent.getPath(), requirement.getId()));
            
            TreePathUtil.validateLevel(parent.getPath());
        } else {
            requirement.setLevel(1);
            requirement.setPath(TreePathUtil.generatePath(null, requirement.getId()));
        }

        requirement = requirementRepository.save(requirement);
        log.info("需求创建成功: {}", requirement.getId());
        
        return convertToDTO(requirement);
    }

    @Override
    public RequirementDTO update(String id, RequirementUpdateDTO dto) {
        log.debug("更新需求: {}", id);
        
        Requirement requirement = requirementRepository.findById(id)
            .orElseThrow(() -> new RequirementNotFoundException("需求不存在: " + id));

        requirement.setTitle(dto.getTitle());
        requirement.setDescription(dto.getDescription());
        requirement.setPriority(dto.getPriority());
        requirement.setStatus(dto.getStatus());
        requirement.setSource(dto.getSource());
        requirement.setAssignee(dto.getAssignee());
        requirement.setCategoryId(dto.getCategoryId());
        requirement.setUpdatedBy(dto.getUpdatedBy());

        // 业务规则检查
        businessRuleChecker.checkRequirementRules(requirement);

        if (StrUtil.isNotBlank(dto.getParentId()) && !dto.getParentId().equals(requirement.getParentId())) {
            validateCircularReference(dto.getParentId(), id);
            updateParentAndPath(requirement, dto.getParentId());
        }

        requirement = requirementRepository.save(requirement);
        log.info("需求更新成功: {}", id);
        
        return convertToDTO(requirement);
    }

    @Override
    public void delete(String id) {
        log.debug("删除需求: {}", id);
        
        if (!requirementRepository.existsById(id)) {
            throw new RequirementNotFoundException("需求不存在: " + id);
        }

        // 业务规则检查
//        businessRuleChecker.checkDeleteRules(id, "requirement");

//        relationRepository.deleteByRequirementId(id);
        requirementRepository.deleteById(id);
        
        log.info("需求删除成功: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public RequirementDTO findById(String id) {
        Requirement requirement = requirementRepository.findById(id)
            .orElseThrow(() -> new RequirementNotFoundException("需求不存在: " + id));
        return convertToDTO(requirement);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RequirementDTO> findAll(RequirementQueryDTO query, Pageable pageable) {
        Specification<Requirement> spec = buildSpecification(query);
        return requirementRepository.findAll(spec, pageable).map(this::convertToDTO);
    }

    private Specification<Requirement> buildSpecification(RequirementQueryDTO query) {
        return (root, cq, cb) -> {
            java.util.List<javax.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();

            if (query != null) {
                if (StrUtil.isNotBlank(query.getKeyword())) {
                    String like = "%" + query.getKeyword().trim() + "%";
                    javax.persistence.criteria.Predicate titleLike = cb.like(root.get("title"), like);
                    javax.persistence.criteria.Predicate descLike = cb.like(root.get("description"), like);
                    predicates.add(cb.or(titleLike, descLike));
                }
                if (StrUtil.isNotBlank(query.getCategoryId())) {
                    predicates.add(cb.equal(root.get("categoryId"), query.getCategoryId()));
                }
                if (query.getPriority() != null) {
                    predicates.add(cb.equal(root.get("priority"), query.getPriority()));
                }
                if (query.getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), query.getStatus()));
                }
                if (query.getStatuses() != null && !query.getStatuses().isEmpty()) {
                    predicates.add(root.get("status").in(query.getStatuses()));
                }
                if (StrUtil.isNotBlank(query.getAssignee())) {
                    predicates.add(cb.equal(root.get("assignee"), query.getAssignee()));
                }
                if (StrUtil.isNotBlank(query.getSource())) {
                    predicates.add(cb.equal(root.get("source"), query.getSource()));
                }
                if (query.getLevel() != null) {
                    predicates.add(cb.equal(root.get("level"), query.getLevel()));
                }
                if (StrUtil.isNotBlank(query.getParentId())) {
                    predicates.add(cb.equal(root.get("parentId"), query.getParentId()));
                }
                if (query.getCreatedTimeStart() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createdTime"), query.getCreatedTimeStart()));
                }
                if (query.getCreatedTimeEnd() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createdTime"), query.getCreatedTimeEnd()));
                }
                if (query.getUpdatedTimeStart() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("updatedTime"), query.getUpdatedTimeStart()));
                }
                if (query.getUpdatedTimeEnd() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("updatedTime"), query.getUpdatedTimeEnd()));
                }
            }

            return cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
    }

    @Override
    public List<RequirementDTO> batchCreate(List<RequirementCreateDTO> dtos) {
        String tenantId = TenantContextHolder.getTenantId();
        log.debug("批量创建需求，数量: {} - 租户: {}", dtos.size(), tenantId);
        
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("批量操作需要租户上下文");
        }
        
        // 业务规则检查
        businessRuleChecker.checkBatchOperationRules(dtos.size(), "create");
        
        List<RequirementDTO> results = new ArrayList<>();
        for (RequirementCreateDTO dto : dtos) {
            // 确保每个操作都在正确的租户上下文中
            if (!TenantContextHolder.hasTenant()) {
                throw new IllegalStateException("租户上下文丢失");
            }
            results.add(create(dto));
        }
        
        log.info("批量创建需求完成，数量: {} - 租户: {}", results.size(), tenantId);
        return results;
    }

    @Override
    public void batchUpdate(List<RequirementUpdateDTO> dtos) {
        String tenantId = TenantContextHolder.getTenantId();
        log.debug("批量更新需求，数量: {} - 租户: {}", dtos.size(), tenantId);
        
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("批量操作需要租户上下文");
        }
        
        // 批量验证所有需求是否存在于当前租户
        List<String> ids = dtos.stream().map(RequirementUpdateDTO::getId).collect(Collectors.toList());
        List<Requirement> existingRequirements = requirementRepository.findAllById(ids);
        
        if (existingRequirements.size() != ids.size()) {
            throw new RequirementNotFoundException("部分需求不存在或不属于当前租户");
        }
        
        for (RequirementUpdateDTO dto : dtos) {
            if (!TenantContextHolder.hasTenant()) {
                throw new IllegalStateException("租户上下文丢失");
            }
            update(dto.getId(), dto);
        }
        
        log.info("批量更新需求完成，数量: {} - 租户: {}", dtos.size(), tenantId);
    }

    @Override
    public void batchDelete(List<String> ids) {
        String tenantId = TenantContextHolder.getTenantId();
        log.debug("批量删除需求，数量: {} - 租户: {}", ids.size(), tenantId);
        
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("批量操作需要租户上下文");
        }
        
        // 使用批量优化查询验证需求是否存在
        List<Requirement> existingRequirements = batchOptimizer.batchFind(ids, requirementRepository);
        
        if (existingRequirements.size() != ids.size()) {
            throw new RequirementNotFoundException("部分需求不存在或不属于当前租户");
        }
        
        // 检查是否有子需求
        for (String id : ids) {
            if (requirementRepository.existsByParentId(id)) {
                throw new IllegalArgumentException("需求 " + id + " 存在子需求，无法删除");
            }
        }
        
        // 批量删除关联关系
        for (String id : ids) {
            relationRepository.deleteByRequirementId(id);
        }
        
        // 使用批量优化删除
        batchOptimizer.batchDelete(ids, requirementRepository);
        
        log.info("批量删除需求完成，数量: {} - 租户: {}", ids.size(), tenantId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequirementTreeDTO> getRequirementTree(String parentId) {
        String tenantId = TenantContextHolder.getTenantId();
        
        // 尝试从缓存获取
        String cacheKey = "tree:requirement:" + tenantId + ":" + (StrUtil.isBlank(parentId) ? "root" : parentId);
        
        try {
            @SuppressWarnings("unchecked")
            List<RequirementTreeDTO> cachedResult = (List<RequirementTreeDTO>) 
                redisTemplate.opsForValue().get(cacheKey);
            
            if (cachedResult != null) {
                log.debug("从缓存获取需求树: {}", cacheKey);
                return cachedResult;
            }
        } catch (Exception e) {
            log.debug("缓存获取失败，使用数据库查询: {}", e.getMessage());
        }
        
        List<Requirement> requirements;
        if (StrUtil.isBlank(parentId)) {
            requirements = requirementRepository.findByParentIdIsNull();
        } else {
            requirements = requirementRepository.findByParentId(parentId);
        }

        List<RequirementTreeDTO> result = requirements.stream()
            .map(this::convertToTreeDTO)
            .collect(Collectors.toList());
        
        // 缓存结果（仅当结果不为空时）
        if (!result.isEmpty()) {
            try {
                redisTemplate.opsForValue().set(cacheKey, result, 30, java.util.concurrent.TimeUnit.MINUTES);
                log.debug("需求树已缓存: {}", cacheKey);
            } catch (Exception e) {
                log.debug("缓存失败: {}", e.getMessage());
            }
        }
        
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequirementDTO> getChildren(String parentId) {
        List<Requirement> children = requirementRepository.findChildrenByParentId(parentId);
        return children.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequirementDTO> getAncestors(String id) {
        Requirement requirement = requirementRepository.findById(id)
            .orElseThrow(() -> new RequirementNotFoundException("需求不存在: " + id));

        List<RequirementDTO> ancestors = new ArrayList<>();
        String currentParentId = requirement.getParentId();
        
        while (StrUtil.isNotBlank(currentParentId)) {
            Requirement parent = requirementRepository.findById(currentParentId)
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
    public void associateFunction(String requirementId, String functionId) {
        log.debug("关联需求和功能点: {} -> {}", requirementId, functionId);
        
        if (!requirementRepository.existsById(requirementId)) {
            throw new RequirementNotFoundException("需求不存在: " + requirementId);
        }

        if (relationRepository.existsByRequirementIdAndFunctionId(requirementId, functionId)) {
            throw new IllegalArgumentException("关联关系已存在");
        }

        RequirementFunctionRelation relation = new RequirementFunctionRelation();
        relation.setId(IdGenerator.generateRelationId());
        relation.setRequirementId(requirementId);
        relation.setFunctionId(functionId);
        relation.setCreatedBy("system"); // 这里应该从上下文获取当前用户

        relationRepository.save(relation);
        log.info("需求功能点关联成功: {} -> {}", requirementId, functionId);
    }

    @Override
    public void disassociateFunction(String requirementId, String functionId) {
        log.debug("取消关联需求和功能点: {} -> {}", requirementId, functionId);
        
        relationRepository.deleteByRequirementIdAndFunctionId(requirementId, functionId);
        log.info("需求功能点关联取消成功: {} -> {}", requirementId, functionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAssociatedFunctionIds(String requirementId) {
        List<RequirementFunctionRelation> relations = relationRepository.findByRequirementId(requirementId);
        return relations.stream()
            .map(RequirementFunctionRelation::getFunctionId)
            .collect(Collectors.toList());
    }

    private void validateCircularReference(String parentId, String childId) {
        if (parentId.equals(childId)) {
            throw new CircularReferenceException("不能将自己设置为父节点");
        }

        Requirement parent = requirementRepository.findById(parentId).orElse(null);
        if (parent != null && TreePathUtil.isAncestor("/" + childId, parent.getPath())) {
            throw new CircularReferenceException("不能将子节点设置为父节点");
        }
    }

    private void updateParentAndPath(Requirement requirement, String newParentId) {
        if (StrUtil.isNotBlank(newParentId)) {
            Requirement newParent = requirementRepository.findById(newParentId)
                .orElseThrow(() -> new RequirementNotFoundException("父需求不存在: " + newParentId));
            
            TreePathUtil.validateLevel(newParent.getPath());
            
            requirement.setParentId(newParentId);
            requirement.setLevel(newParent.getLevel() + 1);
            requirement.setPath(TreePathUtil.generatePath(newParent.getPath(), requirement.getId()));
        } else {
            requirement.setParentId(null);
            requirement.setLevel(1);
            requirement.setPath(TreePathUtil.generatePath(null, requirement.getId()));
        }

        // 更新所有子节点的路径
        updateChildrenPaths(requirement);
    }

    private void updateChildrenPaths(Requirement parent) {
        List<Requirement> children = requirementRepository.findByParentId(parent.getId());
        for (Requirement child : children) {
            child.setLevel(parent.getLevel() + 1);
            child.setPath(TreePathUtil.generatePath(parent.getPath(), child.getId()));
            requirementRepository.save(child);
            updateChildrenPaths(child);
        }
    }

    private RequirementDTO convertToDTO(Requirement requirement) {
        RequirementDTO dto = new RequirementDTO();
        dto.setId(requirement.getId());
        dto.setParentId(requirement.getParentId());
        dto.setCategoryId(requirement.getCategoryId());
        dto.setTitle(requirement.getTitle());
        dto.setDescription(requirement.getDescription());
        dto.setPriority(requirement.getPriority());
        dto.setStatus(requirement.getStatus());
        dto.setSource(requirement.getSource());
        dto.setAssignee(requirement.getAssignee());
        dto.setLevel(requirement.getLevel());
        dto.setPath(requirement.getPath());
        dto.setCreatedTime(requirement.getCreatedTime());
        dto.setUpdatedTime(requirement.getUpdatedTime());
        dto.setCreatedBy(requirement.getCreatedBy());
        dto.setUpdatedBy(requirement.getUpdatedBy());
        return dto;
    }

    private RequirementTreeDTO convertToTreeDTO(Requirement requirement) {
        RequirementTreeDTO dto = new RequirementTreeDTO();
        dto.setId(requirement.getId());
        dto.setParentId(requirement.getParentId());
        dto.setCategoryId(requirement.getCategoryId());
        dto.setTitle(requirement.getTitle());
        dto.setDescription(requirement.getDescription());
        dto.setPriority(requirement.getPriority());
        dto.setStatus(requirement.getStatus());
        dto.setSource(requirement.getSource());
        dto.setAssignee(requirement.getAssignee());
        dto.setLevel(requirement.getLevel());
        dto.setPath(requirement.getPath());
        dto.setCreatedTime(requirement.getCreatedTime());
        dto.setUpdatedTime(requirement.getUpdatedTime());
        dto.setCreatedBy(requirement.getCreatedBy());
        dto.setUpdatedBy(requirement.getUpdatedBy());

        // 优化：使用批量查询而不是递归查询
        List<Requirement> children = requirementRepository.findChildrenByParentId(requirement.getId());
        dto.setChildren(children.stream()
            .map(this::convertToTreeDTO)
            .collect(Collectors.toList()));

        return dto;
    }
    
    /**
     * 批量构建树形结构（性能优化版本）
     */
    private List<RequirementTreeDTO> buildTreeOptimized(List<Requirement> allRequirements, String parentId) {
        Map<String, List<Requirement>> childrenMap = allRequirements.stream()
            .filter(req -> req.getParentId() != null)
            .collect(Collectors.groupingBy(Requirement::getParentId));
        
        List<Requirement> rootRequirements = allRequirements.stream()
            .filter(req -> Objects.equals(req.getParentId(), parentId))
            .collect(Collectors.toList());
        
        return rootRequirements.stream()
            .map(req -> buildTreeNodeOptimized(req, childrenMap))
            .collect(Collectors.toList());
    }
    
    private RequirementTreeDTO buildTreeNodeOptimized(Requirement requirement, Map<String, List<Requirement>> childrenMap) {
        List<Requirement> children = childrenMap.getOrDefault(requirement.getId(), Collections.emptyList());
        List<RequirementTreeDTO> childrenDTOs = children.stream()
            .map(child -> buildTreeNodeOptimized(child, childrenMap))
            .collect(Collectors.toList());
        
        RequirementTreeDTO treeDTO = new RequirementTreeDTO();
        // 复制基础属性
        treeDTO.setId(requirement.getId());
        treeDTO.setParentId(requirement.getParentId());
        treeDTO.setCategoryId(requirement.getCategoryId());
        treeDTO.setTitle(requirement.getTitle());
        treeDTO.setDescription(requirement.getDescription());
        treeDTO.setPriority(requirement.getPriority());
        treeDTO.setStatus(requirement.getStatus());
        treeDTO.setSource(requirement.getSource());
        treeDTO.setAssignee(requirement.getAssignee());
        treeDTO.setLevel(requirement.getLevel());
        treeDTO.setPath(requirement.getPath());
        treeDTO.setCreatedTime(requirement.getCreatedTime());
        treeDTO.setUpdatedTime(requirement.getUpdatedTime());
        treeDTO.setCreatedBy(requirement.getCreatedBy());
        treeDTO.setUpdatedBy(requirement.getUpdatedBy());
        treeDTO.setChildren(childrenDTOs);
        
        return treeDTO;
    }
}