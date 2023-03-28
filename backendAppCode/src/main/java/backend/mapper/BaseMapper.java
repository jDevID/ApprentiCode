package mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<D, T> {
    T fromDTO(D dto);
    D toDTO(T entity);
    List<T> fromDTOList(List<D> dtos);
    List<D> toDTOList(List<T> entities);
    void updateEntity(T source, @MappingTarget T target);
}
