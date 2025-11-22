# springboot-todo-manager
A full-stack Spring Boot To-Do application with user authentication, intelligent status updates (In-Progress / Pending / Completed), task editing, dashboard analytics, and pagination for large task lists.

📝 Overview
This is a simple and interactive To-Do Management Application built using Spring Boot, Thymeleaf, and MySQL.
Users can register, log in, create tasks, edit tasks, update task statuses, and view all tasks with pagination.


The application also features:

✔ User authentication
✔ Task categorization (Completed / Pending / In-Progress)
✔ Automatic task status update based on due date
✔ Pagination in Dashboard & Tasks Page
✔ Responsive UI with icons and sidebar navigation

🚀 Features

🔐 User Authentication
   Login / Logout
   Session-based user tracking
   
📝 Task Management
   Add new tasks
   Edit existing tasks
   Auto-update task status:
       In Progress → For today & future tasks
       Pending → Automatically becomes pending if the date has passed
       Completed → When marked by the user

📊 Dashboard
    Total Tasks
    Completed Tasks
    Pending Tasks
    Recent tasks table
    Pagination support

📂 Project Structure

src/
 ├── main/
 │   ├── java/com/springboot
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


🛠️ Technologies Used
    Java 17+
    Spring Boot
    Spring MVC
    Spring Data JPA
    Hibernate
    MySQL
    Thymeleaf
    HTML / CSS / FontAwesome

⚙️ Setup Instructions

1️⃣ Clone the Repository

git clone https://github.com/Saurabhh0000/springboot-todo-manager.git
cd springboot-todo-manager

2️⃣ Configure Database
    
  Create a MySQL database:
   CREATE DATABASE todo_app;

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/todo_app
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

3️⃣ Run the Application
   mvn spring-boot:run

4️⃣ Visit in Browser

http://localhost:8080/login




