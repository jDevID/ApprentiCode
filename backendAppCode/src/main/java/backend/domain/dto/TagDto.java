package backend.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDto extends BaseDto implements Serializable {
    @NotBlank(message = "Tag name cannot be blank")
    @NotNull(message = "Tag name cannot be null")
    private String name;

    @NotBlank(message = "Operating system must not be blank")
    private String os;

    @Size(min = 3, max = 75, message = "min = 3, max = 75 characters")
    @NotBlank(message = "Tag description cannot be blank")
    private String description;

}
