package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.message.AddMessageDto;
import org.example.socnetapi.dtos.message.GetMessageDto;
import org.example.socnetapi.dtos.message.UpdateMessageDto;
import org.example.socnetapi.entities.Chat;
import org.example.socnetapi.entities.User;
import org.example.socnetapi.exceptions.ForbiddenException;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.MessageMapper;
import org.example.socnetapi.repositories.ChatRepository;
import org.example.socnetapi.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository,
                          ChatRepository chatRepository,
                          MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.messageMapper = messageMapper;
    }

    public List<GetMessageDto> getMessages() {
        return messageRepository.findAll().stream().map(messageMapper::messageToGetMessageDto).toList();
    }

    public GetMessageDto getMessageById(UUID id) {
        var message =  messageRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return messageMapper.messageToGetMessageDto(message);
    }

    public void addMessage(AddMessageDto addMessageDto, UUID authenticatedUserId) {
        if (!authenticatedUserId.toString().equals(addMessageDto.getUserId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var message = messageMapper.addMessageDtoToMessage(addMessageDto);

        var chat = new Chat();
        chat.setId(addMessageDto.getChatId());
        message.setChat(chat);

        var user = new User();
        user.setId(addMessageDto.getUserId());
        message.setUser(user);

        messageRepository.save(message);
    }

    public void updateMessage(UpdateMessageDto updateMessageDto, UUID authenticatedUserId) {
        var message = messageRepository.findById(updateMessageDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (!authenticatedUserId.toString().equals(message.getUser().getId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        message.setText(updateMessageDto.getText());
        messageRepository.save(message);
    }

    public void removeMessageById(UUID id, UUID authenticatedUserId) {
        var message = messageRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (!authenticatedUserId.toString().equals(message.getUser().getId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        messageRepository.deleteById(id);
    }

    public List<GetMessageDto> getMessagesByChatId(UUID chatId, UUID authenticatedUserId) {
        var chat = chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId().toString().equals(authenticatedUserId.toString()))) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        return messageRepository.findByChatId(chatId).stream().map(messageMapper::messageToGetMessageDto).toList();
    }
}
