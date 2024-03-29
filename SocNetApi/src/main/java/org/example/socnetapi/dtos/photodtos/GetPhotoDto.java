package org.example.socnetapi.dtos.photodtos;

import lombok.Data;

import java.util.UUID;

@Data
public class GetPhotoDto {
    private UUID id;
    private String source;
    private String userId;
}
