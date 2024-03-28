package org.example.socnetapi.services;

import org.example.socnetapi.entities.User;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository _userRepository;

    public List<User> getUsers() {
        return _userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return _userRepository.findById(id).orElseThrow(() -> new NotFoundException("no such user"));
    }

    public void addUser(User user) {
        _userRepository.save(user);
    }

    public void updateUser(User user) {
        var existingUser = _userRepository.findById(user.getId());

        if (existingUser.isEmpty()) {
            throw new NotFoundException("no such user");
        }

        _userRepository.save(user);
    }

    public void removeUserById(UUID id) {
        var existingUser = _userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new NotFoundException("no such user");
        }

        _userRepository.deleteById(id);
    }

}
