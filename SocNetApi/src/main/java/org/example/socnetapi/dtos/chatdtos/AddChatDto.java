package org.example.socnetapi.dtos.chatdtos;

import lombok.Data;

import java.util.UUID;

@Data
public class AddChatDto {
    private String name;
    private UUID userId;
}
