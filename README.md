# 📌 Spring Boot To-Do Manager

A full-stack **Spring Boot To-Do Application** with user authentication, task management, intelligent status updates (In-Progress / Pending / Completed), dashboard analytics, and pagination.

---

## 📝 Overview

This is a simple and interactive **To-Do Management Application** built using:

- Spring Boot  
- Thymeleaf  
- MySQL  
- HTML/CSS  
- FontAwesome  

Users can:

✔ Register and Login  
✔ Create tasks  
✔ Edit tasks  
✔ View all tasks  
✔ Automatic task status update  
✔ Paginated task listing  
✔ Beautiful UI with icons & sidebar  

---

## 🚀 Features

### 🔐 User Authentication
- User Login / Logout  
- Session-based authentication  

### 📝 Task Management
- Add new tasks  
- Edit tasks  
- Auto-update task status:
  - **In Progress** → Today & future dates  
  - **Pending** → Automatically when date is past  
  - **Completed** → When marked by user  

### 📊 Dashboard Analytics
- Total tasks  
- Completed tasks  
- Pending tasks  
- Recent tasks  
- Pagination  

---

## 📁 Project Structure

```
src/
 ├── main/
 │   ├── java/com/springboot/
 │   │      ├── controller/
 │   │      ├── entity/
 │   │      ├── repository/
 │   │      └── ToDoApplication.java
 │   └── resources/
 │          ├── templates/
 │          │       ├── dashboard.html
 │          │       ├── tasks.html
 │          │       ├── addTask.html
 │          │       ├── editTask.html
 │          └── application.properties
```

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot  
- Spring MVC  
- Spring Data JPA  
- Hibernate  
- MySQL  
- Thymeleaf  
- HTML / CSS  
- Font Awesome Icons  

---

## ⚙️ Setup Instructions

### **1️⃣ Clone the Repository**

```bash
git clone https://github.com/Saurabhh0000/springboot-todo-manager.git
cd springboot-todo-manager
```

### **2️⃣ Configure Database**

Create your MySQL database:

```sql
CREATE DATABASE todo_app;
```

Now update **application.properties**:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_app
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### **3️⃣ Run the Application**

```bash
mvn spring-boot:run
```

### **4️⃣ Open in Browser**

👉 http://localhost:8080/login  
