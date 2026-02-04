package com.mvc.todo.controller;

import com.mvc.todo.model.Task;
import com.mvc.todo.service.TaskService;
import com.mvc.todo.validator.CreateTaskRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task create(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.createTask(request.getTitle(), request.getDescription(), request.getPriority());
    }
}
