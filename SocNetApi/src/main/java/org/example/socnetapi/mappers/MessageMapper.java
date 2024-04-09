package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.message.AddMessageDto;
import org.example.socnetapi.dtos.message.GetMessageDto;
import org.example.socnetapi.entities.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    GetMessageDto messageToGetMessageDto(Message message);

    Message addMessageDtoToMessage(AddMessageDto addMessageDto);
}
