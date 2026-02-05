package com.mvc.todo.service;

import com.mvc.todo.exception.DuplicateTaskTitleException;
import com.mvc.todo.exception.TaskNotFoundException;
import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Task;
import com.mvc.todo.repository.TaskRepository;
import com.mvc.todo.validator.UpdateTaskRequest;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String validateTextInput(String input, String fieldName, int maxLength) {
        String validTextInput = input.trim().replaceAll("\\s+", " ");
        if (validTextInput.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required and cannot be empty.");
        }
        if (validTextInput.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " must not exceed " + maxLength + " characters.");
        }
        return validTextInput;
    }

    public Task createTask(String title, String description, Priority priority) {
        if (taskRepository.existsByTitle(validateTextInput(title, "Title", 100))) {
            throw new DuplicateTaskTitleException("Task with title '" + title + "' already exists.");
        }
        Task task = new Task(title, description, priority);
        return taskRepository.save(task);
    }
}
