package dev.id.backend.logic.security.controller;

import dev.id.backend.logic.security.models.dtos.PermissionCreateDto;
import dev.id.backend.logic.security.models.dtos.PermissionDetailsDto;
import dev.id.backend.logic.security.models.dtos.PermissionUpdateDto;
import dev.id.backend.logic.security.services.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;


    @GetMapping
    public ResponseEntity<List<PermissionDetailsDto>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDetailsDto> getPermission(@PathVariable String id) {
        return permissionService.getPermission(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PermissionDetailsDto> createPermission(@Valid @RequestBody PermissionCreateDto createPermissionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createPermission(createPermissionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDetailsDto> updatePermission(@PathVariable String id, @Valid @RequestBody PermissionUpdateDto updatePermissionDto) {
        return permissionService.updatePermission(id, updatePermissionDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable String id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }

}