package com.tenant.cvs.util;

import com.tenant.entity.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

/**
 * TXT导入工具类
 */
public class TxtImportUtil {
    
    /**
     * 读取TXT文件并转换为TestCase列表
     */
    public static void readTxt(String filePath, Consumer<List<TestCase>> consumer, int batchSize) {
        try {
            List<TestCase> batch = new ArrayList<>();
            
            Files.lines(Paths.get(filePath))
                .skip(1) // 跳过标题行
                .forEach(line -> {
                    TestCase testCase = parseTxtLine(line);
                    if (testCase != null && isValidTestCase(testCase)) {
                        batch.add(testCase);
                        if (batch.size() >= batchSize) {
                            consumer.accept(new ArrayList<>(batch));
                            batch.clear();
                        }
                    }
                });
            
            // 处理剩余的数据
            if (!batch.isEmpty()) {
                consumer.accept(new ArrayList<>(batch));
            }
            
        } catch (IOException e) {
            throw new RuntimeException("TXT文件读取失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 解析TXT行数据（使用制表符分隔）
     */
    private static TestCase parseTxtLine(String line) {
        String[] parts = line.split("\t");
        if (parts.length < 2) {
            return null;
        }
        
        TestCase testCase = new TestCase();
        testCase.setTitle(parts[0].trim());
        testCase.setDescription(parts[1].trim());
        
        // 处理ID（如果有第三列）
        if (parts.length >= 3) {
            String idStr = parts[2].trim();
            if (!idStr.isEmpty()) {
                try {
                    Long id = Long.valueOf(idStr);
                    testCase.setId(id > 0 ? id : null);
                } catch (NumberFormatException e) {
                    testCase.setId(null);
                }
            } else {
                testCase.setId(null);
            }
        } else {
            testCase.setId(null);
        }
        
        return testCase;
    }
    
    /**
     * 校验TestCase是否有效
     */
    private static boolean isValidTestCase(TestCase testCase) {
        return testCase.getTitle() != null && !testCase.getTitle().trim().isEmpty();
    }
} 