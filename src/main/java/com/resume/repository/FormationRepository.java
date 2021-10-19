package com.resume.repository;

import com.resume.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Integer> {

    List<Formation> findAll();

    Formation findByFormationId(int id);

    Integer deleteFormationByFormationId(int id);

    Formation save(Formation formation);
}
