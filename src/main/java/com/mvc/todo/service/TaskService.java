package com.mvc.todo.service;

import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Status;
import com.mvc.todo.model.Task;
import com.mvc.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title, String description, Priority priority) {
        if (taskRepository.isExistsByTitle((title.trim().replaceAll("\\s+ ", " ")))) {
            throw new IllegalArgumentException("Task with title '" + title + "' already exists.");
        }
        Task task = new Task(title, description, priority);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(Status status, Priority priority) {
        return taskRepository.findAll().stream()
                .filter(task -> status == null || task.getStatus() == status)
                .filter(task -> priority == null || task.getPriority() == priority)
                .collect(Collectors.toList());
    }
}
