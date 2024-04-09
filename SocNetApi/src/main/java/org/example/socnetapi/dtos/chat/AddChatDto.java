package org.example.socnetapi.dtos.chat;

import lombok.Data;

import java.util.UUID;

@Data
public class AddChatDto {
    private String name;
    private UUID userId;
}
