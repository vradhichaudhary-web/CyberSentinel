package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.dto.LoginRequest;
import com.cybersentinel.cyberthreatplatform.dto.LoginResponse;
import com.cybersentinel.cyberthreatplatform.dto.RegisterRequest;
import com.cybersentinel.cyberthreatplatform.service.AuthService;
import org.springframework.web.bind.annotation.*;
import com.cybersentinel.cyberthreatplatform.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ===============================
    // LOGIN
    // ===============================

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);

    }

    @PostMapping("/register")
public String register(@RequestBody RegisterRequest request) {

    return authService.register(request);

}  


    // ===============================
    // ENCRYPT OLD PASSWORDS
    // ===============================

    @GetMapping("/encrypt")
    public String encryptPasswords() {

        return authService.encryptExistingPasswords();

    }

}