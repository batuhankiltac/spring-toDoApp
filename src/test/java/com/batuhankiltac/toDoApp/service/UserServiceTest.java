package com.batuhankiltac.toDoApp.service;

import com.batuhankiltac.toDoApp.entity.Todo;
import com.batuhankiltac.toDoApp.entity.User;
import com.batuhankiltac.toDoApp.repository.TodoRepository;
import com.batuhankiltac.toDoApp.repository.UserRepository;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void it_should_save_user() {
        // Given
        final User user = User.builder()
                .id(1)
                .username("test-username")
                .password("123456")
                .build();

        // When
        when(userService.addUser(user)).thenReturn(user);

        // Then
        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isEqualTo("test-username");
    }

    @Test
    public void it_should_delete_user() {
        // Given
        final User user = User.builder().build();

        // When
        userService.deleteUser(user);

        // Then
        verify(userRepository).delete(user);
    }

    @Test
    public void it_should_get_all_users() {
        // Given
        final User user1 = User.builder()
                .id(1)
                .username("test-username1")
                .password("123456")
                .build();
        final User user2 = User.builder()
                .id(2)
                .username("test-username2")
                .password("123456")
                .build();
        final List<User> all = new ArrayList<>();
        all.add(user1);
        all.add(user2);

        // When
        when(userService.getAllUsers()).thenReturn(all);

        // Then
        assertThat(user1.getId()).isNotEqualTo(user2.getId());
        assertThat(user2.getUsername()).isEqualTo("test-username2");
    }
}
