package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.userdtos.GetUserDto;
import org.example.socnetapi.dtos.userdtos.UpdateUserDto;
import org.example.socnetapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUserDto>> getUsers() {
        var users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<GetUserDto> getUserById(@PathVariable UUID id) {
        var user = userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users")
    @PreAuthorize("authenticated()")
    public ResponseEntity<Object> updateUser(UpdateUserDto updateUserDto) {
        userService.updateUser(updateUserDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> removeUserById(@PathVariable UUID id) {
        userService.removeUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
