package org.example.socnetapi.dtos.comment;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCommentDto {
    private UUID id;
    private String text;
}
