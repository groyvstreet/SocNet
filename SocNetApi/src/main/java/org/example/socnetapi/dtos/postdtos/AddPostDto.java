package org.example.socnetapi.dtos.postdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPostDto {
    private String text;
    private UUID userId;
}
