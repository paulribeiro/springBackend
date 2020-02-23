package com.resume.repository;

import com.resume.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    List<Experience> findAll();

    Experience findByExperienceId(int id);

    void deleteExperienceByExperienceId(int id);

    Experience save(Experience experience);
}