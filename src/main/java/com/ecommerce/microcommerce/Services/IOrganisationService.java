package com.ecommerce.microcommerce.Services;

import com.ecommerce.microcommerce.model.Organisation;

import java.util.List;

public interface IOrganisationService {

    /**
     * Method used to get all the organisations from the database.
     *
     * @return list of Organisations. Empty List if none.
     */
    List<Organisation> getAllOrganisation();

    /**
     * Method used to get the given organisation by the id.
     *
     * @param organisationId : id of the researched organisation.
     * @return the organisation corresponding to the given Id,
     */
    Organisation getOrganisationbyId(Integer organisationId);

    /**
     * Method used to post a new organisation.
     *
     * @param organisation : organisation to be saved
     * @return the organisation as it was saved.
     */
    Organisation postOrganisation(Organisation organisation);

    /**
     * Method used to modify an organisation.
     *
     * @param organisation : organisation to be modified
     * @return the organisation as it was modified.
     */
    Organisation putOrganisation(Organisation organisation);
}
