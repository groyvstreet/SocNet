package org.example.socnetapi.dtos.chat;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateChatDto {
    private UUID id;
    private String name;
}
