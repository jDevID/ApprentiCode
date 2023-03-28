package backend.mapper;


import backend.domain.dto.ResourceDto;
import backend.domain.entity.Resource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ComplexityMapper.class)
public interface ResourceMapper extends BaseMapper<ResourceDto, Resource> {
}
