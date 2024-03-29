package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.messagedtos.AddMessageDto;
import org.example.socnetapi.dtos.messagedtos.GetMessageDto;
import org.example.socnetapi.dtos.messagedtos.UpdateMessageDto;
import org.example.socnetapi.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
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
    public ResponseEntity<Object> addMessage(AddMessageDto addMessageDto) {
        messageService.addMessage(addMessageDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/messages")
    public ResponseEntity<Object> updateMessage(UpdateMessageDto updateMessageDto) {
        messageService.updateMessage(updateMessageDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Object> removeMessageById(@PathVariable UUID id) {
        messageService.removeMessageById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
