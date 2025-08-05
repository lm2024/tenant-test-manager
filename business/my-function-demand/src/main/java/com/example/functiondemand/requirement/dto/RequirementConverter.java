package com.example.functiondemand.requirement.dto;

import com.example.functiondemand.requirement.entity.Requirement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequirementConverter {
    
    /**
     * 实体转DTO
     */
    public RequirementDTO toDTO(Requirement requirement) {
        if (requirement == null) {
            return null;
        }
        
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
    
    /**
     * 实体转树形DTO
     */
    public RequirementTreeDTO toTreeDTO(Requirement requirement) {
        if (requirement == null) {
            return null;
        }
        
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
        
        return dto;
    }
    
    /**
     * 创建DTO转实体
     */
    public Requirement fromCreateDTO(RequirementCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Requirement requirement = new Requirement();
        requirement.setParentId(dto.getParentId());
        requirement.setCategoryId(dto.getCategoryId());
        requirement.setTitle(dto.getTitle());
        requirement.setDescription(dto.getDescription());
        requirement.setPriority(dto.getPriority());
        requirement.setStatus(dto.getStatus());
        requirement.setSource(dto.getSource());
        requirement.setAssignee(dto.getAssignee());
        requirement.setCreatedBy(dto.getCreatedBy());
        requirement.setUpdatedBy(dto.getCreatedBy());
        
        return requirement;
    }
    
    /**
     * 更新DTO应用到实体
     */
    public void applyUpdateDTO(RequirementUpdateDTO dto, Requirement requirement) {
        if (dto == null || requirement == null) {
            return;
        }
        
        if (dto.getParentId() != null) {
            requirement.setParentId(dto.getParentId());
        }
        if (dto.getCategoryId() != null) {
            requirement.setCategoryId(dto.getCategoryId());
        }
        if (dto.getTitle() != null) {
            requirement.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            requirement.setDescription(dto.getDescription());
        }
        if (dto.getPriority() != null) {
            requirement.setPriority(dto.getPriority());
        }
        if (dto.getStatus() != null) {
            requirement.setStatus(dto.getStatus());
        }
        if (dto.getSource() != null) {
            requirement.setSource(dto.getSource());
        }
        if (dto.getAssignee() != null) {
            requirement.setAssignee(dto.getAssignee());
        }
        if (dto.getUpdatedBy() != null) {
            requirement.setUpdatedBy(dto.getUpdatedBy());
        }
    }
    
    /**
     * 批量转换
     */
    public List<RequirementDTO> toDTOList(List<Requirement> requirements) {
        if (requirements == null) {
            return null;
        }
        
        return requirements.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * 批量转换为树形DTO
     */
    public List<RequirementTreeDTO> toTreeDTOList(List<Requirement> requirements) {
        if (requirements == null) {
            return null;
        }
        
        return requirements.stream()
            .map(this::toTreeDTO)
            .collect(Collectors.toList());
    }
}