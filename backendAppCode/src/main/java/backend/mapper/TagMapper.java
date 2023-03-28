package backend.mapper;


import backend.domain.dto.TagDto;
import backend.domain.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper extends BaseMapper<TagDto, Tag> {
}
