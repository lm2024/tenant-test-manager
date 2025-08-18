package com.example.functiondemand.requirement.repository;

import com.example.functiondemand.common.enums.RequirementStatus;
import com.example.functiondemand.requirement.entity.Requirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, String>, JpaSpecificationExecutor<Requirement> {

    List<Requirement> findByParentId(String parentId);

    List<Requirement> findByParentIdIsNull();

    List<Requirement> findByCategoryId(String categoryId);

    List<Requirement> findByStatus(RequirementStatus status);

    List<Requirement> findByAssignee(String assignee);

    @Query("SELECT r FROM Requirement r WHERE r.path LIKE :pathPattern ORDER BY r.path")
    List<Requirement> findByPathStartingWith(@Param("pathPattern") String pathPattern);

    @Query("SELECT r FROM Requirement r WHERE r.level = :level")
    List<Requirement> findByLevel(@Param("level") Integer level);

    @Query("SELECT r FROM Requirement r WHERE r.title LIKE %:keyword% OR r.description LIKE %:keyword%")
    Page<Requirement> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT r FROM Requirement r WHERE r.parentId = :parentId ORDER BY r.createdTime")
    List<Requirement> findChildrenByParentId(@Param("parentId") String parentId);

    @Query("SELECT r FROM Requirement r WHERE r.path LIKE :ancestorPath% AND r.id != :ancestorId ORDER BY r.level, r.createdTime")
    List<Requirement> findDescendantsByAncestorPath(@Param("ancestorPath") String ancestorPath, @Param("ancestorId") String ancestorId);

    @Query("SELECT COUNT(r) FROM Requirement r WHERE r.parentId = :parentId")
    Long countByParentId(@Param("parentId") String parentId);

    @Query("SELECT r FROM Requirement r WHERE r.categoryId = :categoryId ORDER BY r.level, r.createdTime")
    List<Requirement> findByCategoryIdOrderByLevel(@Param("categoryId") String categoryId);

    boolean existsByParentId(String parentId);

    @Query("SELECT r FROM Requirement r WHERE r.status IN :statuses")
    List<Requirement> findByStatusIn(@Param("statuses") List<RequirementStatus> statuses);

    @Query("SELECT r FROM Requirement r WHERE r.categoryId = :categoryId ORDER BY r.level, r.path")
    List<Requirement> findByCategoryIdOptimized(@Param("categoryId") String categoryId);

    @Query("SELECT r FROM Requirement r WHERE r.path LIKE :pathPrefix ORDER BY r.level, r.path")
    List<Requirement> findByPathPrefixOptimized(@Param("pathPrefix") String pathPrefix);

    @Query(value = "SELECT * FROM requirement WHERE parent_id = :parentId ORDER BY level, created_time LIMIT :limit", nativeQuery = true)
    List<Requirement> findChildrenWithLimit(@Param("parentId") String parentId, @Param("limit") int limit);

    @Query("SELECT r FROM Requirement r WHERE r.level <= :maxLevel ORDER BY r.level, r.path")
    List<Requirement> findByMaxLevel(@Param("maxLevel") Integer maxLevel);
}