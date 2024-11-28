package com.mistysoft.proceedhub.modules.participation.application;

import com.mistysoft.proceedhub.modules.participation.domain.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

public class RegisterUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(String username, String rawPassword, Set<Role> roles) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User with this username already exists");
        }

        UserId userId = new UserId(UUID.randomUUID().toString());
        String hashedPassword = passwordEncoder.encode(rawPassword);

        User user = new User(userId, username, hashedPassword, roles);

        userRepository.save(user);
        return user;
    }
}
