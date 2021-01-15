package com.resume.dto;

import com.resume.model.enums.ProjectTypeEnum;

import java.util.Set;

public class ProjectDto {

    private Integer projectId;

    private ExperienceDto experience;

    private String description;

    private String title;

    private String linkToProject;

    private ProjectTypeEnum projectType;

    private Set<CompetenceDto> competencesForProject;

    public ProjectDto() {
    }

    public ProjectDto(Integer projectId, ExperienceDto experience, String description, String title, String linkToProject, ProjectTypeEnum projectType, Set<CompetenceDto> competences) {
        this.projectId = projectId;
        this.experience = experience;
        this.description = description;
        this.title = title;
        this.linkToProject = linkToProject;
        this.projectType = projectType;
        this.competencesForProject = competences;
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

    public Set<CompetenceDto> getCompetences() { return competencesForProject; }

    public void setCompetences(Set<CompetenceDto> competences) { this.competencesForProject = competences; }

    @Override
    public String toString(){
        return "Project{"+
                "projectId=" + projectId +
                ", experience='"+ experience +
                ", description='"+ description +
                ", title='"+ title +
                ", linkToProject='"+ linkToProject +
                ", projectType='"+ projectType +
                ", competences='"+ competencesForProject + '}';
    }

}
