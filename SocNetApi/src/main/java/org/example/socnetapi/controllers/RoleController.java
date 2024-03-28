package org.example.socnetapi.controllers;

import org.example.socnetapi.entities.Role;
import org.example.socnetapi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RoleController {

    @Autowired
    private RoleService _roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        var roles = _roleService.getRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable UUID id) {
        var role = _roleService.getRoleById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<Object> addRole(Role role) {
        _roleService.addRole(role);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/roles")
    public ResponseEntity<Object> updateRole(Role role) {
        _roleService.updateRole(role);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Object> removeRoleById(@PathVariable UUID id) {
        _roleService.removeRoleById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
