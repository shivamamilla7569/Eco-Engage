package com.example.eco_engage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.eco_engage.model.User;
import com.example.eco_engage.Repositories.UserRepository;
import com.example.eco_engage.request.LoginRequest;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user (without password encoding)
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists!");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken!");
        }

        // Directly saving the user without hashing the password
        return ResponseEntity.ok(userRepository.save(user));
    }

    /**
     * Logs in the user by validating username and password
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("User not found!");
        }

        User user = userOptional.get();

        // Directly compare the plain text password (NOT SECURE for production)
        if (!loginRequest.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials!");
        }

        return ResponseEntity.ok("Login successful!");
    }
}
