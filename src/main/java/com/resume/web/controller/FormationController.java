package com.resume.web.controller;

import com.resume.dco.FormationDco;
import com.resume.dto.FormationDto;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedCompetenceException;
import com.resume.web.exceptions.UnexpectedFormationException;
import com.resume.web.serviceImpl.FormationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FormationController {

    public static final Logger logger = LoggerFactory.getLogger(FormationController.class);

    private final FormationServiceImpl formationService;

    public FormationController(FormationServiceImpl formationService) {
        this.formationService = formationService;
    }

    @CrossOrigin()
    @ApiOperation(value = "Get all the formations")
    @GetMapping(value = "/Formations")
    public ResponseEntity<List<FormationDto>> get() {
        return ResponseEntity.ok().body(formationService.getAllFormations());
    }

    @CrossOrigin()
    @ApiOperation(value = "Post a formation")
    @PostMapping(value = "/Formations")
    public ResponseEntity<FormationDto> post(@Valid @RequestBody FormationDco formationDco) {
        return ResponseEntity.ok().body(formationService.postFormation(formationDco));
    }

    @CrossOrigin()
    @ApiOperation(value = "Update a formation")
    @PutMapping(value = "/Formations")
    public ResponseEntity<FormationDto> put(@Valid @RequestBody FormationDco formationDco) {
        logger.info("Updating formation with id {}", formationDco.getFormationId());
        FormationDto updatedFormation = formationService.putFormation(formationDco);

        if (updatedFormation == null) {
            logger.error("Unable to update. Formation with id {} not found.", formationDco.getFormationId());
            throw new UnexpectedFormationException("Unable to update. Organisation with id " + formationDco.getFormationId() + " not found.");
        }

        return ResponseEntity.ok().body(updatedFormation);
    }

    @CrossOrigin()
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

    @CrossOrigin()
    @ApiOperation(value = "Delete a formation")
    @DeleteMapping(value = "/Formations/{formationId}")
    public ResponseEntity<FormationDto> delete(@PathVariable Integer formationId) {
        logger.info("Deleting formation with id {}", formationId);
        FormationDto formationToDelete = formationService.getFormation(formationId);

        if(formationToDelete == null) {
            throw new UnexpectedCompetenceException("Unable to delete. Formation with id " + formationId + " not found.");
        }

        Integer deletedFormation  = formationService.deleteFormation(formationId);

        if(deletedFormation == 0) {
            throw new UnexpectedCompetenceException("Unable to delete. Formation with id " + formationId + " not found.");
        }
        return ResponseEntity.ok().body(formationToDelete);
    }
}
