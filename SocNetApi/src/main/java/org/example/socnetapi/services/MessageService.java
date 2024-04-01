package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.messagedtos.AddMessageDto;
import org.example.socnetapi.dtos.messagedtos.GetMessageDto;
import org.example.socnetapi.dtos.messagedtos.UpdateMessageDto;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.MessageMapper;
import org.example.socnetapi.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository,
                          MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public List<GetMessageDto> getMessages() {
        return messageRepository.findAll().stream().map(messageMapper::messageToGetMessageDto).toList();
    }

    public GetMessageDto getMessageById(UUID id) {
        var message =  messageRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return messageMapper.messageToGetMessageDto(message);
    }

    public void addMessage(AddMessageDto addMessageDto) {
        var message = messageMapper.addMessageDtoToMessage(addMessageDto);
        messageRepository.save(message);
    }

    public void updateMessage(UpdateMessageDto updateMessageDto) {
        var message = messageRepository.findById(updateMessageDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        message.setText(updateMessageDto.getText());
        messageRepository.save(message);
    }

    public void removeMessageById(UUID id) {
        var message = messageRepository.findById(id);

        if (message.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        messageRepository.deleteById(id);
    }

    public List<GetMessageDto> getMessagesByChatId(UUID chatId) {
        return messageRepository.findByChatId(chatId).stream().map(messageMapper::messageToGetMessageDto).toList();
    }
}
