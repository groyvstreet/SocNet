package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.message.AddMessageDto;
import org.example.socnetapi.dtos.message.UpdateMessageDto;
import org.example.socnetapi.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@PreAuthorize("isAuthenticated()")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getMessageById(@PathVariable UUID id) {
        var message = messageService.getMessageById(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addMessage(@RequestBody AddMessageDto addMessageDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        messageService.addMessage(addMessageDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateMessage(@RequestBody UpdateMessageDto updateMessageDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        messageService.updateMessage(updateMessageDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> removeMessageById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        messageService.removeMessageById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Object> getMessagesByChatId(@RequestParam UUID chatId, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        var messages = messageService.getMessagesByChatId(chatId, authenticatedUserId);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
