# 需求管理和功能管理模块设计文档

## 概述

本设计文档描述了需求管理和功能管理模块的技术架构和实现方案。该模块将在my-function-demand服务中实现，提供完整的需求管理、功能点管理、分类目录管理和导入导出功能，并完全支持多租户架构。

## 架构

### 整体架构

```
┌─────────────────────────────────────────────────────────────┐
│                    Controller Layer                         │
├─────────────────────────────────────────────────────────────┤
│                    Service Layer                            │
├─────────────────────────────────────────────────────────────┤
│                    Repository Layer                         │
├─────────────────────────────────────────────────────────────┤
│                    Database Layer                           │
│              (Multi-tenant Databases)                      │
└─────────────────────────────────────────────────────────────┘
```

### 模块结构

```
my-function-demand/
├── src/main/java/com/example/functiondemand/
│   ├── requirement/                    # 需求管理模块
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   └── dto/
│   ├── function/                       # 功能点管理模块
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   └── dto/
│   ├── category/                       # 分类目录管理模块
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   └── dto/
│   ├── relation/                       # 关联关系管理模块
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   └── dto/
│   ├── common/                         # 公共模块
│   │   ├── enums/
│   │   ├── util/
│   │   ├── config/
│   │   └── exception/
│   └── Application.java
```

## 组件和接口

### 核心实体设计

#### 需求实体 (Requirement)
```java
@Entity
@Table(name = "requirement")
public class Requirement {
    @Id
    private String id;                    // 主键ID
    private String parentId;              // 父需求ID
    private String categoryId;            // 分类目录ID
    private String title;                 // 需求标题
    private String description;           // 需求描述
    private String priority;              // 优先级
    private String status;                // 状态
    private String source;                // 需求来源
    private String assignee;              // 负责人
    private Integer level;                // 层级深度
    private String path;                  // 层级路径
    private LocalDateTime createdTime;    // 创建时间
    private LocalDateTime updatedTime;    // 更新时间
    private String createdBy;             // 创建人
    private String updatedBy;             // 更新人
}
```

#### 功能点实体 (Function)
```java
@Entity
@Table(name = "function_point")
public class FunctionPoint {
    @Id
    private String id;                    // 主键ID
    private String parentId;              // 父功能点ID
    private String categoryId;            // 分类目录ID
    private String name;                  // 功能点名称
    private String description;           // 功能点描述
    private String module;                // 所属模块
    private String complexity;            // 复杂度
    private String status;                // 状态
    private String owner;                 // 负责人
    private Integer level;                // 层级深度
    private String path;                  // 层级路径
    private LocalDateTime createdTime;    // 创建时间
    private LocalDateTime updatedTime;    // 更新时间
    private String createdBy;             // 创建人
    private String updatedBy;             // 更新人
}
```

#### 分类目录实体 (Category)
```java
@Entity
@Table(name = "category")
public class Category {
    @Id
    private String id;                    // 主键ID
    private String parentId;              // 父目录ID
    private String name;                  // 目录名称
    private String type;                  // 目录类型(REQUIREMENT/FUNCTION)
    private String description;           // 目录描述
    private Integer level;                // 层级深度
    private String path;                  // 层级路径
    private Integer sortOrder;            // 排序
    private LocalDateTime createdTime;    // 创建时间
    private LocalDateTime updatedTime;    // 更新时间
    private String createdBy;             // 创建人
    private String updatedBy;             // 更新人
}
```

#### 关联关系实体 (RequirementFunctionRelation)
```java
@Entity
@Table(name = "requirement_function_relation")
public class RequirementFunctionRelation {
    @Id
    private String id;                    // 主键ID
    private String requirementId;         // 需求ID
    private String functionId;            // 功能点ID
    private String relationType;          // 关联类型
    private String description;           // 关联描述
    private LocalDateTime createdTime;    // 创建时间
    private String createdBy;             // 创建人
}
```

### 服务接口设计

#### 需求管理服务接口
```java
public interface RequirementService {
    // 基础CRUD
    RequirementDTO create(RequirementCreateDTO dto);
    RequirementDTO update(String id, RequirementUpdateDTO dto);
    void delete(String id);
    RequirementDTO findById(String id);
    Page<RequirementDTO> findAll(RequirementQueryDTO query, Pageable pageable);
    
    // 批量操作
    List<RequirementDTO> batchCreate(List<RequirementCreateDTO> dtos);
    void batchUpdate(List<RequirementUpdateDTO> dtos);
    void batchDelete(List<String> ids);
    
    // 树形结构
    List<RequirementTreeDTO> getRequirementTree(String parentId);
    List<RequirementDTO> getChildren(String parentId);
    List<RequirementDTO> getAncestors(String id);
    
    // 关联功能点
    void associateFunction(String requirementId, String functionId);
    void disassociateFunction(String requirementId, String functionId);
    List<FunctionPointDTO> getAssociatedFunctions(String requirementId);
}
```

#### 功能点管理服务接口
```java
public interface FunctionPointService {
    // 基础CRUD
    FunctionPointDTO create(FunctionPointCreateDTO dto);
    FunctionPointDTO update(String id, FunctionPointUpdateDTO dto);
    void delete(String id);
    FunctionPointDTO findById(String id);
    Page<FunctionPointDTO> findAll(FunctionPointQueryDTO query, Pageable pageable);
    
    // 批量操作
    List<FunctionPointDTO> batchCreate(List<FunctionPointCreateDTO> dtos);
    void batchUpdate(List<FunctionPointUpdateDTO> dtos);
    void batchDelete(List<String> ids);
    
    // 树形结构
    List<FunctionPointTreeDTO> getFunctionTree(String parentId);
    List<FunctionPointDTO> getChildren(String parentId);
    List<FunctionPointDTO> getAncestors(String id);
    
    // 关联需求
    List<RequirementDTO> getAssociatedRequirements(String functionId);
}
```

