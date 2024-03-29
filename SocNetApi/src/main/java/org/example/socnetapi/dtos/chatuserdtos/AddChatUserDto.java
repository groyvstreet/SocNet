package org.example.socnetapi.dtos.chatuserdtos;

import lombok.Data;

import java.util.UUID;

@Data
public class AddChatUserDto {
    private UUID chatId;
    private UUID userId;
}
