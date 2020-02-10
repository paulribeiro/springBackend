package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dto.ExperienceDco;
import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.model.enums.ExperienceType;
import com.ecommerce.microcommerce.web.exceptions.UnexpectedExperienceException;
import com.ecommerce.microcommerce.web.serviceImpl.ExperienceServiceImpl;
import com.ecommerce.microcommerce.web.serviceImpl.LocationServiceImpl;
import com.ecommerce.microcommerce.web.serviceImpl.OrganisationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExperienceController {

    private ExperienceServiceImpl experienceServiceImpl;

    private LocationServiceImpl locationServiceImpl;

    private OrganisationServiceImpl organisationServiceImpl;

    private ModelMapper modelMapper;

    public static final Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    public ExperienceController(ExperienceServiceImpl experienceServiceImpl, OrganisationServiceImpl organisationServiceImpl, LocationServiceImpl locationServiceImpl, ModelMapper modelMapper) {
        this.experienceServiceImpl = experienceServiceImpl;
        this.organisationServiceImpl = organisationServiceImpl;
        this.locationServiceImpl = locationServiceImpl;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Get all the experiences")
    @GetMapping(value = "/Experiences")
    public ResponseEntity<List<Experience>> get() {
        return ResponseEntity.ok().body(experienceServiceImpl.getAllExperiences());
    }

    @ApiOperation(value = "Get a given experience")
    @GetMapping(value = "/Experiences/{experienceId}")
    public ResponseEntity<Experience> get(@PathVariable int experienceId) {
        return ResponseEntity.ok().body(experienceServiceImpl.getExperience(experienceId));
    }

    @ApiOperation(value = "Post an experience")
    @PostMapping(value = "/Experiences")
    public ResponseEntity<Experience> post(@Valid @RequestBody ExperienceDco experienceDco) {
        return ResponseEntity.ok().body(experienceServiceImpl.postExperience(new ConverterHelper().convertToEntity(experienceDco, modelMapper, locationServiceImpl, organisationServiceImpl)));
    }

    @ApiOperation(value = "Update an experience")
    @PutMapping(value = "/Experiences")
    public ResponseEntity<Experience> put(@Valid @RequestBody ExperienceDco experienceDco) {
        logger.info("Updating Experience with id {}", experienceDco.getExperienceId());

        Experience currentExperience = experienceServiceImpl.getExperience(experienceDco.getExperienceId());

        if (currentExperience == null) {
            logger.error("Unable to update. Experience with id {} not found.", experienceDco.getExperienceId());
            return new ResponseEntity(new UnexpectedExperienceException("Unable to upate. User with id " + experienceDco.getExperienceId() + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentExperience.setDescription(experienceDco.getDescription());
        currentExperience.setEndDate(experienceDco.getEndDate());
        currentExperience.setStartDate(experienceDco.getStartDate());
        currentExperience.setExperienceType(ExperienceType.valueOf(experienceDco.getExperienceType()));
        currentExperience.setOrganisation(organisationServiceImpl.getOrganisationbyId(experienceDco.getOrganisationId()));
        currentExperience.setLocation(locationServiceImpl.getLocationbyId(experienceDco.getLocationId()));

        return ResponseEntity.ok().body(experienceServiceImpl.putExperience(currentExperience));
    }

}
