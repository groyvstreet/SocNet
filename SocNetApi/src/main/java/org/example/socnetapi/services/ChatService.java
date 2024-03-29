package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
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
    private ChatRepository chatRepository;

    public List<Chat> getChats() {
        return chatRepository.findAll();
    }

    public Chat getChatById(UUID id) {
        return chatRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
    }

    public void addChat(Chat chat) {
        chatRepository.save(chat);
    }

    public void updateChat(Chat chat) {
        var existingChat = chatRepository.findById(chat.getId());

        if (existingChat.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        chatRepository.save(chat);
    }

    public void removeChatById(UUID id) {
        var existingChat = chatRepository.findById(id);

        if (existingChat.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        chatRepository.deleteById(id);
    }
}
