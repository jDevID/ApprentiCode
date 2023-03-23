package dev.id.backend.data.repositories;

import dev.id.backend.data.entities.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends BaseRepository<Tag, Long> {
}
