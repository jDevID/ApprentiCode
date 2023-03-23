package dev.id.backend.logic.services.specifics;

import dev.id.backend.data.entities.Entry;
import dev.id.backend.data.repositories.BaseRepository;
import dev.id.backend.data.validation.DtoValidationImpl;
import dev.id.backend.logic.dtos.specifics.EntryDto;
import dev.id.backend.logic.mappers.specifics.EntryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Slf4j
@Service
@Transactional
public class EntryServiceImpl extends BaseServiceImpl<Entry, EntryDto, Long, EntryMapper> implements EntryService {
    private final BaseRepository<Entry, Long> repository;
    private final EntryMapper entryMapper;

    @Autowired
    public EntryServiceImpl(
            @Qualifier("entryRepository") BaseRepository<Entry, Long> repository,
            EntryMapper mapper,
            DtoValidationImpl<EntryDto> validator) {
        super(repository, mapper, validator);
        this.repository = repository;
        this.entryMapper = mapper;
    }
    @Override
    public Collection<Entry> list(int limit) {
        PageRequest pageable = PageRequest.of(0, limit);
        return repository.findAll(pageable).getContent();
    }

    @Override
    protected Entry fromDTO(EntryDto dto) {
        return entryMapper.fromDTO(dto);
    }

    @Override
    protected EntryDto toDTO(Entry entity) {
        return entryMapper.toDTO(entity);
    }

    @Override
    protected Long getId(Entry entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Entry entity, Long id) {
        entity.setId(id);
    }

}
