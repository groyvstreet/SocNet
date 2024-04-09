package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.chat.AddChatDto;
import org.example.socnetapi.dtos.chat.GetChatDto;
import org.example.socnetapi.dtos.chat.UpdateChatDto;
import org.example.socnetapi.entities.User;
import org.example.socnetapi.exceptions.AlreadyExistsException;
import org.example.socnetapi.exceptions.ForbiddenException;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.ChatMapper;
import org.example.socnetapi.repositories.ChatRepository;
import org.example.socnetapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatService(ChatRepository chatRepository,
                       UserRepository userRepository,
                       ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.chatMapper = chatMapper;
    }

    public List<GetChatDto> getChats() {
        return chatRepository.findAll().stream().map(chatMapper::chatToGetChatDto).toList();
    }

    public GetChatDto getChatById(UUID id, UUID authenticatedUserId) {
        var chat = chatRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId().toString().equals(authenticatedUserId.toString()))) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        return chatMapper.chatToGetChatDto(chat);
    }

    public void addChat(AddChatDto addChatDto, UUID authenticatedUserId) {
        if (!authenticatedUserId.toString().equals(addChatDto.getUserId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var user = userRepository.findById(addChatDto.getUserId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        var chat = chatMapper.addChatDtoToChat(addChatDto);
        chat.getUsers().add(user);
        chatRepository.save(chat);
    }

    public void updateChat(UpdateChatDto updateChatDto, UUID authenticatedUserId) {
        var chat = chatRepository.findById(updateChatDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId().toString().equals(authenticatedUserId.toString()))) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        chat.setName(updateChatDto.getName());
        chatRepository.save(chat);
    }

    public void removeChatById(UUID id, UUID authenticatedUserId) {
        var chat = chatRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId().toString().equals(authenticatedUserId.toString()))) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        chatRepository.deleteById(id);
    }

    public void addUserToChat(UUID chatId, UUID userId, UUID authenticatedUserId) {
        var chat = chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId().toString().equals(authenticatedUserId.toString()))) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        if (chat.getUsers().stream().anyMatch(user -> user.getId().toString().equals(userId.toString()))) {
            throw new AlreadyExistsException(Constants.CONFLICT);
        }

        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        chat.getUsers().add(user);
        chatRepository.save(chat);
    }

    public void removeUserFromChat(UUID chatId, UUID userId, UUID authenticatedUserId) {
        var chat = chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId().toString().equals(authenticatedUserId.toString()))) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        if (chat.getUsers().stream().noneMatch(user -> user.getId().toString().equals(userId.toString()))) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        chat.getUsers().removeIf(user -> user.getId().toString().equals(userId.toString()));
        chatRepository.save(chat);
    }

    public List<GetChatDto> getChatsByUserId(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        var userSet = new HashSet<User>();
        userSet.add(user);
        var chats = chatRepository.findAllByUsersContains(userSet);

        return chats.stream().map(chatMapper::chatToGetChatDto).toList();
    }
}
