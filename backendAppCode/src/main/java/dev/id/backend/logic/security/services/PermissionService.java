package dev.id.backend.logic.security.services;

import dev.id.backend.logic.security.models.dtos.PermissionCreateDto;
import dev.id.backend.logic.security.models.dtos.PermissionDetailsDto;
import dev.id.backend.logic.security.models.dtos.PermissionUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    PermissionDetailsDto createPermission(PermissionCreateDto createPermissionDto);

   Optional<PermissionDetailsDto> updatePermission(String id, PermissionUpdateDto updatePermissionDto);

    Optional<PermissionDetailsDto> getPermission(String id);

    void deletePermission(String id);

    List<PermissionDetailsDto> getAllPermissions();

}
