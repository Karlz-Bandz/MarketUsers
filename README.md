# Users Service

Users Service is a microservice responsible for **authentication, authorization, and user management** in an e-commerce microservices architecture.

---

## Tech Stack

- Java 21+ / Spring Boot  
- Spring Security  
- Spring Data JPA  
- PostgreSQL  
- JWT (JSON Web Token)  
- Lombok  

---

## Features

- User registration and login  
- Password hashing with BCrypt  
- JWT-based authentication  
- Role-based access control (USER / ADMIN)  
- REST API endpoints for user management  

---

## Getting Started

### Prerequisites

- Java 21+
- PostgreSQL running locally
- Gradle

### Database Setup

Create a database for the service:

```bash
createdb market_users_db
