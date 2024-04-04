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

import java.security.Principal;
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
    public ResponseEntity<List<GetChatDto>> getChats(Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        var chats = chatService.getChatsByUserId(authenticatedUserId);

        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping("/chats/{id}")
    public ResponseEntity<GetChatDto> getChatById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        var chat = chatService.getChatById(id, authenticatedUserId);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/chats")
    public ResponseEntity<Object> addChat(@RequestBody AddChatDto addChatDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.addChat(addChatDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/chats")
    public ResponseEntity<Object> updateChat(@RequestBody UpdateChatDto updateChatDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.updateChat(updateChatDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/chats/{id}")
    public ResponseEntity<Object> removeChatById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.removeChatById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/chats/{chatId}/users/{userId}")
    public ResponseEntity<Object> addUserToChat(@PathVariable UUID chatId, @PathVariable UUID userId, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.addUserToChat(chatId, userId, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/chats/{chatId}/users/{userId}")
    public ResponseEntity<Object> removeUserFromChat(@PathVariable UUID chatId, @PathVariable UUID userId, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.removeUserFromChat(chatId, userId, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
