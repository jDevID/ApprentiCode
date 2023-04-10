package backend.mapper;

import backend.domain.dto.UserDto;
import backend.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {
    @Mapping(target = "authorities", ignore = true)
    void updateUserAuthorities(User source, @MappingTarget User target);

    @Mapping(target = "authorities", ignore = true)
    void updateEntity(User source, @MappingTarget User target);

}
