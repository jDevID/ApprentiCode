package backend.mapper;

import backend.domain.dto.EntryDto;
import backend.domain.entity.Entry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ComplexityMapper.class, TagMapper.class})
public interface EntryMapper extends BaseMapper<EntryDto, Entry> {
}
