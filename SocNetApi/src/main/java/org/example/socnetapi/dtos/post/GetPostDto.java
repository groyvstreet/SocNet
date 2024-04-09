package org.example.socnetapi.dtos.post;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class GetPostDto {
    private UUID id;
    private Instant dateTime;
    private String text;
    private UUID userId;
}
