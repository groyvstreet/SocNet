package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.user.AddUserDto;
import org.example.socnetapi.dtos.user.GetUserDto;
import org.example.socnetapi.dtos.user.UpdateUserDto;
import org.example.socnetapi.entities.Chat;
import org.example.socnetapi.entities.User;
import org.example.socnetapi.exceptions.AlreadyExistsException;
import org.example.socnetapi.exceptions.ForbiddenException;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.UserMapper;
import org.example.socnetapi.repositories.ChatRepository;
import org.example.socnetapi.repositories.RoleRepository;
import org.example.socnetapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ChatRepository chatRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       ChatRepository chatRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.chatRepository = chatRepository;
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
        if (!authenticatedUserId.toString().equals(updateUserDto.getId().toString())) {
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
        if (!authenticatedUserId.toString().equals(id.toString())) {
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

    public List<GetUserDto> getUsersByChatId(UUID chatId, UUID authenticatedUserId, boolean isInChat) {
        var chat = chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (chat.getUsers().stream().noneMatch(user -> user.getId().toString().equals(authenticatedUserId.toString()))) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var chatSet = new HashSet<Chat>();
        chatSet.add(chat);
        List<User> users;

        if (isInChat) {
            users = userRepository.findAllByChatsContains(chatSet);
        }
        else {
            users = userRepository.findAllByChatsNotContains(chatSet);
        }

        return users.stream().map(userMapper::userToGetUserDto).toList();
    }
}
