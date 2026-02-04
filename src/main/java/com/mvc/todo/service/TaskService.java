package com.mvc.todo.service;

import com.mvc.todo.exception.DuplicateTaskTitleException;
import com.mvc.todo.model.Priority;
import com.mvc.todo.model.Task;
import com.mvc.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title, String description, Priority priority) {
        if (taskRepository.isExistsByTitle((title.trim().replaceAll("\\s+ ", " ")))) {
            throw new DuplicateTaskTitleException("Task with title '" + title + "' already exists.");
        }
        Task task = new Task(title, description, priority);
        return taskRepository.save(task);
    }
}
