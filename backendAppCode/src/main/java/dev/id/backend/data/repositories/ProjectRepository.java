package dev.id.backend.data.repositories;


import dev.id.backend.data.entities.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> { }
