package com.mvc.todo.dto;

import com.mvc.todo.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateTaskRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Priority is required")
    private Priority priority;

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public Priority getPriority() {
        return priority;
    }
}
