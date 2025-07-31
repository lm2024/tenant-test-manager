package com.tenant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * 数据库初始化服务
 */
@Slf4j
@Service
public class DbInitService {

    @Autowired
    @Qualifier("tenantCenterDataSource")
    private DataSource dataSource;

    /**
     * 创建数据库和表
     * @param dbName 数据库名称
     * @param dbUser 数据库用户
     * @param dbPassword 数据库密码
     * @param templateSql 模板SQL
     */
    public void createDatabaseAndTables(String dbName, String dbUser, String dbPassword, String templateSql) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            
            // 创建数据库
            String createDbSql = "CREATE DATABASE IF NOT EXISTS `" + dbName + "` " +
                               "DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
            statement.execute(createDbSql);
            log.info("数据库创建成功: {}", dbName);
            
            // 切换到新数据库
            statement.execute("USE `" + dbName + "`");
            
            // 执行模板SQL创建表
            if (templateSql != null && !templateSql.trim().isEmpty()) {
                String[] sqlStatements = templateSql.split(";");
                for (String sql : sqlStatements) {
                    sql = sql.trim();
                    if (!sql.isEmpty()) {
                        statement.execute(sql);
                    }
                }
                log.info("表创建成功: {}", dbName);
            }
            
        } catch (Exception e) {
            log.error("创建数据库和表失败: {}", dbName, e);
            throw new RuntimeException("创建数据库和表失败: " + e.getMessage(), e);
        }
    }

    /**
     * 删除数据库
     * @param dbName 数据库名称
     */
    public void dropDatabase(String dbName) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            
            String dropDbSql = "DROP DATABASE IF EXISTS `" + dbName + "`";
            statement.execute(dropDbSql);
            log.info("数据库删除成功: {}", dbName);
            
        } catch (Exception e) {
            log.error("删除数据库失败: {}", dbName, e);
            throw new RuntimeException("删除数据库失败: " + e.getMessage(), e);
        }
    }

    /**
     * 检查数据库是否存在
     * @param dbName 数据库名称
     * @return 是否存在
     */
    public boolean databaseExists(String dbName) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            
            String checkSql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + dbName + "'";
            java.sql.ResultSet resultSet = statement.executeQuery(checkSql);
            return resultSet.next();
            
        } catch (Exception e) {
            log.error("检查数据库是否存在失败: {}", dbName, e);
            return false;
        }
    }

    /**
     * 获取数据库大小
     * @param dbName 数据库名称
     * @return 数据库大小（字节）
     */
    public long getDatabaseSize(String dbName) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            
            String sizeSql = "SELECT SUM(data_length + index_length) as size " +
                           "FROM information_schema.tables " +
                           "WHERE table_schema = '" + dbName + "'";
            java.sql.ResultSet resultSet = statement.executeQuery(sizeSql);
            
            if (resultSet.next()) {
                return resultSet.getLong("size");
            }
            return 0;
            
        } catch (Exception e) {
            log.error("获取数据库大小失败: {}", dbName, e);
            return 0;
        }
    }
} 