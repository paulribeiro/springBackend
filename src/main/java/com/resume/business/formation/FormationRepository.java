package com.resume.business.formation;

import com.resume.model.formation.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Integer> {

    List<Formation> findAll();

    List<Formation> findByLocation_LocationId(Integer locationId);

    Formation findByFormationId(int id);

    Integer deleteFormationByFormationId(int id);

    Formation save(Formation formation);
}
