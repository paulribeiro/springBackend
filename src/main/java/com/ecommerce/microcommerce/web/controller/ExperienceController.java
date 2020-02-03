package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.web.service.ExperienceService;
import io.swagger.annotations.ApiOperation;
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

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @ApiOperation(value = "Get all the experiences")
    @GetMapping(value = "/Experience")
    public ResponseEntity<List<Experience>> get() {
        return ResponseEntity.ok().body(experienceService.getAllExperiences());
    }

    @ApiOperation(value = "Post an experience")
    @PostMapping(value = "/Experience")
    public Experience post(@Valid @RequestBody Experience experience) {
        return experienceService.postExperience(experience);
    }
}
