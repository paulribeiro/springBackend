package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dco.CompetenceDco;
import com.ecommerce.microcommerce.dco.OrganisationDco;
import com.ecommerce.microcommerce.dto.CompetenceDto;
import com.ecommerce.microcommerce.dto.LocationDto;
import com.ecommerce.microcommerce.dto.OrganisationDto;
import com.ecommerce.microcommerce.model.Competence;
import com.ecommerce.microcommerce.web.serviceImpl.CompetenceServiceImpl;
import com.ecommerce.microcommerce.web.serviceImpl.OrganisationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrganisationController {

    private OrganisationServiceImpl organisationService;

    private ModelMapper modelMapper;

    public OrganisationController(OrganisationServiceImpl organisationService, ModelMapper modelMapper) {
        this.organisationService = organisationService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Get all the Organisations")
    @GetMapping(value = "/Organisations")
    public ResponseEntity<List<OrganisationDto>> get() {
        return ResponseEntity.ok().body(organisationService.getAllOrganisation());
    }

    @ApiOperation(value = "Post an organisation")
    @PostMapping(value = "/Organisations")
    public ResponseEntity<OrganisationDto> post(@Valid @RequestBody OrganisationDco organisationDco) {
        return ResponseEntity.ok().body(organisationService.postOrganisation(ConverterHelper.convertToEntity(organisationDco, modelMapper)));
    }

    @ApiOperation(value = "Get a given organisation")
    @GetMapping(value = "/Organisations/{organisationId}")
    public ResponseEntity<OrganisationDto> get(@PathVariable int organisationId) {
        return ResponseEntity.ok().body(organisationService.getOrganisationbyId(organisationId));
    }

}
