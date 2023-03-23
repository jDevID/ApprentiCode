package dev.id.backend.logic.services.specifics;

import dev.id.backend.data.repositories.BaseRepository;
import dev.id.backend.data.validation.EntityValidator;
import dev.id.backend.logic.mappers.BaseMapper;
import dev.id.backend.logic.services.BaseService;
import dev.id.backend.logic.specs.GenericSpecification;
import dev.id.backend.logic.specs.SearchCriteria;
import dev.id.backend.logic.utils.SearchCriteriaParser;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseServiceImpl<T, D extends Serializable, ID extends Serializable, M> implements BaseService<T, D, ID> {
    protected final BaseRepository<T, ID> repository;
    private final BaseMapper<D, T> mapper;
    private final EntityValidator<D> validator;

    protected BaseServiceImpl(BaseRepository<T, ID> repository, BaseMapper<D, T> mapper, EntityValidator<D> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    private GenericSpecification<T> buildSpecification(List<SearchCriteria> criteriaList) {
        GenericSpecification<T> spec = new GenericSpecification<>();

        for (SearchCriteria criteria : criteriaList) {
            spec.add(criteria);
        }

        return spec;
    }

    @Cacheable("devCache")
    public Page<T> findByFilterString(String filterString, Pageable pageable) {
        List<SearchCriteria> criteriaList = SearchCriteriaParser.parse(filterString);
        GenericSpecification<T> spec = buildSpecification(criteriaList);
        return repository.findAll(spec, pageable);
    }

    @Cacheable("devCache")
    public Page<T> findAll(Pageable pageable) {
        log.debug("Finding all entities");
        return repository.findAll(pageable);
    }

    @Override
    public Optional<T> findById(ID id) {
        log.debug("Finding entity by id: {}", id);
        return repository.findById(id);
    }

    @Override
    public D create(D dto) {
        log.debug("Creating entity {}", dto);
        validator.validate(dto);
        T entity = mapper.fromDTO(dto);
        T createdEntity = repository.save(entity);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public T update(ID id, D dto) {
        validator.validate(dto);
        T existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        T updatedEntity = mapper.fromDTO(dto);
        setId(updatedEntity, getId(existingEntity));
        log.debug("Updating entity with id {}: {}", id, dto);
        return repository.save(updatedEntity);
    }

    @Override
    public boolean deleteById(ID id) {
        log.debug("Deleting entity with id: {}", id);
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<D> list(int limit) {
        log.debug("Listing entities with limit: {}", limit);
        return repository.findAll().stream()
                .limit(limit)
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    protected abstract T fromDTO(D dto);

    protected abstract D toDTO(T entity);

    protected abstract ID getId(T entity);

    protected abstract void setId(T entity, ID id);
}
