package com.example.todos.exceptions;

public class TodoNotFoundException extends Throwable {
    public TodoNotFoundException(String message) {
        super(message);
    }
}
