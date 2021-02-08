package com.resume.web.serviceImpl;

import com.resume.Services.ILocationService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.LocationDto;
import com.resume.model.Location;
import com.resume.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;

    private ModelMapper modelMapper;

    public LocationServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LocationDto> getAllLocation() {
        return ConverterHelper.convertToLocationListDto(locationRepository.findAll(), modelMapper);
    }

    @Override
    public LocationDto getLocationbyId(Integer locationId) {
        Location location = locationRepository.findByLocationId(locationId);
        if(location == null) {
            return null;
        }
        return ConverterHelper.convertToDto(location, modelMapper);
    }

    @Override
    public LocationDto postLocation(Location location) {
        return ConverterHelper.convertToDto(locationRepository.save(location), modelMapper);
    }

    @Override
    public LocationDto putLocation(Location location) {
        return ConverterHelper.convertToDto(locationRepository.save(location), modelMapper);
    }

    @Override
    public Integer deleteLocation(Integer locationId) {
        return locationRepository.deleteLocationByLocationId(locationId);
    }
}
