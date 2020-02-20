package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dco.CompetenceDco;
import com.ecommerce.microcommerce.dto.CompetenceDto;
import com.ecommerce.microcommerce.model.Competence;
import com.ecommerce.microcommerce.repository.CompetenceRepository;
import com.ecommerce.microcommerce.web.exceptions.UnexpectedCompetenceException;
import com.ecommerce.microcommerce.web.exceptions.noContentException;
import com.ecommerce.microcommerce.web.serviceImpl.CompetenceServiceImpl;
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
            throw new noContentException("Unable to get competence with id " + competenceId + " not found.");
        }
        return ResponseEntity.ok().body(competence);
    }

    @ApiOperation(value = "Post a competence")
    @PostMapping(value = "/Competences")
    public ResponseEntity<CompetenceDto> post(@Valid @RequestBody CompetenceDco competenceDco) {
        return ResponseEntity.ok().body(competenceService.postCompetence(ConverterHelper.convertToEntity(competenceDco, modelMapper)));
    }

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
