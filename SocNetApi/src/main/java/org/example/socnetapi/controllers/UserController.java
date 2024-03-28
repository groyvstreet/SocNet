package org.example.socnetapi.controllers;

import org.example.socnetapi.entities.User;
import org.example.socnetapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService _userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        var users = _userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        var user = _userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(User user) {
        _userService.addUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(User user) {
        _userService.updateUser(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> removeUserById(@PathVariable UUID id) {
        _userService.removeUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
