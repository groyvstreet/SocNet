package org.example.socnetapi.dtos.messagedtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMessageDto {
    private String text;
    private UUID chatId;
    private UUID userId;
}
