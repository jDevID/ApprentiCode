package dev.id.backend.logic.security.services;

import dev.id.backend.logic.security.models.dtos.RoleCreateDto;
import dev.id.backend.logic.security.models.dtos.RoleDetailsDto;
import dev.id.backend.logic.security.models.dtos.RoleUpdateDto;

import java.util.List;

public interface RoleService {
    RoleDetailsDto createRole(RoleCreateDto createRoleDto);
    RoleDetailsDto updateRole(String id, RoleUpdateDto roleUpdateDto);
    RoleDetailsDto getRole(String id);
    void deleteRole(String id);
    List<RoleDetailsDto> getAllRoles();
}
