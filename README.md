# 🚀 4S Technology - Backend API

A robust, feature-based RESTful API built with **Spring Boot** and **MongoDB** for managing the 4S Technology platform. This backend provides comprehensive endpoints for Blogs, FAQs, and Contact Us submissions, featuring bilingual support (English/Arabic) and secure Admin routes using JWT authentication.

---

## 🛠️ Tech Stack

* **Framework:** Spring Boot 3.x
* **Database:** MongoDB
* **Security:** Spring Security + JWT (JSON Web Tokens)
* **Utilities:** Lombok, Maven
* **Architecture:** Feature-Based (Domain-Driven Design concepts)

---

## 📂 Project Structure

The project follows a clean **Feature-Based** architecture to ensure scalability, maintainability, and a clear separation of concerns:

src/main/java/com/example/fours/
├── blog/             # Blog models, repositories, services, and controllers
├── faq/              # FAQ models, repositories, services, and controllers
├── contact/          # Contact models, repositories, services, and controllers
├── payload/          # Shared DTOs (Request & Response objects)
├── security/         # JWT Authentication filters and Security configurations
├── exception/        # Global Exception handling
└── FoursApplication.java # Main application entry point

---

## ✨ Key Features

* **Bilingual Support (🌐):** Native support for English and Arabic content across Blogs and FAQs (e.g., titleEn, titleAr).
* **Separation of Concerns:** Distinct controllers for Admin (Secured) and Public (Open) access.
* **Partial Updates (PATCH):** Update specific fields in database records without sending the entire object payload.
* **Pagination:** Built-in pagination for fetching large datasets (Blogs, FAQs, Contact Messages).
* **Security:** JWT-based authentication for all admin-level mutations (POST, PATCH, DELETE).

---

## ⚙️ Setup & Installation

### Prerequisites
* Java 17 or higher
* Maven
* MongoDB (Local instance or MongoDB Atlas cluster)

### Steps to Run

1. Clone the repository:
   git clone https://github.com/merna_elsherief/4s-backend.git
   cd 4s-backend

2. Configure Environment Variables:
   Update your src/main/resources/application.properties with your MongoDB URI and JWT Secret:
   spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster.mongodb.net/4s_db
   jwt.secret=YOUR_SUPER_SECRET_KEY_MAKE_IT_LONG_AND_SECURE

3. Build and Run:
   mvn clean install
   mvn spring-boot:run

The application will start on http://localhost:8080.

---

## 📡 API Endpoints Overview

For detailed API testing and integration, please import the provided **Postman Collection** (4S_Technology_APIs.json) into your Postman workspace.

### 🔐 Authentication
* POST /api/auth/login - Authenticate admin and receive JWT token.

### 📝 Blogs
Public Endpoints:
* GET /api/blogs - Get all published blogs (Paginated).
* GET /api/blogs/{slug} - Get a specific blog by its slug.

Admin Endpoints (Requires JWT):
* POST /api/admin/blogs - Create a new blog.
* PATCH /api/admin/blogs/{id} - Partially update a blog.
* DELETE /api/admin/blogs/{id} - Delete a blog.

### ❓ FAQs
Public Endpoints:
* GET /api/faqs - Get all FAQs (Paginated).

Admin Endpoints (Requires JWT):
* POST /api/admin/faqs - Create a new FAQ.
* PATCH /api/admin/faqs/{id} - Partially update an FAQ.
* DELETE /api/admin/faqs/{id} - Delete an FAQ.

### 📬 Contact Us
Public Endpoints:
* POST /api/contact - Submit a new contact message.

Admin Endpoints (Requires JWT):
* GET /api/admin/contact - Retrieve contact messages (Paginated).

---

## 🛡️ Authentication Note
All /api/admin/** endpoints are protected and require a valid JWT token.
Pass the token in the HTTP request header as follows:

Authorization: Bearer <your_jwt_token>