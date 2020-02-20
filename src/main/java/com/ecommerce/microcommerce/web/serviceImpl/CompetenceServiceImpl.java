package com.ecommerce.microcommerce.web.serviceImpl;

import com.ecommerce.microcommerce.Services.ICompetenceService;
import com.ecommerce.microcommerce.model.Competence;
import com.ecommerce.microcommerce.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceServiceImpl implements ICompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Override
    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence postCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public Competence putCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public Competence getCompetence(Integer competenceId) {
        return competenceRepository.findByCompetenceId(competenceId);
    }
}
