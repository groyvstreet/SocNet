package org.example.socnetapi.dtos.roledtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRoleDto {
    private UUID id;
    private String name;
}
