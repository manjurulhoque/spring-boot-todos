package com.example.todos.controllers;


import com.example.todos.entities.Todo;
import com.example.todos.exceptions.TodoNotFoundException;
import com.example.todos.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class TodoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
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
    public String saveTodo(@Valid Todo todo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "new";
        }
        LOGGER.info(todo.toString());
        todoService.save(todo);

        redirectAttributes.addFlashAttribute("message", "Todo successfully saved");

        return "redirect:/todos";
    }

    @GetMapping("/todos/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Todo todo = todoService.getById(id);
            model.addAttribute("todo", todo);
            return "new";
        } catch (TodoNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage().toString());
            return "redirect:/todos";
        }
    }

    @GetMapping("/todos/delete/{id}")
    public String deleteTodo(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        todoService.delete(id);
        return "redirect:/todos";
    }
}
