package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.chatuserdtos.AddChatUserDto;
import org.example.socnetapi.dtos.chatuserdtos.GetChatUserDto;
import org.example.socnetapi.entities.ChatUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatUserMapper {
    GetChatUserDto chatUserToGetChatUserDto(ChatUser chatUser);

    ChatUser addChatUserDtoToChatUser(AddChatUserDto addChatUserDto);
}
