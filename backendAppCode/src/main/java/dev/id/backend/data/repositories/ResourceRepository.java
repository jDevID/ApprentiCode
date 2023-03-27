package dev.id.backend.data.repositories;

import org.springframework.stereotype.Repository;
import dev.id.backend.data.entities.Resource;

@Repository
public interface ResourceRepository extends BaseRepository<Resource, Long> {
}
