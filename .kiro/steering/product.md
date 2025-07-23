# Multi-Tenant Test Management System

This project is a multi-tenant test management system designed to support 2000+ tenant databases with dynamic data source routing. The system allows different organizations (tenants) to manage their test cases, bugs, and requirements in isolated environments while sharing the same application infrastructure.

## Core Features

- **Multi-tenant architecture** with complete data isolation between tenants
- **Dynamic data source routing** based on tenant ID from request headers
- **Test management** including test cases, execution, bugs, and requirements
- **Cross-database querying** for aggregated reporting across tenants
- **High-performance import/export** supporting Excel, CSV, and TXT formats
- **Tenant database management** including creation, monitoring, backup, and restoration
- **Security and audit** features for comprehensive tracking and access control

## Key Modules

1. **my-product-tenant**: Tenant management service
2. **my-test-execute**: Test execution service
3. **my-function-demand**: Requirements management service
4. **my-bugs**: Bug tracking service
5. **tenant-routing-starter**: Shared library for tenant routing functionality

The system is designed for organizations that need to provide test management capabilities to multiple clients while maintaining strict data isolation between them.