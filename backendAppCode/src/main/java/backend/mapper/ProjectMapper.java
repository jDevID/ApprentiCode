package mapper;

import domain.entity.Project;
import domain.dto.ProjectDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<ProjectDto, Project> {
}
