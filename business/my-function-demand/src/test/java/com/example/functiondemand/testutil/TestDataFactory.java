package com.example.functiondemand.testutil;

import com.example.functiondemand.dto.category.CategoryCreateDTO;
import com.example.functiondemand.dto.functionpoint.FunctionPointCreateDTO;
import com.example.functiondemand.dto.requirement.RequirementCreateDTO;
import com.example.functiondemand.entity.Category;
import com.example.functiondemand.entity.FunctionPoint;
import com.example.functiondemand.entity.Requirement;
import com.example.functiondemand.enums.CategoryType;
import com.example.functiondemand.enums.FunctionPointStatus;
import com.example.functiondemand.enums.FunctionPointType;
import com.example.functiondemand.enums.RequirementStatus;
import com.example.functiondemand.enums.RequirementType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试数据工厂
 * 用于生成各种测试数据
 */
public class TestDataFactory {

    private static final Random random = new Random();
    
    private static final String[] REQUIREMENT_TITLES = {
        "用户登录功能", "数据导出功能", "权限管理", "系统监控", "日志记录",
        "文件上传", "消息通知", "数据备份", "性能优化", "安全加固"
    };
    
    private static final String[] FUNCTION_POINT_NAMES = {
        "用户管理模块", "订单处理", "支付集成", "报表生成", "数据分析",
        "API接口", "前端界面", "数据库设计", "缓存机制", "消息队列"
    };
    
    private static final String[] CATEGORY_NAMES = {
        "核心功能", "辅助功能", "系统管理", "用户体验", "性能相关",
        "安全功能", "集成接口", "数据处理", "监控告警", "运维管理"
    };

    /**
     * 创建需求实体
     */
    public static Requirement createRequirement() {
        Requirement requirement = new Requirement();
        requirement.setTitle(getRandomElement(REQUIREMENT_TITLES) + "_" + random.nextInt(1000));
        requirement.setDescription("这是一个测试需求的详细描述，包含了需求的具体内容和实现要求。");
        requirement.setType(getRandomElement(RequirementType.values()));
        requirement.setStatus(getRandomElement(RequirementStatus.values()));
        requirement.setPriority(random.nextInt(5) + 1);
        requirement.setCreatedAt(LocalDateTime.now());
        requirement.setUpdatedAt(LocalDateTime.now());
        return requirement;
    }

    /**
     * 创建需求创建DTO
     */
    public static RequirementCreateDTO createRequirementCreateDTO() {
        RequirementCreateDTO dto = new RequirementCreateDTO();
        dto.setTitle(getRandomElement(REQUIREMENT_TITLES) + "_" + random.nextInt(1000));
        dto.setDescription("这是一个测试需求的详细描述，包含了需求的具体内容和实现要求。");
        dto.setType(getRandomElement(RequirementType.values()));
        dto.setStatus(getRandomElement(RequirementStatus.values()));
        dto.setPriority(random.nextInt(5) + 1);
        return dto;
    }

