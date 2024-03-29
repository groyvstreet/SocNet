package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.chatdtos.AddChatDto;
import org.example.socnetapi.dtos.chatdtos.GetChatDto;
import org.example.socnetapi.entities.Chat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    GetChatDto chatToGetChatDto(Chat chat);

    Chat addChatDtoToChat(AddChatDto addChatDto);
}
