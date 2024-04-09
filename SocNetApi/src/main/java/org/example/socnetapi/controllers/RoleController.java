package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.role.AddRoleDto;
import org.example.socnetapi.dtos.role.UpdateRoleDto;
import org.example.socnetapi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Object> getRoles() {
        var roles = roleService.getRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable UUID id) {
        var role = roleService.getRoleById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addRole(@RequestBody AddRoleDto addRoleDto) {
        roleService.addRole(addRoleDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateRole(@RequestBody UpdateRoleDto updateRoleDto) {
        roleService.updateRole(updateRoleDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removeRoleById(@PathVariable UUID id) {
        roleService.removeRoleById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
