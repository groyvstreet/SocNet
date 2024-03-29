package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.chatuserdtos.AddChatUserDto;
import org.example.socnetapi.dtos.chatuserdtos.GetChatUserDto;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.ChatUserMapper;
import org.example.socnetapi.repositories.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatUserService {
    private final ChatUserRepository chatUserRepository;
    private final ChatUserMapper chatUserMapper;

    @Autowired
    public ChatUserService(ChatUserRepository chatUserRepository,
                           ChatUserMapper chatUserMapper) {
        this.chatUserRepository = chatUserRepository;
        this.chatUserMapper = chatUserMapper;
    }

    public List<GetChatUserDto> getChatUsers() {
        return chatUserRepository.findAll().stream().map(chatUserMapper::chatUserToGetChatUserDto).toList();
    }

    public GetChatUserDto getChatUserById(UUID id) {
        var chatUser = chatUserRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return chatUserMapper.chatUserToGetChatUserDto(chatUser);
    }

    public void addChatUser(AddChatUserDto addChatUserDto) {
        var chatUser = chatUserMapper.addChatUserDtoToChatUser(addChatUserDto);
        chatUserRepository.save(chatUser);
    }

    public void removeChatUserById(UUID id) {
        var chatUser = chatUserRepository.findById(id);

        if (chatUser.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        chatUserRepository.deleteById(id);
    }
}
