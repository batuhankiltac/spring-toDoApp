package com.batuhankiltac.toDoApp.service;

import com.batuhankiltac.toDoApp.entity.User;
import com.batuhankiltac.toDoApp.repository.UserRepository;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

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
        final List<User> users = Arrays.asList(user1, user2);

        // When
        userService.getAllUsers();

        // Then
        verify(userRepository).findAll();
        assertThat(users).isNotEmpty();
        assertThat(user1.getId()).isNotEqualTo(user2.getId());
        assertThat(user2.getUsername()).isEqualTo("test-username2");
    }

    @Test
    public void it_should_save_user() {
        // Given
        final User user = User.builder()
                .id(1)
                .username("test-username")
                .password("123456")
                .build();

        // When
        userService.addUser(user);

        // Then
        verify(userRepository).save(user);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isEqualTo("test-username");
    }

    @Test
    public void it_should_delete_user() {
        // Given
        final User user = User.builder()
                .id(1)
                .build();

        // When
        userService.deleteUser(user.getId());

        // Then
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    public void it_should_update_user() {
        // Given
        final User user1 = User.builder()
                .id(1)
                .username("test-username1")
                .password("123456")
                .build();
        final User user2 = User.builder()
                .id(1)
                .username("test-username2")
                .password("1234567")
                .build();
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user2));

        // When
        userService.updateUser(user1);

        // Then
        verify(userRepository).findById(user1.getId());
        verify(userRepository).save(user2);
    }
}
