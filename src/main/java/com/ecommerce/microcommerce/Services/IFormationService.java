package com.ecommerce.microcommerce.Services;

import com.ecommerce.microcommerce.dto.FormationDto;
import com.ecommerce.microcommerce.model.Competence;
import com.ecommerce.microcommerce.model.Formation;

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
    FormationDto postFormation(Formation formation);

    /**
     * Method used to modify a formation.
     *
     * @param formation : formation to be modified
     * @return the formation as it was modified.
     */
    FormationDto putFormation(Formation formation);

    /**
     * Method used to get a formation by Id.
     *
     * @param formationId : id of the formation researched.
     * @return the formation which correspond to the given Id/
     */
    FormationDto getFormation(Integer formationId);
}
