package dev.id.backend.logic.mappers.specifics;

import dev.id.backend.data.entities.Project;
import dev.id.backend.logic.dtos.specifics.ProjectDto;
import dev.id.backend.logic.mappers.BaseMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<ProjectDto, Project> {

}
