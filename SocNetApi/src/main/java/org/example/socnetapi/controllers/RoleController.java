package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.roledtos.AddRoleDto;
import org.example.socnetapi.dtos.roledtos.GetRoleDto;
import org.example.socnetapi.dtos.roledtos.UpdateRoleDto;
import org.example.socnetapi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<GetRoleDto>> getRoles() {
        var roles = roleService.getRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<GetRoleDto> getRoleById(@PathVariable UUID id) {
        var role = roleService.getRoleById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/roles")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addRole(@RequestBody AddRoleDto addRoleDto) {
        roleService.addRole(addRoleDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/roles")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateRole(@RequestBody UpdateRoleDto updateRoleDto) {
        roleService.updateRole(updateRoleDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/roles/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removeRoleById(@PathVariable UUID id) {
        roleService.removeRoleById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
