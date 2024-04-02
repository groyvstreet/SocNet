package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.userdtos.AddUserDto;
import org.example.socnetapi.dtos.userdtos.GetUserDto;
import org.example.socnetapi.dtos.userdtos.UpdateUserDto;
import org.example.socnetapi.exceptions.AlreadyExistsException;
import org.example.socnetapi.exceptions.ForbiddenException;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.UserMapper;
import org.example.socnetapi.repositories.RoleRepository;
import org.example.socnetapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    public List<GetUserDto> getUsers() {
        return userRepository.findAll().stream().map(userMapper::userToGetUserDto).toList();
    }

    public GetUserDto getUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return userMapper.userToGetUserDto(user);
    }

    public GetUserDto addUser(AddUserDto addUserDto) {
        var optionalUser = userRepository.findByEmail(addUserDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new AlreadyExistsException(Constants.CONFLICT);
        }

        var user = userMapper.addUserDtoToUser(addUserDto);
        var role = roleRepository.findByName("user").orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        user.setRole(role);
        userRepository.save(user);

        return userMapper.userToGetUserDto(user);
    }

    public void updateUser(UpdateUserDto updateUserDto, UUID authenticatedUserId) {
        if (authenticatedUserId != updateUserDto.getId()) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var user = userRepository.findById(updateUserDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setBirthDate(updateUserDto.getBirthDate());
        user.setImage(updateUserDto.getImage());
        userRepository.save(user);
    }

    public void removeUserById(UUID id, UUID authenticatedUserId) {
        if (authenticatedUserId != id) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        userRepository.deleteById(id);
    }

    public GetUserDto getUserByEmailAndPassword(String email, String password) {
        var user = userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new ForbiddenException("invalid email or password"));

        return userMapper.userToGetUserDto(user);
    }
}
