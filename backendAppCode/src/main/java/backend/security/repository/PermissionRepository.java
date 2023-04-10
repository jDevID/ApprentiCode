package backend.security.repository;


import backend.domain.dto.BaseDto;
import backend.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PermissionRepository<T extends BaseDto, ID extends Serializable> extends BaseRepository<T, ID> {
}
