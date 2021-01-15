package com.resume.model;

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

    private String competenceDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetenceTypeEnum competenceTypeEnum;

    @ManyToMany(mappedBy = "competencesForProject")
    private Set<Project> projects;

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
