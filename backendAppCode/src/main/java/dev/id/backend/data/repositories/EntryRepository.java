package dev.id.backend.data.repositories;

import dev.id.backend.data.entities.Entry;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends BaseRepository<Entry, Long> {
}