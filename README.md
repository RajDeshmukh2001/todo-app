# Todo Application – Spring Boot

## Problem Statement
Design and implement a robust RESTful API for a Task Management System. The application must be built using the Model-View-Controller (MVC) architectural pattern, where the "View" is represented by JSON responses, No Database. The primary goal is to demonstrate mastery of Object-Oriented Programming (OOP) and Clean Architecture.

#### Each task should have:
- A unique identifier (UUID).
- A title (required, max 100 chars).
- A description (required, max 500 chars).
- A status (Pending, In Progress, Completed).
- A priority level (Low, Medium, High).
- Timestamps (Created At, Updated At).

#### The Backend App must support:
- Create Task: Validate input and prevent duplicate titles.
- List All Tasks: Filterable by status or priority.
- Get Single Task: Retrieve details by ID; handle "Not Found" scenarios gracefully.
- Update Task: Partially update fields (e.g., just changing the status).
- Delete Task: Remove a task and return an appropriate status code.
---

## Project Goals

- Design a clean REST API using MVC
- Apply SOLID principles
- Keep controllers thin and services rich
- Ensure proper validation and error handling
- Build a system that is easy to scale and extend in the future

---

## API Functionalities

### 1. Create Task
- Accepts task details via request body
- Validates input
- Prevents duplicate task titles
- Sets default status to `PENDING`
- Automatically generates UUID and timestamps

---

## Tech Stack
- Java 17+
- Spring Boot
- Spring Web
- Spring Validation (Bean Validation)
- Maven
- In-memory data storage (No Database)

---

## Project Structure

```src/main/java/com/example/todoapp
src/main/java/com/mvc/todo
│
├── controller
│   └── TaskController.java
│
├── model
│   ├── Task.java
│   ├── Status.java
│   └── Priority.java
│
├── service
│   └── TaskService.java
│   
├── repository
│   ├── TaskRepository.java
│   └── InMemoryTaskRepository.java
│   
├── exception
│   └── DuplicateTaskTitleException.java
│
├── validator
│   └── CreateTaskRequest.java
│
└── TodoAppApplication.java
```

---

## Installation & Setup
### Prerequisites
- Java 17 or higher
- Maven

### Steps
1. Clone the repository
```
git clone https://github.com/RajDeshmukh2001/todo-app.git
```

2. Navigate to the project directory
```
cd todo-application
```

3. Build the project
```
mvn clean install
```

4. Run the application
```
./mvnw spring-boot:run
```

5. The application will be accessible at `http://localhost:8080`
 ---

## Testing the API

### You can test the API using:
- Postman

All responses are returned in JSON format with appropriate HTTP status codes.

---

## Out of Scope
- No database is used (data resets on application restart)
- Authentication and authorization are out of scope
- Pagination and sorting are not implemented
- Designed purely for learning MVC, OOP, and clean backend architecture
