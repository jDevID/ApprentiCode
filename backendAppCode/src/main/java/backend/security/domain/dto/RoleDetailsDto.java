package backend.security.domain.dto;

import backend.domain.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDetailsDto extends BaseDto implements Serializable {
    private String name;
    private String description;
    private Set<Long> permissionIds;
}
