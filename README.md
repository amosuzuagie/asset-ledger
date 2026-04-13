# Asset Management System (Backend API)

A scalable backend system for managing organizational assets, built with Spring Boot and PostgreSQL. The system supports full asset lifecycle management, secure authentication, role-based access control, and audit tracking.

---

## 🚀 Features

- Secure authentication using JWT
- Role-Based Access Control (Admin, Finance, Manager, Auditor)
- Full Asset Lifecycle Management (Create, Assign, Update, Dispose)
- Asset movement tracking and audit logs
- Advanced search and filtering using dynamic queries (JPA Specifications)
- Bulk asset upload with validation and transactional safety
- Soft delete and data recovery support
- Clean layered architecture (Controller → Service → Repository)
- DTO-based request/response design for security and scalability

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- PostgreSQL
- JPA / Hibernate
- Maven
- JWT Authentication

---

## 🧱 Architecture

The project follows a layered architecture:

Controller → Service → Repository → Database

- Controllers handle API requests  
- Services contain business logic  
- Repositories handle database operations  
- DTOs ensure clean and secure data transfer  

---

## 🔐 Security

- JWT-based authentication
- Password encryption using BCrypt
- Role-based authorization for endpoints
- Stateless session management

---

## 🗄️ Database Design

Key entities include:

- Users
- Roles
- Assets
- Asset Categories
- Branches / Departments
- Asset Movement History

---

## 📦 API Endpoints

### Authentication
- POST /api/auth/login
- POST /api/auth/register

### Assets
- GET /api/assets
- POST /api/assets
- PUT /api/assets/{id}
- DELETE /api/assets/{id}

### Asset Assignment
- POST /api/assets/assign

---

## ⚙️ Setup & Installation

```bash
# Clone repository
git clone https://github.com/your-username/asset-management-system.git

# Navigate to project directory
cd asset-management-system

# Run application
./mvnw spring-boot:run