package com.ecommerce.microcommerce.web.serviceImpl;

import com.ecommerce.microcommerce.Services.ILocationService;
import com.ecommerce.microcommerce.model.Location;
import com.ecommerce.microcommerce.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationbyId(Integer locationId) {
        return locationRepository.findByLocationId(locationId);
    }

    @Override
    public Location postLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location putLocation(Location location) {
        return locationRepository.save(location);
    }
}
