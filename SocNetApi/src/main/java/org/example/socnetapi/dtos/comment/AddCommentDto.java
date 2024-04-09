package org.example.socnetapi.dtos.comment;

import lombok.Data;

import java.util.UUID;

@Data
public class AddCommentDto {
    private String text;
    private UUID postId;
    private UUID userId;
    private UUID commentId;
}
