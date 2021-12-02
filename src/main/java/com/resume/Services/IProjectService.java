package com.resume.Services;

import com.resume.dco.ProjectDco;
import com.resume.dto.ProjectDto;

import java.util.List;

public interface IProjectService {

    /**
     * Method used to get all the projects from the database.
     *
     * @return list of Projects. Empty List if none.
     */
    List<ProjectDto> getAllProjects();

    /**
     * Method used to post a new project.
     *
     * @param project : project to be saved
     * @return the project as it was saved.
     */
    ProjectDto postProject(ProjectDco project);

    /**
     * Method used to modify a project.
     *
     * @param project : project to be modified
     * @return the project modified.
     */
    ProjectDto putProject(ProjectDco project);

    /**
     * Method used to get a project by its Id.
     *
     * @param projectId : Id of the project researched
     * @return The project with the id projectId.
     */
    ProjectDto getProject(Integer projectId);

    /**
     * Method used to delete a project by Id.
     *
     * @param projectId : id of the project to delete.
     * @return the projectId which was deleted
     */
    Integer deleteProject(Integer projectId);
}
