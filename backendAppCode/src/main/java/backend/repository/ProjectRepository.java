package backend.repository;

import org.springframework.stereotype.Repository;
import backend.domain.entity.Project;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> { }
