# Project Structure & Organization

## Module Structure

The project follows a microservice architecture with the following modules:

1. **tenant-routing-starter**: Core library for tenant routing functionality
   - Shared across all microservices
   - Contains annotations, interceptors, and context holders for tenant switching

2. **my-product-tenant**: Tenant management service
   - Manages tenant creation, configuration, and lifecycle
   - Provides tenant database management APIs
   - Handles cross-tenant operations and reporting

3. **my-test-execute**: Test execution service
   - Manages test execution and results
   - Tracks test runs and performance metrics
   - Provides test execution reporting

4. **my-function-demand**: Requirements management service
   - Handles functional requirements and specifications
   - Manages requirement lifecycle and traceability
   - Links requirements to test cases

5. **my-bugs**: Bug tracking service
   - Manages defects and issues
   - Tracks bug lifecycle and resolution
   - Provides bug reporting and analytics

## Directory Structure

```
├── doc/                          # Documentation
│   ├── README.md                 # Main documentation
│   ├── README_TenantLogging.md   # Tenant logging documentation
│   ├── README_TenantSwitch.md    # Tenant switching documentation
│   └── README_TENANT_FEATURES.md # Tenant features documentation
├── docker/                       # Docker configuration
│   ├── docker-compose-*.yml      # Docker compose files for different services
│   ├── deploy.sh                 # Deployment script
│   └── */                        # Service-specific Docker configurations
├── sql/                          # SQL scripts
│   ├── tenant_center.sql         # Tenant center database schema
│   └── db_tenant*.sql            # Sample tenant database schemas
├── tenant-routing-starter/       # Tenant routing library
├── my-product-tenant/            # Tenant management service
├── my-test-execute/              # Test execution service
├── my-function-demand/           # Requirements management service
├── my-bugs/                      # Bug tracking service
└── pom.xml                       # Parent POM file
```

## Code Organization

Each microservice follows a standard Spring Boot application structure:

```
my-{service-name}/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── config/       # Configuration classes
│   │   │       ├── controller/   # REST controllers
│   │   │       ├── service/      # Business logic
│   │   │       ├── repository/   # Data access
│   │   │       ├── entity/       # JPA entities
│   │   │       ├── dto/          # Data transfer objects
│   │   │       ├── exception/    # Custom exceptions
│   │   │       └── util/         # Utility classes
│   │   └── resources/
│   │       ├── application.yml   # Application configuration
│   │       ├── logback-spring.xml # Logging configuration
│   │       └── static/           # Static resources
│   └── test/                     # Test classes
└── pom.xml                       # Module POM file
```

## Naming Conventions

- **Packages**: `com.example.[module].[component]`
- **Classes**:
  - Controllers: `*Controller`
  - Services: `*Service`
  - Repositories: `*Repository`
  - Entities: Singular noun (e.g., `Bug`, `TestCase`)
  - DTOs: `*DTO`
- **Methods**:
  - Controllers: HTTP verb + noun (e.g., `getTestCase`, `createBug`)
  - Services: Action + noun (e.g., `findById`, `createEntity`)
  - Repositories: JPA standard (e.g., `findAll`, `save`)

## Database Organization

- **tenant_center**: Central database for tenant information
- **db_tenant{id}**: Individual tenant databases
  - Each tenant has its own isolated database
  - All tenant databases follow the same schema structure
  - Tables are prefixed by module (e.g., `test_case`, `bug`, `requirement`)