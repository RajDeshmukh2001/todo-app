package com.mvc.todo.repository;

import com.mvc.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskRepository extends JpaRepository<Task, String> {

}
