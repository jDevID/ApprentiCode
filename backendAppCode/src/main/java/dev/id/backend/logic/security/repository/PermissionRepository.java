package dev.id.backend.logic.security.repository;

import dev.id.backend.data.repositories.BaseRepository;
import dev.id.backend.logic.dtos.BaseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface PermissionRepository<T extends BaseDto, ID extends Serializable> extends BaseRepository<T, ID> {
}