package org.example.socnetapi.dtos.role;

import lombok.Data;

import java.util.UUID;

@Data
public class GetRoleDto {
    private UUID id;
    private String name;
}
