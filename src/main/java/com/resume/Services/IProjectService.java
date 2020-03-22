package com.resume.Services;

import com.resume.dto.ProjectDto;
import com.resume.model.Project;
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
    ProjectDto postProject(Project project);

    /**
     * Method used to modify a project.
     *
     * @param project : project to be modified
     * @return the project modified.
     */
    ProjectDto putProject(Project project);

    /**
     * Method used to get a project by its Id.
     *
     * @param projectId : Id of the project researched
     * @return The project with the id projectId.
     */
    ProjectDto getProject(Integer projectId);
}
