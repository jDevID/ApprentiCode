package backend.controller;

import backend.domain.dto.ComplexityDto;
import backend.domain.dto.ProjectDto;
import backend.domain.dto.ResourceDto;
import backend.domain.dto.ResponseDto;
import backend.domain.entity.Complexity;
import backend.domain.entity.Project;
import backend.mapper.ComplexityMapper;
import backend.mapper.ProjectMapper;
import backend.mapper.ResourceMapper;
import backend.service.impl.ProjectServiceImpl;
import backend.util.ResponseUtil;
import jakarta.validation.Valid;
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
@RequestMapping(path = "/project")
public class ProjectController extends BaseController<Project, ProjectDto, Long> {
    private final ProjectServiceImpl projectService;
    private final ProjectMapper projectMapper;
    private final ComplexityMapper complexityMapper;
    private final ResourceMapper resourceMapper;
    @Autowired
    public ProjectController(ProjectServiceImpl projectService, ProjectMapper projectMapper, ComplexityMapper complexityMapper, ResourceMapper resourceMapper) {
        super(projectService, projectMapper);
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.complexityMapper = complexityMapper;
        this.resourceMapper = resourceMapper;
    }
/*
    // 1. Add a new complexity to a project.
    // 2. Update an existing complexity within a project.
    // 3. Delete a complexity from a project.
    // 4. Get all complexities for a specific project using Criteria Builder:
    // 5. Get the project associated with a specific complexity using Criteria Builder:
    // 6. Get a specific complexity within a project.
    // 7. Get all resources for a specific complexity within a project.
    // 8. Assign a resource to a specific complexity within a project.
 */
    // 1. Add a new complexity to a project.
    @PostMapping("/{projectId}/complexities")
    public ResponseEntity<ResponseDto<ComplexityDto>> addComplexityToProject(@PathVariable Long projectId, @Valid @RequestBody ComplexityDto complexityDto) {
        Complexity complexity = complexityMapper.fromDTO(complexityDto);
        Complexity createdComplexity = projectService.addComplexityToProject(projectId, complexity);
        ComplexityDto createdComplexityDto = complexityMapper.toDTO(createdComplexity);

        ResponseDto<ComplexityDto> responseDto = ResponseUtil.buildResponse(
                LocalDateTime.now(),
                HttpStatus.CREATED,
                "Complexity added successfully",
                null,
                createdComplexityDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 2. Update an existing complexity within a project.
    @PutMapping("/{projectId}/complexities/{complexityId}")
    public ResponseEntity<ResponseDto<ComplexityDto>> updateComplexityInProject(@PathVariable Long projectId, @PathVariable Long complexityId, @Valid @RequestBody ComplexityDto complexityDto) {
        ComplexityDto updatedComplexity = projectService.updateComplexityInProject(projectId, complexityId, complexityDto);

        ResponseDto<ComplexityDto> responseDto = ResponseUtil.buildResponse(
                LocalDateTime.now(),
                HttpStatus.OK,
                "Complexity updated successfully",
                null,
                updatedComplexity);

        return ResponseEntity.ok(responseDto);
    }

    // 3. Delete a complexity from a project.
    @DeleteMapping("/{projectId}/complexities/{complexityId}")
    public ResponseEntity<ResponseDto<Void>> deleteComplexityFromProject(@PathVariable Long projectId, @PathVariable Long complexityId) {
        projectService.deleteComplexityFromProject(projectId, complexityId);

        ResponseDto<Void> responseDto = ResponseUtil.buildResponse(
                LocalDateTime.now(),
                HttpStatus.NO_CONTENT,
                "Complexity deleted successfully",
                null,
                null);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
    }

    // 4. Get all complexities for a specific project using Criteria Builder:
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

    // 5. Get the project associated with a specific complexity using Criteria Builder:
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

    // 6. Get a specific complexity within a project.
    @GetMapping("/{projectId}/complexities/{complexityId}")
    public ResponseEntity<ResponseDto<ComplexityDto>> getComplexityById(@PathVariable Long projectId, @PathVariable Long complexityId) {
        Complexity complexity = projectService.getComplexityById(projectId, complexityId);
        ComplexityDto complexityDto = complexityMapper.toDTO(complexity);

        ResponseDto<ComplexityDto> responseDto = ResponseUtil.buildResponse(
                LocalDateTime.now(),
                HttpStatus.OK,
                "Complexity fetched successfully",
                null,
                complexityDto);

        return ResponseEntity.ok(responseDto);
    }

    // 7. Get all resources for a specific complexity within a project.
    @GetMapping("/{projectId}/complexities/{complexityId}/resources")
    public ResponseEntity<ResponseDto<List<ResourceDto>>> getResourcesByComplexity(@PathVariable Long projectId, @PathVariable Long complexityId) {
        List<ResourceDto> resources = projectService.getResourcesByComplexity(projectId, complexityId);

        ResponseDto<List<ResourceDto>> responseDto = ResponseUtil.buildResponse(
                LocalDateTime.now(),
                HttpStatus.OK,
                "Resources fetched successfully",
                null,
                resources);

        return ResponseEntity.ok(responseDto);
    }

    // 8. Assign a resource to a specific complexity within a project.
    @PostMapping("/{projectId}/complexities/{complexityId}/resources/{resourceId}")
    public ResponseEntity<ResponseDto<ComplexityDto>> assignResourceToComplexity(@PathVariable Long projectId, @PathVariable Long complexityId, @PathVariable Long resourceId) {
        ComplexityDto updatedComplexityDto = projectService.assignResourceToComplexity(projectId, complexityId, resourceId);

        ResponseDto<ComplexityDto> responseDto = ResponseUtil.buildResponse(
                LocalDateTime.now(),
                HttpStatus.OK,
                "Resource assigned to complexity successfully",
                null,
                updatedComplexityDto);

        return ResponseEntity.ok(responseDto);
    }

}
