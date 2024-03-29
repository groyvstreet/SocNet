package org.example.socnetapi.dtos.messagedtos;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class GetMessageDto {
    private UUID id;
    private Instant dateTime;
    private String text;
    private UUID chatId;
    private UUID userId;
}
