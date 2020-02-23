package com.resume.model;

import com.resume.model.enums.CompetenceTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "RES_COMPETENCE")
public class Competence {

    @Id
    @GeneratedValue
    private Integer competenceId;

    private Integer evaluation;

    @Column(nullable = false)
    private String competenceTitle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetenceTypeEnum competenceTypeEnum;

    public Competence() {
    }

    public Competence(Integer evaluation, String competenceTitle, CompetenceTypeEnum competenceTypeEnum) {
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceTypeEnum = competenceTypeEnum;
    }

    public Integer getCompetenceId() {
        return competenceId;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
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

    @Override
    public String toString(){
        return "Competence{"+
                "competenceId=" + competenceId +
                ", evaluation='"+ evaluation +
                ", competenceTitle='"+ competenceTitle +
                ", competenceType='"+ competenceTypeEnum + '}';
    }

}
