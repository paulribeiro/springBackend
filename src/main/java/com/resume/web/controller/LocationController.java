package com.resume.web.controller;

import com.resume.converter.ConverterHelper;
import com.resume.dco.LocationDco;
import com.resume.dto.LocationDto;
import com.resume.model.Competence;
import com.resume.model.Location;
import com.resume.repository.LocationRepository;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedCompetenceException;
import com.resume.web.exceptions.UnexpectedLocationException;
import com.resume.web.serviceImpl.LocationServiceImpl;
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

    @CrossOrigin()
    @ApiOperation(value = "Get all the locations")
    @GetMapping(value = "/Locations")
    public ResponseEntity<List<LocationDto>> get() {
        return ResponseEntity.ok().body(locationService.getAllLocation());
    }

    @CrossOrigin()
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

    @CrossOrigin()
    @ApiOperation(value = "Post a location")
    @PostMapping(value = "/Locations")
    public ResponseEntity<LocationDto> post(@Valid @RequestBody LocationDco locationDco) {
        return ResponseEntity.ok().body(locationService.postLocation(ConverterHelper.convertToEntity(locationDco, modelMapper)));
    }

    @CrossOrigin()
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

    @CrossOrigin()
    @ApiOperation(value = "Delete a location")
    @DeleteMapping(value = "/Locations/{locationId}")
    public ResponseEntity<Location> delete(@PathVariable Integer locationId) {
        logger.info("Deleting location with id {}", locationId);
        Location locationToDelete = locationRepository.findByLocationId(locationId);
        Integer deletedLocation  = locationService.deleteLocation(locationId);

        if(deletedLocation == 0) {
            throw new UnexpectedCompetenceException("Unable to delete. Location with id " + locationId + " not found.");
        }
        return ResponseEntity.ok().body(locationToDelete);
    }

}
