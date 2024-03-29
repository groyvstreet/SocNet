package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.roledtos.AddRoleDto;
import org.example.socnetapi.dtos.roledtos.GetRoleDto;
import org.example.socnetapi.dtos.roledtos.UpdateRoleDto;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.RoleMapper;
import org.example.socnetapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository,
                       RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<GetRoleDto> getRoles() {
        return roleRepository.findAll().stream().map(roleMapper::roleToGetRoleDto).toList();
    }

    public GetRoleDto getRoleById(UUID id) {
        var role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return roleMapper.roleToGetRoleDto(role);
    }

    public void addRole(AddRoleDto addRoleDto) {
        var role = roleMapper.addRoleDtoToRole(addRoleDto);
        roleRepository.save(role);
    }

    public void updateRole(UpdateRoleDto updateRoleDto) {
        var role = roleRepository.findById(updateRoleDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        role.setName(updateRoleDto.getName());
        roleRepository.save(role);
    }

    public void removeRoleById(UUID id) {
        var role = roleRepository.findById(id);

        if (role.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        roleRepository.deleteById(id);
    }
}
