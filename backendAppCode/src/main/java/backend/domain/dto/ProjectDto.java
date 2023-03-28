package backend.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto extends BaseDto implements Serializable {
    @NotBlank(message = "Project name must not be blank")
    @Pattern(regexp = "^[A-Z]\\w*$", message = "Project name must start with an uppercase letter")
    private  String name;
    private String description;
    private List<ComplexityDto> complexities;
}
