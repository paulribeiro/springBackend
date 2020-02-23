package com.resume.Services;

import com.resume.dto.CompetenceDto;
import com.resume.model.Competence;

import java.util.List;

public interface ICompetenceService {

    /**
     * Method used to get all the competences from the database.
     *
     * @return list of Competences. Empty List if none.
     */
    List<CompetenceDto> getAllCompetences();

    /**
     * Method used to post a new competence.
     *
     * @param competence : competence to be saved
     * @return the competence as it was saved.
     */
    CompetenceDto postCompetence(Competence competence);

    /**
     * Method used to ,odify a competence.
     *
     * @param competence : competence to be modified
     * @return the competence as it was modified.
     */
    CompetenceDto putCompetence(Competence competence);

    /**
     * Method used to get a competence by Id.
     *
     * @param competenceId : id of the competence researched.
     * @return the competence which correspond to the given Id/
     */
    CompetenceDto getCompetence(Integer competenceId);
}
