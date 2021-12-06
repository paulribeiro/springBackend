package com.resume.business.project;

import com.resume.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAll();

    @Query("select p from Project p left join fetch p.competencesForProject b where p.projectId = ?1")
    Project findByProjectId(int id);

    Integer deleteProjectByProjectId(int id);

    Project save(Project project);

    List<Project> findByExperience_ExperienceId(Integer experienceId);
}