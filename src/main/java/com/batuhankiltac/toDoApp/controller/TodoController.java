package com.batuhankiltac.toDoApp.controller;

import com.batuhankiltac.toDoApp.entity.Todo;
import com.batuhankiltac.toDoApp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/getAll")
    public List<Todo> getAll() {
        return todoService.getAllTodos();
    }

    @PostMapping("/add")
    public Todo addTodo(@Valid @RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    @PutMapping("/update")
    public Todo updateTodo(@Valid @RequestBody Todo todo) {
        return todoService.updateTodo(todo);
    }

    @DeleteMapping("/delete")
    public void deleteTodo(@Valid @RequestBody Todo todo) {
        todoService.deleteTodo(todo);
    }
}