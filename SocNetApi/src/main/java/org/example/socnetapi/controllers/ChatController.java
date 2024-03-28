package org.example.socnetapi.controllers;

import org.example.socnetapi.entities.Chat;
import org.example.socnetapi.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ChatController {

    @Autowired
    private ChatService _chatService;

    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> getChats() {
        var chats = _chatService.getChats();

        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping("/chats/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable UUID id) {
        var chat = _chatService.getChatById(id);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/chats")
    public ResponseEntity<Object> addChat(Chat chat) {
        _chatService.addChat(chat);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/chats")
    public ResponseEntity<Object> updateChat(Chat chat) {
        _chatService.updateChat(chat);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/chats/{id}")
    public ResponseEntity<Object> removeChatById(@PathVariable UUID id) {
        _chatService.removeChatById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
