package com.mvc.todo.controller;

import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Task;
import com.mvc.todo.service.TaskService;
import com.mvc.todo.validator.CreateTaskRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public Task create(@RequestBody CreateTaskRequest request) {
        return taskService.createTask(request.getTitle(), request.getDescription(), request.getPriority());
    }
}
