package com.ecommerce.microcommerce.web.serviceImpl;

import com.ecommerce.microcommerce.Services.ILocationService;
import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dto.LocationDto;
import com.ecommerce.microcommerce.model.Location;
import com.ecommerce.microcommerce.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
import java.util.List;

@Service
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
}
