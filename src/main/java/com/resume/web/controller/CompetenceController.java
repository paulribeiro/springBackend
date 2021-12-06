package com.resume.web.controller;

import com.resume.converter.ConverterHelper;
import com.resume.model.competence.CompetenceDco;
import com.resume.model.competence.CompetenceDto;
import com.resume.model.enums.CompetenceTypeEnum;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedElementException;
import com.resume.web.serviceImpl.CompetenceServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CompetenceController {

    public static final Logger logger = LoggerFactory.getLogger(CompetenceController.class);

    private final CompetenceServiceImpl competenceService;

    public CompetenceController(CompetenceServiceImpl competenceService) {
        this.competenceService = competenceService;
    }

    @CrossOrigin()
    @ApiOperation(value = "Get all the competences")
    @GetMapping(value = "/Competences")
    public ResponseEntity<List<CompetenceDto>> get() {
        return ResponseEntity.ok().body(competenceService.getAllCompetences());
    }

    @CrossOrigin()
    @ApiOperation(value = "Get all the competences by types")
    @GetMapping(value = "/Competences/CompetenceType/{competenceType}")
    public ResponseEntity<List<CompetenceDto>> get(@PathVariable CompetenceTypeEnum competenceType) {
        return ResponseEntity.ok().body(competenceService.getCompetencesByType(competenceType));
    }

    @CrossOrigin()
    @ApiOperation(value = "Get a given competence")
    @GetMapping(value = "/Competences/{competenceId}")
    public ResponseEntity<CompetenceDto> get(@PathVariable int competenceId) {
        CompetenceDto competence = competenceService.getCompetence(competenceId);
        if(competence == null) {
            logger.error("Unable to fin competence with id {} not found.", competenceId);
            throw new NoContentException("Unable to get competence with id " + competenceId + " not found.");
        }
        return ResponseEntity.ok().body(competence);
    }

    @CrossOrigin()
    @ApiOperation(value = "Post a competence")
    @PostMapping(value = "/Competences")
    public ResponseEntity<CompetenceDto> post(@Valid @RequestBody CompetenceDco competenceDco) {
        return ResponseEntity.ok().body(competenceService.postCompetence(ConverterHelper.convertToEntity(competenceDco)));
    }

    @CrossOrigin()
    @ApiOperation(value = "Update a competence")
    @PutMapping(value = "/Competences")
    public ResponseEntity<CompetenceDto> put(@Valid @RequestBody CompetenceDco competenceDco) {
        logger.info("Updating competence with id {}", competenceDco.getCompetenceId());

        CompetenceDto currentCompetence = competenceService.getCompetence(competenceDco.getCompetenceId());

        if (currentCompetence == null) {
            logger.error("Unable to update. competence with id {} not found.", competenceDco.getCompetenceId());
            throw new UnexpectedElementException("Unable to upate. Competence with id " + competenceDco.getCompetenceId() + " not found.");
        }

        currentCompetence.setCompetenceTitle(competenceDco.getCompetenceTitle());
        currentCompetence.setCompetenceTypeEnum(competenceDco.getCompetenceTypeEnum());
        currentCompetence.setEvaluation(competenceDco.getEvaluation());
        currentCompetence.setCompetenceDescription(competenceDco.getCompetenceDescription());

        return ResponseEntity.ok().body(competenceService.putCompetence(currentCompetence));
    }

    @CrossOrigin()
    @ApiOperation(value = "Delete a competence")
    @DeleteMapping(value = "/Competences/{competenceId}")
    public ResponseEntity<CompetenceDto> delete(@PathVariable Integer competenceId) {
        logger.info("Deleting competence with id {}", competenceId);
        CompetenceDto competenceToDelete = competenceService.getCompetence(competenceId);

        if(competenceToDelete != null) {
            Integer deletedCompetenceId = competenceService.deleteCompetence(competenceId);
            if (deletedCompetenceId == 0) {
                throw new UnexpectedElementException("Unable to delete. Competence with id " + competenceId + " not found.");
            }
        } else {
            throw new UnexpectedElementException("Unable to delete. Competence with id " + competenceId + " not found.");
        }
        return ResponseEntity.ok().body(competenceToDelete);
    }

}
