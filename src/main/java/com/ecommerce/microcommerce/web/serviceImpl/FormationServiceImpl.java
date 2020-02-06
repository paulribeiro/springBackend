package com.ecommerce.microcommerce.web.serviceImpl;

import com.ecommerce.microcommerce.Services.IExperienceService;
import com.ecommerce.microcommerce.Services.IFormationService;
import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.model.Formation;
import com.ecommerce.microcommerce.repository.ExperienceRepository;
import com.ecommerce.microcommerce.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationServiceImpl implements IFormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Override
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Formation postFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation putFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation getFormation(Integer formationId) {
        return formationRepository.findByFormationId(formationId);
    }
}
