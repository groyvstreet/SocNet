package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.chatdtos.AddChatDto;
import org.example.socnetapi.dtos.chatdtos.GetChatDto;
import org.example.socnetapi.dtos.chatdtos.UpdateChatDto;
import org.example.socnetapi.exceptions.AlreadyExistsException;
import org.example.socnetapi.exceptions.ForbiddenException;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.ChatMapper;
import org.example.socnetapi.repositories.ChatRepository;
import org.example.socnetapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        if (chat.getUsers().stream().noneMatch(user -> user.getId() == authenticatedUserId)) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        return chatMapper.chatToGetChatDto(chat);
    }

    public void addChat(AddChatDto addChatDto, UUID authenticatedUserId) {
        if (authenticatedUserId != addChatDto.getUserId()) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var user = userRepository.findById(addChatDto.getUserId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        var chat = chatMapper.addChatDtoToChat(addChatDto);
        chat.getUsers().add(user);
        chatRepository.save(chat);
    }

    public void updateChat(UpdateChatDto updateChatDto, UUID authenticatedUserId) {
        var chat = chatRepository.findById(updateChatDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId() == authenticatedUserId)) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        chat.setName(updateChatDto.getName());
        chatRepository.save(chat);
    }

    public void removeChatById(UUID id, UUID authenticatedUserId) {
        var chat = chatRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId() == authenticatedUserId)) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        chatRepository.deleteById(id);
    }

    public void addUserToChat(UUID chatId, UUID userId, UUID authenticatedUserId) {
        var chat = chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId() == authenticatedUserId)) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        if (chat.getUsers().stream().anyMatch(user -> user.getId() == userId)) {
            throw new AlreadyExistsException(Constants.CONFLICT);
        }

        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        chat.getUsers().add(user);
        chatRepository.save(chat);
    }

    public void removeUserFromChat(UUID chatId, UUID userId, UUID authenticatedUserId) {
        var chat = chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId() == authenticatedUserId)) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        if (chat.getUsers().stream().noneMatch(user -> user.getId() == userId)) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        chat.setUsers(chat.getUsers().stream().dropWhile(droppingUser -> droppingUser.getId() == userId).collect(Collectors.toSet()));
        chatRepository.save(chat);
    }
}
