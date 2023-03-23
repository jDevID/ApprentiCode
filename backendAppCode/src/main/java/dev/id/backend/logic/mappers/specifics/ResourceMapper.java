package dev.id.backend.logic.mappers.specifics;

import dev.id.backend.data.entities.Resource;
import dev.id.backend.logic.dtos.specifics.ResourceDto;
import dev.id.backend.logic.mappers.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ComplexityMapper.class)
public interface ResourceMapper extends BaseMapper<ResourceDto, Resource> {
}
