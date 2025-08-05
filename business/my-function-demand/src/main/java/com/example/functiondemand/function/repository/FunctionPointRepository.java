package com.example.functiondemand.function.repository;

import com.example.functiondemand.common.enums.FunctionStatus;
import com.example.functiondemand.function.entity.FunctionPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionPointRepository extends JpaRepository<FunctionPoint, String> {

    List<FunctionPoint> findByParentId(String parentId);

    List<FunctionPoint> findByParentIdIsNull();

    List<FunctionPoint> findByCategoryId(String categoryId);

    List<FunctionPoint> findByStatus(FunctionStatus status);

    List<FunctionPoint> findByOwner(String owner);

    List<FunctionPoint> findByModule(String module);

    @Query("SELECT f FROM FunctionPoint f WHERE f.path LIKE :pathPattern ORDER BY f.path")
    List<FunctionPoint> findByPathStartingWith(@Param("pathPattern") String pathPattern);

    @Query("SELECT f FROM FunctionPoint f WHERE f.level = :level")
    List<FunctionPoint> findByLevel(@Param("level") Integer level);

    @Query("SELECT f FROM FunctionPoint f WHERE f.name LIKE %:keyword% OR f.description LIKE %:keyword%")
    Page<FunctionPoint> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT f FROM FunctionPoint f WHERE f.parentId = :parentId ORDER BY f.createdTime")
    List<FunctionPoint> findChildrenByParentId(@Param("parentId") String parentId);

    @Query("SELECT f FROM FunctionPoint f WHERE f.path LIKE :ancestorPath% AND f.id != :ancestorId ORDER BY f.level, f.createdTime")
    List<FunctionPoint> findDescendantsByAncestorPath(@Param("ancestorPath") String ancestorPath, @Param("ancestorId") String ancestorId);

    @Query("SELECT COUNT(f) FROM FunctionPoint f WHERE f.parentId = :parentId")
    Long countByParentId(@Param("parentId") String parentId);

    @Query("SELECT f FROM FunctionPoint f WHERE f.categoryId = :categoryId ORDER BY f.level, f.createdTime")
    List<FunctionPoint> findByCategoryIdOrderByLevel(@Param("categoryId") String categoryId);

    boolean existsByParentId(String parentId);

    @Query("SELECT f FROM FunctionPoint f WHERE f.status IN :statuses")
    List<FunctionPoint> findByStatusIn(@Param("statuses") List<FunctionStatus> statuses);

    @Query("SELECT f FROM FunctionPoint f WHERE f.module IN :modules")
    List<FunctionPoint> findByModuleIn(@Param("modules") List<String> modules);
}