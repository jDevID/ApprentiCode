package dev.id.backend.logic.security.models.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class CreateRoleDto {
    private String name;
    private Set<String> permissionNames;
}
