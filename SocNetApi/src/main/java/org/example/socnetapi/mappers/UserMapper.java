package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.user.AddUserDto;
import org.example.socnetapi.dtos.user.GetUserDto;
import org.example.socnetapi.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    GetUserDto userToGetUserDto(User user);

    User addUserDtoToUser(AddUserDto addUserDto);
}
