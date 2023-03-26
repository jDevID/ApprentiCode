package dev.id.backend.logic.security.service;

import dev.id.backend.logic.security.models.dtos.CreateRoleDto;
import dev.id.backend.logic.security.models.dtos.RoleDetailsDto;
import dev.id.backend.logic.security.models.dtos.UpdateRoleDto;

import java.util.List;

public interface RoleService {
    RoleDetailsDto createRole(CreateRoleDto createRoleDto);
    RoleDetailsDto updateRole(String id, UpdateRoleDto updateRoleDto);
    RoleDetailsDto getRole(String id);
    void deleteRole(String id);
    List<RoleDetailsDto> getAllRoles();
}
