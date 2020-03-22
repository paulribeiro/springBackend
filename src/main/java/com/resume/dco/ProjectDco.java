package com.resume.dco;

import com.resume.model.enums.ExperienceTypeEnum;
import com.resume.model.enums.ProjectTypeEnum;
import com.resume.validator.interfaces.ValueOfEnum;

import java.util.Date;

public class ProjectDco {

    private Integer projectId;

    private Integer experienceId;

    private String description;

    private String title;

    private String linkToProject;

    @ValueOfEnum(enumClass = ProjectTypeEnum.class)
    private String projectType;

    public ProjectDco(Integer projectId, Integer experienceId, String description, String title, String linkToProject, String projectType) {
        this.projectId = projectId;
        this.experienceId = experienceId;
        this.description = description;
        this.title = title;
        this.linkToProject = linkToProject;
        this.projectType = projectType;
    }

    public ProjectDco(Integer experienceId, String description, String title, String linkToProject, String projectType) {
        this.experienceId = experienceId;
        this.description = description;
        this.title = title;
        this.linkToProject = linkToProject;
        this.projectType = projectType;
    }

    public ProjectDco() {
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
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

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}
