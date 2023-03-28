package backend.repository;

import backend.domain.entity.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends BaseRepository<Resource, Long> {
}
