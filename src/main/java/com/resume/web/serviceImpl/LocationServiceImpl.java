package com.resume.web.serviceImpl;

import com.resume.Services.ILocationService;
import com.resume.converter.ConverterHelper;
import com.resume.model.location.LocationDco;
import com.resume.model.location.LocationDto;
import com.resume.model.location.Location;
import com.resume.repository.LocationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements ILocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<LocationDto> getAllLocation() {
        return ConverterHelper.convertToLocationListDto(locationRepository.findAll());
    }

    @Override
    public LocationDto getLocationbyId(Integer locationId) {
        Location location = locationRepository.findByLocationId(locationId);
        if(location == null) {
            return null;
        }
        return ConverterHelper.convertToDto(location);
    }

    @Override
    public LocationDto postLocation(Location location) {

        return ConverterHelper.convertToDto(locationRepository.save(location));
    }

    @Override
    public LocationDto putLocation(LocationDco locationDco) {

        Location location = locationRepository.findByLocationId(locationDco.getLocationId());
        if(location == null) {
            return null;
        }
        location.setCity(locationDco.getCity());
        location.setCountry(locationDco.getCountry());
        location.setNumber(locationDco.getNumber());
        location.setStreet(locationDco.getStreet());
        location.setZipCode(locationDco.getZipCode());

        return ConverterHelper.convertToDto(locationRepository.save(location));
    }

    @Override
    public Integer deleteLocation(Integer locationId) {
        return locationRepository.deleteLocationByLocationId(locationId);
    }
}
