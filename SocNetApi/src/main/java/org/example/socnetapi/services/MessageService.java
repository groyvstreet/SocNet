package org.example.socnetapi.services;

import org.example.socnetapi.entities.Message;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepository _messageRepository;

    public List<Message> getMessages() {
        return _messageRepository.findAll();
    }

    public Message getMessageById(UUID id) {
        return _messageRepository.findById(id).orElseThrow(() -> new NotFoundException("no such message"));
    }

    public void addMessage(Message message) {
        _messageRepository.save(message);
    }

    public void updateMessage(Message message) {
        var existingMessage = _messageRepository.findById(message.getId());

        if (existingMessage.isEmpty()) {
            throw new NotFoundException("no such message");
        }

        _messageRepository.save(message);
    }

    public void removeMessageById(UUID id) {
        var existingMessage = _messageRepository.findById(id);

        if (existingMessage.isEmpty()) {
            throw new NotFoundException("no such message");
        }

        _messageRepository.deleteById(id);
    }
    
}
