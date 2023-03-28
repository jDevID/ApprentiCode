package backend.service.impl;

import domain.entity.Complexity;
import domain.entity.Project;
import domain.entity.Resource;
import repositories.ComplexityRepository;
import repositories.ProjectRepository;
import repositories.ResourceRepository;
import domain.dto.ComplexityDto;
import domain.dto.ProjectDto;
import domain.dto.ResourceDto;
import mapper.ComplexityMapper;
import mapper.ProjectMapper;
import mapper.ResourceMapper;
import backend.specification.GenericSpecification;
import criteria.SearchCriteria;
import backend.util.SearchOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.service.ProjectService;

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

    protected GenericSpecification<Complexity> buildComplexitySpecification(List<SearchCriteria> criteriaList) {
        return new GenericSpecification<>(criteriaList);
    }

    @Override
    public Page<ProjectDto> search(String searchFilter, String tagName, String complexityName, String command, Boolean active, Long projectId, Pageable pageable) {
        List<SearchCriteria> criteriaList = new ArrayList<>();

        if (searchFilter != null) {
            criteriaList.add(new SearchCriteria("searchFilter", SearchOperation.LIKE, searchFilter));
        }
        if (tagName != null) {
            criteriaList.add(new SearchCriteria("tagName", SearchOperation.LIKE, tagName));
        }
        if (complexityName != null) {
            criteriaList.add(new SearchCriteria("complexityName", SearchOperation.LIKE, complexityName));
        }
        if (command != null) {
            criteriaList.add(new SearchCriteria("command", SearchOperation.LIKE, command));
        }
        if (active != null) {
            criteriaList.add(new SearchCriteria("active", SearchOperation.EQUALITY, active));
        }
        if (projectId != null) {
            criteriaList.add(new SearchCriteria("projectId", SearchOperation.EQUALITY, projectId));
        }

        Specification<Project> spec = new GenericSpecification<>(criteriaList);


        Page<Project> projectPage = repository.findAll(spec, pageable);
        return projectPage.map(projectMapper::toDTO);
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

        GenericSpecification<Complexity> spec = buildComplexitySpecification(criteriaList);
        return complexityRepository.findAll(spec);
    }

    public Project findProjectByComplexityId(Long complexityId, String name) {
        List<SearchCriteria> criteriaList = new ArrayList<>();
        criteriaList.add(new SearchCriteria("id", SearchOperation.EQUALITY, complexityId));

        if (name != null && !name.trim().isEmpty()) {
            criteriaList.add(new SearchCriteria("project.name", SearchOperation.LIKE, name.toLowerCase()));
        }

        GenericSpecification<Complexity> spec = buildComplexitySpecification(criteriaList);
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
