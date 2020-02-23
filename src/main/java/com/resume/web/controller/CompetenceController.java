package com.resume.web.controller;

import com.resume.converter.ConverterHelper;
import com.resume.dco.CompetenceDco;
import com.resume.dto.CompetenceDto;
import com.resume.model.Competence;
import com.resume.repository.CompetenceRepository;
import com.resume.web.exceptions.UnexpectedCompetenceException;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.serviceImpl.CompetenceServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CompetenceController {

    public static final Logger logger = LoggerFactory.getLogger(CompetenceController.class);

    private CompetenceServiceImpl competenceService;

    private CompetenceRepository competenceRepository;

    private ModelMapper modelMapper;

    public CompetenceController(CompetenceServiceImpl competenceService, CompetenceRepository competenceRepository, ModelMapper modelMapper) {
        this.competenceService = competenceService;
        this.competenceRepository = competenceRepository;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Get all the competences")
    @GetMapping(value = "/Competences")
    public ResponseEntity<List<CompetenceDto>> get() {
        return ResponseEntity.ok().body(competenceService.getAllCompetences());
    }

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

    @ApiOperation(value = "Post a competence")
    @PostMapping(value = "/Competences")
    public ResponseEntity<CompetenceDto> post(@Valid @RequestBody CompetenceDco competenceDco) {
        return ResponseEntity.ok().body(competenceService.postCompetence(ConverterHelper.convertToEntity(competenceDco, modelMapper)));
    }

    /*
        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
     */

    @ApiOperation(value = "Update a competence")
    @PutMapping(value = "/Competences")
    public ResponseEntity<CompetenceDto> put(@Valid @RequestBody CompetenceDco competenceDco) {
        logger.info("Updating competence with id {}", competenceDco.getCompetenceId());

        Competence currentCompetence = competenceRepository.findByCompetenceId(competenceDco.getCompetenceId());

        if (currentCompetence == null) {
            logger.error("Unable to update. competence with id {} not found.", competenceDco.getCompetenceId());
            throw new UnexpectedCompetenceException("Unable to upate. Competence with id " + competenceDco.getCompetenceId() + " not found.");
        }

        currentCompetence.setCompetenceTitle(competenceDco.getCompetenceTitle());
        currentCompetence.setCompetenceTypeEnum(competenceDco.getCompetenceTypeEnum());
        currentCompetence.setEvaluation(competenceDco.getEvaluation());

        return ResponseEntity.ok().body(competenceService.putCompetence(currentCompetence));
    }
}
