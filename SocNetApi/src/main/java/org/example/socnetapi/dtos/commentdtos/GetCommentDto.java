package org.example.socnetapi.dtos.commentdtos;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class GetCommentDto {
    private UUID id;
    private Instant dateTime;
    private String text;
    private UUID postId;
    private UUID userId;
    private UUID commentId;
}
