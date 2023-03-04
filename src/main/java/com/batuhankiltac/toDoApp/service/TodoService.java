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
        Todo foundTodo = todoRepository.findById(todo.getId())
                .orElseThrow(() -> new RuntimeException("Todo not found."));
        foundTodo.setId(todo.getId());
        foundTodo.setName(todo.getName());
        foundTodo.setDescription(todo.getDescription());
        foundTodo.setStatus(todo.getStatus());
        return todoRepository.save(foundTodo);
    }

    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);
    }
}