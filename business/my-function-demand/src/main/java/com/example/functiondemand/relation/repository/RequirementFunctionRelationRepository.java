package com.example.functiondemand.relation.repository;

import com.example.functiondemand.common.enums.RelationType;
import com.example.functiondemand.relation.entity.RequirementFunctionRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementFunctionRelationRepository extends JpaRepository<RequirementFunctionRelation, String> {

    List<RequirementFunctionRelation> findByRequirementId(String requirementId);

    List<RequirementFunctionRelation> findByFunctionId(String functionId);

    Optional<RequirementFunctionRelation> findByRequirementIdAndFunctionId(String requirementId, String functionId);

    List<RequirementFunctionRelation> findByRelationType(RelationType relationType);

    @Query("SELECT r FROM RequirementFunctionRelation r WHERE r.requirementId = :requirementId AND r.relationType = :relationType")
    List<RequirementFunctionRelation> findByRequirementIdAndRelationType(@Param("requirementId") String requirementId, @Param("relationType") RelationType relationType);

    @Query("SELECT r FROM RequirementFunctionRelation r WHERE r.functionId = :functionId AND r.relationType = :relationType")
    List<RequirementFunctionRelation> findByFunctionIdAndRelationType(@Param("functionId") String functionId, @Param("relationType") RelationType relationType);

    @Query("SELECT COUNT(r) FROM RequirementFunctionRelation r WHERE r.requirementId = :requirementId")
    Long countByRequirementId(@Param("requirementId") String requirementId);

    @Query("SELECT COUNT(r) FROM RequirementFunctionRelation r WHERE r.functionId = :functionId")
    Long countByFunctionId(@Param("functionId") String functionId);

    boolean existsByRequirementIdAndFunctionId(String requirementId, String functionId);

    @Query("SELECT r FROM RequirementFunctionRelation r WHERE r.requirementId IN :requirementIds")
    List<RequirementFunctionRelation> findByRequirementIdIn(@Param("requirementIds") List<String> requirementIds);

    @Query("SELECT r FROM RequirementFunctionRelation r WHERE r.functionId IN :functionIds")
    List<RequirementFunctionRelation> findByFunctionIdIn(@Param("functionIds") List<String> functionIds);

    void deleteByRequirementId(String requirementId);

    void deleteByFunctionId(String functionId);

    void deleteByRequirementIdAndFunctionId(String requirementId, String functionId);
}