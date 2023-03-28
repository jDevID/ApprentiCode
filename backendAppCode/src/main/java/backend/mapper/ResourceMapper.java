package mapper;

import domain.entity.Resource;
import domain.dto.ResourceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ComplexityMapper.class)
public interface ResourceMapper extends BaseMapper<ResourceDto, Resource> {
}
