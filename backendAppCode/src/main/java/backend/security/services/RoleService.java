package security.services;

import security.models.dtos.RoleCreateDto;
import security.models.dtos.RoleDetailsDto;
import security.models.dtos.RoleUpdateDto;

import java.util.List;

public interface RoleService {
    RoleDetailsDto createRole(RoleCreateDto createRoleDto);
    RoleDetailsDto updateRole(String id, RoleUpdateDto roleUpdateDto);
    RoleDetailsDto getRole(String id);
    void deleteRole(String id);
    List<RoleDetailsDto> getAllRoles();
}
