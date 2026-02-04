package com.mvc.todo.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private final String id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String title, String description, Priority priority) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.status = Status.PENDING;
        this.priority = priority;
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
    public void setStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
        this.updatedAt = LocalDateTime.now();
    }
}
