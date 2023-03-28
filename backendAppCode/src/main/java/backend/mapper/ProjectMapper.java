package backend.mapper;


import backend.domain.dto.ProjectDto;
import backend.domain.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<ProjectDto, Project> {
}
