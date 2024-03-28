package org.example.socnetapi.services;

import org.example.socnetapi.entities.ChatUser;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.repositories.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatUserService {

    @Autowired
    private ChatUserRepository _chatUserRepository;

    public List<ChatUser> getChatUsers() {
        return _chatUserRepository.findAll();
    }

    public ChatUser getChatUserById(UUID id) {
        return _chatUserRepository.findById(id).orElseThrow(() -> new NotFoundException("no such chatUser"));
    }

    public void addChatUser(ChatUser chatUser) {
        _chatUserRepository.save(chatUser);
    }

    public void updateChatUser(ChatUser chatUser) {
        var existingChatUser = _chatUserRepository.findById(chatUser.getId());

        if (existingChatUser.isEmpty()) {
            throw new NotFoundException("no such chatUser");
        }

        _chatUserRepository.save(chatUser);
    }

    public void removeChatUserById(UUID id) {
        var existingChatUser = _chatUserRepository.findById(id);

        if (existingChatUser.isEmpty()) {
            throw new NotFoundException("no such chatUser");
        }

        _chatUserRepository.deleteById(id);
    }
    
}
