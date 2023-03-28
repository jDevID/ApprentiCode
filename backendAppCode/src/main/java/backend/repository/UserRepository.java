package repositories;

import domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository @SuppressWarnings("unused") // TODO: SECURITY ALONG ANGULAR
public interface UserRepository  extends BaseRepository<User, Long> {
}
