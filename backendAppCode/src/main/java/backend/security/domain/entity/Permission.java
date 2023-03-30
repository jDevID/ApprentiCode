package backend.security.domain.entity;

import backend.domain.dto.BaseDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Permission extends BaseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
}
