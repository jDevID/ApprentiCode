package dev.id.backend.logic.mappers.specifics;

import dev.id.backend.data.entities.Entry;
import dev.id.backend.logic.dtos.specifics.EntryDto;
import dev.id.backend.logic.mappers.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ComplexityMapper.class, TagMapper.class})
public interface EntryMapper extends BaseMapper<EntryDto, Entry> {
}
