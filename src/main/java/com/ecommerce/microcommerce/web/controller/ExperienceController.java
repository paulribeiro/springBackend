package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dto.ExperienceDco;
import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.web.serviceImpl.ExperienceServiceImpl;
import com.ecommerce.microcommerce.web.serviceImpl.LocationServiceImpl;
import com.ecommerce.microcommerce.web.serviceImpl.OrganisationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExperienceController {

    private ExperienceServiceImpl experienceServiceImpl;

    private LocationServiceImpl locationServiceImpl;

    private OrganisationServiceImpl organisationServiceImpl;

    private ModelMapper modelMapper;

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

    @ApiOperation(value = "Post an experience")
    @PostMapping(value = "/Experiences")
    public Experience post(@Valid @RequestBody ExperienceDco experienceDco) {

        return experienceServiceImpl.postExperience(new ConverterHelper().convertToEntity(experienceDco, modelMapper, locationServiceImpl, organisationServiceImpl));
    }
}
