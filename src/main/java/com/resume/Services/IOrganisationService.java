package com.resume.Services;

import com.resume.dto.OrganisationDto;
import com.resume.model.Organisation;

import java.util.List;

public interface IOrganisationService {

    /**
     * Method used to get all the organisations from the database.
     *
     * @return list of Organisations. Empty List if none.
     */
    List<OrganisationDto> getAllOrganisation();

    /**
     * Method used to get the given organisation by the id.
     *
     * @param organisationId : id of the researched organisation.
     * @return the organisation corresponding to the given Id,
     */
    OrganisationDto getOrganisationbyId(Integer organisationId);

    /**
     * Method used to post a new organisation.
     *
     * @param organisation : organisation to be saved
     * @return the organisation as it was saved.
     */
    OrganisationDto postOrganisation(Organisation organisation);

    /**
     * Method used to modify an organisation.
     *
     * @param organisation : organisation to be modified
     * @return the organisation as it was modified.
     */
    OrganisationDto putOrganisation(OrganisationDto organisation);

    /**
     * Method used to delete an organisation by Id.
     *
     * @param organisationId : id of the organisation to delete.
     * @return the organisation which was deleted
     */
    Integer deleteOrganisation(Integer organisationId);

}
