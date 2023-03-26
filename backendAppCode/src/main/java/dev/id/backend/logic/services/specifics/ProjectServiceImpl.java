package dev.id.backend.logic.services.specifics;

import dev.id.backend.data.entities.Complexity;
import dev.id.backend.data.entities.Project;
import dev.id.backend.data.entities.Resource;
import dev.id.backend.data.repositories.ComplexityRepository;
import dev.id.backend.data.repositories.ProjectRepository;
import dev.id.backend.data.repositories.ResourceRepository;
import dev.id.backend.logic.dtos.specifics.ComplexityDto;
import dev.id.backend.logic.dtos.specifics.ProjectDto;
import dev.id.backend.logic.dtos.specifics.ResourceDto;
import dev.id.backend.logic.mappers.specifics.ComplexityMapper;
import dev.id.backend.logic.mappers.specifics.ProjectMapper;
import dev.id.backend.logic.mappers.specifics.ResourceMapper;
import dev.id.backend.logic.specs.SearchCriteria;
import dev.id.backend.logic.specs.SearchOperation;
import dev.id.backend.logic.utils.SpecificationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class ProjectServiceImpl extends BaseServiceImpl<Project, ProjectDto, Long, ProjectMapper> implements ProjectService {

    private final ComplexityRepository complexityRepository;
    private final ResourceRepository resourceRepository;
    private final ProjectMapper projectMapper;
    private final ComplexityMapper complexityMapper;
    private final ResourceMapper resourceMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ComplexityRepository complexityRepository, ResourceRepository resourceRepository, ProjectMapper projectMapper, ComplexityMapper complexityMapper, ResourceMapper resourceMapper) {
        super(projectRepository, projectMapper, null, null, null);
        this.complexityRepository = complexityRepository;
        this.resourceRepository = resourceRepository;
        this.projectMapper = projectMapper;
        this.complexityMapper = complexityMapper;
        this.resourceMapper = resourceMapper;
    }


    public ComplexityDto updateComplexityInProject(Long projectId, Long complexityId, ComplexityDto complexityDto) {
        Project project = getProject(projectId);
        Complexity existingComplexity = getComplexityBelongingToProject(complexityId, project);

        existingComplexity.setName(complexityDto.getName());
        Complexity updatedComplexity = complexityRepository.save(existingComplexity);
        return complexityMapper.toDTO(updatedComplexity);
    }


    @Override
    public List<ProjectDto> list(int limit) {
        PageRequest pageable = PageRequest.of(0, limit);
        List<Project> projects = repository.findAll(pageable).getContent();
        return projects.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Complexity addComplexityToProject(Long projectId, Complexity complexity) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        complexity.setProject(project);
        return complexityRepository.save(complexity);
    }

    public List<Complexity> findComplexitiesByProjectId(Long projectId, String name) {
        List<SearchCriteria> criteriaList = new ArrayList<>();
        criteriaList.add(new SearchCriteria("project.id", SearchOperation.EQUALITY, projectId));

        if (name != null && !name.trim().isEmpty()) {
            criteriaList.add(new SearchCriteria("name", SearchOperation.LIKE, name.toLowerCase()));
        }

        Specification<Complexity> spec = SpecificationUtil.createSpecificationFromCriteria(criteriaList);
        return complexityRepository.findAll(spec);
    }

    public Project findProjectByComplexityId(Long complexityId, String name) {
        List<SearchCriteria> criteriaList = new ArrayList<>();
        criteriaList.add(new SearchCriteria("id", SearchOperation.EQUALITY, complexityId));

        if (name != null && !name.trim().isEmpty()) {
            criteriaList.add(new SearchCriteria("project.name", SearchOperation.LIKE, name.toLowerCase()));
        }

        Specification<Complexity> spec = SpecificationUtil.createSpecificationFromCriteria(criteriaList);
        Complexity complexity = complexityRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Complexity not found"));
        return complexity.getProject();
    }

    public void deleteComplexityFromProject(Long projectId, Long complexityId) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        Complexity complexity = complexityRepository.findById(complexityId)
                .orElseThrow(() -> new ResourceNotFoundException("Complexity not found"));

        if (!complexity.getProject().getId().equals(project.getId())) {
            throw new IllegalArgumentException("Complexity does not belong to the specified project");
        }

        complexityRepository.deleteById(complexityId);
    }

    public Complexity getComplexityById(Long projectId, Long complexityId) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        Complexity complexity = complexityRepository.findById(complexityId)
                .orElseThrow(() -> new ResourceNotFoundException("Complexity not found"));

        if (!complexity.getProject().getId().equals(project.getId())) {
            throw new IllegalArgumentException("Complexity does not belong to the specified project");
        }

        return complexity;
    }

    public List<ResourceDto> getResourcesByComplexity(Long projectId, Long complexityId) {
        Project project = getProject(projectId);
        Complexity complexity = getComplexityBelongingToProject(complexityId, project);

        List<Resource> resources = complexity.getResources();
        return resourceMapper.toDTOList(resources);
    }

    public ComplexityDto assignResourceToComplexity(Long projectId, Long complexityId, Long resourceId) {
        Project project = getProject(projectId);
        Complexity complexity = getComplexityBelongingToProject(complexityId, project);
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        complexity.getResources().add(resource);
        Complexity updatedComplexity = complexityRepository.save(complexity);
        return complexityMapper.toDTO(updatedComplexity);
    }

    private Project getProject(Long projectId) {
        return repository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    private Complexity getComplexityBelongingToProject(Long complexityId, Project project) {
        Complexity complexity = complexityRepository.findById(complexityId)
                .orElseThrow(() -> new ResourceNotFoundException("Complexity not found"));

        if (!complexity.getProject().getId().equals(project.getId())) {
            throw new IllegalArgumentException("Complexity does not belong to the specified project");
        }

        return complexity;
    }

}
