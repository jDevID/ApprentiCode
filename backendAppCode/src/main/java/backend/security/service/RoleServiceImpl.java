package backend.security.service;

import backend.exception.NotFoundException;
import backend.security.domain.dto.RoleCreateDto;
import backend.security.domain.dto.RoleDetailsDto;
import backend.security.domain.dto.RoleUpdateDto;
import backend.security.domain.entity.Permission;
import backend.security.domain.entity.Role;
import backend.security.repository.PermissionRepository;
import backend.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository<Permission, Long> permissionRepository;

    @Override
    public RoleDetailsDto createRole(RoleCreateDto roleCreateDto) {
        Role role = new Role();
        role.setName(roleCreateDto.getName());
        role.setDescription(roleCreateDto.getDescription());
        Set<Permission> permissions = new HashSet<>();
        for (Long permissionId : roleCreateDto.getPermissionIds()) {
            permissions.add(permissionRepository.findById(permissionId).orElseThrow(
                    () -> new NotFoundException(String.format("Permission with id %s not found", permissionId))));
        }
        role.setPermissions(permissions);
        Role savedRole = roleRepository.save(role);
        return convertToRoleDetailsDto(savedRole);
    }

    @Override
    public RoleDetailsDto updateRole(String id, RoleUpdateDto roleUpdateDto) {
        Role role = roleRepository.findById(Long.parseLong(id)).orElseThrow(
                () -> new NotFoundException(String.format("Role with id %s not found", id)));
        role.setName(roleUpdateDto.getName());
        role.setDescription(roleUpdateDto.getDescription());
        Set<Permission> permissions = new HashSet<>();
        for (Long permissionId : roleUpdateDto.getPermissionIds()) {
            permissions.add(permissionRepository.findById(permissionId).orElseThrow(
                    () -> new NotFoundException(String.format("Permission with id %s not found", permissionId))));
        }
        role.setPermissions(permissions);
        Role savedRole = roleRepository.save(role);
        return convertToRoleDetailsDto(savedRole);
    }

    @Override
    public RoleDetailsDto getRole(String id) {
        Role role = roleRepository.findById(Long.parseLong(id)).orElseThrow(
                () -> new NotFoundException(String.format("Role with id %s not found", id)));
        return convertToRoleDetailsDto(role);
    }

    @Override
    public void deleteRole(String id) {
        Role role = roleRepository.findById(Long.parseLong(id)).orElseThrow(
                () -> new NotFoundException(String.format("Role with id %s not found", id)));
        roleRepository.delete(role);
    }

    @Override
    public List<RoleDetailsDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(this::convertToRoleDetailsDto).collect(Collectors.toList());
    }

    private RoleDetailsDto convertToRoleDetailsDto(Role role) {
        RoleDetailsDto roleDetailsDto = new RoleDetailsDto();
        roleDetailsDto.setId(role.getId());
        roleDetailsDto.setName(role.getName());
        roleDetailsDto.setDescription(role.getDescription());
        roleDetailsDto.setPermissionIds(role.getPermissions().stream().map(Permission::getId)
                .collect(Collectors.toSet()));
        return roleDetailsDto;
    }
}
