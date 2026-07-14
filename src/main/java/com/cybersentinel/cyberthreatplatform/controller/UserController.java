package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.entity.User;
import com.cybersentinel.cyberthreatplatform.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "Cyber Threat Intelligence Platform is Running!";
    }

    @PostMapping
    public User saveUser(@RequestBody @NonNull User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}