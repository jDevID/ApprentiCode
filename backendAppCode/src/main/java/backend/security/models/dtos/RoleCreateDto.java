package security.models.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class RoleCreateDto {
    private String name;
    private String description;
    private Set<Long> permissionIds;
}
