package backend.security.repository;


import backend.repository.BaseRepository;
import backend.security.domain.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
}
