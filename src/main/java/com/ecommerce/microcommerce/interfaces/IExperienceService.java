package com.ecommerce.microcommerce.interfaces;

import com.ecommerce.microcommerce.model.Experience;

import java.util.List;

public interface IExperienceService {

    /**
     * Method used to get all the experiences from the database.
     *
     * @return list of Experiences. Empty List if none.
     */
    List<Experience> getAllExperiences();

    /**
     * Method used to post a new experience.
     *
     * @param experience : experience to be saved
     * @return the experience as it was saved.
     */
    Experience postExperience(Experience experience);
}
