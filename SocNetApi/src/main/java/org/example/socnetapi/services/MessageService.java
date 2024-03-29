package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
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
    private MessageRepository messageRepository;

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(UUID id) {
        return messageRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public void updateMessage(Message message) {
        var existingMessage = messageRepository.findById(message.getId());

        if (existingMessage.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        messageRepository.save(message);
    }

    public void removeMessageById(UUID id) {
        var existingMessage = messageRepository.findById(id);

        if (existingMessage.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        messageRepository.deleteById(id);
    }
}
