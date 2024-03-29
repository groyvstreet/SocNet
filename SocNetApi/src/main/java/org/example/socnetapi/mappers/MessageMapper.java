package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.messagedtos.AddMessageDto;
import org.example.socnetapi.dtos.messagedtos.GetMessageDto;
import org.example.socnetapi.entities.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    GetMessageDto messageToGetMessageDto(Message message);

    Message addMessageDtoToMessage(AddMessageDto addMessageDto);
}
