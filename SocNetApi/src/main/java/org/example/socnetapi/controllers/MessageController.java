package org.example.socnetapi.controllers;

import org.example.socnetapi.entities.Message;
import org.example.socnetapi.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    private MessageService _messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        var messages = _messageService.getMessages();

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable UUID id) {
        var message = _messageService.getMessageById(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<Object> addMessage(Message message) {
        _messageService.addMessage(message);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/messages")
    public ResponseEntity<Object> updateMessage(Message message) {
        _messageService.updateMessage(message);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Object> removeMessageById(@PathVariable UUID id) {
        _messageService.removeMessageById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