    /**
     * 批量创建需求创建DTO
     */
    public static List<RequirementCreateDTO> createRequirementCreateDTOList(int count) {
        List<RequirementCreateDTO> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(createRequirementCreateDTO());
        }
        return list;
    }

    /**
     * 创建功能点实体
     */
    public static FunctionPoint createFunctionPoint() {
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setName(getRandomElement(FUNCTION_POINT_NAMES) + "_" + random.nextInt(1000));
        functionPoint.setDescription("这是一个测试功能点的详细描述，包含了功能的具体内容和实现要求。");
        functionPoint.setType(getRandomElement(FunctionPointType.values()));
        functionPoint.setStatus(getRandomElement(FunctionPointStatus.values()));
        functionPoint.setPriority(random.nextInt(5) + 1);
        functionPoint.setCreatedAt(LocalDateTime.now());
        functionPoint.setUpdatedAt(LocalDateTime.now());
        return functionPoint;
    }

    /**
     * 创建功能点创建DTO
     */
    public static FunctionPointCreateDTO createFunctionPointCreateDTO() {
        FunctionPointCreateDTO dto = new FunctionPointCreateDTO();
        dto.setName(getRandomElement(FUNCTION_POINT_NAMES) + "_" + random.nextInt(1000));
        dto.setDescription("这是一个测试功能点的详细描述，包含了功能的具体内容和实现要求。");
        dto.setType(getRandomElement(FunctionPointType.values()));
        dto.setStatus(getRandomElement(FunctionPointStatus.values()));
        dto.setPriority(random.nextInt(5) + 1);
        return dto;
    }

    /**
     * 批量创建功能点创建DTO
     */
    public static List<FunctionPointCreateDTO> createFunctionPointCreateDTOList(int count) {
        List<FunctionPointCreateDTO> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(createFunctionPointCreateDTO());
        }
        return list;
    }

    /**
     * 创建分类实体
     */
    public static Category createCategory() {
        Category category = new Category();
        category.setName(getRandomElement(CATEGORY_NAMES) + "_" + random.nextInt(1000));
        category.setDescription("这是一个测试分类的详细描述，用于组织和管理相关的需求或功能点。");
        category.setType(getRandomElement(CategoryType.values()));
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }

    /**
     * 创建分类创建DTO
     */
    public static CategoryCreateDTO createCategoryCreateDTO() {
        CategoryCreateDTO dto = new CategoryCreateDTO();
        dto.setName(getRandomElement(CATEGORY_NAMES) + "_" + random.nextInt(1000));
        dto.setDescription("这是一个测试分类的详细描述，用于组织和管理相关的需求或功能点。");
        dto.setType(getRandomElement(CategoryType.values()));
        return dto;
    }

    /**
     * 批量创建分类创建DTO
     */
    public static List<CategoryCreateDTO> createCategoryCreateDTOList(int count) {
        List<CategoryCreateDTO> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(createCategoryCreateDTO());
        }
        return list;
    }

    /**
     * 创建树形结构的需求
     */
    public static List<Requirement> createRequirementTree(int levels, int childrenPerLevel) {
        List<Requirement> requirements = new ArrayList<>();
        createRequirementTreeRecursive(requirements, null, levels, childrenPerLevel, 1, "/");
        return requirements;
    }

    private static void createRequirementTreeRecursive(List<Requirement> requirements, Long parentId, 
                                                      int remainingLevels, int childrenPerLevel, 
                                                      int currentLevel, String currentPath) {
        if (remainingLevels <= 0) {
            return;
        }

        for (int i = 1; i <= childrenPerLevel; i++) {
            Requirement requirement = createRequirement();
            requirement.setTitle("Level" + currentLevel + "_Child" + i + "_" + requirement.getTitle());
            requirement.setParentId(parentId);
            requirement.setLevel(currentLevel);
            requirement.setPath(currentPath);
            requirements.add(requirement);

            // 递归创建子节点
            if (remainingLevels > 1) {
                String childPath = currentPath + requirement.getId() + "/";
                createRequirementTreeRecursive(requirements, requirement.getId(), 
                                             remainingLevels - 1, childrenPerLevel, 
                                             currentLevel + 1, childPath);
            }
        }
    }

    /**
     * 创建树形结构的功能点
     */
    public static List<FunctionPoint> createFunctionPointTree(int levels, int childrenPerLevel) {
        List<FunctionPoint> functionPoints = new ArrayList<>();
        createFunctionPointTreeRecursive(functionPoints, null, levels, childrenPerLevel, 1, "/");
        return functionPoints;
    }

    private static void createFunctionPointTreeRecursive(List<FunctionPoint> functionPoints, Long parentId, 
                                                        int remainingLevels, int childrenPerLevel, 
                                                        int currentLevel, String currentPath) {
        if (remainingLevels <= 0) {
            return;
        }

        for (int i = 1; i <= childrenPerLevel; i++) {
            FunctionPoint functionPoint = createFunctionPoint();
            functionPoint.setName("Level" + currentLevel + "_Child" + i + "_" + functionPoint.getName());
            functionPoint.setParentId(parentId);
            functionPoint.setLevel(currentLevel);
            functionPoint.setPath(currentPath);
            functionPoints.add(functionPoint);

            // 递归创建子节点
            if (remainingLevels > 1) {
                String childPath = currentPath + functionPoint.getId() + "/";
                createFunctionPointTreeRecursive(functionPoints, functionPoint.getId(), 
                                               remainingLevels - 1, childrenPerLevel, 
                                               currentLevel + 1, childPath);
            }
        }
    }

    /**
     * 创建树形结构的分类
     */
    public static List<Category> createCategoryTree(int levels, int childrenPerLevel) {
        List<Category> categories = new ArrayList<>();
        createCategoryTreeRecursive(categories, null, levels, childrenPerLevel, 1, "/");
        return categories;
    }

    private static void createCategoryTreeRecursive(List<Category> categories, Long parentId, 
                                                   int remainingLevels, int childrenPerLevel, 
                                                   int currentLevel, String currentPath) {
        if (remainingLevels <= 0) {
            return;
        }

        for (int i = 1; i <= childrenPerLevel; i++) {
            Category category = createCategory();
            category.setName("Level" + currentLevel + "_Child" + i + "_" + category.getName());
            category.setParentId(parentId);
            category.setLevel(currentLevel);
            category.setPath(currentPath);
            categories.add(category);

            // 递归创建子节点
            if (remainingLevels > 1) {
                String childPath = currentPath + category.getId() + "/";
                createCategoryTreeRecursive(categories, category.getId(), 
                                          remainingLevels - 1, childrenPerLevel, 
                                          currentLevel + 1, childPath);
            }
        }
    }

    /**
     * 获取随机元素
     */
    private static <T> T getRandomElement(T[] array) {
        return array[random.nextInt(array.length)];
    }

    /**
     * 生成随机字符串
     */
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * 生成随机数字
     */
    public static int generateRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 生成随机布尔值
     */
    public static boolean generateRandomBoolean() {
        return random.nextBoolean();
    }
}