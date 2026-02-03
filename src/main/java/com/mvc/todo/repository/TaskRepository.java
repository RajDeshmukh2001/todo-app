package com.mvc.todo.repository;

import com.mvc.todo.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
}
