package security.services;

import security.models.dtos.PermissionCreateDto;
import security.models.dtos.PermissionDetailsDto;
import security.models.dtos.PermissionUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    PermissionDetailsDto createPermission(PermissionCreateDto createPermissionDto);

   Optional<PermissionDetailsDto> updatePermission(String id, PermissionUpdateDto updatePermissionDto);

    Optional<PermissionDetailsDto> getPermission(String id);

    void deletePermission(String id);

    List<PermissionDetailsDto> getAllPermissions();

}
