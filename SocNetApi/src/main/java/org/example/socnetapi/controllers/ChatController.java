package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.chat.AddChatDto;
import org.example.socnetapi.dtos.chat.UpdateChatDto;
import org.example.socnetapi.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/chats")
@PreAuthorize("isAuthenticated()")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<Object> getChats(Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        var chats = chatService.getChatsByUserId(authenticatedUserId);

        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getChatById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        var chat = chatService.getChatById(id, authenticatedUserId);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addChat(@RequestBody AddChatDto addChatDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.addChat(addChatDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateChat(@RequestBody UpdateChatDto updateChatDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.updateChat(updateChatDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> removeChatById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.removeChatById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("users")
    public ResponseEntity<Object> addUserToChat(@RequestParam UUID chatId, @RequestParam UUID userId, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.addUserToChat(chatId, userId, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("users")
    public ResponseEntity<Object> removeUserFromChat(@RequestParam UUID chatId, @RequestParam UUID userId, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        chatService.removeUserFromChat(chatId, userId, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
