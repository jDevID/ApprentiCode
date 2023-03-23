package dev.id.backend.data.repositories;

import dev.id.backend.data.entities.User;
import org.springframework.stereotype.Repository;

@Repository @SuppressWarnings("unused") // TODO: SECURITY ALONG ANGULAR
public interface UserRepository  extends BaseRepository<User, Long> {
}
