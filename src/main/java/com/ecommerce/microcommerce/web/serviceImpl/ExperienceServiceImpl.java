package com.ecommerce.microcommerce.web.serviceImpl;

import com.ecommerce.microcommerce.Services.IExperienceService;
import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.model.enums.ExperienceType;
import com.ecommerce.microcommerce.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements IExperienceService {

    public ExperienceServiceImpl() {
    }

    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience postExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public Experience putExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public Experience getExperience(Integer experienceId) {
        return experienceRepository.findByExperienceId(experienceId);
    }

}
