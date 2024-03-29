package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.userdtos.AddUserDto;
import org.example.socnetapi.dtos.userdtos.GetUserDto;
import org.example.socnetapi.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    GetUserDto userToGetUserDto(User user);

    User addUserDtoToUser(AddUserDto addUserDto);
}
