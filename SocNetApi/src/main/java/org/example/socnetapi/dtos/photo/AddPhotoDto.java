package org.example.socnetapi.dtos.photo;

import lombok.Data;

import java.util.UUID;

@Data
public class AddPhotoDto {
    private String source;
    private UUID userId;
}
