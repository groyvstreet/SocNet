package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.entities.Role;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public void updateRole(Role role) {
        var existingRole = roleRepository.findById(role.getId());

        if (existingRole.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        roleRepository.save(role);
    }

    public void removeRoleById(UUID id) {
        var existingRole = roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        roleRepository.deleteById(id);
    }
}
