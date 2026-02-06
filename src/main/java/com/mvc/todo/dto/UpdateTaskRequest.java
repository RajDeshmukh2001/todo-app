package com.mvc.todo.dto;

import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Status;

public class UpdateTaskRequest {
    private String title;
    private String description;
    private Priority priority;
    private Status status;

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Priority getPriority() { return priority; }
    public Status getStatus() { return status; }
}
