package org.example.socnetapi.services;

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
    private RoleRepository _roleRepository;

    public List<Role> getRoles() {
        return _roleRepository.findAll();
    }

    public Role getRoleById(UUID id) {
        return _roleRepository.findById(id).orElseThrow(() -> new NotFoundException("no such role"));
    }

    public void addRole(Role role) {
        _roleRepository.save(role);
    }

    public void updateRole(Role role) {
        var existingRole = _roleRepository.findById(role.getId());

        if (existingRole.isEmpty()) {
            throw new NotFoundException("no such role");
        }

        _roleRepository.save(role);
    }

    public void removeRoleById(UUID id) {
        var existingRole = _roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new NotFoundException("no such role");
        }

        _roleRepository.deleteById(id);
    }

}
