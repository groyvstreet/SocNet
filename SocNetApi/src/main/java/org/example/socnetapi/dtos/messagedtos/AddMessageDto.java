package org.example.socnetapi.dtos.messagedtos;

import lombok.Data;

import java.util.UUID;

@Data
public class AddMessageDto {
    private String text;
    private UUID chatId;
    private UUID userId;
}
