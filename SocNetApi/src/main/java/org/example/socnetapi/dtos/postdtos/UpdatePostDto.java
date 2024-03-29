package org.example.socnetapi.dtos.postdtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdatePostDto {
    private UUID id;
    private String text;
}
