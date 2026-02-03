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

## Architecture Overview

The project follows **MVC + Clean Architecture**:

- **Controller Layer**
    - Handles HTTP requests and responses
    - Delegates business logic to services

- **Service Layer**
    - Contains business rules and validations
    - Coordinates between controller and repository

- **Model Layer**
    - Represents domain objects and enums
    - Independent of frameworks

- **Repository Layer**
    - In-memory data storage
    - Abstracted using interfaces for future DB support
