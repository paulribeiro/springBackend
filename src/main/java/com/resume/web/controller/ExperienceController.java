package com.resume.web.controller;

import com.resume.converter.ConverterHelper;
import com.resume.dco.ExperienceDco;
import com.resume.dto.ExperienceDto;
import com.resume.model.Experience;
import com.resume.model.enums.ExperienceTypeEnum;
import com.resume.repository.ExperienceRepository;
import com.resume.repository.LocationRepository;
import com.resume.repository.OrganisationRepository;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedExperienceException;
import com.resume.web.serviceImpl.ExperienceServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExperienceController {

    private ExperienceServiceImpl experienceServiceImpl;

    private ExperienceRepository experienceRepository;

    private LocationRepository locationRepository;

    private OrganisationRepository organisationRepository;

    private ModelMapper modelMapper;

    public static final Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    public ExperienceController(ExperienceServiceImpl experienceServiceImpl, ExperienceRepository experienceRepository, LocationRepository locationRepository, OrganisationRepository organisationRepository, ModelMapper modelMapper) {
        this.experienceServiceImpl = experienceServiceImpl;
        this.experienceRepository = experienceRepository;
        this.locationRepository = locationRepository;
        this.organisationRepository = organisationRepository;
        this.modelMapper = modelMapper;
    }


    @CrossOrigin()
    @ApiOperation(value = "Get all the experiences")
    @GetMapping(value = "/Experiences")
    public ResponseEntity<List<ExperienceDto>> get() {
        return ResponseEntity.ok().body(experienceServiceImpl.getAllExperiences());
    }

    @CrossOrigin()
    @ApiOperation(value = "Get a given experience")
    @GetMapping(value = "/Experiences/{experienceId}")
    public ResponseEntity<ExperienceDto> get(@PathVariable int experienceId) {
        ExperienceDto experienceDto = experienceServiceImpl.getExperience(experienceId);
        if(experienceDto == null) {
            logger.error("Unable to fin experience with id {} not found.", experienceId);
            throw new NoContentException("Unable to get experience with id " + experienceId + " not found.");
        }
        return ResponseEntity.ok().body(experienceDto);
    }

    @CrossOrigin()
    @ApiOperation(value = "Post an experience")
    @PostMapping(value = "/Experiences")
    public ResponseEntity<ExperienceDto> post(@Valid @RequestBody ExperienceDco experienceDco) {
        return ResponseEntity.ok().body(experienceServiceImpl.postExperience(ConverterHelper.convertToEntity(experienceDco, modelMapper, locationRepository, organisationRepository)));
    }

    @CrossOrigin()
    @ApiOperation(value = "Update an experience")
    @PutMapping(value = "/Experiences")
    public ResponseEntity<ExperienceDto> put(@Valid @RequestBody ExperienceDco experienceDco) {
        logger.info("Updating Experience with id {}", experienceDco.getExperienceId());

        Experience currentExperience = experienceRepository.findByExperienceId(experienceDco.getExperienceId());

        if (currentExperience == null) {
            logger.error("Unable to update. Experience with id {} not found.", experienceDco.getExperienceId());
            throw new UnexpectedExperienceException("Unable to upate. User with id " + experienceDco.getExperienceId() + " not found.");
        }

        currentExperience.setDescription(experienceDco.getDescription());
        currentExperience.setEndDate(experienceDco.getEndDate());
        currentExperience.setStartDate(experienceDco.getStartDate());
        currentExperience.setExperienceType(ExperienceTypeEnum.valueOf(experienceDco.getExperienceType()));
        currentExperience.setOrganisation(organisationRepository.findByOrganisationId(experienceDco.getOrganisationId()));
        currentExperience.setLocation(locationRepository.findByLocationId(experienceDco.getLocationId()));
        currentExperience.setTitle(experienceDco.getTitle());

        return ResponseEntity.ok().body(experienceServiceImpl.putExperience(currentExperience));
    }

}
