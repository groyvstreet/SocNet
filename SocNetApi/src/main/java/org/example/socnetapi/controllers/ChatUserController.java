package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.chatuserdtos.AddChatUserDto;
import org.example.socnetapi.dtos.chatuserdtos.GetChatUserDto;
import org.example.socnetapi.services.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ChatUserController {
    private final ChatUserService chatUserService;

    @Autowired
    public ChatUserController(ChatUserService chatUserService) {
        this.chatUserService = chatUserService;
    }

    @GetMapping("/chatUsers")
    public ResponseEntity<List<GetChatUserDto>> getChatUsers() {
        var chatUsers = chatUserService.getChatUsers();

        return new ResponseEntity<>(chatUsers, HttpStatus.OK);
    }

    @GetMapping("/chatUsers/{id}")
    public ResponseEntity<GetChatUserDto> getChatUserById(@PathVariable UUID id) {
        var chatUser = chatUserService.getChatUserById(id);

        return new ResponseEntity<>(chatUser, HttpStatus.OK);
    }

    @PostMapping("/chatUsers")
    public ResponseEntity<Object> addChatUser(AddChatUserDto addChatUserDto) {
        chatUserService.addChatUser(addChatUserDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/chatUsers/{id}")
    public ResponseEntity<Object> removeChatUserById(@PathVariable UUID id) {
        chatUserService.removeChatUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
