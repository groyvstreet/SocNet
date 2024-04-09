package org.example.socnetapi.dtos.message;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateMessageDto {
    private UUID id;
    private String text;
}
