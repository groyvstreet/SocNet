package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
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
    private ChatUserRepository chatUserRepository;

    public List<ChatUser> getChatUsers() {
        return chatUserRepository.findAll();
    }

    public ChatUser getChatUserById(UUID id) {
        return chatUserRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
    }

    public void addChatUser(ChatUser chatUser) {
        chatUserRepository.save(chatUser);
    }

    public void updateChatUser(ChatUser chatUser) {
        var existingChatUser = chatUserRepository.findById(chatUser.getId());

        if (existingChatUser.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        chatUserRepository.save(chatUser);
    }

    public void removeChatUserById(UUID id) {
        var existingChatUser = chatUserRepository.findById(id);

        if (existingChatUser.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        chatUserRepository.deleteById(id);
    }
}
