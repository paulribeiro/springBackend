package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dto.CompetenceDco;
import com.ecommerce.microcommerce.dto.ExperienceDco;
import com.ecommerce.microcommerce.model.Competence;
import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.web.serviceImpl.CompetenceServiceImpl;
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
public class CompetenceController {

    private CompetenceServiceImpl competenceService;

    private ModelMapper modelMapper;

    public CompetenceController(CompetenceServiceImpl competenceService, ModelMapper modelMapper) {
        this.competenceService = competenceService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Get all the competences")
    @GetMapping(value = "/Competences")
    public ResponseEntity<List<Competence>> get() {
        return ResponseEntity.ok().body(competenceService.getAllCompetences());
    }

    @ApiOperation(value = "Post a competence")
    @PostMapping(value = "/Competences")
    public Competence post(@Valid @RequestBody CompetenceDco competenceDco) {
        return competenceService.postCompetence(new ConverterHelper().convertToEntity(competenceDco, modelMapper));
    }
}
