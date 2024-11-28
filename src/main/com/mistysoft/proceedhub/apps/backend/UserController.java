package com.mistysoft.proceedhub.apps.backend;

import com.mistysoft.proceedhub.modules.participation.domain.User;
import com.mistysoft.proceedhub.modules.participation.infrastructure.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final JpaUserRepository userRepository;

    @Autowired
    public UserController(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}