package backend.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;
public interface BaseMapper<D, E> {
    E fromDTO(D dto);
    D toDTO(E entity);
    List<E> fromDTOList(List<D> dtos);
    List<D> toDTOList(List<E> entities);
    void updateEntity(E source, @MappingTarget E target);
}
