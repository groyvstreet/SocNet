package org.example.socnetapi.dtos.post;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdatePostDto {
    private UUID id;
    private String text;
}