#### 分类目录管理服务接口
```java
public interface CategoryService {
    // 基础CRUD
    CategoryDTO create(CategoryCreateDTO dto);
    CategoryDTO update(String id, CategoryUpdateDTO dto);
    void delete(String id);
    CategoryDTO findById(String id);
    List<CategoryDTO> findByType(CategoryType type);
    
    // 批量操作
    List<CategoryDTO> batchCreate(List<CategoryCreateDTO> dtos);
    void batchUpdate(List<CategoryUpdateDTO> dtos);
    void batchDelete(List<String> ids);
    
    // 树形结构
    List<CategoryTreeDTO> getCategoryTree(CategoryType type, String parentId);
    List<CategoryDTO> getChildren(String parentId);
    String generatePath(String parentId);
    void validateLevel(String parentId);
}
```

## 数据模型

### 数据库表设计

#### 需求表 (requirement)
```sql
CREATE TABLE requirement (
    id VARCHAR(32) PRIMARY KEY,
    parent_id VARCHAR(32),
    category_id VARCHAR(32),
    title VARCHAR(200) NOT NULL,
    description TEXT,
    priority VARCHAR(20) DEFAULT 'MEDIUM',
    status VARCHAR(20) DEFAULT 'DRAFT',
    source VARCHAR(100),
    assignee VARCHAR(100),
    level INT DEFAULT 1,
    path VARCHAR(500),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    INDEX idx_parent_id (parent_id),
    INDEX idx_category_id (category_id),
    INDEX idx_status (status),
    INDEX idx_assignee (assignee)
);
```

#### 功能点表 (function_point)
```sql
CREATE TABLE function_point (
    id VARCHAR(32) PRIMARY KEY,
    parent_id VARCHAR(32),
    category_id VARCHAR(32),
    name VARCHAR(200) NOT NULL,
    description TEXT,
    module VARCHAR(100),
    complexity VARCHAR(20) DEFAULT 'MEDIUM',
    status VARCHAR(20) DEFAULT 'PLANNING',
    owner VARCHAR(100),
    level INT DEFAULT 1,
    path VARCHAR(500),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    INDEX idx_parent_id (parent_id),
    INDEX idx_category_id (category_id),
    INDEX idx_module (module),
    INDEX idx_status (status)
);
```

#### 分类目录表 (category)
```sql
CREATE TABLE category (
    id VARCHAR(32) PRIMARY KEY,
    parent_id VARCHAR(32),
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL,
    description VARCHAR(500),
    level INT DEFAULT 1,
    path VARCHAR(500),
    sort_order INT DEFAULT 0,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    INDEX idx_parent_id (parent_id),
    INDEX idx_type (type),
    INDEX idx_level (level)
);
```

#### 关联关系表 (requirement_function_relation)
```sql
CREATE TABLE requirement_function_relation (
    id VARCHAR(32) PRIMARY KEY,
    requirement_id VARCHAR(32) NOT NULL,
    function_id VARCHAR(32) NOT NULL,
    relation_type VARCHAR(20) DEFAULT 'IMPLEMENT',
    description VARCHAR(500),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    UNIQUE KEY uk_req_func (requirement_id, function_id),
    INDEX idx_requirement_id (requirement_id),
    INDEX idx_function_id (function_id)
);
```

## 错误处理

### 异常类型定义
```java
public class RequirementNotFoundException extends RuntimeException {}
public class FunctionPointNotFoundException extends RuntimeException {}
public class CategoryNotFoundException extends RuntimeException {}
public class InvalidTreeStructureException extends RuntimeException {}
public class MaxLevelExceededException extends RuntimeException {}
public class CircularReferenceException extends RuntimeException {}
public class DuplicateRelationException extends RuntimeException {}
```

### 全局异常处理
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RequirementNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRequirementNotFound(RequirementNotFoundException e);
    
    @ExceptionHandler(InvalidTreeStructureException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTreeStructure(InvalidTreeStructureException e);
    
    @ExceptionHandler(MaxLevelExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxLevelExceeded(MaxLevelExceededException e);
}
```

## 测试策略

### 单元测试
- 服务层业务逻辑测试
- 仓储层数据访问测试
- 工具类功能测试
- 异常处理测试

### 集成测试
- 控制器API测试
- 数据库事务测试
- 多租户数据隔离测试
- 导入导出功能测试

### 性能测试
- 批量操作性能测试
- 树形结构查询性能测试
- 大数据量导入导出测试
- 并发操作测试

### 测试数据准备
```java
@TestConfiguration
public class TestDataConfig {
    @Bean
    @Primary
    public DataProcessor<Requirement> requirementTestProcessor();
    
    @Bean
    @Primary
    public DataProcessor<FunctionPoint> functionTestProcessor();
}
```

### 多租户测试
```java
@Test
@WithTenant("tenant001")
public void testRequirementCRUD() {
    // 测试租户隔离的需求CRUD操作
}

@Test
@WithTenant("tenant002")
public void testFunctionPointCRUD() {
    // 测试租户隔离的功能点CRUD操作
}
```