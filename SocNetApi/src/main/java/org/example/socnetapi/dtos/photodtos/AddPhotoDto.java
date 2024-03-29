package org.example.socnetapi.dtos.photodtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPhotoDto {
    private String source;
    private UUID userId;
}
