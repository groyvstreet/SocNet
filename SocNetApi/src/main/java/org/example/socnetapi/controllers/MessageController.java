package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.messagedtos.AddMessageDto;
import org.example.socnetapi.dtos.messagedtos.GetMessageDto;
import org.example.socnetapi.dtos.messagedtos.UpdateMessageDto;
import org.example.socnetapi.services.MessageService;
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
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<GetMessageDto>> getMessages() {
        var messages = messageService.getMessages();

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<GetMessageDto> getMessageById(@PathVariable UUID id) {
        var message = messageService.getMessageById(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<Object> addMessage(@RequestBody AddMessageDto addMessageDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        messageService.addMessage(addMessageDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/messages")
    public ResponseEntity<Object> updateMessage(@RequestBody UpdateMessageDto updateMessageDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        messageService.updateMessage(updateMessageDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Object> removeMessageById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        messageService.removeMessageById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/chats/{id}/messages")
    public ResponseEntity<Object> getMessagesByChatId(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        var messages = messageService.getMessagesByChatId(id, authenticatedUserId);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
