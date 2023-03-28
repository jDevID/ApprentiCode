package backend.service.impl;

import backend.domain.validation.DtoValidation;
import backend.filter.SearchCriteria;
import backend.filter.SearchCriteriaParser;
import backend.mapper.BaseMapper;
import backend.repository.BaseRepository;
import backend.service.BaseService;
import backend.specification.GenericSpecification;
import backend.util.IdUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseServiceImpl<T, D extends Serializable, ID extends Serializable, M extends BaseMapper<D, T>> implements BaseService<T, D, ID> {
    protected final BaseRepository<T, ID> repository;
    protected final M mapper;
    private final DtoValidation<D> validator;
    protected final IdUtil.IdGetter<T, ID> idGetter;
    protected final IdUtil.IdSetter<T, ID> idSetter;

    protected BaseServiceImpl(BaseRepository<T, ID> repository, M mapper, DtoValidation<D> validator, IdUtil.IdGetter<T, ID> idGetter, IdUtil.IdSetter<T, ID> idSetter) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.idGetter = idGetter;
        this.idSetter = idSetter;
    }

    protected GenericSpecification<T> buildSpecification(List<SearchCriteria> criteriaList) {
        return new GenericSpecification<>(criteriaList);
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

        ID updatedId = idGetter.getId(updatedEntity);
        if (!updatedId.equals(idGetter.getId(existingEntity))) {
            // Check if new ID unique
            if (repository.existsById(updatedId)) {
                throw new EntityExistsException("Entity with updated id: " + updatedId + " already exists.");
            }
            idSetter.setId(existingEntity, updatedId);
        }
        log.debug("Updating entity with id {}: {}", id, dto);

        // single responsibility principle
        // convert/update entity
        mapper.updateEntity(updatedEntity, existingEntity);
        // works on DB
        return repository.save(existingEntity);
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
        PageRequest pageable = PageRequest.of(0, limit);
        Page<T> page = repository.findAll(pageable);
        return page.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

}
