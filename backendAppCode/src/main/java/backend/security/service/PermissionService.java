package backend.security.service;


import backend.security.domain.dto.PermissionCreateDto;
import backend.security.domain.dto.PermissionDetailsDto;
import backend.security.domain.dto.PermissionUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    PermissionDetailsDto createPermission(PermissionCreateDto createPermissionDto);

   Optional<PermissionDetailsDto> updatePermission(String id, PermissionUpdateDto updatePermissionDto);

    Optional<PermissionDetailsDto> getPermission(String id);

    void deletePermission(String id);

    List<PermissionDetailsDto> getAllPermissions();

}
