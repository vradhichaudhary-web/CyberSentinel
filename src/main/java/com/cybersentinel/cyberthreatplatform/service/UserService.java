package com.cybersentinel.cyberthreatplatform.service;

import com.cybersentinel.cyberthreatplatform.entity.User;
import com.cybersentinel.cyberthreatplatform.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public User saveUser(@NonNull User user) {

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);

    }

}