package com.example.todos.controllers;


import com.example.todos.entities.Todo;
import com.example.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/todos/new")
    public String newTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "new";
    }

    @PostMapping("/todos/save")
    public String saveTodo(Todo todo, RedirectAttributes redirectAttributes) {
        todoService.save(todo);

        redirectAttributes.addFlashAttribute("message", "Todo successfully saved");

        return "redirect:/todos";
    }
}
