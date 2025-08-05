package com.example.functiondemand.category.repository;

import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.common.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findByType(CategoryType type);

    List<Category> findByParentId(String parentId);

    List<Category> findByParentIdIsNull();

    List<Category> findByTypeAndParentId(CategoryType type, String parentId);

    List<Category> findByTypeAndParentIdIsNull(CategoryType type);

    @Query("SELECT c FROM Category c WHERE c.path LIKE :pathPattern ORDER BY c.path")
    List<Category> findByPathStartingWith(@Param("pathPattern") String pathPattern);

    @Query("SELECT c FROM Category c WHERE c.level = :level")
    List<Category> findByLevel(@Param("level") Integer level);

    @Query("SELECT c FROM Category c WHERE c.type = :type AND c.level = :level")
    List<Category> findByTypeAndLevel(@Param("type") CategoryType type, @Param("level") Integer level);

    @Query("SELECT c FROM Category c WHERE c.parentId = :parentId ORDER BY c.sortOrder, c.createdTime")
    List<Category> findChildrenByParentIdOrderBySortOrder(@Param("parentId") String parentId);

    @Query("SELECT c FROM Category c WHERE c.type = :type AND c.parentId = :parentId ORDER BY c.sortOrder, c.createdTime")
    List<Category> findByTypeAndParentIdOrderBySortOrder(@Param("type") CategoryType type, @Param("parentId") String parentId);

    @Query("SELECT c FROM Category c WHERE c.path LIKE :ancestorPath% AND c.id != :ancestorId ORDER BY c.level, c.sortOrder")
    List<Category> findDescendantsByAncestorPath(@Param("ancestorPath") String ancestorPath, @Param("ancestorId") String ancestorId);

    @Query("SELECT COUNT(c) FROM Category c WHERE c.parentId = :parentId")
    Long countByParentId(@Param("parentId") String parentId);

    @Query("SELECT c FROM Category c WHERE c.type = :type ORDER BY c.level, c.sortOrder, c.createdTime")
    List<Category> findByTypeOrderByLevelAndSort(@Param("type") CategoryType type);

    boolean existsByParentId(String parentId);

    boolean existsByTypeAndName(CategoryType type, String name);

    @Query("SELECT c FROM Category c WHERE c.type = :type AND c.name LIKE %:keyword%")
    List<Category> findByTypeAndNameContaining(@Param("type") CategoryType type, @Param("keyword") String keyword);

    @Query("SELECT c FROM Category c WHERE c.type = :type AND c.name = :name")
    Optional<Category> findByTypeAndName(@Param("type") CategoryType type, @Param("name") String name);
}