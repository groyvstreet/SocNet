package org.example.socnetapi.dtos.message;

import lombok.Data;
import org.example.socnetapi.dtos.user.GetUserDto;

import java.time.Instant;
import java.util.UUID;

@Data
public class GetMessageDto {
    private UUID id;
    private Instant dateTime;
    private String text;
    private UUID chatId;
    private GetUserDto user;
}
