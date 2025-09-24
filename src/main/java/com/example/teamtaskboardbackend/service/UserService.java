package com.example.teamtaskboardbackend.service;

import com.example.teamtaskboardbackend.entity.User;
import com.example.teamtaskboardbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User register(String email, String rawPassword) {
        // Prevent duplicate registration
        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists with email: " + email);
        }

        String hash = passwordEncoder.encode(rawPassword);
        User user = User.builder()
                .email(email)
                .passwordHash(hash)
                .build();
        return userRepo.save(user);
    }

    public User login(String email, String rawPassword) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password for email: " + email);
        }

        return user;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}
