package com.example.todos.controllers;


import com.example.todos.entities.Todo;
import com.example.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TodoController {
    @Autowired private TodoService todoService;

    @GetMapping("/todos")
    public String showTodos(Model model) {
        List<Todo> todos = todoService.listAll();
        model.addAttribute("todos", todos);

        return "todos";
    }
}
