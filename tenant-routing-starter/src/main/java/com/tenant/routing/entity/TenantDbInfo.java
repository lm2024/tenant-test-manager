package com.tenant.routing.entity;

import java.io.Serializable;

/**
 * 租户数据库信息实体
 */
public class TenantDbInfo implements Serializable {
    
    private Long id;
    private String dbName;
    private String dbPassword;
    private String dbUrl;
    private String dbUser;
    private String tenantId;
    private String tenantName;
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDbName() {
        return dbName;
    }
    
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    
    public String getDbPassword() {
        return dbPassword;
    }
    
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    
    public String getDbUrl() {
        return dbUrl;
    }
    
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
    
    public String getDbUser() {
        return dbUser;
    }
    
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
    
    public String getTenantId() {
        return tenantId;
    }
    
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    
    public String getTenantName() {
        return tenantName;
    }
    
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    
    @Override
    public String toString() {
        return "TenantDbInfo{" +
                "id=" + id +
                ", dbName='" + dbName + '\'' +
                ", dbUrl='" + dbUrl + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                '}';
    }
}