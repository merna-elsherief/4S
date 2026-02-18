# 🚀 4S Technology - Backend API

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-green?style=for-the-badge&logo=spring)
![MongoDB](https://img.shields.io/badge/MongoDB-Database-47A248?style=for-the-badge&logo=mongodb)
![JWT](https://img.shields.io/badge/Security-JWT-black?style=for-the-badge&logo=json-web-tokens)
![Swagger](https://img.shields.io/badge/Swagger-UI-85EA2D?style=for-the-badge&logo=swagger)

This repository contains the backend service for the **4S Technology** website. It is built using **Spring Boot 3** and **MongoDB**, providing a robust, secure, and fast RESTful API for content management (Blogs, FAQs) and handling customer inquiries.

🔗 **Frontend Application:** [https://4s-techology.vercel.app](https://4s-techology.vercel.app)

---

## ✨ Key Features

* 🔐 **Secure Admin Authentication:** Stateless JWT authentication for the admin panel without requiring a dedicated user database collection.
* 📄 **Blog Management:** Full CRUD operations for administrators. Includes SEO-friendly unique slugs and paginated reading for public users.
* ❓ **FAQ Management:** Full CRUD operations for managing Frequently Asked Questions.
* 📬 **Contact Us System:** Public endpoints for customers to submit inquiries, and secured endpoints for admins to review them.
* 🌐 **Dynamic CORS Configuration:** Configured to accept requests from specific origins (e.g., Vercel and Localhost).
* 📑 **Interactive API Documentation:** Integrated with **Swagger UI** for automated documentation and live endpoint testing.
* ⚠️ **Global Exception Handling:** Standardized and clean JSON error responses across the entire application.

---

## 🛠️ Tech Stack

* **Core:** Java 17, Spring Boot 3.2.5
* **Database:** Spring Data MongoDB
* **Security:** Spring Security 6, JJWT (0.12.5)
* **API Documentation:** SpringDoc OpenAPI (Swagger 3)
* **Utilities:** Lombok, Maven

---

## ⚙️ Setup & Installation

Follow these steps to run the project locally:

### 1. Clone the Repository
```bash
git clone https://github.com/YourUsername/4s-backend.git
cd 4s-backend
```

### 2. Configure Environment Variables
Ensure your `src/main/resources/application.properties` is configured correctly. You can update the values based on your local or production environment:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/fours_db

# Admin Credentials (In-Memory)
admin.email=admin@4s-tech.com
admin.password=admin123

# JWT Configuration (Ensure the secret is long enough for HS256)
jwt.secret=YourSuperSecretKeyForJwtAuthenticationMustBeLongEnough
jwt.expiration=86400000

# CORS Configuration (Comma-separated origins, no spaces)
app.cors.allowed-origins=http://localhost:3000,https://4s-techology.vercel.app
```

### 3. Run the Application
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`.

---

## 📖 API Documentation (Swagger)

This project includes an embedded **Swagger UI** to explore and test the APIs directly from your browser.
Once the application is running, navigate to:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

*Note: To test secured endpoints, login via `/api/auth/login`, copy the generated token, click the "Authorize" button at the top of the Swagger page, and paste the token.*

---

## 📡 API Endpoints Summary

### 👤 Authentication
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Authenticate admin and get JWT | **Public** |

### 📝 Blogs
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/blogs?page=0&size=10` | Get all blogs (Paginated) | **Public** |
| `GET` | `/api/blogs/{slug}` | Get a single blog by its unique slug | **Public** |
| `POST` | `/api/admin/blogs` | Create a new blog | 🔒 **Admin** |
| `PATCH` | `/api/admin/blogs/{id}` | Update an existing blog | 🔒 **Admin** |
| `DELETE` | `/api/admin/blogs/{id}` | Delete a blog | 🔒 **Admin** |

### ❓ FAQs
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/faqs` | Get all FAQs | **Public** |
| `POST` | `/api/admin/faqs` | Create a new FAQ | 🔒 **Admin** |
| `DELETE` | `/api/admin/faqs/{id}` | Delete an FAQ | 🔒 **Admin** |

### 📞 Contact Us
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/contact` | Submit a contact form | **Public** |
| `GET` | `/api/admin/contact` | View all submitted messages | 🔒 **Admin** |

---