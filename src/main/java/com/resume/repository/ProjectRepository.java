package com.resume.repository;

import com.resume.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAll();

    Project findByProjectId(int id);

    void deleteProjectByProjectId(int id);

    Project save(Project project);
}
