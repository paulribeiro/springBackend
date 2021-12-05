package com.resume.web.controller;

import com.resume.Services.IExperienceService;
import com.resume.Services.IProjectService;
import com.resume.dco.ExperienceDco;
import com.resume.dto.ExperienceDto;
import com.resume.dto.ProjectDto;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedCompetenceException;
import com.resume.web.exceptions.UnexpectedExperienceException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExperienceController {

    private final IExperienceService experienceService;

    private final IProjectService projectService;

    public static final Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    public ExperienceController(IExperienceService experienceService, IProjectService projectService) {
        this.experienceService = experienceService;
        this.projectService = projectService;
    }


    @CrossOrigin()
    @ApiOperation(value = "Get all the experiences")
    @GetMapping(value = "/Experiences")
    public ResponseEntity<List<ExperienceDto>> get() {
        return ResponseEntity.ok().body(experienceService.getAllExperiences());
    }

    @CrossOrigin()
    @ApiOperation(value = "Get a given experience")
    @GetMapping(value = "/Experiences/{experienceId}")
    public ResponseEntity<ExperienceDto> get(@PathVariable int experienceId) {
        ExperienceDto experienceDto = experienceService.getExperience(experienceId);
        if(experienceDto == null) {
            logger.error("Unable to fin experience with id {} not found.", experienceId);
            throw new NoContentException("Unable to get experience with id " + experienceId + " not found.");
        }
        return ResponseEntity.ok().body(experienceDto);
    }

    @CrossOrigin()
    @ApiOperation(value = "Post an experience")
    @PostMapping(value = "/Experiences")
    public ResponseEntity<ExperienceDto> post(@Valid @RequestBody ExperienceDco experienceDco) {
        return ResponseEntity.ok().body(experienceService.postExperience(experienceDco));
    }

    @CrossOrigin()
    @ApiOperation(value = "Update an experience")
    @PutMapping(value = "/Experiences")
    public ResponseEntity<ExperienceDto> put(@Valid @RequestBody ExperienceDco experienceDco) {
        logger.info("Updating Experience with id {}", experienceDco.getExperienceId());
        ExperienceDto updatedExperience = experienceService.putExperience(experienceDco);

        if (updatedExperience == null) {
            logger.error("Unable to update. Experience with id {} not found.", experienceDco.getExperienceId());
            throw new UnexpectedExperienceException("Unable to upate. Experience with id " + experienceDco.getExperienceId() + " not found.");
        }

        return ResponseEntity.ok().body(updatedExperience);
    }

    @CrossOrigin()
    @ApiOperation(value = "Delete an experience")
    @DeleteMapping(value = "/Experiences/{experienceId}")
    public ResponseEntity<ExperienceDto> delete(@PathVariable Integer experienceId) {
        logger.info("Deleting experience with id {}", experienceId);
        ExperienceDto experienceToDelete = experienceService.getExperience(experienceId);

        if(experienceToDelete == null) {
            throw new UnexpectedCompetenceException("Unable to delete. Experience with id " + experienceId + " not found.");
        }

        List<ProjectDto> projectLinkedToExperience = projectService.getProjectLinkedToExperience(experienceToDelete.getExperienceId());
        if (projectLinkedToExperience != null && !projectLinkedToExperience.isEmpty())  {
            String dependantProjects = projectLinkedToExperience.stream()
                    .map(ProjectDto::toString)
                    .reduce("\n", String::concat);
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "There are some projects still linked to the experience you want to delete.\n" +
                            "Please delete the link to the following elements and try again later:\n" + dependantProjects);
        }

        Integer deletedExperience  = experienceService.deleteExperience(experienceId);
        if(deletedExperience == 0) {
            throw new UnexpectedCompetenceException("Unable to delete. Experience with id " + experienceId + " not found.");
        }
        return ResponseEntity.ok().body(experienceToDelete);
    }

}
