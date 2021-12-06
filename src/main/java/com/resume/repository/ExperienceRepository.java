package com.resume.repository;

import com.resume.model.experience.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    List<Experience> findAll();

    List<Experience> findByLocation_LocationId(Integer locationId);

    List<Experience> findByOrganisation_OrganisationId(Integer organisationId);

    Experience findByExperienceId(int id);

    Integer deleteExperienceByExperienceId(int id);

    Experience save(Experience experience);


}
