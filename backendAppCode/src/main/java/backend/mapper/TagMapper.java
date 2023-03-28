package mapper;


import domain.entity.Tag;
import domain.dto.TagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper extends BaseMapper<TagDto, Tag> {
}
