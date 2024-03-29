package org.example.socnetapi.dtos.photodtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdatePhotoDto {
    private UUID id;
    private String source;
}
