package com.mistysoft.proceedhub.apps.backend;

import com.mistysoft.proceedhub.modules.user.application.LoginUser;
import com.mistysoft.proceedhub.modules.user.application.RegisterUser;
import com.mistysoft.proceedhub.modules.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUser registerUser;
    private final LoginUser loginUser;

    @Autowired
    public UserController(RegisterUser registerUser, LoginUser loginUser) {
        this.registerUser = registerUser;
        this.loginUser = loginUser;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        registerUser.execute(user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            loginUser.execute(user.getUsername(), user.getPassword());
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
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