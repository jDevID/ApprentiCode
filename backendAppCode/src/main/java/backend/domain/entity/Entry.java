package backend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Entity@Getter
@Setter @ToString
@Table(name = "entry")
public class Entry extends BaseEntity {

    @Size(min = 3, max = 50, message = "min = 3, max = 100 characters")
    @NotBlank(message = "Command must not be blank")
    @NotNull
    @Column(name = "command", nullable = false)
    private String command;
    @Size(min = 3, max = 100)
    @Column(name = "response")
    private String response;
    @Size(min = 3, max = 100)
    @Column(name = "hint")
    private String hint;

    @ManyToOne
    @JoinColumn(name = "complexity_id")
    private Complexity complexity;

    @ManyToMany
    @JoinTable(
            name = "entry_tags",
            joinColumns = @JoinColumn(name = "entry_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ToString.Exclude
    private List<Tag> tags;
}
