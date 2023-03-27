package dev.id.backend.logic.config;

import dev.id.backend.logic.mappers.specifics.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ComplexityMapper complexityMapper() {
        return Mappers.getMapper(ComplexityMapper.class);
    }

    @Bean
    public ProjectMapper projectMapper() {
        return Mappers.getMapper(ProjectMapper.class);
    }

    @Bean
    public ResourceMapper resourceMapper() {
        return Mappers.getMapper(ResourceMapper.class);
    }

    @Bean
    public EntryMapper entryMapper() {
        return Mappers.getMapper(EntryMapper.class);
    }

    @Bean
    public TagMapper tagMapper() {
        return Mappers.getMapper(TagMapper.class);
    }

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }
}
