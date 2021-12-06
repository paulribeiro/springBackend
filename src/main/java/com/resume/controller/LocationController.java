package com.resume.controller;

import com.resume.controller.Services.IExperienceService;
import com.resume.controller.Services.IFormationService;
import com.resume.controller.Services.ILocationService;
import com.resume.converter.ConverterHelper;
import com.resume.model.location.LocationDco;
import com.resume.model.experience.ExperienceDto;
import com.resume.model.formation.FormationDto;
import com.resume.model.location.LocationDto;
import com.resume.model.exceptions.NoContentException;
import com.resume.model.exceptions.UnexpectedElementException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LocationController {

    public static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    private final ILocationService locationService;

    private final IFormationService formationService;

    private final IExperienceService experienceService;

    public LocationController(ILocationService locationService, IFormationService formationService,
                              IExperienceService experienceService) {
        this.locationService = locationService;
        this.formationService = formationService;
        this.experienceService = experienceService;
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
        return ResponseEntity.ok().body(locationService.postLocation(ConverterHelper.convertToEntity(locationDco)));
    }

    @CrossOrigin()
    @ApiOperation(value = "Update a location")
    @PutMapping(value = "/Locations")
    public ResponseEntity<LocationDto> put(@Valid @RequestBody LocationDco locationDco) {
        logger.info("Updating location with id {}", locationDco.getLocationId());

        LocationDto currentLocation = locationService.putLocation(locationDco);

        if (currentLocation == null) {
            logger.error("Unable to update. Location with id {} not found.", locationDco.getLocationId());
            throw new UnexpectedElementException("Unable to update. Location with id " + locationDco.getLocationId() + " not found.");
        }

        return ResponseEntity.ok().body(currentLocation);
    }

    @CrossOrigin()
    @ApiOperation(value = "Delete a location")
    @DeleteMapping(value = "/Locations/{locationId}")
    public ResponseEntity<LocationDto> delete(@PathVariable Integer locationId) {
        logger.info("Deleting location with id {}", locationId);
        LocationDto locationToDelete = locationService.getLocationbyId(locationId);

        if(locationToDelete == null) {
            throw new UnexpectedElementException("Unable to delete. Location with id " + locationId + " not found.");
        }

        List<FormationDto> formationLinkedToLocation = formationService.getFormationLinkedToLocation(locationToDelete.getLocationId());
        if (formationLinkedToLocation != null && !formationLinkedToLocation.isEmpty())  {
            String dependantFormations = formationLinkedToLocation.stream()
                                        .map(FormationDto::toString)
                                        .reduce("\n", String::concat);
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "There are some formations still linked to the location you want to delete.\n" +
                            "Please delete the link to the following elements and try again later:\n" + dependantFormations);
        }

        List<ExperienceDto> experienceLinkedToLocation = experienceService.getExperienceLinkedToLocation(locationToDelete.getLocationId());
        if (experienceLinkedToLocation != null && !experienceLinkedToLocation.isEmpty())  {
            String dependantExperiences = experienceLinkedToLocation.stream()
                    .map(ExperienceDto::toString)
                    .reduce("\n", String::concat);
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "There are some experiences still linked to the location you want to delete.\n" +
                            "Please delete the link to the following elements and try again later:\n" + dependantExperiences);
        }

        Integer deletedLocation  = locationService.deleteLocation(locationId);
        if(deletedLocation == 0) {
            throw new UnexpectedElementException("Unable to delete. Location with id " + locationId + " not found.");
        }
        return ResponseEntity.ok().body(locationToDelete);
    }

}
