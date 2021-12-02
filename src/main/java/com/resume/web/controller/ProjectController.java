package com.resume.web.controller;

import com.resume.Services.IProjectService;
import com.resume.dco.ProjectDco;
import com.resume.dto.ProjectDto;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedProjectException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {

    private final IProjectService projectService;

    public static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }


    @CrossOrigin()
    @ApiOperation(value = "Get all the projects")
    @GetMapping(value = "/Projects")
    public ResponseEntity<List<ProjectDto>> get() {
        return ResponseEntity.ok().body(projectService.getAllProjects());
    }

    @CrossOrigin()
    @ApiOperation(value = "Get a given project")
    @GetMapping(value = "/Projects/{projectId}")
    public ResponseEntity<ProjectDto> get(@PathVariable int projectId) {
        ProjectDto projectDto = projectService.getProject(projectId);
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
        return ResponseEntity.ok().body(projectService.postProject(projectDco));
    }

    @CrossOrigin()
    @ApiOperation(value = "Update a project")
    @PutMapping(value = "/Projects")
    public ResponseEntity<ProjectDto> put(@Valid @RequestBody ProjectDco projectDco) {
        logger.info("Updating Project with id {}", projectDco.getProjectId());

        ProjectDto currentProject = projectService.getProject(projectDco.getProjectId());

        if (currentProject == null) {
            logger.error("Unable to update. Project with id {} not found.", projectDco.getProjectId());
            throw new UnexpectedProjectException("Unable to update. Project with id " + projectDco.getProjectId() + " not found.");
        }

        return ResponseEntity.ok().body(projectService.putProject(projectDco));
    }

    @CrossOrigin()
    @ApiOperation(value = "Delete a project")
    @DeleteMapping(value = "/Projects/{projectId}")
    public ResponseEntity<ProjectDto> delete(@PathVariable Integer projectId) {
        logger.info("Deleting project with id {}", projectId);
        ProjectDto projectToDelete = projectService.getProject(projectId);

        if(projectToDelete != null) {
            Integer deletedProjectId = projectService.deleteProject(projectId);
            if (deletedProjectId == 0) {
                throw new UnexpectedProjectException("Unable to delete. Project with id " + projectId + " not found.");
            }
        } else {
            throw new UnexpectedProjectException("Unable to delete. Project with id " + projectId + " not found.");
        }
        return ResponseEntity.ok().body(projectToDelete);
    }

}
