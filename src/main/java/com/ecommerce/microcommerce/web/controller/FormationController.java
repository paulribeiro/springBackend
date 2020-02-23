package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dco.FormationDco;
import com.ecommerce.microcommerce.dco.OrganisationDco;
import com.ecommerce.microcommerce.dto.FormationDto;
import com.ecommerce.microcommerce.dto.OrganisationDto;
import com.ecommerce.microcommerce.model.Formation;
import com.ecommerce.microcommerce.model.Organisation;
import com.ecommerce.microcommerce.model.enums.FormationTypeEnum;
import com.ecommerce.microcommerce.repository.FormationRepository;
import com.ecommerce.microcommerce.repository.LocationRepository;
import com.ecommerce.microcommerce.web.exceptions.NoContentException;
import com.ecommerce.microcommerce.web.exceptions.UnexpectedFormationException;
import com.ecommerce.microcommerce.web.exceptions.UnexpectedLocationException;
import com.ecommerce.microcommerce.web.serviceImpl.FormationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FormationController {

    public static final Logger logger = LoggerFactory.getLogger(FormationController.class);

    private FormationServiceImpl formationService;

    private LocationRepository locationRepository;

    private FormationRepository formationRepository;

    private ModelMapper modelMapper;

    public FormationController(FormationServiceImpl formationService, LocationRepository locationRepository, FormationRepository formationRepository, ModelMapper modelMapper) {
        this.formationService = formationService;
        this.locationRepository = locationRepository;
        this.formationRepository = formationRepository;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Get all the formations")
    @GetMapping(value = "/Formations")
    public ResponseEntity<List<FormationDto>> get() {
        return ResponseEntity.ok().body(formationService.getAllFormations());
    }

    @ApiOperation(value = "Post a formation")
    @PostMapping(value = "/Formations")
    public ResponseEntity<FormationDto> post(@Valid @RequestBody FormationDco formationDco) {
        return ResponseEntity.ok().body(formationService.postFormation(ConverterHelper.convertToEntity(formationDco, modelMapper, locationRepository)));
    }

    @ApiOperation(value = "Update a formation")
    @PutMapping(value = "/Formations")
    public ResponseEntity<FormationDto> put(@Valid @RequestBody FormationDco formationDco) {
        logger.info("Updating formation with id {}", formationDco.getFormationId());
        Formation currentFormation = formationRepository.findByFormationId(formationDco.getFormationId());

        if (currentFormation == null) {
            logger.error("Unable to update. Formation with id {} not found.", formationDco.getFormationId());
            throw new UnexpectedFormationException("Unable to upate. Organisation with id " + formationDco.getFormationId() + " not found.");
        }

        currentFormation.setStartDate(formationDco.getStartDate());
        currentFormation.setEndDate(formationDco.getEndDate());
        currentFormation.setFormationTitle(formationDco.getFormationTitle());
        currentFormation.setFormationType(FormationTypeEnum.valueOf(formationDco.getFormationType()));
        currentFormation.setLocation(locationRepository.findByLocationId(formationDco.getLocationId()));

        return ResponseEntity.ok().body(formationService.putFormation(currentFormation));
    }

    @ApiOperation(value = "Get a given formation")
    @GetMapping(value = "/Formations/{formationId}")
    public ResponseEntity<FormationDto> get(@PathVariable int formationId) {
        FormationDto formationDto = formationService.getFormation(formationId);
        if(formationDto == null){
            logger.error("Unable to get formation with id {} not found.", formationId);
            throw new NoContentException("Unable to get formation with id " + formationId + " not found.");
        }
        return ResponseEntity.ok().body(formationDto);
    }
}
