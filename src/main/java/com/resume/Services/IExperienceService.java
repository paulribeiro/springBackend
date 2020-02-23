package com.resume.Services;

import com.resume.dto.ExperienceDto;
import com.resume.model.Experience;

import java.util.List;

public interface IExperienceService {

    /**
     * Method used to get all the experiences from the database.
     *
     * @return list of Experiences. Empty List if none.
     */
    List<ExperienceDto> getAllExperiences();

    /**
     * Method used to post a new experience.
     *
     * @param experience : experience to be saved
     * @return the experience as it was saved.
     */
    ExperienceDto postExperience(Experience experience);

    /**
     * Method used to modify an experience.
     *
     * @param experience : experience to be modified
     * @return the experience modified.
     */
    ExperienceDto putExperience(Experience experience);

    /**
     * Method used to get an experience by its Id.
     *
     * @param experienceId : Id of the experience researched
     * @return The experience with the id experienceId.
     */
    ExperienceDto getExperience(Integer experienceId);
}
