package com.example.functiondemand.requirement.service;

import com.example.functiondemand.requirement.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequirementService {

    RequirementDTO create(RequirementCreateDTO dto);

    RequirementDTO update(String id, RequirementUpdateDTO dto);

    void delete(String id);

    RequirementDTO findById(String id);

    Page<RequirementDTO> findAll(RequirementQueryDTO query, Pageable pageable);

    List<RequirementDTO> batchCreate(List<RequirementCreateDTO> dtos);

    void batchUpdate(List<RequirementUpdateDTO> dtos);

    void batchDelete(List<String> ids);

    List<RequirementTreeDTO> getRequirementTree(String parentId);

    List<RequirementDTO> getChildren(String parentId);

    List<RequirementDTO> getAncestors(String id);

    void associateFunction(String requirementId, String functionId);

    void disassociateFunction(String requirementId, String functionId);

    List<String> getAssociatedFunctionIds(String requirementId);
}