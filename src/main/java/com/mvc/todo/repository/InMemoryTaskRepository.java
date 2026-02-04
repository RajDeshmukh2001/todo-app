package com.mvc.todo.repository;

import com.mvc.todo.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
    private final Map<String, Task> taskMap = new HashMap<>();

    @Override
    public Task save(Task task) {
        taskMap.put(task.getId(), task);
        return task;
    }

    @Override
    public boolean isExistsByTitle(String title) {
        return taskMap.values().stream().anyMatch(task -> task.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }
}
