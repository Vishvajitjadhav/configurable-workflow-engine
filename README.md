# Configurable Approval Workflow Engine

## Overview

This project is a backend-based Configurable Approval Workflow Engine developed using Java and Spring Boot. The system supports dynamic multi-step approval workflows where workflow logic is configured entirely from the database instead of hardcoded business conditions.

The project demonstrates scalable backend architecture, role-based workflow processing, audit history tracking, transaction management, and JWT-based authentication.

---

## Tech Stack

* Java 17
* Spring Boot 3
* Spring Security
* JWT Authentication
* Spring Data JPA
* H2 Database
* Maven
* Lombok
* Postman

---

## Features

* Create workflow requests
* Dynamic database-driven approval flow
* Multi-step approval processing
* Role-based authorization
* Request approval and rejection
* Immutable approval audit history
* JWT-based login authentication
* Transactional workflow handling
* Exception handling using GlobalExceptionHandler
* H2 in-memory database support

---

## Project Structure

```text
controller
service
repository
entity
dto
security
config
exception
enums
```

---

## Workflow Configuration

Workflow behavior is dynamically managed using the `ApprovalStep` table.

### Sample LEAVE Workflow

1. APPROVER
2. ADMIN

### Sample EXPENSE Workflow

1. APPROVER
2. ADMIN

---

## Sample Users

| Username  | Password | Role      |
| --------- | -------- | --------- |
| requester | password | REQUESTER |
| approver  | password | APPROVER  |
| admin     | password | ADMIN     |

---

## API Endpoints

### Authentication

```http
POST /auth/login
```

### Create Request

```http
POST /requests
```

### Approve Request

```http
POST /requests/{id}/approve
```

### Reject Request

```http
POST /requests/{id}/reject
```

### Get Request Details

```http
GET /requests/{id}
```

### Get Approval History

```http
GET /requests/history/{id}
```

---

## H2 Database Console

```text
http://localhost:8080/h2-console
```

### JDBC URL

```text
jdbc:h2:mem:testdb
```

Username:

```text
sa
```

Password:

```text
(empty)
```

---

## Run Project

1. Clone repository
2. Open project in IntelliJ IDEA
3. Run WorkflowApplication.java
4. Test APIs using Postman

---

## Demo Video

Add your Google Drive video link below:

```text
https://drive.google.com/file/d/1EtLbprh5IfjePTjqmH_1bFSZLWAvLfUm/view?usp=sharing
```
---

## Author

Vishvajit Jadhav
