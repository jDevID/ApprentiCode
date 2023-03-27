package dev.id.backend.logic.security.models.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class RoleDetailsDto {
    private String id;
    private String name;
    private String description;
    private Set<Long> permissionIds;
}
