package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.chatdtos.AddChatDto;
import org.example.socnetapi.dtos.chatdtos.GetChatDto;
import org.example.socnetapi.dtos.chatdtos.UpdateChatDto;
import org.example.socnetapi.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("isAuthenticated()")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chats")
    public ResponseEntity<List<GetChatDto>> getChats() {
        var chats = chatService.getChats();

        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping("/chats/{id}")
    public ResponseEntity<GetChatDto> getChatById(@PathVariable UUID id) {
        var chat = chatService.getChatById(id);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/chats")
    public ResponseEntity<Object> addChat(AddChatDto addChatDto) {
        chatService.addChat(addChatDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/chats")
    public ResponseEntity<Object> updateChat(UpdateChatDto updateChatDto) {
        chatService.updateChat(updateChatDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/chats/{id}")
    public ResponseEntity<Object> removeChatById(@PathVariable UUID id) {
        chatService.removeChatById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
