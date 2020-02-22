package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dco.LocationDco;
import com.ecommerce.microcommerce.dto.LocationDto;
import com.ecommerce.microcommerce.model.Location;
import com.ecommerce.microcommerce.repository.LocationRepository;
import com.ecommerce.microcommerce.web.exceptions.NoContentException;
import com.ecommerce.microcommerce.web.exceptions.UnexpectedLocationException;
import com.ecommerce.microcommerce.web.serviceImpl.LocationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LocationController {

    public static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    private LocationServiceImpl locationService;

    private LocationRepository locationRepository;

    private ModelMapper modelMapper;

    public LocationController(LocationServiceImpl locationService, LocationRepository locationRepository, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Get all the locations")
    @GetMapping(value = "/Locations")
    public ResponseEntity<List<LocationDto>> get() {
        return ResponseEntity.ok().body(locationService.getAllLocation());
    }

    @ApiOperation(value = "Get a given location")
    @GetMapping(value = "/Locations/{locationId}")
    public ResponseEntity<LocationDto> get(@PathVariable int locationId) {
        logger.info("getting location with id {}", locationId);
        LocationDto location = locationService.getLocationbyId(locationId);

        if(location == null) {
            logger.error("Unable to get location with id {} not found.", locationId);
            throw new NoContentException("Unable to get location with id " + locationId + " not found.");
        }
        return ResponseEntity.ok().body(location);
    }

    @ApiOperation(value = "Post a location")
    @PostMapping(value = "/Locations")
    public ResponseEntity<LocationDto> post(@Valid @RequestBody LocationDco locationDco) {
        return ResponseEntity.ok().body(locationService.postLocation(ConverterHelper.convertToEntity(locationDco, modelMapper)));
    }

    @ApiOperation(value = "Update a location")
    @PutMapping(value = "/Locations")
    public ResponseEntity<LocationDto> put(@Valid @RequestBody LocationDco locationDco) {
        logger.info("Updating location with id {}", locationDco.getLocationId());

        Location currentLocation = locationRepository.findByLocationId(locationDco.getLocationId());

        if (currentLocation == null) {
            logger.error("Unable to update. Location with id {} not found.", locationDco.getLocationId());
            throw new UnexpectedLocationException("Unable to upate. Location with id " + locationDco.getLocationId() + " not found.");
        }

        currentLocation.setCity(locationDco.getCity());
        currentLocation.setCountry(locationDco.getCountry());
        currentLocation.setNumber(locationDco.getNumber());
        currentLocation.setStreet(locationDco.getStreet());
        currentLocation.setZipCode(locationDco.getZipCode());

        return ResponseEntity.ok().body(locationService.putLocation(currentLocation));
    }

}
