package com.mvc.todo.controller;

import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Status;
import com.mvc.todo.model.Task;
import com.mvc.todo.service.TaskService;
import com.mvc.todo.dto.CreateTaskRequest;
import com.mvc.todo.dto.UpdateTaskRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public Task getTask(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
