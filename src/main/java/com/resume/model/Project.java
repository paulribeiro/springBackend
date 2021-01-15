package com.resume.model;

import com.resume.model.enums.ExperienceTypeEnum;
import com.resume.model.enums.ProjectTypeEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "RES_PROJECT")
public class Project {

    @Id
    @GeneratedValue
    private Integer projectId;

    @ManyToOne
    @JoinColumn(name = "EXPERIENCE_ID_FK", referencedColumnName = "experienceId")
    private Experience experience;

    @Column(nullable = false)
    @Length(max=2000)
    private String description;

    @Column(nullable = false)
    @Length(max=2000)
    private String title;

    @Column(nullable = false)
    @Length(max=2000)
    private String linkToProject;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectTypeEnum projectType;

    @ManyToMany
    @JoinTable(
            name = "RES_PROJECTCOMPETENCE",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "competenceId"))
    private Set<Competence> competencesForProject;

    public Project(Integer projectId, Experience experience, String description, String title, String linkToProject, ProjectTypeEnum projectType) {
        this.projectId = projectId;
        this.experience = experience;
        this.description = description;
        this.title = title;
        this.linkToProject = linkToProject;
        this.projectType = projectType;
    }

    public Project() {
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
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

    public Set<Competence> getCompetencesForProject() {
        return competencesForProject;
    }

    public void setCompetencesForProject(Set<Competence> competencesForProject) {
        this.competencesForProject = competencesForProject;
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
