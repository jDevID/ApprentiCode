package dev.id.backend.logic.mappers.specifics;

import dev.id.backend.data.entities.User;
import dev.id.backend.logic.dtos.specifics.UserDto;
import dev.id.backend.logic.mappers.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {
}
