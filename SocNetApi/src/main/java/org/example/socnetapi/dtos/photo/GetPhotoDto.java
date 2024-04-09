package org.example.socnetapi.dtos.photo;

import lombok.Data;

import java.util.UUID;

@Data
public class GetPhotoDto {
    private UUID id;
    private String source;
    private String userId;
}
