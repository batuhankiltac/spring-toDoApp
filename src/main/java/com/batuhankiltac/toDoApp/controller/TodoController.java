package com.batuhankiltac.toDoApp.controller;

import com.batuhankiltac.toDoApp.entity.Todo;
import com.batuhankiltac.toDoApp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<Todo> getAll() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo addTodo(@Valid @RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    @PutMapping
    public Todo updateTodo(@Valid @RequestBody Todo todo) {
        return todoService.updateTodo(todo);
    }

    @DeleteMapping
    public void deleteTodo(@RequestParam Integer id) {
        todoService.deleteTodo(id);
    }
}