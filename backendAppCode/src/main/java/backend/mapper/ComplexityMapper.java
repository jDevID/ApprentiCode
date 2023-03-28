package backend.mapper;


import backend.domain.dto.ComplexityDto;
import backend.domain.entity.Complexity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplexityMapper extends BaseMapper<ComplexityDto, Complexity> {
}
