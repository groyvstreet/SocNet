package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.user.UpdateUserDto;
import org.example.socnetapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getUsers() {
        var users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID id) {
        var user = userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserDto updateUserDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        userService.updateUser(updateUserDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removeUserById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        userService.removeUserById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("chats")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> getUsersByChatId(@RequestParam UUID chatId, @RequestParam boolean isInChat, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        var users = userService.getUsersByChatId(chatId, authenticatedUserId, isInChat);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
