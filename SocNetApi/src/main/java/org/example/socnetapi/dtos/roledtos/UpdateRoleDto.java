package org.example.socnetapi.dtos.roledtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleDto {
    private UUID id;
    private String name;
}
