package com.resume.web.controller;

import com.resume.converter.ConverterHelper;
import com.resume.dco.OrganisationDco;
import com.resume.dto.OrganisationDto;
import com.resume.model.Organisation;
import com.resume.repository.OrganisationRepository;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedLocationException;
import com.resume.web.serviceImpl.OrganisationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrganisationController {

    public static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);

    private OrganisationServiceImpl organisationService;

    private OrganisationRepository organisationRepository;

    private ModelMapper modelMapper;

    public OrganisationController(OrganisationServiceImpl organisationService, OrganisationRepository organisationRepository, ModelMapper modelMapper) {
        this.organisationService = organisationService;
        this.organisationRepository = organisationRepository;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin()
    @ApiOperation(value = "Get all the Organisations")
    @GetMapping(value = "/Organisations")
    public ResponseEntity<List<OrganisationDto>> get() {
        return ResponseEntity.ok().body(organisationService.getAllOrganisation());
    }

    @CrossOrigin()
    @ApiOperation(value = "Post an organisation")
    @PostMapping(value = "/Organisations")
    public ResponseEntity<OrganisationDto> post(@Valid @RequestBody OrganisationDco organisationDco) {
        return ResponseEntity.ok().body(organisationService.postOrganisation(ConverterHelper.convertToEntity(organisationDco, modelMapper)));
    }

    @CrossOrigin()
    @ApiOperation(value = "Update an Organisation")
    @PutMapping(value = "/Organisations")
    public ResponseEntity<OrganisationDto> put(@Valid @RequestBody OrganisationDco organisationDco) {
        logger.info("Updating organisation with id {}", organisationDco.getOrganisationId());
        Organisation currentOrganisation = organisationRepository.findByOrganisationId(organisationDco.getOrganisationId());

        if (currentOrganisation == null) {
            logger.error("Unable to update. Organisation with id {} not found.", organisationDco.getOrganisationId());
            throw new UnexpectedLocationException("Unable to upate. Organisation with id " + organisationDco.getOrganisationId() + " not found.");
        }

        currentOrganisation.setLogoAddress(organisationDco.getLogoAddress());
        currentOrganisation.setOrganisationName(organisationDco.getOrganisationName());

        return ResponseEntity.ok().body(organisationService.putOrganisation(currentOrganisation));
    }

    @CrossOrigin()
    @ApiOperation(value = "Get a given organisation")
    @GetMapping(value = "/Organisations/{organisationId}")
    public ResponseEntity<OrganisationDto> get(@PathVariable int organisationId) {

        logger.info("getting organisation with id {}", organisationId);
        OrganisationDto organisationDto = organisationService.getOrganisationbyId(organisationId);

        if(organisationDto == null) {
            logger.error("Unable to get organisation with id {} not found.", organisationId);
            throw new NoContentException("Unable to get location with id " + organisationId + " not found.");
        }

        return ResponseEntity.ok().body(organisationDto);
    }

}
