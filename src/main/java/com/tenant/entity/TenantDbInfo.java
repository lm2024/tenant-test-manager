package com.tenant.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "tenant_db_info")
@Data
public class TenantDbInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_id", nullable = false, unique = true)
    private String tenantId;

    @Column(name = "tenant_name", nullable = false, unique = true)
    private String tenantName;

    @Column(name = "db_name", nullable = false, unique = true)
    private String dbName;

    @Column(name = "db_url", nullable = false)
    private String dbUrl;

    @Column(name = "db_user", nullable = false)
    private String dbUser;

    @Column(name = "db_password", nullable = false)
    private String dbPassword;
} 