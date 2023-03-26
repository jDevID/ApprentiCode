package dev.id.backend.logic.services.specifics;

import dev.id.backend.data.entities.Entry;
import dev.id.backend.data.repositories.ComplexityRepository;
import dev.id.backend.data.repositories.EntryRepository;
import dev.id.backend.data.repositories.TagRepository;
import dev.id.backend.logic.dtos.specifics.EntryDto;
import dev.id.backend.logic.mappers.specifics.EntryMapper;
import dev.id.backend.logic.specs.SearchCriteria;
import dev.id.backend.logic.specs.specifics.EntrySpecification;
import dev.id.backend.logic.utils.SearchCriteriaParser;
import dev.id.backend.logic.utils.SpecificationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class EntryServiceImpl extends BaseServiceImpl<Entry, EntryDto, Long, EntryMapper> implements EntryService {
    private final TagRepository tagRepository;
    private final ComplexityRepository complexityRepository;
    private final EntryMapper entryMapper;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository, TagRepository tagRepository, ComplexityRepository complexityRepository, EntryMapper entryMapper) {
        super(entryRepository, entryMapper, null, null, null);
        this.complexityRepository = complexityRepository;
        this.entryMapper = entryMapper;
        this.tagRepository = tagRepository;
    }

    public Page<EntryDto> search(String searchFilter, String tagName, String complexityName, String command, Boolean active, Long projectId, Pageable pageable) {        List<SearchCriteria> searchCriteria = SearchCriteriaParser.parse(searchFilter);

        Specification<Entry> combinedSpec = SpecificationUtil.createSpecificationFromCriteria(searchCriteria);
        if (tagName != null) combinedSpec = combinedSpec.and(EntrySpecification.hasTag(tagName));
        if (complexityName != null) combinedSpec = combinedSpec.and(EntrySpecification.hasComplexity(complexityName));
        if (command != null) combinedSpec = combinedSpec.and(EntrySpecification.hasCommandLike(command));
        if (active != null) combinedSpec = combinedSpec.and(EntrySpecification.isActive(active));
        if (projectId != null) combinedSpec = combinedSpec.and(EntrySpecification.hasProject(projectId));

        Page<Entry> entries = repository.findAll(combinedSpec, pageable);

        return entries.map(entryMapper::toDTO);
    }

    @Override
    public List<EntryDto> list(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Entry> projects = repository.findAll(pageable).getContent();
        return projects.stream()
                .map(entryMapper::toDTO)
                .collect(Collectors.toList());
    }


}
