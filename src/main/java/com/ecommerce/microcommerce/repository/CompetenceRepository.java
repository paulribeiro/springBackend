package com.ecommerce.microcommerce.repository;

import com.ecommerce.microcommerce.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {

    List<Competence> findAll();

    Competence findByCompetenceId(int id);

    void deleteCompetenceByCompetenceId(int id);

    Competence save(Competence competence);
}
