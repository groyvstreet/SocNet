package org.example.socnetapi.services;

import org.example.socnetapi.authorization.JwtUtil;
import org.example.socnetapi.dtos.userdtos.AddUserDto;
import org.example.socnetapi.responses.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(UserService userService,
                                 JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse signIn(String email, String password) {
        var user = userService.getUserByEmailAndPassword(email, password);
        var token = jwtUtil.generateToken(user.getId(), user.getEmail());

        return new AuthenticationResponse(user.getId(), token);
    }

    public AuthenticationResponse signUp(AddUserDto addUserDto) {
        var user = userService.addUser(addUserDto);
        var token = jwtUtil.generateToken(user.getId(), user.getEmail());

        return new AuthenticationResponse(user.getId(), token);
    }
}
