package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.dto.LoginRequest;
import com.cybersentinel.cyberthreatplatform.dto.LoginResponse;
import com.cybersentinel.cyberthreatplatform.entity.User;
import com.cybersentinel.cyberthreatplatform.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return new LoginResponse(
                    false,
                    "User not found",
                    ""
            );
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return new LoginResponse(
                    false,
                    "Invalid Password",
                    ""
            );
        }

        return new LoginResponse(
                true,
                "Login Successful",
                user.getRole()
        );
    }
}