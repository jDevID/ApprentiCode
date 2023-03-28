package backend.security.service;


import backend.security.domain.dto.RoleCreateDto;
import backend.security.domain.dto.RoleDetailsDto;
import backend.security.domain.dto.RoleUpdateDto;

import java.util.List;

public interface RoleService {
    RoleDetailsDto createRole(RoleCreateDto createRoleDto);
    RoleDetailsDto updateRole(String id, RoleUpdateDto roleUpdateDto);
    RoleDetailsDto getRole(String id);
    void deleteRole(String id);
    List<RoleDetailsDto> getAllRoles();
}
