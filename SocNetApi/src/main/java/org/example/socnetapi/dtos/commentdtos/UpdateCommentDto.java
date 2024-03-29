package org.example.socnetapi.dtos.commentdtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCommentDto {
    private UUID id;
    private String text;
}
