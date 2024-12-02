package com.mistysoft.proceedhub.apps.backend;

import com.mistysoft.proceedhub.modules.user.application.RegisterUser;
import com.mistysoft.proceedhub.modules.user.domain.User;
import com.mistysoft.proceedhub.modules.user.infrastructure.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

/*
    private final JpaUserRepository userRepository;

    @Autowired
    public UserController(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }
*/
    private final RegisterUser registerUser;

    @Autowired
    public UserController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        registerUser.execute(user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

/*
    @GetMapping("/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
*/

    @GetMapping
    public String getAllUsers() {
        return "Aqui todos los ususarios papu";
    }
}