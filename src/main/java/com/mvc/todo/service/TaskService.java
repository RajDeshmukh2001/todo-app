package com.mvc.todo.service;

import com.mvc.todo.dto.CreateTaskRequest;
import com.mvc.todo.exception.DuplicateTaskTitleException;
import com.mvc.todo.exception.TaskNotFoundException;
import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Status;
import com.mvc.todo.model.Task;
import com.mvc.todo.repository.TaskRepository;
import com.mvc.todo.dto.UpdateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String validateTextInput(String input, String fieldName, int maxLength) {
        String textInput = input.trim().replaceAll("\\s+", " ");
        if (textInput.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required and cannot be empty.");
        }
        if (textInput.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " must not exceed " + maxLength + " characters.");
        }
        return textInput;
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
        if (request.getTitle() != null && !request.getTitle().equalsIgnoreCase(task.getTitle())) {
            String title = validateTextInput(request.getTitle(), "Title", 100);
            if (!title.equals(task.getTitle()) && taskRepository.existsByTitle(title)) {
                throw new DuplicateTaskTitleException("Task with title '" + title + "' already exists.");
            }
            task.setTitle(title);
            updated = true;
        }

        if (request.getDescription() != null && !request.getDescription().equalsIgnoreCase(task.getDescription())) {
            String description = validateTextInput(request.getDescription(), "Description", 500);
            task.setDescription(description);
            updated = true;
        }

        if (request.getStatus() != null && request.getStatus() != task.getStatus()) {
            task.setStatus(request.getStatus());
            updated = true;
        }

        if (request.getPriority() != null && request.getPriority() != task.getPriority()) {
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

    public Task getTaskById(String id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException("Task with id '" + id + "' not found.");
        } else {
            return task;
        }
    }

    public void deleteTaskById(String id) {
        if(taskRepository.findById(id) == null) {
            throw new TaskNotFoundException("Task with id '" + id + "' not found.");
        }

        taskRepository.deleteById(id);
    }

    public List<Task> createTasks(List<CreateTaskRequest> requests) {
        List<Task> createdTasks = new ArrayList<>();

        for (CreateTaskRequest request: requests) {
            String title = request.getTitle();
            String description = request.getDescription();
            Priority priority = request.getPriority();

            String validTitle = validateTextInput(title, "Title", 100);
            String validDescription = validateTextInput(description, "Description", 500);

            // Checking if there is any repeated title in the request's list of tasks
            boolean alreadyInList = createdTasks.stream().
                    anyMatch(task -> task.getTitle().equalsIgnoreCase(validTitle));

            if(alreadyInList) {
                throw new DuplicateTaskTitleException("Duplicate title in the provided list:" + validTitle);
            }

            if (taskRepository.existsByTitle(validTitle)) {
                throw new DuplicateTaskTitleException("Task with title '" + title + "' already exists.");
            }

            Task createdTask = new Task(validTitle, validDescription, priority);

            createdTasks.add(createdTask);
        }

        return taskRepository.saveAll(createdTasks);
    }
}
