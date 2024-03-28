package org.example.socnetapi.controllers;

import org.example.socnetapi.entities.ChatUser;
import org.example.socnetapi.services.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ChatUserController {

    @Autowired
    private ChatUserService _chatUserService;

    @GetMapping("/chatUsers")
    public ResponseEntity<List<ChatUser>> getChatUsers() {
        var chatUsers = _chatUserService.getChatUsers();

        return new ResponseEntity<>(chatUsers, HttpStatus.OK);
    }

    @GetMapping("/chatUsers/{id}")
    public ResponseEntity<ChatUser> getChatUserById(@PathVariable UUID id) {
        var chatUser = _chatUserService.getChatUserById(id);

        return new ResponseEntity<>(chatUser, HttpStatus.OK);
    }

    @PostMapping("/chatUsers")
    public ResponseEntity<Object> addChatUser(ChatUser chatUser) {
        _chatUserService.addChatUser(chatUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/chatUsers")
    public ResponseEntity<Object> updateChatUser(ChatUser chatUser) {
        _chatUserService.updateChatUser(chatUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/chatUsers/{id}")
    public ResponseEntity<Object> removeChatUserById(@PathVariable UUID id) {
        _chatUserService.removeChatUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
