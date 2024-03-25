package com.TodoLists.Application.Exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task Not Found");
    }
}
