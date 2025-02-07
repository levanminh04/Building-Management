Building Management Web Application

Overview

This project is a RESTful web application developed using Spring Boot for backend services and jQuery AJAX for API calls. The application facilitates the management of buildings, customers, and transactions for a property management company. It provides distinct functionalities for different user roles including managers, staff, and general users.

Features

User Roles and Permissions

General User:

View the list of buildings.

Submit contact information for sales inquiries.

Staff:

View and manage assigned buildings.

Handle customer interactions and maintain transaction records.

Manager:

Perform CRUD operations on buildings (including text and image data stored on Cloudinary).

Manage and assign buildings to staff.

Manage staff information.

Handle CRUD operations for customer data.

Assign customers to staff for personalized management.

Security Features

Spring Security with JWT:

Authentication and authorization using JWT tokens stored in HttpOnly cookies.

Automatic token refresh mechanism when access tokens expire.

Token revocation upon user logout.

Role-based Access Control: Fine-grained permission control for managers, staff, and general users.

Secure Password Reset:

OTP-based password reset functionality using Redis for secure, time-limited OTP storage.

OTPs expire after 5 minutes to enhance security.

Building Management

Managers can add, update, delete, and view building information.

Staff can view but not modify building information assigned to them.

Customer Management

Managers can handle customer data with full CRUD functionality.

Managers assign customers to specific staff members for better service.

Staff members maintain customer interaction logs and transaction histories.

Additional Features

Image storage for building details using Cloudinary.

Email notifications for OTP-based password recovery using Gmail SMTP.

Technical Details

Backend Technologies

Framework: Spring Boot 3.x

Server: Apache Tomcat 10.x

Servlet API: Jakarta Servlet API 6.x

Deployment: Packaged as .war file

Design Patterns: Builder Pattern for object construction

Architecture: MVC (Model, View, Controller) with layered structure

Controller

Service

Repository

Frontend Technologies

View Engine: JSP files for dynamic content rendering

AJAX: jQuery for asynchronous API calls

Database and Storage

Database: MySQL for persistent data storage

Redis: For secure and time-limited OTP storage

Cloudinary: For image storage related to building information

Security

Authentication: JWT stored in HttpOnly cookies

Authorization: Role-based access control

Password Security: Secure password reset via email OTP

Setup Instructions

Prerequisites

Java Development Kit (JDK) 17 or higher

Apache Maven

MySQL Server

Redis Server

Running the Application Locally

Clone the repository:

git clone <repository_url>
cd <project_directory>

Configure the database connection in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>

Configure Redis and Cloudinary credentials in application.properties.

Build and run the application:

mvn clean package
java -jar target/<project_name>.war

Access the application at http://localhost:8080.

Deployment

The application can be deployed to any servlet container supporting Jakarta Servlet API 6.x, such as Apache Tomcat 10.x.

Future Enhancements

Improve frontend design for a more user-friendly experience.

Implement additional features for enhanced reporting and analytics.

Integrate third-party APIs for better customer engagement.

###Conclusion

This project showcases a comprehensive understanding of backend development with Spring Boot, secure application design using JWT and Redis, and practical knowledge of web technologies essential for a Java Backend Developer role.

![image](https://github.com/user-attachments/assets/1f9b41c9-1b86-4312-835f-8f76434cc610)
