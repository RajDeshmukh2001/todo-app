package com.mvc.todo.exception;

public class DuplicateTaskTitleException extends RuntimeException {
    public DuplicateTaskTitleException(String title) {
        super(title);
    }
}
