package mapper;

import domain.entity.Entry;
import domain.dto.EntryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ComplexityMapper.class, TagMapper.class})
public interface EntryMapper extends BaseMapper<EntryDto, Entry> {
}
