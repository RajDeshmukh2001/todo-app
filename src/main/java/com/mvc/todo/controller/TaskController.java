package com.mvc.todo.controller;

import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Status;
import com.mvc.todo.model.Task;
import com.mvc.todo.service.TaskService;
import com.mvc.todo.validator.CreateTaskRequest;
import com.mvc.todo.validator.UpdateTaskRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Task> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority) {
        return taskService.getTasks(status, priority);
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable String id, @RequestBody UpdateTaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
