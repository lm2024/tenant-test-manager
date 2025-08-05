package com.example.functiondemand.function.service;

import com.example.functiondemand.function.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FunctionPointService {

    FunctionPointDTO create(FunctionPointCreateDTO dto);

    FunctionPointDTO update(String id, FunctionPointUpdateDTO dto);

    void delete(String id);

    FunctionPointDTO findById(String id);

    Page<FunctionPointDTO> findAll(FunctionPointQueryDTO query, Pageable pageable);

    List<FunctionPointDTO> batchCreate(List<FunctionPointCreateDTO> dtos);

    void batchUpdate(List<FunctionPointUpdateDTO> dtos);

    void batchDelete(List<String> ids);

    List<FunctionPointTreeDTO> getFunctionTree(String parentId);

    List<FunctionPointDTO> getChildren(String parentId);

    List<FunctionPointDTO> getAncestors(String id);

    List<String> getAssociatedRequirementIds(String functionId);
}