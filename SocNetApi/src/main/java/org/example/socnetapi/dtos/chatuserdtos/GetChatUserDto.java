package org.example.socnetapi.dtos.chatuserdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetChatUserDto {
    private UUID id;
    private UUID chatId;
    private UUID userId;
}
