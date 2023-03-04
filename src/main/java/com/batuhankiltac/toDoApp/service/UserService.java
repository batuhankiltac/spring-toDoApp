package com.batuhankiltac.toDoApp.service;

import com.batuhankiltac.toDoApp.entity.User;
import com.batuhankiltac.toDoApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User foundUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found."));
        foundUser.setId(user.getId());
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());
        return userRepository.save(foundUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}