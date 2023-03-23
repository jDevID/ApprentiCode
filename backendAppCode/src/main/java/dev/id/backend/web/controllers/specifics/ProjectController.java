package dev.id.backend.web.controllers.specifics;

import dev.id.backend.data.entities.Complexity;
import dev.id.backend.data.entities.Project;
import dev.id.backend.logic.dtos.specifics.ComplexityDto;
import dev.id.backend.logic.dtos.specifics.ProjectDto;
import dev.id.backend.logic.mappers.specifics.ComplexityMapper;
import dev.id.backend.logic.mappers.specifics.ProjectMapper;
import dev.id.backend.logic.services.specifics.ProjectServiceImpl;
import dev.id.backend.logic.utils.ResponseUtil;
import dev.id.backend.web.controllers.BaseController;
import dev.id.backend.web.dtos.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path ="/project")
public class ProjectController extends BaseController<Project, ProjectDto, Long> {
    private final ProjectServiceImpl projectService;
    private final ProjectMapper projectMapper;
    private final ComplexityMapper complexityMapper;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService, ProjectMapper projectMapper, ComplexityMapper complexityMapper) {
        super(projectService, projectMapper);
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.complexityMapper = complexityMapper;
    }
    // Get all complexities for a specific project using Criteria Builder:
    @GetMapping("/{projectId}/complexities")
    public ResponseEntity<ResponseDto<List<ComplexityDto>>> getComplexitiesByProject(@PathVariable Long projectId, @RequestParam(required = false) String name) {
        List<Complexity> complexities = projectService.findComplexitiesByProjectId(projectId, name);
        List<ComplexityDto> complexityDtos = complexities.stream().map(complexityMapper::toDTO).collect(Collectors.toList());

        ResponseDto<List<ComplexityDto>> responseDto = ResponseUtil.buildResponse(
                LocalDateTime.now(),
                HttpStatus.OK,
                "Complexities fetched successfully",
                null,
                complexityDtos);

        return ResponseEntity.ok(responseDto);
    }

    // Get the project associated with a specific complexity using Criteria Builder:
    @GetMapping("/complexity/{complexityId}/project")
    public ResponseEntity<ResponseDto<ProjectDto>> getProjectByComplexity(@PathVariable Long complexityId, @RequestParam(required = false) String name) {
        Project project = projectService.findProjectByComplexityId(complexityId, name);
        ProjectDto projectDto = projectMapper.toDTO(project);
        ResponseDto<ProjectDto> responseDto = ResponseUtil.buildResponse(
                LocalDateTime.now(),
                HttpStatus.OK,
                "Project fetched successfully",
                null, projectDto);

        return ResponseEntity.ok(responseDto);
    }

}
