package dev.id.backend.logic.mappers;

import java.util.List;

public interface BaseMapper<D, T> {
    T fromDTO(D dto);
    D toDTO(T entity);
    List<T> fromDTOList(List<D> dtos);
    List<D> toDTOList(List<T> entities);
}
