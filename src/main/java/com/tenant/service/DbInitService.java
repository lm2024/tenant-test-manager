package com.tenant.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Service
public class DbInitService {
    public void createDatabaseAndTables(String dbName, String dbUser, String dbPassword, String templateSql) throws Exception {
        // 1. 连接MySQL主库，执行CREATE DATABASE
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", dbUser, dbPassword);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
        }
        // 2. 连接新库，执行templateSql
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", dbUser, dbPassword);
             Statement stmt = conn.createStatement()) {
            // 按分号分割逐条执行
            String[] sqlStatements = templateSql.split(";");
            for (String sql : sqlStatements) {
                if (sql != null && !sql.trim().isEmpty()) {
                    stmt.execute(sql.trim());
                }
            }
        }
    }
} 