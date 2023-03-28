package security.repositories;

import repositories.BaseRepository;
import domain.dto.BaseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface PermissionRepository<T extends BaseDto, ID extends Serializable> extends BaseRepository<T, ID> {
}
