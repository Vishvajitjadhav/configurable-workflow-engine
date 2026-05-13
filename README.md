# Configurable Approval Workflow Engine

## Tech Stack

* Java 17
* Spring Boot 3
* Spring Security
* JWT
* H2 Database
* Spring Data JPA

## Features

* Create workflow requests
* Multi-step approval workflow
* Dynamic DB-driven workflow configuration
* Approval/rejection handling
* Audit history tracking
* JWT-based login
* Transaction management

## Sample Users

| Username  | Password | Role      |
| --------- | -------- | --------- |
| requester | password | REQUESTER |
| approver  | password | APPROVER  |
| admin     | password | ADMIN     |

## Sample Workflow

LEAVE:

1. APPROVER
2. ADMIN

EXPENSE:

1. APPROVER
2. ADMIN

## Run Project

1. Open project in IntelliJ
2. Run WorkflowApplication.java
3. Access APIs using Postman

## H2 Console

http://localhost:8080/h2-console

JDBC URL:
jdbc:h2:mem:testdb
