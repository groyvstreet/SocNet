package org.example.socnetapi.dtos.chatdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetChatDto {
    private UUID id;
    private String name;
}
