package dev.id.backend.logic.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, D, ID extends Serializable> {
    Page<T> findAll(Pageable pageable);
    Page<T> findByFilterString(String filterString, Pageable pageable);
    D create(D dto) throws IOException;
    Optional<T> findById(ID id);
    T update(ID id, D dto);
    boolean deleteById(ID id);
    List<D> list(int limit);}
