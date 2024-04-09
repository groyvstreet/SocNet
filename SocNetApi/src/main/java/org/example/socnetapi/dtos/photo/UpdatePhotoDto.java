package org.example.socnetapi.dtos.photo;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdatePhotoDto {
    private UUID id;
    private String source;
}
