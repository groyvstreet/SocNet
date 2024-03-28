package org.example.socnetapi.services;

import org.example.socnetapi.entities.Chat;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatService {

    @Autowired
    private ChatRepository _chatRepository;

    public List<Chat> getChats() {
        return _chatRepository.findAll();
    }

    public Chat getChatById(UUID id) {
        return _chatRepository.findById(id).orElseThrow(() -> new NotFoundException("no such chat"));
    }

    public void addChat(Chat chat) {
        _chatRepository.save(chat);
    }

    public void updateChat(Chat chat) {
        var existingChat = _chatRepository.findById(chat.getId());

        if (existingChat.isEmpty()) {
            throw new NotFoundException("no such chat");
        }

        _chatRepository.save(chat);
    }

    public void removeChatById(UUID id) {
        var existingChat = _chatRepository.findById(id);

        if (existingChat.isEmpty()) {
            throw new NotFoundException("no such chat");
        }

        _chatRepository.deleteById(id);
    }
    
}
