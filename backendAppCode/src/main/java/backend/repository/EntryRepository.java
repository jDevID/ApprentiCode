package repositories;

import domain.entity.Entry;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends BaseRepository<Entry, Long> {
}
