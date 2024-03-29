package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.userdtos.AddUserDto;
import org.example.socnetapi.dtos.userdtos.GetUserDto;
import org.example.socnetapi.dtos.userdtos.UpdateUserDto;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.UserMapper;
import org.example.socnetapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<GetUserDto> getUsers() {
        return userRepository.findAll().stream().map(userMapper::userToGetUserDto).toList();
    }

    public GetUserDto getUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return userMapper.userToGetUserDto(user);
    }

    public void addUser(AddUserDto addUserDto) {
        var user = userMapper.addUserDtoToUser(addUserDto);

        userRepository.save(user);
    }

    public void updateUser(UpdateUserDto updateUserDto) {
        var user = userRepository.findById(updateUserDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setBirthDate(updateUserDto.getBirthDate());
        user.setImage(updateUserDto.getImage());
        userRepository.save(user);
    }

    public void removeUserById(UUID id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        userRepository.deleteById(id);
    }
}
