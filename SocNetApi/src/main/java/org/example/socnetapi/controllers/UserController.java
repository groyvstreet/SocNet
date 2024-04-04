package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.userdtos.GetUserDto;
import org.example.socnetapi.dtos.userdtos.UpdateUserDto;
import org.example.socnetapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserDto updateUserDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        userService.updateUser(updateUserDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removeUserById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        userService.removeUserById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/chats/{id}/users")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> getUsersByChatId(@PathVariable UUID id, @RequestParam boolean isInChat, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        var users = userService.getUsersByChatId(id, authenticatedUserId, isInChat);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
