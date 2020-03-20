package com.resume.repository;

import com.resume.model.Competence;
import com.resume.model.enums.CompetenceTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {

    List<Competence> findAll();

    List<Competence> findAllByCompetenceTypeEnum(CompetenceTypeEnum competenceTypeEnum);

    Competence findByCompetenceId(int id);

    Integer deleteCompetenceByCompetenceId(Integer competenceId);

    Competence save(Competence competence);
}
