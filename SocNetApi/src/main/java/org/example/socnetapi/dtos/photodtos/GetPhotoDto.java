package org.example.socnetapi.dtos.photodtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPhotoDto {
    private UUID id;
    private String source;
    private String userId;
}
