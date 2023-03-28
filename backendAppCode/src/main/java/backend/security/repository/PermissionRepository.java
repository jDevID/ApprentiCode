package backend.security.repository;


import backend.domain.dto.BaseDto;
import backend.repository.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface PermissionRepository<T extends BaseDto, ID extends Serializable> extends BaseRepository<T, ID> {
}
