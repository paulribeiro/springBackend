package com.resume.dto;

import com.resume.model.enums.ProjectTypeEnum;

public class ProjectDto {

    private Integer projectId;

    private ExperienceDto experience;

    private String description;

    private String title;

    private String linkToProject;

    private ProjectTypeEnum projectType;

    public ProjectDto() {
    }

    public ProjectDto(Integer projectId, ExperienceDto experience, String description, String title, String linkToProject, ProjectTypeEnum projectType) {
        this.projectId = projectId;
        this.experience = experience;
        this.description = description;
        this.title = title;
        this.linkToProject = linkToProject;
        this.projectType = projectType;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ExperienceDto getExperience() {
        return experience;
    }

    public void setExperience(ExperienceDto experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkToProject() {
        return linkToProject;
    }

    public void setLinkToProject(String linkToProject) {
        this.linkToProject = linkToProject;
    }

    public ProjectTypeEnum getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectTypeEnum projectType) {
        this.projectType = projectType;
    }

    @Override
    public String toString(){
        return "Project{"+
                "projectId=" + projectId +
                ", experience='"+ experience +
                ", description='"+ description +
                ", title='"+ title +
                ", linkToProject='"+ linkToProject +
                ", projectType='"+ projectType + '}';
    }

}
