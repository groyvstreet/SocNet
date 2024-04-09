package org.example.socnetapi.dtos.post;

import lombok.Data;

import java.util.UUID;

@Data
public class AddPostDto {
    private String text;
    private UUID userId;
}
