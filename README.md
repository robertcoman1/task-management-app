# To-Do Web App

A full-stack to-do application with task management features. Users can add, delete, and mark tasks as completed. The application is built with Spring Boot for the backend, PostgreSQL (running in Docker) for persistent storage, and a static frontend built with HTML, CSS, and JavaScript.

---

## Features

- Add new tasks  
- Delete existing tasks  
- Mark tasks as completed or incomplete  
- Persistent storage using PostgreSQL in Docker  
- RESTful backend built with Spring Boot  
- Simple and responsive frontend

---

## Prerequisites

- Java JDK 17 
- Maven 3.8+  
- Docker and Docker Compose installed and running  

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/robertcoman1/to-do-web-app.git
cd to-do-web-app
```

### 2. Start the Database with Docker
From the project root (or wherever your `docker-compose.yml` is located):
```bash
docker-compose up -d
```

This will start a PostgreSQL container with the configuration specified in `docker-compose.yml`.

### 3. Configure the Backend
Update the `application.properties` file in `backend/src/main/resources` with your database connection details. Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tododb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 4. Build and Run the Backend
Navigate to the backend folder and start the Spring Boot application:

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend will be available at `http://localhost:8080`.

### 5. Run the Frontend
Open `frontend/index.html` in your browser, or serve it with a local web server.  
If you have the Live Server extension in VS Code, right-click `index.html` and select "Open with Live Server".  

Ensure that the JavaScript code in `script.js` is configured to call the correct backend API URL (e.g., `http://localhost:8080/api/tasks`).

---

## Usage

- Open the frontend in a browser.  
- Add tasks using the input form.  
- Mark tasks as complete or incomplete.  
- Delete tasks when no longer needed.  
- All tasks are stored persistently in PostgreSQL.
