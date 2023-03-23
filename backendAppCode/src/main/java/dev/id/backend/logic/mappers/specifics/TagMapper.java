package dev.id.backend.logic.mappers.specifics;


import dev.id.backend.data.entities.Tag;
import dev.id.backend.logic.dtos.specifics.TagDto;
import dev.id.backend.logic.mappers.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper extends BaseMapper<TagDto, Tag> {
}