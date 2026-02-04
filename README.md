# Todo Application – Spring Boot

## Problem Statement
Design and implement a robust RESTful API for a Task Management System. The application must be built using the Model-View-Controller (MVC) architectural pattern, where the "View" is represented by JSON responses, No Database. The primary goal is to demonstrate mastery of Object-Oriented Programming (OOP) and Clean Architecture.

## Tech Stack

- Language: [Java 21](https://www.oracle.com/in/java/technologies/downloads/#java21)  
- Framework: [Spring Boot 3.4.2](https://spring.io/blog/2025/02/20/spring-boot-3-4-3-available-now)  
- Build Tool: [Maven](https://maven.apache.org/)  
- Architecture: [MVC](https://en.wikipedia.org/wiki/Model-view-controller)



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

## Features to Be Implemented

### Task Entity
Each task will have:
- Unique identifier (**UUID**)
- Title (required, max 100 characters, must be unique)
- Description (required, max 500 characters)
- Status (`PENDING`, `IN_PROGRESS`, `COMPLETED`)
- Priority (`LOW`, `MEDIUM`, `HIGH`)
- Timestamps:
    - Created At
    - Updated At

---

## Features Implemented

### 1. Create Task API
Allows users to create new tasks with strict validation rules.
- **Auto-generated ID:** Each task gets a unique UUID.
- **Timestamps:** Automatically records `createdAt` and `updatedAt`.
- **Default Status:** New tasks are set to `PENDING` by default.
- **Duplicate Prevention:** The system checks if a task with the same title already exists before saving.
- **Input Validation:** Ensures titles and descriptions meet length requirements.

```markdown
### Clone \& Run the Application
You need to get the code and start the backend server first.

```bash
# 1. Clone the repository
git clone https://github.com/RajDeshmukh2001/todo-app.git

# 2. Navigate into the project directory
cd todo-app

# 3. Start the server (Using Maven Wrapper)
./mvnw spring-boot:run
```

Success: You will see Started TodoApplication in ... seconds in your terminal. The server is now running at http://localhost:8080.

### Test with Postman
To create a task, send a POST request with the JSON payload shown below.

- Open Postman.
- Create a new request.
- Method: POST
- URL: `http://localhost:8080/v1/api/tasks/create`
- Body: raw `JSON` and paste the payload.

```json
{
    "title": "   Complete Backend Demo   ",
    "description": "Test the Create Task feature using Postman and verify the response.",
    "priority": "HIGH"
}
```

### Verify the Result

Expected Success Response \(200 OK\):

```json
{
    "id": "c5f8d2-418b-4c5c-a5cb-150fd7200ab6",
    "title": "Complete Backend Demo",
    "description": "Test the Create Task feature using Postman and verify the response.",
    "status": "PENDING",
    "priority": "HIGH",
    "createdAt": "2026-02-04T12:00:00.000",
    "updatedAt": "2026-02-04T12:00:00.000"
}
```

 Expected Failure \(Duplicate Title\):

Plaintext:

```
Error: Task with title 'Complete Backend Demo' already exists.
```
### 2. List All Tasks API
Retrieve all tasks with optional filtering capabilities.

#### Endpoint
```
GET /v1/api/tasks
```

#### Query Parameters (Optional)
| Parameter | Values | Description |
|-----------|--------|-------------|
| `status` | `PENDING`, `IN_PROGRESS`, `COMPLETED` | Filter tasks by status |
| `priority` | `LOW`, `MEDIUM`, `HIGH` | Filter tasks by priority |


**Test with Postman:**
- Open Postman.
- Create a new request.
- Method: GET
- URL: `http://localhost:8080/v1/api/tasks` (or add query parameters like `?priority=HIGH` or `priority=MEDIUM&status=PENDING` , parameters are currently in Uppercase ex, HIGH,LOW,MEDIUM for priority, COMPLETED, IN_PROGESS,  PENDING for status)
- Click Send.

#### Expected Response (200 OK)
```json
[
    {
        "id": "c5f8d2-418b-4c5c-a5cb-150fd7200ab6",
        "title": "Complete Backend Demo",
        "description": "Verify the Create Task feature.",
        "status": "PENDING",
        "priority": "HIGH",
        "createdAt": "2026-02-04T12:00:00.000",
        "updatedAt": "2026-02-04T12:00:00.000"
    }
]
```
