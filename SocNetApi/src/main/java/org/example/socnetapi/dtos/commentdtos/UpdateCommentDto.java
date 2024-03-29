package org.example.socnetapi.dtos.commentdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommentDto {
    private UUID id;
    private String text;
}
