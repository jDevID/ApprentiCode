package dev.id.backend.logic.services.specifics;

import dev.id.backend.data.entities.Complexity;
import dev.id.backend.data.entities.Project;
import dev.id.backend.data.repositories.ComplexityRepository;
import dev.id.backend.data.repositories.ProjectRepository;
import dev.id.backend.logic.dtos.specifics.ProjectDto;
import dev.id.backend.logic.mappers.specifics.ProjectMapper;
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
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProjectServiceImpl extends BaseServiceImpl<Project, ProjectDto, Long, ProjectMapper> implements ProjectService {

    private final ComplexityRepository complexityRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ComplexityRepository complexityRepository, ProjectMapper projectMapper) {
        super(projectRepository, projectMapper, null);
        this.complexityRepository = complexityRepository;
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


    @Override
    public Collection<Project> list(int limit) {
        PageRequest pageable = PageRequest.of(0, limit);
        return repository.findAll(pageable).getContent();
    }

    @Override
    protected Project fromDTO(ProjectDto dto) {
        return null;
    }

    @Override
    protected ProjectDto toDTO(Project entity) {
        return null;
    }

    @Override
    protected Long getId(Project entity) {
        return null;
    }

    @Override
    protected void setId(Project entity, Long aLong) {

    }

}
