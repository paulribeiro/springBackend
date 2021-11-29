package com.resume.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.resume.model.enums.CompetenceEvaluationEnum;
import com.resume.model.enums.CompetenceTypeEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RES_COMPETENCE")
public class Competence {

    @Id
    @GeneratedValue
    private Integer competenceId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetenceEvaluationEnum evaluation;

    @Column(nullable = false)
    private String competenceTitle;

    @Column(length = 10000)
    private String competenceDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetenceTypeEnum competenceTypeEnum;

    @ManyToMany(mappedBy = "competencesForProject")
    @JsonBackReference
    private Set<Project> projects;

    @PreRemove
    private void removeCompetencesFromProjects() {
        for (Project p : projects) {
            p.getCompetencesForProject().remove(this);
        }
    }

    public Competence() {
    }

    public Competence(CompetenceEvaluationEnum evaluation, String competenceTitle, String competenceDescription, CompetenceTypeEnum competenceTypeEnum) {
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceDescription = competenceDescription;
        this.competenceTypeEnum = competenceTypeEnum;
    }

    public Integer getCompetenceId() {
        return competenceId;
    }

    public CompetenceEvaluationEnum getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(CompetenceEvaluationEnum evaluation) {
        this.evaluation = evaluation;
    }

    public String getCompetenceTitle() {
        return competenceTitle;
    }

    public void setCompetenceTitle(String competenceTitle) {
        this.competenceTitle = competenceTitle;
    }

    public CompetenceTypeEnum getCompetenceTypeEnum() {
        return competenceTypeEnum;
    }

    public void setCompetenceTypeEnum(CompetenceTypeEnum competenceTypeEnum) {
        this.competenceTypeEnum = competenceTypeEnum;
    }

    public String getCompetenceDescription() {
        return competenceDescription;
    }

    public void setCompetenceDescription(String competenceDescription) {
        this.competenceDescription = competenceDescription;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString(){
        return "Competence{"+
                "competenceId=" + competenceId +
                ", evaluation='"+ evaluation +
                ", competenceTitle='"+ competenceTitle +
                ", competenceDescription='"+ competenceDescription +
                ", competenceType='"+ competenceTypeEnum + '}';
    }

}
