package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.chatdtos.AddChatDto;
import org.example.socnetapi.dtos.chatdtos.GetChatDto;
import org.example.socnetapi.dtos.chatdtos.UpdateChatDto;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.ChatMapper;
import org.example.socnetapi.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatService(ChatRepository chatRepository,
                       ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
    }

    public List<GetChatDto> getChats() {
        return chatRepository.findAll().stream().map(chatMapper::chatToGetChatDto).toList();
    }

    public GetChatDto getChatById(UUID id) {
        var chat = chatRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return chatMapper.chatToGetChatDto(chat);
    }

    public void addChat(AddChatDto addChatDto) {
        var chat = chatMapper.addChatDtoToChat(addChatDto);
        chatRepository.save(chat);
    }

    public void updateChat(UpdateChatDto updateChatDto) {
        var chat = chatRepository.findById(updateChatDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        chat.setName(updateChatDto.getName());
        chatRepository.save(chat);
    }

    public void removeChatById(UUID id) {
        var chat = chatRepository.findById(id);

        if (chat.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        chatRepository.deleteById(id);
    }
}
