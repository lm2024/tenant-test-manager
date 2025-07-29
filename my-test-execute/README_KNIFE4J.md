# Knife4j Integration

This document describes how to use the Knife4j API documentation integrated into the `my-test-execute` service.

## Accessing the API Documentation

Once the service is running, you can access the Knife4j API documentation by navigating to the following URL in your web browser:

[http://localhost:8082/doc.html](http://localhost:8082/doc.html)

This will open the Knife4j interface, which provides a user-friendly way to explore and interact with the service's APIs.

## Key Features

- **API Exploration**: Browse all available API endpoints, view their details (parameters, request/response bodies, etc.), and understand their functionality.
- **API Testing**: Directly test the APIs from the documentation interface. You can provide parameters, send requests, and view the responses.
- **Model Viewer**: View the structure of the data models used in the APIs.

## Configuration

The Knife4j integration is configured in `com.tenant.test.config.Knife4jConfig`. The `knife4j-openapi2-spring-boot-starter` dependency is included in the `pom.xml`.
