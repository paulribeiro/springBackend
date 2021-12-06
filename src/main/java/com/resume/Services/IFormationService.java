package com.resume.Services;

import com.resume.model.formation.FormationDco;
import com.resume.model.formation.FormationDto;

import java.util.List;

public interface IFormationService {

    /**
     * Method used to get all the formations from the database.
     *
     * @return list of Formations. Empty List if none.
     */
    List<FormationDto> getAllFormations();

    /**
     * Method used to post a new formation.
     *
     * @param formation : formation to be saved
     * @return the formation as it was saved.
     */
    FormationDto postFormation(FormationDco formation);

    /**
     * Method used to modify a formation.
     *
     * @param formation : formation to be modified
     * @return the formation as it was modified.
     */
    FormationDto putFormation(FormationDco formation);

    /**
     * Method used to get a formation by Id.
     *
     * @param formationId : id of the formation researched.
     * @return the formation which correspond to the given Id/
     */
    FormationDto getFormation(Integer formationId);

    /**
     * Method used to delete a formation by Id.
     *
     * @param formationId : id of the formation to delete.
     * @return the formationId which was deleted
     */
    Integer deleteFormation(Integer formationId);

    /**
     * Method used to find all formation currently linked to a given locationId.
     *
     * @param locationId : id of the location to search.
     * @return the formation which are attached to the given locationId
     */
    List<FormationDto> getFormationLinkedToLocation(Integer locationId);

}
