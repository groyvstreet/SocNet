package org.example.socnetapi.dtos.messagedtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateMessageDto {
    private UUID id;
    private String text;
}
