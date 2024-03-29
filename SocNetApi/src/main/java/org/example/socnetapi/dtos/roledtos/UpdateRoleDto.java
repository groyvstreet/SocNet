package org.example.socnetapi.dtos.roledtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateRoleDto {
    private UUID id;
    private String name;
}
