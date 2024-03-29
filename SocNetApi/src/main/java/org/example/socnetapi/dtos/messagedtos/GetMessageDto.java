package org.example.socnetapi.dtos.messagedtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMessageDto {
    private UUID id;
    private Instant dateTime;
    private String text;
    private UUID chatId;
    private UUID userId;
}
