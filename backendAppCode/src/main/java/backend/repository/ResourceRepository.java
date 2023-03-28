package repositories;

import org.springframework.stereotype.Repository;
import domain.entity.Resource;

@Repository
public interface ResourceRepository extends BaseRepository<Resource, Long> {
}
