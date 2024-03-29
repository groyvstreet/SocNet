package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.roledtos.AddRoleDto;
import org.example.socnetapi.dtos.roledtos.GetRoleDto;
import org.example.socnetapi.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    GetRoleDto roleToGetRoleDto(Role role);

    Role addRoleDtoToRole(AddRoleDto addRoleDto);
}
