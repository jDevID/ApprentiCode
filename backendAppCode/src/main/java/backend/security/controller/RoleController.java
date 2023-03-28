package backend.security.controller;


import backend.security.domain.dto.RoleCreateDto;
import backend.security.domain.dto.RoleDetailsDto;
import backend.security.domain.dto.RoleUpdateDto;
import backend.security.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> getRole(@PathVariable String id) {
        Optional<RoleDetailsDto> roleDetailsDtoOptional = Optional.ofNullable(roleService.getRole(id));
        if (roleDetailsDtoOptional.isPresent()) {
            return ResponseEntity.ok(roleDetailsDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RoleDetailsDto> createRole(@Valid @RequestBody RoleCreateDto roleCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable String id, @Valid @RequestBody RoleUpdateDto roleUpdateDto) {
        Optional<RoleDetailsDto> roleDetailsDtoOptional = Optional.ofNullable(roleService.updateRole(id, roleUpdateDto));
        if (roleDetailsDtoOptional.isPresent()) {
            return ResponseEntity.ok(roleDetailsDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
