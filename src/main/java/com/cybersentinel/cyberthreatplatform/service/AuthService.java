package com.cybersentinel.cyberthreatplatform.service;

import com.cybersentinel.cyberthreatplatform.dto.LoginRequest;
import com.cybersentinel.cyberthreatplatform.dto.LoginResponse;
import com.cybersentinel.cyberthreatplatform.dto.RegisterRequest;
import com.cybersentinel.cyberthreatplatform.entity.User;
import com.cybersentinel.cyberthreatplatform.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ===============================
    // LOGIN
    // ===============================

    public LoginResponse login(LoginRequest request) {

        System.out.println("=================================");
        System.out.println("Email Received : " + request.getEmail());
        System.out.println("Password Received : " + request.getPassword());

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        System.out.println("User Found : " + optionalUser.isPresent());

        if (optionalUser.isEmpty()) {

            return new LoginResponse(
                    false,
                    "User not found",
                    ""
            );

        }

        User dbUser = optionalUser.get();

        System.out.println("Database Email : " + dbUser.getEmail());
        System.out.println("Database Password : " + dbUser.getPassword());

        boolean match = passwordEncoder.matches(
                request.getPassword(),
                dbUser.getPassword()
        );

        System.out.println("Password Match : " + match);
        System.out.println("=================================");

        if (!match) {

            return new LoginResponse(
                    false,
                    "Invalid Password",
                    ""
            );

        }

        return new LoginResponse(
                true,
                "Login Successful",
                dbUser.getRole()
        );
    }

    // ===============================
    // ENCRYPT OLD PASSWORDS
    // ===============================

    public String encryptExistingPasswords() {

        List<User> users = userRepository.findAll();

        for (User user : users) {

            String password = user.getPassword();

            if (!password.startsWith("$2a$")
                    && !password.startsWith("$2b$")
                    && !password.startsWith("$2y$")) {

                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);

            }

        }

        return "Passwords Updated Successfully";
    }

   // ===============================
// REGISTER
// ===============================

public String register(RegisterRequest request) {

    Optional<User> existingUser =
            userRepository.findByEmail(request.getEmail());

    if (existingUser.isPresent()) {

        return "Email already exists";

    }

    User user = new User();

    user.setName(request.getName());

    user.setEmail(request.getEmail());

    user.setPassword(
            passwordEncoder.encode(request.getPassword())
    );

    user.setRole("USER");

    userRepository.save(user);

    return "Registration Successful";

}
}