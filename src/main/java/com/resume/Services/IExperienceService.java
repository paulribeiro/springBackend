package com.resume.Services;

import com.resume.dco.ExperienceDco;
import com.resume.dto.ExperienceDto;

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
    ExperienceDto postExperience(ExperienceDco experience);

    /**
     * Method used to modify an experience.
     *
     * @param experience : experience to be modified
     * @return the experience modified.
     */
    ExperienceDto putExperience(ExperienceDco experience);

    /**
     * Method used to get an experience by its Id.
     *
     * @param experienceId : Id of the experience researched
     * @return The experience with the id experienceId.
     */
    ExperienceDto getExperience(Integer experienceId);

    /**
     * Method used to find all experiences currently linked to a given locationId.
     *
     * @param locationId : id of the location to search.
     * @return the experiences which are attached to the given locationId
     */
    List<ExperienceDto> getExperienceLinkedToLocation(Integer locationId);

    /**
     * Method used to find all experiences currently linked to a given organisationId.
     *
     * @param organisationId : id of the organisation to search.
     * @return the experiences which are attached to the given organisationId
     */
    List<ExperienceDto> getExperienceLinkedToOrganisation(Integer organisationId);

    /**
     * Method used to delete an experience by Id.
     *
     * @param experienceId : id of the experience to delete.
     * @return the experience id which was deleted
     */
    Integer deleteExperience(Integer experienceId);

}
