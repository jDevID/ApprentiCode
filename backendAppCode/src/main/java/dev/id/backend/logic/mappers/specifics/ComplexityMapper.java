package dev.id.backend.logic.mappers.specifics;

import dev.id.backend.data.entities.Complexity;
import dev.id.backend.logic.dtos.specifics.ComplexityDto;
import dev.id.backend.logic.mappers.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplexityMapper extends BaseMapper<ComplexityDto, Complexity> {
}
