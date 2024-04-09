package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.user.AddUserDto;
import org.example.socnetapi.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("signin")
    public ResponseEntity<Object> signIn(@RequestParam String email, @RequestParam String password) {
        var authenticationResponse = authenticationService.signIn(email, password);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<Object> signUp(@RequestBody AddUserDto addUserDto) {
        var authenticationResponse = authenticationService.signUp(addUserDto);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
