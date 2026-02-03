package com.mvc.todo.repository;

import com.mvc.todo.model.Task;

public interface TaskRepository {
    Task saveTask(Task task);
}
