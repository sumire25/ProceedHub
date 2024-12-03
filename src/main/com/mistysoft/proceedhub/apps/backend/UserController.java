package com.mistysoft.proceedhub.apps.backend;

import com.mistysoft.proceedhub.modules.user.application.LoginUser;
import com.mistysoft.proceedhub.modules.user.application.RegisterUser;
import com.mistysoft.proceedhub.modules.user.application.SearchUser;
import com.mistysoft.proceedhub.modules.user.application.dto.UserDTO;
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
    private final SearchUser searchUser;

    @Autowired
    public UserController(RegisterUser registerUser, LoginUser loginUser, SearchUser searchUser) {
        this.registerUser = registerUser;
        this.loginUser = loginUser;
        this.searchUser = searchUser;
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

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Optional<User> user = searchUser.findByUsername(username);
        if (user.isPresent()) {
            UserDTO userDTO = new UserDTO(user.get());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}