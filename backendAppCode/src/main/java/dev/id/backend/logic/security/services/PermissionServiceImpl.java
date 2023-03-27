package dev.id.backend.logic.security.services;

import dev.id.backend.logic.exceptions.NotFoundException;
import dev.id.backend.logic.security.models.dtos.PermissionCreateDto;
import dev.id.backend.logic.security.models.dtos.PermissionDetailsDto;
import dev.id.backend.logic.security.models.dtos.PermissionUpdateDto;
import dev.id.backend.logic.security.models.entities.Permission;
import dev.id.backend.logic.security.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository<Permission, Long> permissionRepository;

    @Override
    public PermissionDetailsDto createPermission(PermissionCreateDto createPermissionDto) {
        Permission permission = new Permission();
        permission.setName(createPermissionDto.getName());
        permission.setDescription(createPermissionDto.getDescription());
        Permission savedPermission = permissionRepository.save(permission);
        return convertToPermissionDetailsDto(savedPermission);
    }

    @Override
    public Optional<PermissionDetailsDto> updatePermission(String id, PermissionUpdateDto updatePermissionDto) {
        Permission permission = permissionRepository.findById(Long.parseLong(id)).orElseThrow(
                () -> new NotFoundException(String.format("Permission with id %s not found", id)));
        permission.setName(updatePermissionDto.getName());
        permission.setDescription(updatePermissionDto.getDescription());
    Permission savedPermission = permissionRepository.save(permission);
        return convertToPermissionDetailsDto(savedPermission);
    }
    @Override
    public Optional<PermissionDetailsDto> getPermission(String id) {
        Optional<Permission> permission = permissionRepository.findById(Long.parseLong(id));
        return permission.map(this::convertToPermissionDetailsDto);
    }

    @Override
    public void deletePermission(String id) {
        Permission permission = permissionRepository.findById(Long.parseLong(id)).orElseThrow(
                () -> new NotFoundException(String.format("Permission with id %s not found", id)));
        permissionRepository.delete(permission);
    }

    @Override
    public List<PermissionDetailsDto> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(this::convertToPermissionDetailsDto).collect(Collectors.toList());
    }

    private PermissionDetailsDto convertToPermissionDetailsDto(Permission permission) {
        PermissionDetailsDto permissionDetailsDto = new PermissionDetailsDto();
        permissionDetailsDto.setId(permission.getId().toString());
        permissionDetailsDto.setName(permission.getName());
        permissionDetailsDto.setDescription(permission.getDescription());
        return permissionDetailsDto;
    }

}