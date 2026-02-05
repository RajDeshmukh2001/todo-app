package com.mvc.todo.service;

import com.mvc.todo.exception.DuplicateTaskTitleException;
import com.mvc.todo.exception.TaskNotFoundException;
import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Status;
import com.mvc.todo.model.Task;
import com.mvc.todo.repository.TaskRepository;
import com.mvc.todo.validator.UpdateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

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
        String validTitle = validateTextInput(title, "Title", 100);
        String validDescription = validateTextInput(description, "Description", 500);
        if (taskRepository.existsByTitle(validTitle)) {
            throw new DuplicateTaskTitleException("Task with title '" + title + "' already exists.");
        }
        Task task = new Task(validTitle, validDescription, priority);
        return taskRepository.save(task);
    }

    public Task updateTask(String id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException("Task with id '" + id + "' not found.");
        }

        boolean updated = false;
        if (request.getTitle() != null) {
            String title = validateTextInput(request.getTitle(), "Title", 100);
            if (!title.equals(task.getTitle()) && taskRepository.existsByTitle(title)) {
                throw new DuplicateTaskTitleException("Task with title '" + title + "' already exists.");
            }
            task.setTitle(title);
            updated = true;
        }

        if (request.getDescription() != null) {
            String description = validateTextInput(request.getDescription(), "Description", 500);
            task.setDescription(description);
            updated = true;
        }

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
            updated = true;
        }

        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
            updated = true;
        }

        if (updated) {
            task.setUpdatedAt();
        }

        return taskRepository.save(task);
    }

    public List<Task> getTasks(Status status, Priority priority) {
        return taskRepository.findAll().stream()
                .filter(task -> status == null || task.getStatus() == status)
                .filter(task -> priority == null || task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public void deleteTask(String id) {
        if (taskRepository.findById(id) == null) {
            throw new TaskNotFoundException("Task with id '" + id + "' not found.");
        }
        taskRepository.deleteById(id);
    }
}
