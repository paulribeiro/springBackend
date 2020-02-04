package com.ecommerce.microcommerce.interfaces;

import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.model.Location;

import java.util.List;

public interface ILocationService {

    /**
     * Method used to get all the locations from the database.
     *
     * @return list of Locations. Empty List if none.
     */
    List<Location> getAllLocation();

    /**
     * Method used to get the given by the id.
     *
     * @param locationId : id of the researched location.
     * @return the location corresponding to the given Id,
     */
    Location getLocationbyId(Integer locationId);

    /**
     * Method used to post a new location.
     *
     * @param location : location to be saved
     * @return the location as it was saved.
     */
    Location postLocation(Location location);
}
