package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dto.ExperienceDto;
import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.model.Location;
import com.ecommerce.microcommerce.web.service.ExperienceService;
import com.ecommerce.microcommerce.web.service.LocationService;
import com.ecommerce.microcommerce.web.service.OrganisationService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExperienceController {

    private ExperienceService experienceService;

    private LocationService locationService;

    private OrganisationService organisationService;

    private ModelMapper modelMapper;

    public ExperienceController(ExperienceService experienceService, OrganisationService organisationService, LocationService locationService, ModelMapper modelMapper) {
        this.experienceService = experienceService;
        this.organisationService = organisationService;
        this.locationService = locationService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Get all the experiences")
    @GetMapping(value = "/Experience")
    public ResponseEntity<List<Experience>> get() {
        return ResponseEntity.ok().body(experienceService.getAllExperiences());
    }

    @ApiOperation(value = "Post an experience")
    @PostMapping(value = "/Experience")
    public Experience post(@Valid @RequestBody ExperienceDto experienceDto) {

        return experienceService.postExperience(new ConverterHelper().convertToEntity(experienceDto, modelMapper, locationService, organisationService));
    }
}
