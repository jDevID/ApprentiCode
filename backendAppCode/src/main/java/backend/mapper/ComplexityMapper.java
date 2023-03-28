package mapper;

import domain.entity.Complexity;
import domain.dto.ComplexityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplexityMapper extends BaseMapper<ComplexityDto, Complexity> {
}
