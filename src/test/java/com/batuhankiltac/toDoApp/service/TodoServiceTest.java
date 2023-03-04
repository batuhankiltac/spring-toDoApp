package com.batuhankiltac.toDoApp.service;

import com.batuhankiltac.toDoApp.entity.Todo;
import com.batuhankiltac.toDoApp.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    public void it_should_get_all_todos() {
        // Given
        final Todo todo1 = Todo.builder()
                .id(1)
                .description("test-description1")
                .build();
        final Todo todo2 = Todo.builder()
                .id(2)
                .description("test-description2")
                .build();
        final List<Todo> todos = Arrays.asList(todo1, todo2);

        // When
        todoService.getAllTodos();

        // Then
        verify(todoRepository).findAll();
        assertThat(todos).isNotEmpty();
        assertThat(todo1.getId()).isNotEqualTo(todo2.getId());
        assertThat(todo2.getDescription()).isEqualTo("test-description2");
    }

    @Test
    public void it_should_save_todo() {
        // Given
        final Todo todo = Todo.builder()
                .id(1)
                .description("test-description")
                .build();

        // When
        todoService.addTodo(todo);

        // Then
        verify(todoRepository).save(todo);
        assertThat(todo.getId()).isNotNull();
        assertThat(todo.getDescription()).isEqualTo("test-description");
    }

    @Test
    public void it_should_delete_todo() {
        // Given
        final Todo todo = Todo.builder()
                .id(1)
                .build();

        // When
        todoService.deleteTodo(todo.getId());

        // Then
        verify(todoRepository).deleteById(todo.getId());
    }

    @Test
    public void it_should_update_todo() {
        // Given
        final Todo todo1 = Todo.builder()
                .id(1)
                .description("test-description1")
                .build();
        final Todo todo2 = Todo.builder()
                .id(2)
                .description("test-description2")
                .build();
        when(todoRepository.findById(todo1.getId())).thenReturn(Optional.of(todo2));

        // When
        todoService.updateTodo(todo1);

        // Then
        verify(todoRepository).findById(todo1.getId());
        verify(todoRepository).save(todo2);
    }
}