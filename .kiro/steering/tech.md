# Technology Stack & Build System

## Core Technologies

- **Java 8**
- **Spring Boot 2.5.x** - Application framework
- **Spring Data JPA/Hibernate** - ORM and data access
- **MySQL 5.x/8.x** - Database
- **Druid** - Connection pool management
- **Redis** - Caching and queue management
- **Swagger/Knife4j** - API documentation

## Libraries & Dependencies

- **Lombok** - Reduces boilerplate code
- **Hutool** - Utility library for common operations
- **EasyExcel** - Excel processing
- **Commons CSV** - CSV processing
- **Logback** - Logging with tenant context support
- **Logstash Encoder** - JSON format logging for ELK

## Build System

The project uses **Maven** as the build system with a multi-module structure.

### Common Build Commands

```bash
# Install the tenant-routing-starter (required first)
cd tenant-routing-starter
mvn clean install

# Build the entire project
mvn clean package

# Run a specific module
cd my-product-tenant
mvn spring-boot:run

# Run with specific profile
mvn spring-boot:run -Dspring.profiles.active=dev
```

## Docker Environment

Docker Compose files are available in the `docker` directory for setting up:
- MySQL database
- Redis cache
- Elasticsearch for logging
- ActiveMQ for messaging
- Nginx for reverse proxy

### Docker Commands

```bash
# Start all services
cd docker
docker-compose up -d

# Start specific service
docker-compose -f docker-compose-mysql.yml up -d

# Deploy application to Docker
./deploy.sh
```

## Testing

The system uses JUnit for unit testing. Test databases are automatically created and populated with test data.

## API Documentation

API documentation is available through Swagger UI and Knife4j:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Knife4j: `http://localhost:8080/doc.html`