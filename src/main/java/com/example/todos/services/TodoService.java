package com.example.todos.services;

import com.example.todos.entities.Todo;
import com.example.todos.exceptions.TodoNotFoundException;
import com.example.todos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> listAll() {
        return todoRepository.findAll();
    }

    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    public Todo getById(Integer id) throws TodoNotFoundException {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            return todo.get();
        }
        throw new TodoNotFoundException("Couldn't find any todo with give id");
    }

    public void delete(Integer id) {
        todoRepository.deleteById(id);
    }
}
