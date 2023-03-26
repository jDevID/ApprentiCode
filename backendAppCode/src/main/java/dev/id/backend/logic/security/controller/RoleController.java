package dev.id.backend.logic.security.controller;

import dev.id.backend.logic.security.models.dtos.CreateRoleDto;
import dev.id.backend.logic.security.models.dtos.RoleDetailsDto;
import dev.id.backend.logic.security.models.dtos.UpdateRoleDto;
import dev.id.backend.logic.security.models.entity.Role;
import dev.id.backend.logic.security.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDetailsDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDetailsDto> getRole(@PathVariable String id) {
        return roleService.getRole(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoleDetailsDto> createRole(@Valid @RequestBody CreateRoleDto createRoleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDetailsDto> updateRole(@PathVariable String id, @Valid @RequestBody UpdateRoleDto updateRoleDto) {        return roleService.updateRole(id, updatedRoleDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
