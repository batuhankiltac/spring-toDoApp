package com.batuhankiltac.toDoApp.service;

import com.batuhankiltac.toDoApp.entity.Todo;
import com.batuhankiltac.toDoApp.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    public void it_should_save_todo() {
        // Given
        final Todo todo = Todo.builder()
                .id(1)
                .description("test-description")
                .build();

        // When
        when(todoService.addTodo(todo)).thenReturn(todo);

        // Then
        assertThat(todo.getId()).isNotNull();
        assertThat(todo.getDescription()).isEqualTo("test-description");
    }

    @Test
    public void it_should_delete_todo() {
        // Given
        final Todo todo = Todo.builder().build();

        // When
        todoService.deleteTodo(todo);

        // Then
        verify(todoRepository).delete(todo);
    }

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
        final List<Todo> all = new ArrayList<>();
        all.add(todo1);
        all.add(todo2);

        // When
        when(todoService.getAllTodos()).thenReturn(all);

        // Then
        assertThat(todo1.getId()).isNotEqualTo(todo2.getId());
        assertThat(todo2.getDescription()).isEqualTo("test-description2");
    }
}