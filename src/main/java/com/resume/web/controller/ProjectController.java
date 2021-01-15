package com.resume.web.controller;

import com.resume.converter.ConverterHelper;
import com.resume.dco.ExperienceDco;
import com.resume.dco.ProjectDco;
import com.resume.dto.ExperienceDto;
import com.resume.dto.ProjectDto;
import com.resume.model.Competence;
import com.resume.model.Experience;
import com.resume.model.Project;
import com.resume.model.enums.ExperienceTypeEnum;
import com.resume.model.enums.ProjectTypeEnum;
import com.resume.repository.*;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedExperienceException;
import com.resume.web.serviceImpl.ExperienceServiceImpl;
import com.resume.web.serviceImpl.ProjectServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ProjectController {

    private ProjectServiceImpl projectServiceImpl;

    private ExperienceRepository experienceRepository;

    private CompetenceRepository competenceRepository;

    private ProjectRepository projectRepository;

    private ModelMapper modelMapper;

    public static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    public ProjectController(ProjectServiceImpl projectServiceImpl, ExperienceRepository experienceRepository, ProjectRepository projectRepository, ModelMapper modelMapper, CompetenceRepository competenceRepository) {
        this.projectServiceImpl = projectServiceImpl;
        this.experienceRepository = experienceRepository;
        this.projectRepository = projectRepository;
        this.competenceRepository = competenceRepository;
        this.modelMapper = modelMapper;
    }


    @CrossOrigin()
    @ApiOperation(value = "Get all the projects")
    @GetMapping(value = "/Projects")
    public ResponseEntity<List<ProjectDto>> get() {
        return ResponseEntity.ok().body(projectServiceImpl.getAllProjects());
    }

    @CrossOrigin()
    @ApiOperation(value = "Get a given project")
    @GetMapping(value = "/Projects/{projectId}")
    public ResponseEntity<ProjectDto> get(@PathVariable int projectId) {
        ProjectDto projectDto = projectServiceImpl.getProject(projectId);
        if(projectDto == null) {
            logger.error("Unable to find project with id {} not found.", projectId);
            throw new NoContentException("Unable to get project with id " + projectId + " not found.");
        }
        return ResponseEntity.ok().body(projectDto);
    }

    @CrossOrigin()
    @ApiOperation(value = "Post a project")
    @PostMapping(value = "/Projects")
    public ResponseEntity<ProjectDto> post(@Valid @RequestBody ProjectDco projectDco) {
        return ResponseEntity.ok().body(projectServiceImpl.postProject(ConverterHelper.convertToEntity(projectDco, modelMapper, experienceRepository, competenceRepository)));
    }

    @CrossOrigin()
    @ApiOperation(value = "Update a project")
    @PutMapping(value = "/Projects")
    public ResponseEntity<ProjectDto> put(@Valid @RequestBody ProjectDco projectDco) {
        logger.info("Updating Project with id {}", projectDco.getProjectId());

        Project currentProject = projectRepository.findByProjectId(projectDco.getProjectId());

        if (currentProject == null) {
            logger.error("Unable to update. Project with id {} not found.", projectDco.getProjectId());
            throw new UnexpectedExperienceException("Unable to update. Project with id " + projectDco.getProjectId() + " not found.");
        }

        currentProject.setDescription(projectDco.getDescription());
        currentProject.setLinkToProject(projectDco.getLinkToProject());
        currentProject.setProjectType(ProjectTypeEnum.valueOf(projectDco.getProjectType()));
        currentProject.setTitle(projectDco.getTitle());
        currentProject.setExperience(experienceRepository.findByExperienceId(projectDco.getExperienceId()));
        Set<Competence> competencesForProject = projectDco.getCompetenceIds().stream().map(id -> competenceRepository.findByCompetenceId(id)).collect(Collectors.toSet());
        currentProject.setCompetencesForProject(competencesForProject);

        return ResponseEntity.ok().body(projectServiceImpl.putProject(currentProject));
    }

}
