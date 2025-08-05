package com.example.functiondemand;

import com.example.functiondemand.controller.CategoryControllerIntegrationTest;
import com.example.functiondemand.controller.FunctionPointControllerIntegrationTest;
import com.example.functiondemand.controller.RequirementControllerIntegrationTest;
import com.example.functiondemand.importexport.ImportExportIntegrationTest;
import com.example.functiondemand.performance.PerformanceIntegrationTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * 集成测试套件
 * 包含所有控制器、导入导出和性能测试
 */
@Suite
@SelectClasses({
    RequirementControllerIntegrationTest.class,
    FunctionPointControllerIntegrationTest.class,
    CategoryControllerIntegrationTest.class,
    ImportExportIntegrationTest.class,
    PerformanceIntegrationTest.class
})
public class IntegrationTestSuite {
    // 测试套件类，用于统一运行所有集成测试
}