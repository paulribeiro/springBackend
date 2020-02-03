package com.ecommerce.microcommerce.web.service;

import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    /**
     * Method used to get all the experiences from the database.
     *
     * @return list of Experiences. Empty List if none.
     */
    public List<Experience> getAllExperiences()
    {
        return experienceRepository.findAll();
    }

    public Experience postExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

}
