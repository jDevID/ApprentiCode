package backend.security.domain.dto;

import backend.domain.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionUpdateDto extends BaseDto implements Serializable {
        private String name;
        private String description;
}
