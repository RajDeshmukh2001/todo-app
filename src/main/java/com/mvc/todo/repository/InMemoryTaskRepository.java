package com.mvc.todo.repository;

import com.mvc.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    @Override
    public Task saveTask(Task task) {
        tasks.add(task);
        return task;
    }
}
