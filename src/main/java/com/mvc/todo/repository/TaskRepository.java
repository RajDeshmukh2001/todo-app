package com.mvc.todo.repository;

import com.mvc.todo.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
    Task findById(String id);
    void deleteById(String id);
    boolean isExistsById(String id);
    boolean isExistsByTitle(String title);
}
