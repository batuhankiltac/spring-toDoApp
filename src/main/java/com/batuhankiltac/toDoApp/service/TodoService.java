package com.batuhankiltac.toDoApp.service;

import com.batuhankiltac.toDoApp.entity.Todo;
import com.batuhankiltac.toDoApp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        todoRepository.findById(todo.getId());
        todo.setId(todo.getId());
        todo.setName(todo.getName());
        todo.setDescription(todo.getDescription());
        todo.setStatus(todo.getStatus());
        return todoRepository.save(todo);
    }

    public void deleteTodo(Todo todo) {
        todoRepository.delete(todo);
    }
}