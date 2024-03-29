package org.example.socnetapi.dtos.commentdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentDto {
    private UUID id;
    private Instant dateTime;
    private String text;
    private UUID postId;
    private UUID userId;
    private UUID commentId;
}
